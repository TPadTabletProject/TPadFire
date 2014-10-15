package nxr.tpaddemo;

import nxr.tpaddemo.TPad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

public class TextureView extends SurfaceView implements Runnable {

	// Creates thread for drawing to run on
	Thread thread = null;

	String displayString;

	TPad myTpad;

	// Declare variables that will be used in onTouch method. These will be used
	// to update
	// state of the application when the user touches the screen.

	volatile boolean touching = false;
	volatile boolean connected = false;

	long touchTimer;
	volatile float friction, px, py;
	volatile float frequency;
	volatile int type;
	private int bx, by;
	private int delay;

	// Sets up Holder to manipulate the surface view for us
	SurfaceHolder holder;

	Bitmap backgroundbmp;
	Bitmap graybmp, logobmp, stripesbmp;
	Bitmap displaybmp;

	public boolean isShowing;
	public float[] hsv = new float[3];

	private VelocityTracker vTracker;
	private float vy, vx;

	// Sets up boolean to check if thread is running
	volatile boolean isRunning = false;

	// define canvas once so we are not constantly re-allocating memory in
	// our draw thread
	Canvas c;

	/*
	 * Objects we will draw onto the canvas. Initialize everything you intend to
	 * use as a draw variables here
	 */
	Paint black = new Paint();
	Paint white = new Paint();
	Paint blue = new Paint();

	// SurfaceView Constructor
	public TextureView(Context context, AttributeSet attrs) {

		super(context, attrs);
		// Passes surface view to our holder;
		holder = getHolder();

		/*
		 * Setup the paint variables here to certain colors/attributes
		 */

		black.setColor(Color.BLACK);
		black.setAlpha(255);
		white.setColor(Color.WHITE);
		blue.setColor(Color.BLUE);
		blue.setTextAlign(Paint.Align.LEFT);
		blue.setTextSize(24);
		blue.setAntiAlias(true);
		black.setAntiAlias(true);
		black.setDither(true);

		backgroundbmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.texturemap);
		graybmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.graytexture);
		logobmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.logoblackfinal);
		stripesbmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bwstipes);

		displaybmp = backgroundbmp;

		delay = 3;

		myTpad = DemoStart.tpad;

		isFocusable();
		isFocusableInTouchMode();

	}

	public void TextureTasks() {

		if (px >= displaybmp.getWidth() - 1)
			bx = displaybmp.getWidth() - 1;
		else if (px <= 0)
			bx = 0;
		else
			bx = (int) px;
		if (py >= displaybmp.getHeight() - 1)
			by = displaybmp.getHeight() - 1;
		else if (py <= 0)
			by = 0;
		else
			by = (int) py;

		int pixel = displaybmp.getPixel(bx, by);
		friction = pixelToFriction(pixel);
		frequency = pixelToFrequency(pixel);
		type = pixelToWaveType(pixel);

		myTpad.texture(type, frequency, friction);
		// myTPad.texture();

	}

	// Handling touch events and sending TPad correct values
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// Update old positions

			px = event.getX();
			py = event.getY();

			touchTimer = System.nanoTime();
			touching = true;
			TextureTasks();
			break;

		case MotionEvent.ACTION_MOVE:
			// Update old positions

			px = event.getX();
			py = event.getY();

			touchTimer = System.nanoTime();
			TextureTasks();
			break;

		case MotionEvent.ACTION_UP:

			touchTimer = System.nanoTime();

			break;

		}

		return true;
	}

	public void run() {

		// Make sure thread is running
		while (isRunning) {
			// checks to make sure the holder has a valid surfaceview in it,
			// if not then skip
			if (!holder.getSurface().isValid()) {
				continue;
			}

			displayString = String.valueOf(friction);

			/*
			 * The following code is where we do all of the drawing to the
			 * screen First we lock the canvas, then draw a white background.
			 * Then we draw text and our two circles
			 */

			// Lock canvas so we can manpipulate it
			c = holder.lockCanvas();

			c.drawARGB(255, 0, 0, 0);
			// Do all of our drawing to the canvas

			if (isShowing)
				c.drawBitmap(displaybmp, 0, 0, black);
			// c.drawCircle(px, py, 10, black);
			// c.drawText(displayString, 5, 25, white);

			// Unlock the canvas and draw it to the screen
			holder.unlockCanvasAndPost(c);

		}

	}

	// Takes care of stopping our drawing thread when the system is paused
	public void pause() {
		isRunning = false;
		while (true) {
			try {
				thread.join();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		thread = null;
	}

	public float pixelToFriction(int pixel) {
		// int[] rgb = { Color.red(pixel), Color.green(pixel), Color.blue(pixel)
		// };
		Color.colorToHSV(pixel, hsv);

		// return (.5f / 255f) * (computeMax(rgb) + computeMin(rgb));
		return hsv[2];
	}

	public float pixelToFrequency(int pixel) {
		Color.colorToHSV(pixel, hsv);
		float normHue = hsv[0] / 360f;
		// Convert hue to normalized float, then scale to between 10 and 300hz
		return (normHue + 1 - 2 * normHue) * 290 + 10;
	}

	public int pixelToWaveType(int pixel) {
		int tmp = TPad.SINUSOID;
		Color.colorToHSV(pixel, hsv);
		if (hsv[0] < 120 || hsv[0] > 320)
			tmp = TPad.SQUARE;
		return tmp;

	}

	// Takes care of resuming our thread when the system is resumed
	public void resume() {
		isRunning = true;
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();

	}
}
