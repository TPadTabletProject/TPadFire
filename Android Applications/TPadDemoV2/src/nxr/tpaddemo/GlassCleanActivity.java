package nxr.tpaddemo;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.app.Activity;
import android.content.Intent;

public class GlassCleanActivity extends Activity {

	GlassCleanView myGlassCleanView;

	SlidingDrawer myDrawer;
	public static TPadDrawer myTPadDrawer;
	ImageButton ComboButton;
	ImageButton BlueBallButton;
	ImageButton BitmapButton;
	ImageButton GlassCleanButton;
	ImageButton TextureButton;
	ImageButton AudioButton;

	Button CleanButton;
	Button DrawButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.glassclean);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// initialize screenview
		myGlassCleanView = (GlassCleanView) findViewById(R.id.glassView);

		ComboButton = (ImageButton) findViewById(R.id.ComboButton);
		BlueBallButton = (ImageButton) findViewById(R.id.BlueBallButton);
		BitmapButton = (ImageButton) findViewById(R.id.BitmapButton);
		GlassCleanButton = (ImageButton) findViewById(R.id.GlassButton);
		TextureButton = (ImageButton) findViewById(R.id.TextureButton);
		AudioButton = (ImageButton) findViewById(R.id.AudioButton);

		CleanButton = (Button) findViewById(R.id.cleanButton);
		DrawButton = (Button) findViewById(R.id.drawButton);
		DrawButton.setText("Feel");

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

		CleanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myGlassCleanView.wipeScreen();

			}
		});

		DrawButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myGlassCleanView.drawing = !(myGlassCleanView.drawing);
				if (myGlassCleanView.drawing == true) {
					DrawButton.setText("Feel");
				} else
					DrawButton.setText("Draw");
			}
		});

	}

	@Override
	protected void onPause() {
		// Pauses our surfaceview thread
		super.onPause();
		// Stop our drawing thread (which runs in the screenview object) when
		// the screen is paused
		myGlassCleanView.pause();
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
		myGlassCleanView.resume();
		myTPadDrawer.resume();
	}

}
