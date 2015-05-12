package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

public class AAddCompl extends Activity
{
	
	private final String appDir = "train";
	private final int REQUEST_CODE_PICK_DIR = 1;
	private final int REQUEST_CODE_PICK_FILE = 2;
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newcomplex);
	 }
	 
	 public void chooseFile (View view)
	 {
		
		  final Activity activityForButton = this;
		 
		 Intent fileExploreIntent = new Intent(
				FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
 				null,
 				activityForButton,
 				FileBrowserActivity.class
 				);
			//If the parameter below is not provided the Activity will try to start from sdcard(external storage),
			// if fails, then will start from roor "/"
			// Do not use "/sdcard" instead as base address for sdcard use Environment.getExternalStorageDirectory() 
	
		fileExploreIntent.putExtra(	FileBrowserActivity.startDirectoryParameter, Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + appDir);
 		startActivityForResult(
 				fileExploreIntent,
 				REQUEST_CODE_PICK_FILE
 				);
		 
	 }
	 
	 
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
		 {
		 if (requestCode == REQUEST_CODE_PICK_FILE) {
	        	if(resultCode == RESULT_OK) {
	        		String choosedFile = data.getStringExtra(FileBrowserActivity.returnFileParameter);
	        		 parseFile(choosedFile);		        	
	        	}
	        	else
	        	{
	        		Toast.makeText(
	        				this, 
	        				"Received NO result from file browser",
	        				Toast.LENGTH_LONG).show(); 
	        	}
	        }
			
			
			
			super.onActivityResult(requestCode, resultCode, data);
			 
		 }

	private void parseFile(String choosedFile)
	{
		
		
		
		
	}
	 
	 

}
