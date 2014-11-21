package com.example.prueba2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	ListView listView ;
	private static final String TAG = "Photo";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_TAKE_PHOTO = 1;
	private ArrayList<PictureRecord> list = new ArrayList<PictureRecord>();
	public ArrayList<PictureRecord> readAllFiles(){
		File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
		String[] listOfFiles = fileList();
		for (int i=0;i<listOfFiles.length;i++){
			String fileName = storageDir.toString()+listOfFiles[i];
			list.add(read(fileName));
		}
		/*
		 * 
getFilesDir()
Gets the absolute path to the filesystem directory where your internal files are saved.
getDir()
Creates (or opens an existing) directory within your internal storage space.
deleteFile()
Deletes a file saved on the internal storage.
fileList()
Returns an array of files currently saved by your application.
		 */
		
    	return list;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "Entered ....onCreate....");
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
         };/* change to retrieve names of files=images from sdcard  */

        
        
        
        list = readAllFiles();
        /*code: give control View --> adapterView (mFiles_stored)*/
        
        
        
        
        
        
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        
        /* Change to a new PictureViewAdapter 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);
          */

        PictureViewAdapter mAdapter = new PictureViewAdapter(list, this);
        // Assign adapter to ListView
        listView.setAdapter(mAdapter); 
        
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
             /* Add code to start new Activity to show the selected picture BIG_Picture*/
              }
        });
    }
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	    	Log.i(TAG, "Entered ....onActivityResult....");
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        
	        	Log.i(TAG, "Entered ....onActivityResult....Saved  BitmapFile to Internal Storage");
	        
	        ImageView mImageView = null;
			mImageView.setImageBitmap(imageBitmap);
			listView.addView(mImageView);
	    }
	}
	/*
	
	public void saveImageToInternalStorage(Bitmap image) {
		 ContextWrapper cw = new ContextWrapper(getApplicationContext());
		 String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		 String imageFileName = "PNG_" + timeStamp + "_";
        // path to /data/data/yourapp/app_data/imageDir
        File storageDir = Environment.getExternalStoragePublicDirectory(
           Environment.DIRECTORY_PICTURES);
       // Create imageDir
       File mypath = new File(storageDir, imageFileName);

       FileOutputStream fos = null;
       try {           

           fos = new FileOutputStream(mypath);

      // Use the compress method on the BitMap object to write image to the OutputStream
           image.compress(Bitmap.CompressFormat.PNG, 100, fos);
           fos.close();
       } catch (Exception e) {
       	Log.i(TAG, "......saveToInternalStorage()......ERROR");
           e.printStackTrace();
       }
      
	}
	*/	
		
		
	String mCurrentPhotoPath;

	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "PNG_" + timeStamp + "_";
	    // path to /data/data/yourapp/app_data/imageDir
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".png",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}

	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // Ensure that there's a camera activity to handle the intent
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        // Create the File where the photo should go
	        File photoFile = null;
	        try {
	            photoFile = createImageFile();
	        } catch (IOException ex) {
	            // Error occurred while creating the File

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
