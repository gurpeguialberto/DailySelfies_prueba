package com.example.prueba2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity extends Activity{
	private Bitmap mBitmap; 
	private ImageView mView;
	//private Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		mView = (ImageView) findViewById(R.id.detail_image);
		Intent mIntent = getIntent();
		if (mIntent.hasExtra("myImage")){
			mBitmap = (Bitmap) mIntent.getParcelableExtra("myImage");
		}
		mView.setImageBitmap(mBitmap);
		
	}
}
