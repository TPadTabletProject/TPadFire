package nxr.tpaddemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ComboChooseView extends SurfaceView implements Runnable {

	// Creates thread for drawing to run on
	Thread thread = null;

	// Sets up Holder to manipulate the surface view for us
	SurfaceHolder holder;

	TPad myTpad;

	// Sets up boolean to check if thread is running
	volatile boolean isRunning = false;
	volatile Float friction;
	volatile float d1, a, speed;
	float x, y;
	long timer1;
	PVector Fing, Fing_old; // current and past position of the cursor
	// PVector p2, p2_old; // current and past position of the ball
	volatile boolean touchlast = false;
	volatile boolean touching = false;
	volatile boolean connected = false;
	volatile boolean tPadOn = true;
	volatile String debugstring = "none";

	String displayString;

	volatile PVector p12; // vector p1-p2

	final float rad1 = 35; // radius of cursor

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
	ComboChoose combochoose;
	Color cr = new Color();

	// SurfaceView Constructor
	public ComboChooseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Passes surface view to our holder;
		holder = getHolder();

		/*
		 * Setup the paint variables here to certain colors/attributes
		 */
		combochoose = new ComboChoose(2, 100, 250, 400, 500, 4, 1);// (int
																	// value,
																	// int
																	// initx,
																	// int
																	// inity,
																	// int
																	// initw,
																	// int
																	// inith,
																	// int
																	// initnumring,
																	// int
																	// version)
		black.setColor(Color.BLACK);
		black.setAlpha(100);
		white.setColor(Color.WHITE);
		blue.setColor(Color.BLUE);
		blue.setTextAlign(Paint.Align.LEFT);
		blue.setTextSize(24);
		blue.setAntiAlias(true);
		black.setAntiAlias(true);
		blue.setDither(true);

		isFocusable();
		isFocusableInTouchMode();

		myTpad = DemoStart.tpad;
		
		

		// initializing variables
		x = y = 50;
		Fing = new PVector(50f, 50f);
		Fing_old = new PVector(50f, 50f);
	}

	// Handling touch events and sending TPad correct values
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// switch depending on the type of action the user performs
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Fing.x = event.getX();
			Fing.y = event.getY();
			// timer1 and touching boolean control the timeout feature,
			// determine if the user is currently interacting with the screen
			timer1 = System.nanoTime();
			touching = true;
			break;

		case MotionEvent.ACTION_MOVE:
			Fing.x = event.getX();
			Fing.y = event.getY();
			timer1 = System.nanoTime();
			touching = true;
			break;

		case MotionEvent.ACTION_UP:
			timer1 = System.nanoTime();
			touching = false;
			break;
		}
		// Update old positions
		Fing_old.x = Fing.x;
		Fing_old.y = Fing.y;

		timer1 = System.nanoTime();

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

			touchlast = touching;
			// Checks the timeout timer. If it has hit 5 seconds, then user
			// is not touching the screen anymore
			if ((System.nanoTime() - timer1) > 1000000000f) {
				touching = false;
			}

			// Set TPaD output
			if (!touching) {
				friction = 0.0f;
			}

			//displayString = String.valueOf(friction);

			// Lock canvas so we can manipulate it
			c = holder.lockCanvas();

			// Do all of our drawing to the canvas
			c.drawARGB(255, 225, 225, 225); // fills canvas with this color
			//c.drawText(displayString, 5, 25, blue);

			combochoose.drawit();

			// Unlock the canvas and draw it to the screen
			holder.unlockCanvasAndPost(c);

			// Finally we send the value of friction to the tpad through the
			// main activities tPadSend command
			if (!tPadOn)
				friction = 0.0f;

			if (ComboChooseActivity.myTPadDrawer.isScrolling == false) { // check to make sure we aren't scrolling!
				myTpad.send(friction);
			}

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

	class ComboChoose {
		int x;
		int y;
		int w;
		int h;
		int numring;
		int ringw;
		int currentnum;
		int tempcurrentnum;
		int version = 1;
		int curring = 1;
		ComboRing[] comboringarr;
		float rel;

		ComboChoose(int value, int initx, int inity, int initw, int inith,
				int initnumring, int initversion) {

			x = initx;
			y = inity;
			w = initw;
			h = inith;
			numring = initnumring;
			version = initversion;
			ringw = w / numring;
			rel = 50;
			comboringarr = new ComboRing[numring];
			for (int i = 0; i < numring; i++) {
				comboringarr[i] = new ComboRing(x + ringw * i, y, ringw, h, 10,
						version);
			}
		}

		void update() {
			for (int i = 0; i < numring; i++) {
				comboringarr[i].reset();
			}

		}

		void drawit() {
			tempcurrentnum = 1000000;
			// curring = 1;//curring = (int)(sVal4);
			for (int i = 0; i < numring; i++) {
				tempcurrentnum = tempcurrentnum + comboringarr[i].currentnum
						* (int) (Math.pow(10, (numring - 1 - i)));

				if (version == 2) {
					if (curring == i + 1) {
						comboringarr[i].selected = true;
					} else {
						comboringarr[i].selected = false;
					}
				} else {
					if (comboringarr[i].within) {
						comboringarr[i].selected = true;
						curring = i;
					} else {
						comboringarr[i].selected = false;
					}
				}
				comboringarr[i].drawit();
			}
			currentnum = tempcurrentnum;
			debugstring = String.valueOf(comboringarr[curring].ang);

			rel = comboringarr[curring].ang % 100f;
			if (!touching) {
				friction = 0f;
			} else {
				if (rel > 66) {
					friction = (rel - (66f)) / (33f);
				} else if (rel < 33) {
					friction = 1 - rel / (33f);
				} else {
					friction = 0f;
				}
			}
			if (Fing.x < x || Fing.x > x + w) {
				friction = 1f;
			}
			for (int i = 1; i < numring; i++) {
				if (Fing.x > x + i * ringw - 10 / 2
						&& Fing.x < x + i * ringw + 10 / 2) {
					// (xRela > wideButt-borWide/2 || xRela < borWide/2 &&
					// xCurs>limitLo + borWide/2 && xCurs <
					// limitHi-borWide/2){
					friction = 1f;
				}
			}

			Paint G = new Paint();
			G.setARGB(255, 225, 225, 225);// fill(225);
			RectF R1 = new RectF(x, 0, x + w, y); // These two rectangles
													// cover the numbers
													// that go off the edge.
			RectF R2 = new RectF(x, y + h, x + w, getHeight());
			c.drawRect(R1, G);// These two rectangles cover the numbers that
								// go off the edge.
			c.drawRect(R2, G);
		}
	}

	class ComboNum {
		int x;
		int y;
		int yoff;
		String label;
		int listh = 10;
		Paint font;

		ComboNum(int initx, int inity, int inityoff, String initLabel,
				Paint ifont) {
			x = initx;
			y = inity + inityoff;
			yoff = inityoff;
			label = initLabel;
			font = new Paint();
			font = ifont;
		}

		void display() {
			c.drawText(label, x, y + 18, font);
		}
	}

	class ComboRing {
		int x;
		int y;
		int w;
		int h;
		float ang;
		boolean within;
		boolean first;
		float circ;
		int numnums;
		int currentnum;
		float initang;
		boolean selected = false;
		int xenter;
		float yenter;
		float angenter;
		Paint font = new Paint(); // color(200,200,200,0);
		Paint C1 = new Paint(); // color(200,200,200,0);
		Paint C2 = new Paint();
		Paint selectionColor = new Paint();
		Paint otherColor = new Paint();
		Paint white = new Paint();
		Paint blackline = new Paint();

		int version = 1;
		int[] pastnums;

		ComboNum[] nums; // String[] numarr;

		ComboRing(int initx, int inity, int initw, int inith, int initnumnums,
				int initversion) {
			x = initx;
			y = inity;
			w = initw;
			h = inith;
			initang = 1150;// y+h/5;
			ang = initang;
			within = false;
			circ = h * 2;
			numnums = initnumnums;
			currentnum = 0;
			version = initversion;
			nums = new ComboNum[numnums];
			selectionColor.setARGB(255, 25, 25, 25);

			otherColor.setARGB(255, 200, 200, 200);
			C1 = otherColor;
			C2.setARGB(200, 100, 100, 100);
			white.setColor(Color.WHITE);

			blackline.setColor(Color.BLACK); // stroke(0);
			blackline.setStrokeWidth(2); // strokeWeight(2)
			font.setColor(Color.BLACK); // stroke(0);
			font.setTextAlign(Paint.Align.CENTER);
			font.setTextSize(48);
			font.setAntiAlias(true);// font = loadFont("ArialMT-48.vlw");

			for (int i = 0; i < numnums; i++) {
				nums[i] = new ComboNum(x + w / 2, y,
						(int) (y + (numnums - (i - 1)) * circ / numnums), ""
								+ i, font);
			}
			pastnums = new int[8];
			for (int k = 0; k < 8; k++) {
				pastnums[k] = 0;
			}
		}

		void drawit() {

			RectF backrect = new RectF(x, y, x + w, y + h);
			c.drawRect(backrect, white);

			// if(version == 2){withinarea(Curs.xPix, Fing.yPix); //For
			// combochoose 2, use cursor position rather than finger
			// position in x
			// }else{
			withinarea(Fing.x, Fing.y);
			// }

			if (selected) {
				C1 = selectionColor;
			} else {
				C1 = otherColor;
			}

			// setGradient(x,int(y+.625*h),w,int(h*.375),C1,C2,Y_AXIS);
			// setGradient(x,y,w,int(h*.375),C2,C1,Y_AXIS);

			Shader mShader = new LinearGradient(x, y, x, y + h / 4, new int[] {
					Color.argb(55, 100, 100, 100), Color.TRANSPARENT }, null,
					Shader.TileMode.MIRROR); // CLAMP MIRROR REPEAT
			Paint gradient = new Paint();
			gradient.setShader(mShader);
			c.drawRect(x, (int) (y), x + w, (int) (y + h / 4), gradient);
			Shader rShader = new LinearGradient(x, y + .75f * (float) h, x, y
					+ h, new int[] { Color.TRANSPARENT,
					Color.argb(55, 100, 100, 100) }, null,
					Shader.TileMode.MIRROR); // CLAMP MIRROR REPEAT
			Paint gradient2 = new Paint();

			gradient2.setShader(rShader);
			// c.drawCircle(60, 60, 30, paint);
			c.drawRect(x, y + .75f * (float) h, x + w, y + h, gradient2);

			c.drawLine(x, y, x, y + h, black);
			c.drawLine(x + w, y, x + w, y + h, black);

			if (version != 2 && within) {
				rollover((int) (Fing.x), (int) (Fing.y));
				// }else if(version == 2 && selected && touching){
				// rollover((int)(Curs.xPix), (int)(Fing.yPix)); //For
				// combochoose 2, use cursor position rather than finger
				// position in x
			} else {
				if (touchlast && !touching) {
					currentnum = pastnums[0];
				}
				if (nums[currentnum].y < y + h / 2) {
					ang = ang + .25f * (y + h / 2 - nums[currentnum].y);
				} else // return to home
				if (nums[currentnum].y >= y + h / 2) {
					ang = ang + .25f * (y + h / 2 - nums[currentnum].y);
				}
				update();
			}
			for (int i = 0; i < numnums; i++) {
				if (nums[i].y > (y - 20) && nums[i].y < y + h + 20) {
					nums[i].display();
				}
			}
		}

		boolean withinarea(float xin, float yin) {
			boolean withinlast = within;
			if ((x < xin && xin <= x + w && touching)) {// &&( y < yin &&
														// yin <= y + h)){
														// //Uncomment this
														// to restrict the
														// active area to
														// the graphic area;
				within = true;

			} else {
				within = false;
			}
			if (within && !withinlast) {
				first = true;
			} else {
				first = false;
			}
			return within;
		}

		void rollover(int xin, int yin) {
			if (first) {
				xenter = xin;
				yenter = yin * 1.5f;
				angenter = ang;
			}
			if (Fing_old.y != 0) {
				ang = angenter + Fing.y * 1.5f - yenter;
				ang = ang % circ;
				if (ang < y) {
					ang = ang + circ;
				}
			}

			update();
		}

		void reset() {
			ang = initang;
		}

		void update() {
			for (int i = 0; i < 10; i++) {
				nums[i].y = (nums[i].yoff + (int) (ang));// %listh;
				nums[i].y = (int) (nums[i].y % circ); // circ
				if (nums[i].y < y - 20) {
					nums[i].y = y + (int) (circ);
				}
				c.drawLine(x, y + h / 2 - circ / (numnums * 2), x + w, y + h
						/ 2 - circ / (numnums * 2), black);
				c.drawLine(x, y + h / 2 + circ / (numnums * 2), x + w, y + h
						/ 2 + circ / (numnums * 2), black);
				if (y + h / 2 - circ / (numnums * 2) < nums[i].y
						&& nums[i].y <= y + h / 2 + circ / (numnums * 2)) {
					currentnum = i;
					for (int k = 1; k < 8; k++) {
						pastnums[k - 1] = pastnums[k];
					}
					pastnums[7] = currentnum;
				}
			}
		}

	}
}
