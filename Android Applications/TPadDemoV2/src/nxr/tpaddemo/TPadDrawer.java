package nxr.tpaddemo;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.SlidingDrawer;

public class TPadDrawer implements Runnable {

	public SlidingDrawer tpadDrawer;
	public TPad tpad;
	boolean isRunning, isScrolling;
	
	private float py;
	public float tpadValue;
	public final float height;
	public final float buttonoffset;
	public final float drawerlength;
	public float drawery;
	private final float rampwidth;

	private VelocityTracker vTracker;
	private float vy;
	public float velocityOffset;
	Thread thread;

	public TPadDrawer(TPad tp, SlidingDrawer sd, float ht, float rpwth, float off) {

		tpad = tp;
		tpadDrawer = sd;
		height = ht;
		rampwidth = rpwth;
		buttonoffset = off;
		
		drawerlength = height - buttonoffset;

		tpadDrawer
				.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {

					@Override
					public void onScrollStarted() {

						isScrolling = true;

					}

					@Override
					public void onScrollEnded() {

						isScrolling = false;
					}
				});

		tpadDrawer.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (vTracker == null) {
						vTracker = VelocityTracker.obtain();
					} else {
						vTracker.clear();
					}
					vTracker.addMovement(event);
					break;
				case MotionEvent.ACTION_MOVE:

					if (isScrolling == true) {
						// TODO something while scrolling
						py = event.getY();
						vTracker.addMovement(event);
						vTracker.computeCurrentVelocity(1000);
						vy = vTracker.getYVelocity();

					}
					break;
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_CANCEL:
					vTracker.recycle();
					break;
				}
				return false;

			}
		});
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
		thread.setPriority(Thread.MAX_PRIORITY-2);
		thread.start();

	}

	public void run() {
		while (isRunning) {
			

			if (isScrolling) {

				velocityOffset = vy * (-1/35f);
				drawery = py-buttonoffset-velocityOffset;
				
				if( drawery>=0 && drawery < (drawerlength-rampwidth)/2f  ){	//finger in top range
					tpadValue = 1;
					
				}else if( drawery >= (drawerlength-rampwidth)/2f && drawery < (drawerlength-rampwidth)/2f+rampwidth ){	//finger in ramp range

					tpadValue = 1-(drawery-(drawerlength-rampwidth)/2f)/rampwidth; 
				}else if( drawery >= (drawerlength-rampwidth)/2f+rampwidth ){	//finger in bottom range
					tpadValue = 0;
				}else if (drawery<0){
					tpadValue = 0;
				}
				
				tpad.send(tpadValue);
				
			}

			try {
				Thread.sleep(4);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
