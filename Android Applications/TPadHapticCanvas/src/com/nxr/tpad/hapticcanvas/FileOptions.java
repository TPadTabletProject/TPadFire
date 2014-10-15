package com.nxr.tpad.hapticcanvas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import nxr.tpad.lib.TPad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FileOptions extends Activity {

	private static Button LoadButton, SaveButton;
	
	private static EditText FileName;

	private static final int REQ_CODE_PICK_IMAGE = 100;

	TPad myTPad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filelayout);

		myTPad = HapticCanvasActivity.myTPad;
		
		LoadButton = (Button) findViewById(R.id.loadButton);
		SaveButton = (Button) findViewById(R.id.saveButton);
		FileName = (EditText) findViewById(R.id.filename);

		// Action to take when picture selection button is clicked
		LoadButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// We must start a new intent to request data from the system's
				// image picker
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, REQ_CODE_PICK_IMAGE);
			}
		});
		// Action to take when save picture button is clicked
		SaveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String file_path = Environment.getExternalStorageDirectory()
						.toString() + "/TPad Images";
				OutputStream fOut = null;
				File dir = new File(file_path);

				if (!dir.exists())
					dir.mkdirs();
				dir = new File(file_path, "HapticCanvas "
						+ FileName.getText().toString() + ".png");

				try {
					fOut = new FileOutputStream(dir);

					HapticCanvasActivity.myHapticView.getBitmap().compress(
							Bitmap.CompressFormat.PNG, 100, fOut);
					fOut.flush();
					fOut.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		});
	}

	// http://stackoverflow.com/questions/9564644/null-pointer-exception-while-loading-images-from-gallery
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

		// Parse the activity result
		switch (requestCode) {
		case REQ_CODE_PICK_IMAGE:
			if (resultCode == RESULT_OK) {
				Uri bitmapUri = imageReturnedIntent.getData();

				try {

					// set the display bitmap based on the bitmap we just got
					// back from our intent
					Bitmap b = Media.getBitmap(getContentResolver(), bitmapUri);
					HapticCanvasActivity.myHapticView.setBitmap(b);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			}
		}
	}

	@Override
	protected void onResume() {
		myTPad.send(0);
		super.onResume();
	}

}
