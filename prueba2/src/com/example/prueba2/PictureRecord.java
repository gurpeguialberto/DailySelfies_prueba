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
	
	public String getmDayStamp() {
		int first = mPicUri.indexOf('_')+1;
		int last = mPicUri.indexOf("_", first);
		String dayStamp = mPicUri.substring(first, last);
		
		return dayStamp;		
	}
	public String getmTimeStamp() {
		int first = mPicUri.indexOf('_')+1;
		int second = mPicUri.indexOf("_", first)+1;
		int third = mPicUri.indexOf("_", second);
		String timeStamp = mPicUri.substring(second, third);
		
		return timeStamp;
	}
}
