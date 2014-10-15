package com.nxr.tpad.hapticcanvas;

import java.util.Arrays;

import nxr.tpad.lib.TPad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

public class HapticCanvasView extends SurfaceView implements Runnable {

	// Creates thread for drawing to run on
	Thread myThread = null;

	// Sets up boolean to check if thread is running
	private boolean isRunning = false;

	private boolean isDrawing;

	// Screen height and width
	private int height, width;

	// Output string used for debugging
	String displayString;

	// TPad Object for this activity
	private TPad myTPad = new TPad();

	// Keeps track of touching on screen
	private boolean isTouching = false;

	private static String currentTab;

	// Controls timout timer
	private long touchTimer;
	// private static final double TIMEOUT = 3000000000f;
	private static final double TIMEOUT = 50000000f;

	private static final Matrix identityMatrix = new Matrix();

	// Friction level variable
	private float friction;
	private float frequency;
	private int type;

	// Finger position variables
	private float px, py;
	private float px_old, py_old;

	// Bitmap position variables (these have a velocity component added on)
	private int bx, by;

	// height of averaging patch (in pixels)
	private static final int patchWidth = 4; // must be even!
	private static final int patchHeight = 4; // must be even!
	private static final int bitmapmargin = Math.max(patchHeight, patchWidth) / 2;

	// int array that will hold our patch color values
	private static volatile int patch[] = new int[patchWidth * patchHeight];
	private static volatile int patchAvg;

	public static PaintPalatte myPaintBucket;

	// Sets up Holder to manipulate the surface view for us
	private SurfaceHolder holder;

	/*
	 * Holds the HSV data for each pixel hsv[0] is hue 0-360 hsv[1] is
	 * saturation 0-1 hsv[2] is value 0-1
	 */
	public float[] hsv = new float[3];

	private VelocityTracker vTracker;
	private static double vy, vx;

	private static final int PREDICT_HORIZON = (int) (TPad.TextureSampleRate * (.020f)); // 10,000Hz
																							// times
																							// 20ms
																							// =
																							// 200samples

	private static float[] predictedPixels = new float[PREDICT_HORIZON];

	// define canvas once so we are not constantly re-allocating memory in
	// our draw thread
	private static volatile Bitmap myDrawBitmap = null;
	private static volatile Bitmap myBackgroundBitmap = null;
	private static volatile Canvas myDrawCanvas = null;
	private static volatile Canvas myPostCanvas = null;

	/*
	 * Objects we will draw onto the canvas. Initialize everything you intend to
	 * use as a draw variables here
	 */
	private Paint black = new Paint();
	private Paint white = new Paint();
	private Paint patchColor = new Paint();
	private Paint blue = new Paint();
	private Paint bluetext = new Paint();

	// Brush variables
	private Paint brush = new Paint();
	private int brushColor;
	private int brushWidth;

	// SurfaceView Constructor
	public HapticCanvasView(Context context, AttributeSet attrs) {

		super(context, attrs);

		// Passes surface view to our holder;

		holder = getHolder();

		// Setup the paint variables here to certain colors/attributes

		black.setColor(Color.BLACK);
		black.setAlpha(255);
		black.setAntiAlias(true);

		white.setColor(Color.WHITE);

		patchColor.setColor(Color.WHITE);
		patchColor.setAntiAlias(true);

		bluetext.setColor(Color.BLUE);
		bluetext.setTextAlign(Paint.Align.LEFT);
		bluetext.setTextSize(24);
		bluetext.setAntiAlias(true);

		blue.setColor(Color.BLUE);
		blue.setDither(true);
		blue.setAlpha(200);

		brushColor = Color.WHITE;
		brushWidth = 10;

		brush.setColor(brushColor);
		brush.setStrokeCap(Paint.Cap.ROUND);
		brush.setStrokeWidth(brushWidth);
		brush.setAntiAlias(true);

		width = 600;
		height = 1024;

		myPaintBucket = new PaintPalatte();

		myDrawBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);

		myDrawCanvas = new Canvas(myDrawBitmap);

