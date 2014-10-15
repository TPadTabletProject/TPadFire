package nxr.tpaddemo;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

public class BlueBallView extends SurfaceView implements Runnable {

	// Creates thread for drawing to run on
	Thread thread = null;

	String displayString;

	TPad myTpad;

	Random random = new Random();
	// Declare variables that will be used in onTouch method. These will be used
	// to update
	// state of the application when the user touches the screen.

	float x, y;
	volatile PVector p1, p1_old; // current and past position of the cursor
	volatile PVector[] p2 = new PVector[NUMBALLS]; // current position of the
													// balls
	volatile PVector[] p2_old = new PVector[NUMBALLS]; // past position of the
														// balls

	volatile boolean touching = false;
	volatile boolean connected = false;
	// These could probably be in beginning of screenview, since they are only
	// accessed there to update
	// the state when the screen is drawn
	volatile PVector p12[] = new PVector[NUMBALLS]; // vector p1-p2
	volatile PVector v1; // velocity of the cursor
	volatile PVector[] v2 = new PVector[NUMBALLS];

	// Constants and timing variables
	final float rad1 = 30; // radius of cursor
	final float rad2 = 32; // radius of the ball
	final float height = 1000;
	final float width = 600;
	static final int MAX_SPEED = 10;
	static final int NUMBALLS = 60;
	long touchTimer;
	volatile float friction, a, speed;
	volatile float[] d1 = new float[NUMBALLS];
	volatile float[] colltime = new float[NUMBALLS];
	volatile float[] colltimeold = new float[NUMBALLS];

	volatile int numCollisions;

	// Sets up Holder to manipulate the surface view for us
	SurfaceHolder holder;

	Bitmap bubble;
	Bitmap backgroundbmp;

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
	RectF background = new RectF();
	RectF stripe1 = new RectF();
	RectF stripe2 = new RectF();
	RectF stripe3 = new RectF();
	RectF stripe4 = new RectF();
	RectF stripe5 = new RectF();

	// SurfaceView Constructor
	public BlueBallView(Context context, AttributeSet attrs) {

		super(context, attrs);
		// Passes surface view to our holder;
		holder = getHolder();

		/*
		 * Setup the paint variables here to certain colors/attributes
		 */

		black.setColor(Color.BLACK);
		black.setAlpha(0);
		white.setColor(Color.WHITE);
		white.setDither(true);
		blue.setColor(Color.BLUE);
		blue.setTextAlign(Paint.Align.LEFT);
		blue.setTextSize(24);
		blue.setAntiAlias(true);
		black.setAntiAlias(true);
		blue.setDither(true);
		blue.setAlpha(200);

		bubble = BitmapFactory.decodeResource(getResources(),
				R.drawable.blueball);
		backgroundbmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bluebackground);

		// initializing variables
		x = y = 50;
		friction = 0f;
		p1 = new PVector(0.0f, 0.0f);
		p1_old = new PVector(0, 0);

		for (int i = 0; i < NUMBALLS; i++) {
			p2[i] = new PVector(random.nextInt((int) width),
					random.nextInt((int) height));
			p2_old[i] = new PVector(0, 0);
			v2[i] = new PVector(0, 0);
			p12[i] = new PVector(0, 0);
			colltime[i] = 0;
		}
		

		v1 = new PVector(0, 0);

		myTpad = DemoStart.tpad;
		

