package com.nxr.tpad.hapticcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BrushPreview extends View {
	
	private int height, width;
	private Paint brush;

	public BrushPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		brush = new Paint();
		brush.setStrokeCap(Paint.Cap.ROUND);
		brush.setAntiAlias(true);
		setColor(Color.WHITE);
		brush.setAlpha(255);
		setWidth(10);
		
	}
	
	public void setColor(int color){
		brush.setColor(color);
		invalidate();

	}
	
	public void setWidth(int w){
		brush.setStrokeWidth(w);
		invalidate();

	}

	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		
		Log.i("BrushPreview", "onMeasure called: "+String.valueOf(width) + " " + String.valueOf(height));
		this.setMeasuredDimension(width, height);
		
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawARGB(0, 255, 255, 255);
		canvas.drawPoint(width/2, height/2, brush);
		
		
	}

	

}
