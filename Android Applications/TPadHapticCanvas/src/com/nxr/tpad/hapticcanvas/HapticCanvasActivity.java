package com.nxr.tpad.hapticcanvas;

import nxr.tpad.lib.TPad;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HapticCanvasActivity extends TabActivity {

	// Used to initialize our screenview object for
	// drawing on to

	public static TabHost mTabHost;

	public static HapticCanvasView myHapticView;

	public static TPad myTPad = new TPad();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// set the content view to our layout .xml
		setContentView(R.layout.hapticcanvas);

		mTabHost = getTabHost();

		TabSpec FileSpec = mTabHost.newTabSpec("File");
		FileSpec.setIndicator("Save/Load");
		Intent fileIntent = new Intent(this, FileOptions.class);
		FileSpec.setContent(fileIntent);
		
		TabSpec BrushSpec = mTabHost.newTabSpec("Brush");
		BrushSpec.setIndicator("Brush Options");
		Intent brushIntent = new Intent(this, BrushOptions.class);
		BrushSpec.setContent(brushIntent);
		
		TabSpec EditSpec = mTabHost.newTabSpec("Edit");
		EditSpec.setIndicator("Edit");
		Intent editIntent = new Intent(this, EditScreen.class);
		EditSpec.setContent(editIntent);
		
		TabSpec FeelSpec = mTabHost.newTabSpec("Feel");
		FeelSpec.setIndicator("Feel");
		Intent feelIntent = new Intent(this, FeelScreen.class);
		FeelSpec.setContent(feelIntent);
		
		mTabHost.addTab(FileSpec);
		mTabHost.addTab(BrushSpec);
		mTabHost.addTab(EditSpec);
		mTabHost.addTab(FeelSpec);
				

		// initialize screenview class object
		myHapticView = (HapticCanvasView) findViewById(R.id.hapticCanvasView);

		// Start communication with TPad
		myTPad.startTPad(this, 33600);

	}

	// Following code modified from

	@Override
	protected void onPause() {
		// Pauses our surfaceview thread
		super.onPause();
		// Stop our drawing thread (which runs in the screenview object) when
		// the screen is paused
		myHapticView.pause();

	}

	@Override
	protected void onDestroy() {
		
		myTPad.stopTPad();
		myHapticView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// resume the drawing thread (which runs in the screenview object) when
		// screen is resumed.
		myHapticView.resume();

	}

}