		isFocusable();
		isFocusableInTouchMode();

	}

	public void simulationTasks() {

		/*
		 * The following code takes care of all of the calculations for the ball
		 * and cursor positions and velocities
		 */

		numCollisions = 0;
		for (int i = 0; i < NUMBALLS; i++) {
			p2[i].x = p2[i].x + v2[i].x * 1.5f;
			p2[i].y = p2[i].y + v2[i].y * 1.5f;

			// Take energy out of system
			v2[i].x *= .99;
			v2[i].y *= .99;

			// Test to see if the shape exceeds the boundaries of the screen
			// If it does, reverse its direction by multiplying by -1
			if (p2[i].x > width - rad2) {
				v2[i].x *= -1;
				p2[i].x = width - rad2;
			}
			if (p2[i].x < rad2) {
				v2[i].x *= -1;
				p2[i].x = rad2;
			}
			if (p2[i].y > height - rad2-24 ) {
				v2[i].y *= -1;
				p2[i].y = height - rad2-24;
			}
			if (p2[i].y < rad2) {
				v2[i].y *= -1;
				p2[i].y = rad2;
			}

			// Calculate mouse velocity
			v1.x = (p1.x - p1_old.x);
			v1.y = -(p1.y - p1_old.y);

			// Calculate distance between balls and the angle between their
			// velocities
			p12[i].x = p1.x - p2[i].x;
			p12[i].y = p1.y - p2[i].y;

			d1[i] = p12[i].mag();

			p12[i].normalize();
			a = (float) Math.atan2(p12[i].y, p12[i].x);

			// Detect collision
			if (d1[i] < rad1 + rad2) {
				colltimeold[i] = colltime[i];
				colltime[i] = System.nanoTime();
				if (colltime[i] > colltimeold[i] + 3000000f) {
					p2[i].x = (float) (p1.x - (rad1 + rad2) * Math.cos(a));
					p2[i].y = (float) (p1.y - (rad1 + rad2) * Math.sin(a));
					v2[i].x = (float) (-(.5 * v2[i].mag() + .7 * v1.mag()) * Math
							.cos(a));
					v2[i].y = (float) (-(.5 * v2[i].mag() + .7 * v1.mag()) * Math
							.sin(a));
				}
			}
			if (d1[i] < rad1 + rad2 + 5)
				numCollisions++;

			// limit the ball speed to 10
			speed = v2[i].mag();
			if (speed > MAX_SPEED) {
				v2[i].x = v2[i].x / speed * MAX_SPEED;
				v2[i].y = v2[i].y / speed * MAX_SPEED;
			}
		}

		if (System.nanoTime() > touchTimer + 1000000000)
			touching = false;

		if (!touching) {
			friction = 0f;
		} else if (numCollisions <= 0) {
			friction = 1f;
		} else if (numCollisions < 2) {
			friction = .4f;

		} else if (numCollisions < 3) {
			friction = .3f;

		} else if (numCollisions < 4) {
			friction = .2f;

		} else if (numCollisions < 5) {
			friction = .1f;

		} else if (numCollisions < 6) {
			friction = 0f;
		}

		if (BlueBallActivity.myTPadDrawer.isScrolling == false) { // check to make sure we aren't scrolling!
			myTpad.send(friction);
		}
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Handling touch events and sending TPad correct values
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// Update old positions
			p1_old.x = p1.x;
			p1_old.y = p1.y;

			for (int i = 0; i < NUMBALLS; i++) {
				p2_old[i].x = p2[i].x;
				p2_old[i].y = p2[i].y;
			}
			p1.x = event.getX();
			p1.y = event.getY();

			if (vTracker == null) {
				vTracker = VelocityTracker.obtain();
			} else {
				vTracker.clear();
			}
			vTracker.addMovement(event);

			touchTimer = System.nanoTime();
			touching = true;
			break;

		case MotionEvent.ACTION_MOVE:
			// Update old positions
			p1_old.x = p1.x;
			p1_old.y = p1.y;

			for (int i = 0; i < NUMBALLS; i++) {
				p2_old[i].x = p2[i].x;
				p2_old[i].y = p2[i].y;
			}

			vTracker.addMovement(event);
			vTracker.computeCurrentVelocity(1000);
			vx = vTracker.getXVelocity();
			vy = vTracker.getYVelocity();

			p1.x = event.getX() + vx / 30f;
			p1.y = event.getY() + vy / 30f;

			touchTimer = System.nanoTime();
			break;

		case MotionEvent.ACTION_UP:
			p1.x = 700;
			p1.y = 1200;
			touchTimer = System.nanoTime();

			break;
		case MotionEvent.ACTION_CANCEL:
			vTracker.recycle();
			break;
		}

		return true;
	}

	public void run() {

		// Promote our draw thread to urgent display (Maybe we could do this
		// outside of the run command itself?)
		if (BlueBallActivity.myTPadDrawer.isScrolling == true) {
			thread.setPriority(Thread.MIN_PRIORITY);
		} else {
			thread.setPriority(Thread.MAX_PRIORITY);
		}

		// Make sure thread is running
		while (isRunning) {
			// checks to make sure the holder has a valid surfaceview in it,
			// if not then skip
			if (!holder.getSurface().isValid()) {
				continue;
			}

			simulationTasks();

			//displayString = String.valueOf(friction);

			/*
			 * The following code is where we do all of the drawing to the
			 * screen First we lock the canvas, then draw a white background.
			 * Then we draw text and our two circles
			 */

			// Lock canvas so we can manpipulate it
			c = holder.lockCanvas();

			// Do all of our drawing to the canvas

			c.drawBitmap(backgroundbmp, 0, 0, white);

			//c.drawText(displayString, 5, 25, blue);
			c.drawCircle(p1.x, p1.y, rad1, black); // draw cursor
			// c.drawCircle(vp1x, vp1y, rad1, blue); // draw cursor
			for (int i = 0; i < NUMBALLS; i++) {
				c.drawBitmap(bubble, p2[i].x - 32, p2[i].y - 32, blue);
				// c.drawCircle(p2[i].x, p2[i].y, rad2, blue); // draw ball
			}
			// c.drawBitmap(CanvasBitmap, identityMatrix, null);
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

	// Takes care of resuming our thread when the system is resumed
	public void resume() {
		isRunning = true;
		thread = new Thread(this);

		thread.start();

	}
}
