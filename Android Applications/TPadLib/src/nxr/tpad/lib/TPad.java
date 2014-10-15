package nxr.tpad.lib;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

import nxr.tpad.lib.Client;
import nxr.tpad.lib.Server;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/*
 * TPad Library Version 1.2
 * 
 * This version uses a data packets of type short
 * 
 *  Sending a 0f turns the TPad off, sending a 1f turns it fully on.
 *  There are then 255 values in between fully on and fully off
 *  
 *  A 1.0f corresponds to the lowest friction value the TPad can display. 
 * 
 */

public class TPad extends Activity implements Runnable {

	public Server server;

	private Thread myThread = null;

	private AbstractServerListener serverListener;
	public boolean isConnected;
	private boolean isRunning;
	
	// Default TPad Frequency
	private int frequency = 33700;

	private ByteBuffer b = ByteBuffer.allocate(4);
	private ByteBuffer shortB = ByteBuffer.allocate(2);

	final String TAG = null;

	public Context mContext;

	private static final byte[] MAGCOMMAND = { 0x0a };
	private static final byte[] FREQCOMMAND = { 0x0b };
	private static final byte[] TEXTURECOMMAND = { 0x0c };
	private static final byte[] KEEPALIVECOMMAND = { 0x0d };

	public static final int LINEAR = 1;
	public static final int SQUARE = 2;
	public static final int SINUSOID = 3;
	public static final int RANDOM = 4;

	public static final float TextureSampleRate = 10000;

	private static Random randomGen;

	public static final int MAX_INT_PACKET = 1000;
	public static final int MAX_FRICTION_INT = 1024;

	public static final int MAX_SHORT_PACKET = 500;
	public static final int MAX_FRICTION_SHORT = 255;

	public static final int MAX_BYTE_PACKET = 1000;
	public static final int MAX_FRICTION_BYTE = 255;

	public TPad() {
		randomGen = new Random();

	}

	public void startTPad(Context context, int freq) {
		mContext = context;
		frequency = freq;
		isConnected = false;
		// starting the communication server
		try {
			server = new Server();
			server.start();
		} catch (IOException e) {
			Log.e(TAG, "Unable to start TCP server", e);
			System.exit(-1);
		}

		serverListener = initializeServerListener();
		server.addListener(serverListener);

		isRunning = true;
		myThread = new Thread(this);
		myThread.setPriority(Thread.MAX_PRIORITY);
		myThread.start();

	}

