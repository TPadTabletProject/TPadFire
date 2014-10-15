package nxr.tpaddemo;

import nxr.tpaddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;

public class TextureActivity extends Activity {

	// Used to initialize our screenview object for
	// drawing on to

	TextureView myTextureView;
	SlidingDrawer myDrawer;
	public static TPadDrawer myTPadDrawer;
	ImageButton ComboButton;
	ImageButton BlueBallButton;
	ImageButton BitmapButton;
	ImageButton GlassCleanButton;
	ImageButton TextureButton;
	ImageButton AudioButton;
	CheckBox ShowButton;

	Thread myThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.texture);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// initialize screenview
		myTextureView = (TextureView) findViewById(R.id.textureView);
		myTextureView.isShowing = true;

		ComboButton = (ImageButton) findViewById(R.id.ComboButton);
		BlueBallButton = (ImageButton) findViewById(R.id.BlueBallButton);
		BitmapButton = (ImageButton) findViewById(R.id.BitmapButton);
		GlassCleanButton = (ImageButton) findViewById(R.id.GlassButton);
		TextureButton = (ImageButton) findViewById(R.id.TextureButton);
		AudioButton= (ImageButton) findViewById(R.id.AudioButton);
		
		ShowButton = (CheckBox) findViewById(R.id.checkBox1);
		ShowButton.setChecked(true);
		
		

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

		ShowButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ShowButton.isChecked())
					myTextureView.isShowing = true;
				else
					myTextureView.isShowing = false;
			}
		});
	}

	@Override
	protected void onPause() {
		// Pauses our surfaceview thread
		super.onPause();
		// Stop our drawing thread (which runs in the screenview object) when
		// the screen is paused
		myTextureView.pause();
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

		myTextureView.resume();
		myTPadDrawer.resume();
	}

}
