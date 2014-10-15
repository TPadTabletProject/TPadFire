package nxr.tpaddemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GlassCleanView extends SurfaceView implements Runnable {

	volatile float friction;
	volatile boolean touching = false;
	volatile boolean drawing = true;
	volatile PVector p1, p1_old; // current and past position of the cursor
	long timer1;
	float x, y;
	float[] hsv = new float[3];
	TPad myTPad;

	String displayString;

	// Creates thread for drawing to run on
	Thread thread = null;
	int width;
	int height;
	// Sets up Holder to manipulate the surface view for us
	SurfaceHolder holder;

	// Sets up boolean to check if thread is running
	volatile boolean isRunning = false;

	// define canvas once so we are not constantly re-allocating memory in
	// our draw thread
	Bitmap myCanvasBitmap = null;
	Canvas c = null;
	Canvas bc = null;
	Matrix identityMatrix;

	Paint clear = new Paint();
	Paint white = new Paint();

	// SurfaceView Constructor
	public GlassCleanView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Passes surface view to our holder;
		holder = getHolder();
		width = 600;
		height = 1024;
		myCanvasBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bc = new Canvas(myCanvasBitmap);
		bc.drawARGB(255, 0, 0, 0);

		// color of the drawing by finger

		clear.setColor(Color.argb(20, 255, 255, 255));
		
		white.setStrokeCap(Paint.Cap.ROUND);
		clear.setXfermode(new PorterDuffXfermode(Mode.LIGHTEN));
		identityMatrix = new Matrix();
		white.setColor(Color.WHITE);

		white.setAntiAlias(true);
		white.setStrokeWidth(15);
		/*
		 * Setup the paint variables here to certain colors/attributes
		 */
		p1 = new PVector(0.0f, 0.0f);
		p1_old = new PVector(0, 0);

		myTPad = DemoStart.tpad;

		isFocusable();
		isFocusableInTouchMode();

	}

	@Override
	public void run() {

		// Make sure thread is running
		while (isRunning) {
			// checks to make sure the holder has a valid surfaceview in it,
			// if not then skip
			if (!holder.getSurface().isValid()) {
				continue;
			}
			if (drawing)
				thread.setPriority(Thread.MAX_PRIORITY-3);
			else
				thread.setPriority(Thread.MAX_PRIORITY);

			// Lock canvas so we can manipulate it
			c = holder.lockCanvas();
			
			// Do all of our drawing to the canvas
			// bc is the canvas to draw on the bitmap, draw there and then draw
			// that onto the screen
			friction = pixelToFriction(myCanvasBitmap.getPixel((int) p1.x,
					(int) p1.y));
			if (GlassCleanActivity.myTPadDrawer.isScrolling == false && !drawing) { // check
																		// to
				// make
				// sure we
				// aren't
				// scrolling!
				myTPad.send(friction);
			}

			//displayString = String.valueOf(friction);
			
			c.drawBitmap(myCanvasBitmap, identityMatrix, null);
			//c.drawText(displayString, 300, 20, white);

			// Unlock the canvas and draw it to the screen
			holder.unlockCanvasAndPost(c);

		}

	}

	// Takes care of stopping our drawing thread when the system is paused
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// Update old positions
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// Update old positions

			p1.x = event.getX();
			p1.y = event.getY();

			break;

		case MotionEvent.ACTION_MOVE:
			// Update old positions

			p1_old.x = p1.x;
			p1_old.y = p1.y;

			p1.x = event.getX();
			p1.y = event.getY();

			if (drawing) {
				bc.drawLine(p1_old.x, p1_old.y, p1.x, p1.y, white);
			}

			touching = true;
			break;

		case MotionEvent.ACTION_UP:

			touching = false;

			break;

		}

		return true;
	}

	public void wipeScreen() {

		bc.drawARGB(255, 0, 0, 0);
	}

	public float pixelToFriction(int pixel) {

		Color.colorToHSV(pixel, hsv);

		return hsv[2];
	}

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

	// Takes care of resuming our thread when the system is resumed
	public void resume() {
		isRunning = true;
		thread = new Thread(this);

		thread.start();

	}
}