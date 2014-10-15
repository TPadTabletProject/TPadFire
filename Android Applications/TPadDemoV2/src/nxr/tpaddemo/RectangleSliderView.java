package nxr.tpaddemo;

import java.util.Arrays;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;
import android.view.View;

public class RectangleSliderView extends SurfaceView implements Runnable {
	volatile Float friction;

	int width = 600;
	int height = 1024;

	static final int numRects = 8;
	static final int rectWidth = 400;
	static final int rectHeight = 50;
	static final int rectGap = 20;
	Rect boundingRect;
	MediaPlayer mp;
	boolean[] marked = new boolean[numRects];
	Rect[] rects = new Rect[numRects];
	Rect[] gaps = new Rect[numRects];
	// Sets up Holder to manipulate the surface view for us
	SurfaceHolder holder;
	Thread thread = null;
	// Sets up boolean to check if thread is running
	volatile boolean isRunning = false;

	TPad myTPad;
	int x, y;
	private VelocityTracker vTracker;
	private float vy, vx;
	boolean isSwitchingSong;

	Bitmap myCanvasBitmap = null;
	Canvas c = null;
	Canvas bc = null;
	Matrix identityMatrix;
	Paint paint = new Paint();
	Paint bold = new Paint();
	Paint text = new Paint();
	GestureDetectorCompat gD;
	int[] myMusic = { R.raw.vivaldishort, R.raw.psyshort, R.raw.dukeshort,
			R.raw.onionshort };
	int currTrack = 0;
	
	private static final String DEBUG_TAG = "Gestures";

	public RectangleSliderView(Context context, AttributeSet attrs) {

		super(context, attrs);
		// Passes surface view to our holder;
		holder = getHolder();
		myCanvasBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bc = new Canvas();
		bc.setBitmap(myCanvasBitmap);
		bc.drawARGB(255, 50, 50, 50);
		bold.setColor(Color.argb(150, 200, 200, 200));
		bold.setStrokeWidth(20);
		bold.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(5);
		paint.setColor(Color.argb(255, 0, 0, 0));
		text.setTextAlign(Paint.Align.CENTER);
		text.setColor(Color.argb(150, 250, 250, 250));
		text.setTextSize(35);
		text.setAntiAlias(true);

		mp = MediaPlayer.create(context, myMusic[0]);
		mp.setLooping(true);
		mp.start();
		boundingRect = new Rect(100, 0, rectWidth + 100, 600);
		for (int i = 0; i < numRects; i++) {
			int bot = height - ((i + 22) * rectGap + i * rectHeight);
			rects[i] = new Rect(boundingRect.left, bot - rectHeight,
					boundingRect.right, bot);
			gaps[i] = new Rect(boundingRect.left, rects[i].top - rectGap,
					boundingRect.right, rects[i].top);
			bc.drawRect(rects[i], paint);

		}

		myTPad = DemoStart.tpad;

		// color of the drawing by finger
		identityMatrix = new Matrix();
		isFocusable();
		isFocusableInTouchMode();

	}

	@Override
	public void run() {

		// Promote our draw thread to urgent display (Maybe we could do this
		// outside of the run command itself?)

		// Make sure thread is running
		while (isRunning) {
			// checks to make sure the holder has a valid surfaceview in it,
			// if not then skip
			if (!holder.getSurface().isValid()) {
				continue;
			}

			// Lock canvas so we can manipulate it
			c = holder.lockCanvas();

			// Do all of our drawing to the canvas
			// bc is the canvas to draw on the bitmap, draw there and then draw
			// that onto the screen

			// check which rects are true and draw bold rects there
			bc.drawARGB(255, 50, 50, 50);
			for (int i = 0; i < numRects; i++) {
				if (marked[i]) {
					bc.drawRect(rects[i], bold);
				} else {
					bc.drawRect(rects[i], paint);
				}
			}
			// bc.drawRect(rects[0], paint);
			c.drawBitmap(myCanvasBitmap, identityMatrix, null);
			c.drawText("Swipe Left/Right for next track", 300, 750, text);
			// Unlock the canvas and draw it to the screen
			holder.unlockCanvasAndPost(c);

		}

	}

	// Takes care of stopping our drawing thread when the system is paused
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		x = (int) event.getX();
		y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			
			vTracker.addMovement(event);
			vTracker.computeCurrentVelocity(1000);
			vx = vTracker.getXVelocity();
			vy = vTracker.getYVelocity();
			
