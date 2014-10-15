package com.nxr.tpad.hapticcanvas;

import android.graphics.Color;

public class PaintPalatte {

	private int numColors = 15;

	private int[] colors = new int[numColors];

	public PaintPalatte() {

		// Initialize all colors
		colors[0] = Color.RED;
		colors[1] = Color.YELLOW;
		colors[2] = Color.GREEN;
		colors[3] = Color.BLUE;
		colors[4] = Color.MAGENTA;
		colors[10] = Color.WHITE;
		colors[11] = Color.LTGRAY;
		colors[12] = Color.GRAY;
		colors[13] = Color.DKGRAY;
		colors[14] = Color.BLACK;
		
	

	}

	public int length() {
		return numColors;
	}

	public void setColor(int index, int col) {

		colors[index] = col;

	}
	

	public int[] getColors() {

		return colors;

	}

	public int getColor(int c) {
		return colors[c];
	}

}
