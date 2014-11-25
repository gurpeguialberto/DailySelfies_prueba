package com.example.prueba2;



import android.graphics.Bitmap;

public class PictureRecord {
	private String mPicUri;
	private Bitmap mPicture;
	
	public PictureRecord(String mPicUri, Bitmap mPicture) {
		super();
		this.mPicUri = mPicUri;
		this.mPicture = mPicture;
	}
	public String getmPicUri() {
		return mPicUri;
	}
	public void setmPicUri(String mPicUri) {
		this.mPicUri = mPicUri;
	}
	public Bitmap getmPicture() {
		return mPicture;
	}
	public void setmPicture(Bitmap mPicture) {
		this.mPicture = mPicture;
	}
	/*
	 * public void setmPicName(String mPicName) {
		this.mPicName = mPicName;
	}
	public String getmPath() {
		return mPath;
	}
	
	*/
	 
	

}