			try {

				if (vx > 1000f && !isSwitchingSong) {
					isSwitchingSong = true;
					//myTPad.pulse(1, 1, 5);
					myTPad.send(0f);
					mp.reset();
					mp.setDataSource(
							getContext(),
							Uri.parse("android.resource://nxr.tpaddemo/"
									+ getNextTrack()));
					mp.prepare();
					mp.setLooping(true);
					mp.start();
					
				} else if (vx < -1000f && !isSwitchingSong) {
					isSwitchingSong = true;
					//myTPad.pulse(1, 1, 5);
					myTPad.send(0f);
					mp.reset();
					mp.setDataSource(
							getContext(),
							Uri.parse("android.resource://nxr.tpaddemo/"
									+ getPrevTrack()));
					mp.prepare();
					mp.setLooping(true);
					mp.start();
				}

			} catch (Exception e) {
				Log.d(DEBUG_TAG, e.toString());
			}
			
			
			
			if (boundingRect.contains(x, y)) {
				for (int i = 0; i < numRects; i++) {
					if (rects[i].contains(x, y)) {
						if (i == 0) {
							marked[0] = true;
						} else {
							marked[i] = marked[i - 1];
						}
						float log1 = (float) (Math.log(numRects - i) / Math
								.log(numRects));
						mp.setVolume(1 - log1, 1 - log1);
						Arrays.fill(marked, i + 1, numRects, false);
						if (RectangleSliderActivity.myTPadDrawer.isScrolling == false) {
							myTPad.send(1f);
						}
						continue;
					} else if (gaps[i].contains(x, y)) {

						if (RectangleSliderActivity.myTPadDrawer.isScrolling == false) {
							myTPad.send(0f);
						}
					}
				}

			} else {
				// Arrays.fill(marked, false);
				if (RectangleSliderActivity.myTPadDrawer.isScrolling == false) {
					myTPad.send(1f);
				}
			}
			break;
		case MotionEvent.ACTION_DOWN:

			if (vTracker == null) {
				vTracker = VelocityTracker.obtain();
			} else {
				vTracker.clear();
			}
			vTracker.addMovement(event);

			isSwitchingSong = false;
			friction = 0f;
			if (boundingRect.contains(x, y)) {
				for (int i = 0; i < numRects; i++) {
					if (rects[i].contains(x, y)) {
						float log1 = (float) (Math.log(numRects - i) / Math
								.log(numRects));
						mp.setVolume(1 - log1, 1 - log1);
						Arrays.fill(marked, i + 1, numRects, false);
						Arrays.fill(marked, 0, i, true);

						friction = 1f;

						break;
					} else if (gaps[i].contains(x, y)) {

					}
				}

			} else {

			}// Arrays.fill(marked, false);
			if (RectangleSliderActivity.myTPadDrawer.isScrolling == false) {
				myTPad.send(friction);

			}

			break;
		case MotionEvent.ACTION_UP:

			break;

		case MotionEvent.ACTION_CANCEL:
			vTracker.recycle();
			break;
		}

		return true;
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
		mp.release();
	}

	// Takes care of resuming our thread when the system is resumed
	public void resume() {
		isRunning = true;
		thread = new Thread(this);
		// thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();

	}

	public int getNextTrack() {
		currTrack++;
		if (currTrack == myMusic.length)
			currTrack = 0;
		return myMusic[currTrack];
	}

	public int getPrevTrack() {
		currTrack--;
		if (currTrack == -1)
			currTrack = myMusic.length - 1;
		return myMusic[currTrack];
	}

	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
		

		@Override
		public boolean onDown(MotionEvent event) {
			// Log.d(DEBUG_TAG, "onDown: ");
			int x = (int) event.getX();
			int y = (int) event.getY();
			friction = 0f;
			if (boundingRect.contains(x, y)) {
				for (int i = 0; i < numRects; i++) {
					if (rects[i].contains(x, y)) {
						float log1 = (float) (Math.log(numRects - i) / Math
								.log(numRects));
						mp.setVolume(1 - log1, 1 - log1);
						Arrays.fill(marked, i + 1, numRects, false);
						Arrays.fill(marked, 0, i, true);

						friction = 1f;

						break;
					} else if (gaps[i].contains(x, y)) {

					}
				}

			} else {

			}// Arrays.fill(marked, false);
			if (RectangleSliderActivity.myTPadDrawer.isScrolling == false) {
				myTPad.send(friction);

			}
			return true;
		}

		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			Log.d(DEBUG_TAG,
					"onFling: " + event1.toString() + event2.toString() + " "
							+ Float.toString(velocityX) + " "
							+ Float.toString(velocityY));
			try {

				if (velocityX > 2000f) {
					mp.reset();
					mp.setDataSource(
							getContext(),
							Uri.parse("android.resource://nxr.tpaddemo/"
									+ getNextTrack()));
					mp.prepare();
					mp.setLooping(true);
					mp.start();
				} else if (velocityX < -2000f) {
					mp.reset();
					mp.setDataSource(
							getContext(),
							Uri.parse("android.resource://nxr.tpaddemo/"
									+ getPrevTrack()));
					mp.prepare();
					mp.setLooping(true);
					mp.start();
				}

			} catch (Exception e) {
				Log.d(DEBUG_TAG, e.toString());
			}
			return true;
		}
	}
}