	public void stopTPad() {

		isRunning = false;
		while (true) {
			try {
				myThread.join();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		myThread = null;

		server.stop();

	}

	public AbstractServerListener initializeServerListener() {

		return new AbstractServerListener() {
			@Override
			public void onClientConnect(Server server, Client client) {
				isConnected = true;
				runOnUiThread(new Runnable() {
					public void run() {

						Toast mToast = Toast.makeText(mContext,
								"TPad Connected", Toast.LENGTH_LONG);
						mToast.show();
						
						freq(frequency);
					}
				});
			};

			public void onClientDisconnect(Server server, Client client) {
				isConnected = false;
				runOnUiThread(new Runnable() {
					public void run() {

						Toast mToast = Toast.makeText(mContext,
								"TPad Disconnected", Toast.LENGTH_SHORT);
						mToast.show();
					}
				});

			}

		};
	}

	//Convert an array of floats into scales bytes, include texture command to have the PIC put it in the buffer
	private byte[] generateTexture(float[] friction){
		ByteBuffer tempBuff = ByteBuffer.allocate(friction.length+1);
		
		tempBuff.clear();
		tempBuff.put(TEXTURECOMMAND);
		
		for(int i = 0; i<friction.length; i++){
			
			tempBuff.put((byte) (friction[i]*MAX_FRICTION_BYTE));
			
		}
		
		return tempBuff.array();
		
		
	}
	
	
	
	private byte[] generateTexture(int type, float freq, float amp) {
		int periodSamps = (int)((1 / freq) * TextureSampleRate);
		ByteBuffer tempBuff;
		//check to see if we are larger than our max packet size
		if (periodSamps > MAX_BYTE_PACKET) {
			tempBuff = ByteBuffer.allocate((MAX_BYTE_PACKET) + 1);
		} else {
			tempBuff = ByteBuffer.allocate((int)Math.floor(periodSamps) + 1);}
		
		short tp;
		tempBuff.clear();
		tempBuff.put(TEXTURECOMMAND);

		switch (type) {

		case LINEAR:

			break;

		case SQUARE:
			for (float i = 0; i < (tempBuff.array().length-1); i++) {

				tp = (short) ((1 + Math.sin(2 * Math.PI * freq * i / 10000)) / 2f * MAX_FRICTION_BYTE);

				if (tp > ((short) MAX_FRICTION_BYTE / 2)) {
					tempBuff.put((byte) (amp*MAX_FRICTION_BYTE));

				} else
					tempBuff.put((byte) 0);

			}

			break;

		case SINUSOID:
			for (float i = 0; i < (tempBuff.array().length-1); i++) {

				tp = (short) ((1 + Math.sin(2 * Math.PI * freq * i / 10000))
						/ 2f * MAX_FRICTION_BYTE * amp);

				tempBuff.put((byte) tp);

			}

			break;

		case RANDOM:
			double y = 0;
			for (float i = 0; i < (tempBuff.array().length-1); i++) {
				
				
				y = randomGen.nextGaussian();
						
				tp = (short)y;
				Log.i("Random Gen", String.valueOf(y));

				tempBuff.put((byte) tp);

			}

			break;

		}

		return tempBuff.array();
	}

	private byte[] generateMag(float amp) {
		ByteBuffer tempBuff = ByteBuffer.allocate(2);
		tempBuff.clear();
		tempBuff.put(MAGCOMMAND);
		tempBuff.put((byte) (amp * MAX_FRICTION_BYTE));

		return tempBuff.array();
	}

	// Send data method for TPad: float 1.0 = Tpad turned on (low fiction) float
	// 0.0 = Tpad turned off (high friction)
	public void send(float friction) {

		try {
			server.send(generateMag(friction));
		} catch (IOException e) {
			Log.e(TAG, "Error transmitting data to the PIC32", e);
		}

	}
	
	public void send(float[] friction) {

		try {
			server.send(generateTexture(friction));
		} catch (IOException e) {
			Log.e(TAG, "Error transmitting data to the PIC32", e);
		}

	}

	// Send a PWM frequency to the TPaD. This is to tune a specific resonant
	// frequency of each TPaD
	// The PIC does not remember this after it has been turned off, so you must
	// send a new one each session;
	public void freq(int freq) {
		frequency = freq;
		try {
			server.send(concatAll(FREQCOMMAND, intToBytes(freq)));
		} catch (IOException e) {
			Log.e(TAG, "Error transmitting data to the PIC32", e);
		}

	}

	public void texture() {

		try {
			server.send(generateTexture(SINUSOID, 150, 1.0f));
		} catch (IOException e) {
			Log.e(TAG, "Error transmitting data to the PIC32", e);
		}

	}
	

	public void texture(int type, float freq, float amp) {

		try {
			server.send(generateTexture(type, freq, amp));

		} catch (IOException e) {
			Log.e(TAG, "Error transmitting data to the PIC32", e);
		}

	}

	public void keepAlive() {

		try {
			server.send(KEEPALIVECOMMAND);

		} catch (IOException e) {
			Log.e(TAG, "Error transmitting data to the PIC32", e);
		}

	}

	// Convert an int to an array of bytes. The Tpad accepts an array of bytes,
	// and we send 4 bytes at a time, represening one int
	private byte[] intToBytes(int i) {
		b.clear();
		return b.order(ByteOrder.LITTLE_ENDIAN).putInt(i).array();
	}

	/*
	private byte[] shortToBytes(short sh) {
		shortB.clear();
		return shortB.order(ByteOrder.LITTLE_ENDIAN).putShort(sh).array();
	}
	*/

	public static final byte[] concatAll(byte[]... arrays) {
		int totalLength = 0;
		for (byte[] array : arrays) {
			totalLength += array.length;
		}
		byte[] result = new byte[totalLength];
		int offset = 0;
		for (byte[] array : arrays) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	@Override
	public void run() {
		while (isRunning) {

			keepAlive();
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