		// Import default background and set as initial display bmp
		myBackgroundBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.texturemap);

		myDrawCanvas.drawBitmap(myBackgroundBitmap, 0, 0, null);

		isDrawing = false;

		if (!this.isInEditMode()) {
			myTPad = HapticCanvasActivity.myTPad;
		}

		isFocusable();
		isFocusableInTouchMode();

	}

	public boolean getTouching() {

		return isTouching;
	}

	public void setBitmap(Bitmap bmp) {

		myDrawCanvas.drawBitmap(bmp, identityMatrix, null);
		myBackgroundBitmap = bmp;
	}

	public Bitmap getBitmap() {

		return myDrawBitmap;

	}

	public void setBrushWidth(int i) {

		brushWidth = i;
		brush.setStrokeWidth(brushWidth);
	}

	public void setBrushColor(int c) {

		brushColor = c;
		brush.setColor(brushColor);
	}

	public void wipeScreen() {
		myDrawCanvas.drawBitmap(myBackgroundBitmap, identityMatrix, null);
	}

	public void setDrawing(boolean bool) {
		isDrawing = bool;
	}

	public boolean getDrawing() {
		return isDrawing;
	}

	// This method takes in a pixel value and maps it to a corresponding
	// friction
	// See the hsv declaration for info on what the array contains
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

	private void bitmapTasks() {

		// Here we make sure that the position coordinate are within the
		// displaying bitmap
		if (px >= myDrawBitmap.getWidth() - bitmapmargin)
			bx = myDrawBitmap.getWidth() - bitmapmargin;
		else if (px <= bitmapmargin)
			bx = bitmapmargin;
		else
			bx = (int) px;
		if (py >= myDrawBitmap.getHeight() - bitmapmargin)
			by = myDrawBitmap.getHeight() - bitmapmargin;
		else if (py <= bitmapmargin)
			by = bitmapmargin;
		else
			by = (int) py;

		// Read in the bitmap pixel value off and convert it to a fiction value
		/*
		 * int pixel = myDrawBitmap.getPixel(bx, by); friction =
		 * pixelToFriction(pixel); frequency = pixelToFrequency(pixel); type =
		 * pixelToWaveType(pixel);
		 */

		/*myDrawBitmap.getPixels(patch, 0, patchWidth, bx - patchWidth / 2, by
				- patchHeight / 2, patchWidth, patchHeight);

		patchAvg = computePatchAvg();

		friction = pixelToFriction(patchAvg);
		frequency = pixelToFrequency(patchAvg);
		type = pixelToWaveType(patchAvg);
		*/


		patchColor.setColor(patchAvg);

	}

	private int computePatchAvg() {
		int redBin = 0;
		int greenBin = 0;
		int blueBin = 0;

		for (int i = 0; i < patch.length; i++) {
			redBin += Color.red(patch[i]);
			greenBin += Color.green(patch[i]);
			blueBin += Color.blue(patch[i]);
		}

		return Color.rgb(redBin / patch.length, greenBin / patch.length,
				blueBin / patch.length);
	}

	private int computeMin(int[] minArray) {

		int min = minArray[0];
		for (int i : minArray) {
			if (i < min)
				min = i;
		}

		return min;
	}

	private int computeMax(int[] maxArray) {

		int max = maxArray[0];
		for (int i : maxArray) {
			if (i > max)
				max = i;
		}

		return max;
	}

	// Handling touch events
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		currentTab = HapticCanvasActivity.mTabHost.getCurrentTabTag();
		if (currentTab == "Brush") {
			HapticCanvasActivity.mTabHost.setCurrentTabByTag("Edit");
			return false;

		} else if (currentTab == "File")
			HapticCanvasActivity.mTabHost.setCurrentTabByTag("Feel");

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			px = event.getX();
			py = event.getY();

			vx = 0;
			vy = 0;

			// Start a new velocity tracker
			if (vTracker == null) {
				vTracker = VelocityTracker.obtain();
			} else {
				vTracker.clear();
			}
			vTracker.addMovement(event);

			bitmapTasks();

			// Call the timeout timer
			touchTimer = System.nanoTime();

			// Set touching to true
			isTouching = true;

			break;

		case MotionEvent.ACTION_MOVE:
			// Update old positions

			// Update cursor positions
			px_old = px;
			py_old = py;

			px = event.getX();
			py = event.getY();

			vTracker.addMovement(event);

			// Compute velocity in pixels per 1 ms
			vTracker.computeCurrentVelocity(1);

			// get current velocities
			vx = vTracker.getXVelocity();
			vy = vTracker.getYVelocity();

			Log.i("Velocities: ", String.valueOf(vx) + " " + String.valueOf(vy));

			bitmapTasks();

			if (!isDrawing) {
				// fills predictedPixels array with most recent predictions
				predictPixels();

				// send predicted pixels along
				myTPad.send(predictedPixels);
			}

			if (isDrawing) {

				myDrawCanvas.drawLine(px_old, py_old, px, py, brush);

			}

			touchTimer = System.nanoTime();

			break;

		case MotionEvent.ACTION_UP:

			isTouching = false;
			touchTimer = System.nanoTime();
			myTPad.send(0);
			myTPad.send(0);
			break;

		case MotionEvent.ACTION_CANCEL:
			vTracker.recycle();
			break;
		}

		return true;
	}

	private void predictPixels() {
		float friction;
		int x, y;
		
		for (int i = 0; i < predictedPixels.length; i++) {

			x = (int) (px + vx / 10f * i);	//1st order hold in x direction
			if (x >= myDrawBitmap.getWidth()) {
				x = myDrawBitmap.getWidth()- 1;
			} else if (x < 0)
				x = 0;

			y = (int) (py + vy / 10f * i); //1st order hold in y direction
			if (y >= myDrawBitmap.getHeight()) {
				y = myDrawBitmap.getHeight()-1;
			} else if (y < 0)
				y = 0;
			/*
			myDrawBitmap.getPixels(patch, 0, patchWidth, x - patchWidth / 2, y
					- patchHeight / 2, patchWidth, patchHeight);
			
			Arrays.sort(patch);
			
			friction = patch[(int) ((int)patch.length/2f)];
			predictedPixels[i] = friction;
			*/
			
			friction = pixelToFriction(myDrawBitmap.getPixel(x, y));
			predictedPixels[i] = friction;
		}

	}

	// Below is the main background thread for performing other tasks
	// On this thread we do the drawing to the screen, and updating of state
	// variables except the finger position and friction.
	public void run() {

		// Make sure thread is running
		while (isRunning) {
			if (isDrawing) {
				// checks to make sure the holder has a valid surfaceview in it,
				// if not then skip
				if (!holder.getSurface().isValid()) {
					continue;
				}

				displayString = String.valueOf(friction);

				if (System.nanoTime() > touchTimer + TIMEOUT) {
					friction = 0;
					myTPad.send(friction);
				}

				/*
				 * The following code is where we do all of the drawing to the
				 * screen.
				 * 
				 * Drawing to the PostCanvas does not reflect bitmap changes
				 */

				// Lock canvas so we can manpipulate it
				myPostCanvas = holder.lockCanvas();

				// myDrawCanvas.drawARGB(255, 0, 0, 0);
				// myPostCanvas.drawBitmap(myBackgroundBitmap, 0, 0, null);

				myPostCanvas.drawBitmap(myDrawBitmap, 0, 0, null);

				// myPostCanvas.drawRect(250, 800, 300, 850, patchColor);
				// c.drawCircle(bx, by, 5, white);
				// c.drawCircle(px, py, 10, white);
				// myPostCanvas.drawText(displayString, 275, 900, bluetext);

				// Unlock the canvas and draw it to the screen
				holder.unlockCanvasAndPost(myPostCanvas);

				/*
				 * if (!isDrawing) { // Send the friction value to the TPad //
				 * myTPad.send(friction); myTPad.texture(type, frequency,
				 * friction); // myTPad.texture(); try { Thread.sleep(60); }
				 * catch (InterruptedException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); }
				 * 
				 * }
				 */
			}

		}

	}

	// Takes care of stopping our drawing thread when the system is paused
	public void pause() {
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
	}

	// Takes care of resuming our thread when the system is resumed
	public void resume() {
		isRunning = true;
		myThread = new Thread(this);
		myThread.setPriority(Thread.MAX_PRIORITY - 3);
		myThread.start();

	}

	// Takes care of resuming our thread when the system is resumed
	public void destroy() {

		myBackgroundBitmap.recycle();
		myDrawBitmap.recycle();
	}

}
