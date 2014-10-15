package nxr.tpaddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;

public class ComboChooseActivity extends Activity {

	ComboChooseView myComboView;
	SlidingDrawer myDrawer;
	TPad myTPad;
	static TPadDrawer myTPadDrawer;
	String displayString;

	ImageButton ComboButton;
	ImageButton BlueBallButton;
	ImageButton BitmapButton;
	ImageButton GlassCleanButton;
	ImageButton TextureButton;
	ImageButton AudioButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.combochoose);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// initialize screenview and setup onTouch Listener
		myComboView = (ComboChooseView) findViewById(R.id.comboView);
		myDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

		ComboButton = (ImageButton) findViewById(R.id.ComboButton);
		BlueBallButton = (ImageButton) findViewById(R.id.BlueBallButton);
		BitmapButton = (ImageButton) findViewById(R.id.BitmapButton);
		GlassCleanButton = (ImageButton) findViewById(R.id.GlassButton);
		TextureButton = (ImageButton) findViewById(R.id.TextureButton);
		AudioButton= (ImageButton) findViewById(R.id.AudioButton);
		
		myTPadDrawer = new TPadDrawer(DemoStart.tpad, myDrawer,
				DemoStart.height, DemoStart.rampwidth, DemoStart.buttonoffset);

		// Open touchscreen activity
		ComboButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myDrawer.animateClose();
				Intent intent = new Intent("nxr.tpaddemo.COMBO");
				startActivity(intent);
				finish();

			}
		});

		// Open touchscreen activity
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
		myComboView.pause();
		myTPadDrawer.pause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myComboView.resume();
		myTPadDrawer.resume();
	}

}
