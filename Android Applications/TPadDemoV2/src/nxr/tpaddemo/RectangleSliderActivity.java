package nxr.tpaddemo;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;

public class RectangleSliderActivity extends Activity{

	RectangleSliderView myRectangleSlider;
	
	SlidingDrawer myDrawer;
	public static TPadDrawer myTPadDrawer;
	ImageButton ComboButton;
	ImageButton BlueBallButton;
	ImageButton BitmapButton;
	ImageButton GlassCleanButton;
	ImageButton TextureButton;
	ImageButton AudioButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.rectangles);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		ComboButton = (ImageButton) findViewById(R.id.ComboButton);
		BlueBallButton = (ImageButton) findViewById(R.id.BlueBallButton);
		BitmapButton = (ImageButton) findViewById(R.id.BitmapButton);
		GlassCleanButton = (ImageButton) findViewById(R.id.GlassButton);
		TextureButton = (ImageButton) findViewById(R.id.TextureButton);
		AudioButton= (ImageButton) findViewById(R.id.AudioButton);
		
		myRectangleSlider = (RectangleSliderView)findViewById(R.id.RectangleView);
		

		myDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		myTPadDrawer = new TPadDrawer(DemoStart.tpad, myDrawer,
				DemoStart.height, DemoStart.rampwidth, DemoStart.buttonoffset);
		ComboButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.COMBO");
				startActivity(intent);
				finish();

			}
		});

	
		BlueBallButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.BALL");
				startActivity(intent);
				finish();

			}
		});

		
		BitmapButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.BITMAP");
				startActivity(intent);
				finish();

			}
		});
		
		GlassCleanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.GLASS");
				startActivity(intent);
				finish();

			}
		});
		AudioButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.AUDIO");
				startActivity(intent);
				finish();

			}
		});
		
		TextureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.TEXTURE");
				startActivity(intent);
				finish();

			}
		});
	}


	@Override
	protected void onPause() {
		// Pauses our surfaceview thread
		super.onPause();
		// Stop our drawing thread (which runs in the screenview object) when
		// the screen is paused
		myRectangleSlider.pause();
		myTPadDrawer.pause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// stop our communication server when activity is exited from menu
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// resume the drawing thread (which runs in the screenview object) when
		// screen is resumed.
		myRectangleSlider.resume();
		myTPadDrawer.resume();
	}

	
}
