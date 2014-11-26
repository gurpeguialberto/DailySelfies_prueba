package com.example.prueba2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity extends Activity{

	private Bitmap mBitmap; 
	private ImageView mView;
	//private Intent mIntent;
	 String mCurrentPhotoLocation;
	 String mCurrentPhotoPath;
	static String TAG = "DetailActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		mView = (ImageView) findViewById(R.id.detail_image);
		
		mView.setAdjustViewBounds(true);
		
		Log.i(TAG, "Entered ....onCreate...DetailActivity.");
		Intent mIntent = getIntent();
		mCurrentPhotoPath = mIntent.getStringExtra("myImageUri");
		mCurrentPhotoLocation = mCurrentPhotoPath.substring(mCurrentPhotoPath.indexOf(':')+1);
		mView.setImageURI(Uri.parse(mCurrentPhotoPath));
		//Bundle bundle = this.getIntent().getExtras();
		//mBitmap = (Bitmap) bundle.getParcelable("myImageUri");
		Log.i(TAG, "Entered ....onCreate...DetailActivity...mCurrentPhotoPath=" + mCurrentPhotoPath);
		Log.i(TAG, "Entered ....onCreate...DetailActivity...mCurrentPhotoLocation=" + mCurrentPhotoLocation);
	}
}
