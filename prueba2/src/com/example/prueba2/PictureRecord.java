package com.example.prueba2;

import java.util.Date;

import android.graphics.Bitmap;

public class PictureRecord {
	private String mPicUri;
	private Bitmap mPicture;
	private Date mDate;
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public PictureRecord(String mPicUri, Bitmap mPicture) {
		super();
		this.mPicUri = mPicUri;
		this.mPicture = mPicture;
	}
	public String getmPicUri() {
		return mPicUri;
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
	public void setmPath(String mPath) {
		this.mPath = mPath;
	}
	*/
	 
	

}
