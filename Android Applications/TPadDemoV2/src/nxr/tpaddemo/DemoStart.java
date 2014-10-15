package nxr.tpaddemo;

import nxr.tpaddemo.TPad;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class DemoStart extends Activity implements Runnable {

	public static TPad tpad = new TPad();

	// Creates thread for drawing to run on
	Thread StartThread = null;

	private TextView DebugText;
	private TextView PositionText;
	private ImageButton ComboButton;
	private ImageButton BlueBallButton;
	private ImageButton BitmapButton;
	private ImageButton GlassCleanButton;
	private ImageButton TextureButton;
	private ImageButton AudioButton;

	private Button UpButton, DownButton;

	private SlidingDrawer Slider;
	private TPadDrawer myTPadDrawer;
	private boolean isRunning;
	private boolean isScrolling;

	public final static float offset = 500;

	public final static float width = 600;
	public final static float height = 1000;
	public final static float buttonoffset = offset + 10;
	public final static float drawerlength = height - buttonoffset;
	public final static float rampwidth = 80;
	public float drawery;

	int tpadFreq = 33600;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tpad_start);

		// start the new tpad server for our tpad object
		tpad.startTPad(this, tpadFreq);

		DebugText = (TextView) findViewById(R.id.DebugText);
		ComboButton = (ImageButton) findViewById(R.id.ComboButton);
		BlueBallButton = (ImageButton) findViewById(R.id.BlueBallButton);
		BitmapButton = (ImageButton) findViewById(R.id.BitmapButton);
		GlassCleanButton = (ImageButton) findViewById(R.id.GlassButton);
		TextureButton = (ImageButton) findViewById(R.id.TextureButton);
		AudioButton= (ImageButton) findViewById(R.id.AudioButton);

		UpButton = (Button) findViewById(R.id.UpF);
		DownButton = (Button) findViewById(R.id.DownF);

		Slider = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		myTPadDrawer = new TPadDrawer(tpad, Slider, height, rampwidth,
				buttonoffset);

	
		UpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tpadFreq += 30;
				DebugText.setText("TPad Freq: " + String.valueOf(tpadFreq));
				tpad.freq(tpadFreq);

			}
		});

		DownButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tpadFreq += -30;
				DebugText.setText("TPad Freq: " + String.valueOf(tpadFreq));
				tpad.freq(tpadFreq);

			}
		});

		// Open touchscreen activity
		ComboButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("nxr.tpaddemo.COMBO");
				startActivity(intent);

			}
		});

		// Open touchscreen activity
		BlueBallButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Slider.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.BALL");
				startActivity(intent);

			}
		});
		// Open touchscreen activity
		BitmapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Slider.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.BITMAP");
				startActivity(intent);

			}
		});

		GlassCleanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Slider.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.GLASS");
				startActivity(intent);

			}
		});
		
		AudioButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Slider.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.AUDIO");
				startActivity(intent);

			}
		});
		
		TextureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Slider.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.TEXTURE");
				startActivity(intent);

			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getX() > 300) {

			tpad.send(1f);
		} else
			tpad.send(0f);

		return super.onTouchEvent(event);
	}

	public void run() {
		while (isRunning) {
			// Demo Start Screen Tasks go here
			runOnUiThread(new Runnable() {
				public void run() {

					if (isScrolling) {

						// tpadValue = (float)
						// (1-(Math.pow(Math.sin((px-offset)*7*Math.PI/(8*(width-offset))+Math.PI),5)+1));

						/*
						 * tpadValue = 0; for (int i = 0; i <= indents; i++) {
						 * if (Math.abs((px - offset) - (((width - offset) * i)
						 * / indents)) < sliderwidth) { tpadValue = (float)
						 * (((slideramp / sliderwidth) Math.abs(((px - offset) -
						 * (((width - offset) * i) / indents))))); } }
						 */

					}

					//PositionText.setText("Vel Y: "
					//		+ myTPadDrawer.velocityOffset);

					/*
					 * if (tpad.isConnected == true) {
					 * HeadingText.setText("TPad Status: Connected"); } else {
					 * HeadingText.setText("TPad Status: Disconnected"); }
					 */

				}
			});

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	@Override
	protected void onPause() {

		super.onPause();

		isRunning = false;
		while (true) {
			try {
				StartThread.join();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		StartThread = null;
		
		myTPadDrawer.pause();
	}

	@Override
	protected void onResume() {

		super.onResume();
		isRunning = true;
		StartThread = new Thread(this);
		StartThread.start();
		myTPadDrawer.resume();
	}

	@Override
	protected void onDestroy() {

		tpad.stopTPad();
		super.onDestroy();
	}

}
