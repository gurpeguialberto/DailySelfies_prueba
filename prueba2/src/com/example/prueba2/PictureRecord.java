package com.example.prueba2;

import java.util.Date;

import android.graphics.Bitmap;

public class PictureRecord {
	private String mPicName;
	private String mPath;
	private Bitmap mPicture;
	private Date mDate;
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public PictureRecord(String mPicName, String mPath, Bitmap mPicture) {
		super();
		this.mPicName = mPicName;
		this.mPath = mPath;
		this.mPicture = mPicture;
	}
	public String getmPicName() {
		return mPicName;
	}
	public void setmPicName(String mPicName) {
		this.mPicName = mPicName;
	}
	public String getmPath() {
		return mPath;
	}
	public void setmPath(String mPath) {
		this.mPath = mPath;
	}
	public Bitmap getmPicture() {
		return mPicture;
	}
	public void setmPicture(Bitmap mPicture) {
		this.mPicture = mPicture;
	}
	
	

}
