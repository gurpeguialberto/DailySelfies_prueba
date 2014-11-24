package com.example.prueba2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {
	
	private static final String TAG = "Photo";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_TAKE_PHOTO = 1;
	String mCurrentPhotoPath;
	String mCurrentPhotoLocation;
	private PictureViewAdapter adapter;
	
	ListView listView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, "Entered ....onCreate....");
		
		adapter = new PictureViewAdapter(getApplicationContext());
		listView = getListView();
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		listView.setAdapter(adapter);
		/*-----------------------------------------------------*/
		/*
		setContentView(R.layout.activity_main);
		  // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        
        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View", 
                                         "Adapter implementation",
                                         "Simple List View In Android",
                                         "Create List View Android", 
                                         "Android Example", 
                                         "List View Source Code", 
                                         "List View Array Adapter", 
                                         "Android Example List View" 
         };// algurpe: change to retrieve names of files=images from sdcard  

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        */
        /*-----------------------------------------------------*/
        
        
        
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {
        	 @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
        		 Log.i(TAG, "Entered ....onItemClick....position=" + position);
               // ListView Clicked item index
               int itemPosition     = position;
               
               // ListView Clicked item value
               String  itemValue    = (String) listView.getItemAtPosition(position);
                  
                // Show Alert 
                Toast.makeText(getApplicationContext(),
                  "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                  .show();
             /* Add code to start new Activity to show the selected picture */
              }
        });
    }
	protected void onResume(Bundle savedInstanceState){
		//listView.getList();
	}
	private Bitmap setPic() {
	    // Get the dimensions of the View
	    int targetW = listView.getWidth();
	    int targetH = listView.getHeight();

	    // Get the dimensions of the bitmap
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(mCurrentPhotoLocation, bmOptions);
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    // Determine how much to scale down the image
	    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

	    // Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoLocation, bmOptions);
	    return bitmap;
	    //listView.addView(adapter.getView(position, convertView, parent));
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
	    	Log.i(TAG, "Entered ....onActivityResult....REQUEST_TAKE_PHOTO_OK...RESULT_OK..OK");
	    	
	    	// adding URI file makes returned intent be NULL
	    	
	    	PictureRecord mPictureRecord = new PictureRecord(mCurrentPhotoPath, setPic());
	    	
	    	
	        adapter.add(mPictureRecord);
	        galleryAddPic();
	       
			
	    } else if (resultCode != RESULT_OK){
	    	if (requestCode == REQUEST_TAKE_PHOTO){
	    		 Log.i(TAG, "Entered ....onActivityResult... NOno RESULT_OK..... SIS REQUEST_TAKE_PHOTO");
	    	}else{
	    		Log.i(TAG, "Entered ....onActivityResult... NOno RESULT_OK..... NoNo REQUEST_TAKE_PHOTO");	
	    	}
	    	
	    }else{
	    	Log.i(TAG, "Entered ....onActivityResult... SISI RESULT_OK..... NoNo REQUEST_TAKE_PHOTO");
	    }
	}
	private void galleryAddPic() {
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    File f = new File(mCurrentPhotoPath);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    this.sendBroadcast(mediaScanIntent);
	}
	

	private File createImageFile() throws IOException {
		Log.i(TAG, "Entered ....createImageFile....StorageState= " + Environment.getExternalStorageState());
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	    if ( !storageDir.exists()) {
	    	Log.i(TAG, "storageDir does not exist: absPath = " + storageDir.getAbsolutePath());
    		return null;
	    	} else {
	    		Log.i(TAG, "storageDir exists: " + storageDir.getAbsolutePath());
	    	}
	    if ( !storageDir.canWrite()){
	    	Log.i(TAG, "storageDir is not writable: " + storageDir.getAbsolutePath());
    		return null;
	    	} else {
	    		Log.i(TAG, "storageDir is writable: " + storageDir.getAbsolutePath());
	    	}
	   
	    File image = null;
	    try{
	    	
		    image = File.createTempFile(
		        imageFileName,  /* prefix */
		        ".jpg",         /* suffix */
		        storageDir      /* directory */
		    );
	    } catch (IOException ex){
	    	Log.i(TAG, "Entered ....createImageFile....File= NOT CREATED");
	    	ex.printStackTrace();
	    }
	    Log.i(TAG, "Entered ....createImageFile....File= " + image.toString());

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoLocation = image.getAbsolutePath();
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	private void dispatchTakePictureIntent() {
		Log.i(TAG, "Entered ....dispatchTakePictureIntent....");
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try {
	            photoFile = createImageFile();
	        } catch (IOException ex) {
	            // Error occurred while creating the File
	        	Log.i(TAG, "Entered ....dispatchTakePictureIntent...Error occurred while creating the File.");
	        	ex.printStackTrace();
	        }
	        // Continue only if the File was successfully created
	        if (photoFile != null) {
	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	                    Uri.fromFile(photoFile));
	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	        }
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_camera) {
			dispatchTakePictureIntent();
		}
		return super.onOptionsItemSelected(item);
	}
}
