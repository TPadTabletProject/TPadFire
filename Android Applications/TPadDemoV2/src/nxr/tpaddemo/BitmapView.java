package nxr.tpaddemo;


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

public class BitmapView extends SurfaceView implements Runnable {

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
	private int bx, by;

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
	public BitmapView(Context context, AttributeSet attrs) {

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
		blue.setDither(true);
		blue.setAlpha(200);

		backgroundbmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bluegrad);
		graybmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.graytexture);
		logobmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.logoblackfinal);
		stripesbmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bwstipes);

		displaybmp = logobmp;
		
		
		myTpad = DemoStart.tpad;

		isFocusable();
		isFocusableInTouchMode();

	}

	public void bitmapTasks() {

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

		friction = pixelToFriction(displaybmp.getPixel(bx, by));

		if (BitmapActivity.myTPadDrawer.isScrolling == false) { // check to make
																// sure we
																// aren't
																// scrolling!
			myTpad.send(friction);
		}
	/*
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

	// Handling touch events and sending TPad correct values
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// Update old positions

			px = event.getX();
			py = event.getY();

			if (vTracker == null) {
				vTracker = VelocityTracker.obtain();
			} else {
				vTracker.clear();
			}
			vTracker.addMovement(event);

			touchTimer = System.nanoTime();
			touching = true;
			bitmapTasks();
			break;

		case MotionEvent.ACTION_MOVE:
			// Update old positions

			vTracker.addMovement(event);
			vTracker.computeCurrentVelocity(1000);
			vx = vTracker.getXVelocity();
			vy = vTracker.getYVelocity();

			if(vx>4000){displaybmp = stripesbmp;}
			if(vx<-4000){displaybmp = logobmp;}
			
			px = event.getX() + vx / 30f;
			py = event.getY() + vy / 30f;

			touchTimer = System.nanoTime();
			bitmapTasks();
			break;

		case MotionEvent.ACTION_UP:

			touchTimer = System.nanoTime();

			break;
		case MotionEvent.ACTION_CANCEL:
			vTracker.recycle();
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
			//c.drawCircle(px, py, 10, black);
			//c.drawText(displayString, 5, 25, white);

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
		Color.colorToHSV(pixel, hsv);
		
		return hsv[2];
	}

	// Takes care of resuming our thread when the system is resumed
	public void resume() {
		isRunning = true;
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();

	}
}
