package com.nxr.tpad.hapticcanvas;

import com.nxr.tpad.hapticcanvas.ColorPickerDialog.OnColorChangedListener;
import nxr.tpad.lib.TPad;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BrushOptions extends Activity implements OnColorChangedListener {

	private Button[] ColorButtons;
	private SeekBar BrushWidthBar;
	private BrushPreview BrushView;

	Context mContext;
	OnColorChangedListener mColorListener;
	TPad myTPad;

	PaintPalatte myPalatte;
	int colorID = 0;
	HapticCanvasView myHapticView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.brushlayout);
		
		myHapticView = HapticCanvasActivity.myHapticView;
		myPalatte = HapticCanvasView.myPaintBucket;

		ColorButtons = new Button[myPalatte.length()];
		
		ColorButtons[0] = (Button) findViewById(R.id.color0);
		ColorButtons[1] = (Button) findViewById(R.id.color1);
		ColorButtons[2] = (Button) findViewById(R.id.color2);
		ColorButtons[3] = (Button) findViewById(R.id.color3);
		ColorButtons[4] = (Button) findViewById(R.id.color4);
		ColorButtons[5] = (Button) findViewById(R.id.color5);
		ColorButtons[6] = (Button) findViewById(R.id.color6);
		ColorButtons[7] = (Button) findViewById(R.id.color7);
		ColorButtons[8] = (Button) findViewById(R.id.color8);
		ColorButtons[9] = (Button) findViewById(R.id.color9);
		ColorButtons[10] = (Button) findViewById(R.id.color10);
		ColorButtons[11] = (Button) findViewById(R.id.color11);
		ColorButtons[12] = (Button) findViewById(R.id.color12);
		ColorButtons[13] = (Button) findViewById(R.id.color13);
		ColorButtons[14] = (Button) findViewById(R.id.color14);

		BrushWidthBar = (SeekBar) findViewById(R.id.brushSizeSlider);
		BrushView = (BrushPreview) findViewById(R.id.brushPreview);
		myTPad = HapticCanvasActivity.myTPad;

		mContext = this;
		mColorListener = this;

		BrushWidthBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progressChanged = 0;

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progressChanged = progress;
				int width = (int) (5 + progressChanged / 100. * 50);
				BrushView.setWidth(width);
			}
			

			public void onStopTrackingTouch(SeekBar seekBar) {
				int width = (int) (5 + progressChanged / 100. * 50);
				myHapticView
						.setBrushWidth(width);
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				//

			}
		});
		
		ColorButtons[0].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				colorID = 0;
				myHapticView.setBrushColor(myPalatte.getColor(0));
				BrushView.setColor(myPalatte.getColor(0));
				refreshColors();
			}
		});
		ColorButtons[1].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				colorID = 1;
				myHapticView.setBrushColor(myPalatte.getColor(1));
				BrushView.setColor(myPalatte.getColor(1));
				refreshColors();
			}
		});
		ColorButtons[2].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				colorID = 2;
				myHapticView.setBrushColor(myPalatte.getColor(2));
				BrushView.setColor(myPalatte.getColor(2));
				refreshColors();
			}
		});
		ColorButtons[3].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				colorID = 3;
				myHapticView.setBrushColor(myPalatte.getColor(3));
				BrushView.setColor(myPalatte.getColor(3));
				refreshColors();
			}
		});
		ColorButtons[4].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				colorID = 4;
				myHapticView.setBrushColor(myPalatte.getColor(4));
				BrushView.setColor(myPalatte.getColor(4));
				refreshColors();
			}
		});
		ColorButtons[5].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(5));
				BrushView.setColor(myPalatte.getColor(5));
			}
		});
		ColorButtons[6].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(6));
				BrushView.setColor(myPalatte.getColor(6));
			}
		});
		ColorButtons[7].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(7));
				BrushView.setColor(myPalatte.getColor(7));
			}
		});
		ColorButtons[8].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(8));
				BrushView.setColor(myPalatte.getColor(8));
			}
		});
		ColorButtons[9].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(9));
				BrushView.setColor(myPalatte.getColor(9));
			}
		});
		ColorButtons[10].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(10));
				BrushView.setColor(myPalatte.getColor(10));
			}
		});
		ColorButtons[11].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(11));
				BrushView.setColor(myPalatte.getColor(11));
			}
		});
		ColorButtons[12].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(12));
				BrushView.setColor(myPalatte.getColor(12));
				
			}
		});
		ColorButtons[13].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(13));
				BrushView.setColor(myPalatte.getColor(13));
			}
		});
		ColorButtons[14].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myHapticView.setBrushColor(myPalatte.getColor(14));
				BrushView.setColor(myPalatte.getColor(14));
			}
		});
		
		
		
		
		
		
		ColorButtons[0].setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				colorID = 0;
				new ColorPickerDialog(mContext,mColorListener, myPalatte.getColor(0)).show();
				return true;
			}
		});
		ColorButtons[1].setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				colorID = 1;
				new ColorPickerDialog(mContext,mColorListener, myPalatte.getColor(1)).show();
				return true;
			}
		});
		ColorButtons[2].setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				colorID = 2;
				new ColorPickerDialog(mContext,mColorListener, myPalatte.getColor(2)).show();
				return true;
			}
		});
		ColorButtons[3].setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				colorID = 3;
				new ColorPickerDialog(mContext,mColorListener, myPalatte.getColor(3)).show();
				return true;
			}
		});
		ColorButtons[4].setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				colorID = 4;
				new ColorPickerDialog(mContext,mColorListener, myPalatte.getColor(4)).show();
				return true;
			}
		});
		

	}

	@Override
	protected void onResume() {
		super.onResume();
		refreshColors();
		myTPad.send(0);		

	}
	
	public void refreshColors(){
		
		myPalatte.setColor(9, darkenColor(myPalatte.getColor(colorID), 110, 2));
		myPalatte.setColor(8, darkenColor(myPalatte.getColor(colorID), 110, 1));
		myPalatte.setColor(7, myPalatte.getColor(colorID));
		myPalatte.setColor(6, ligthenColor(myPalatte.getColor(colorID), 110, 1));
		myPalatte.setColor(5, ligthenColor(myPalatte.getColor(colorID), 110, 2));
		
		
		for (int i = 0; i < ColorButtons.length; i++) {
			ColorButtons[i].setBackgroundColor(myPalatte.getColor(i));
		}
	}

	@Override
	public void colorChanged(int color) {
		myPalatte.setColor(colorID, color);
		myHapticView.setBrushColor(color);
		BrushView.setColor(myPalatte.getColor(colorID));
		refreshColors();
	}

	public int ligthenColor(int color, int val, int num){
		
		int red,green,blue;
		red = Color.red(color);
		green = Color.green(color);
		blue = Color.blue(color);
		
		red = red+val*num;
		if (red>255) red=255;
		
		green = green+val*num;
		if (green>255) green = 255;
		
		blue = blue+val*num;
		if (blue>255) blue = 255;
		
		return 0xFF000000+(red<<16)+(green<<8)+blue;		
		
	}
	
	public int darkenColor(int color, int val, int num){
		int red,green,blue;
		red = Color.red(color);
		green = Color.green(color);
		blue = Color.blue(color);
		
		red = red-val*num;
		if (red<0) red=0;
		
		green = green-val*num;
		if (green<0) green = 0;
		
		blue = blue-val*num;
		if (blue<0) blue = 0;
		
		return 0xFF000000+(red<<16)+(green<<8)+blue;			
		
	}
	

}


