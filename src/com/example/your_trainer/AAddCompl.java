package com.example.your_trainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.p007_file.AExerForParsing;

public class AAddCompl extends Activity
{

	private final String appDir = "train";
	private final int REQUEST_CODE_PICK_DIR = 1;
	private final int REQUEST_CODE_PICK_FILE = 2;
	private TextView tvInfo;
	private List<AExerForParsing> list;


	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newcomplex);

		tvInfo = (TextView) findViewById(R.id.tvStatus);

		list = new ArrayList<AExerForParsing>();

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
				tvInfo.setText(choosedFile);

				try
	        		 {
						parseFile(choosedFile);
					sendListToDataBase();
					 }

	        		 catch (IOException e)
					{
						e.printStackTrace();
					}
	        	}
	        	else
	        	{
				Toast.makeText(this, "Received NO result from file browser",
						Toast.LENGTH_LONG).show();
	        	}
	        }



			super.onActivityResult(requestCode, resultCode, data);

		 }


	private void sendListToDataBase() {
		// 1. Create connection with database
		DBHelper dbhelper;
		SQLiteDatabase dataBase;
		dbhelper = new DBHelper(this, "dbTrainer");
		dataBase = dbhelper.getWritableDatabase();

		// 2. Send list to write to database
		dbhelper.insertData(list, dataBase);

		// 3. close database
		dbhelper.close();
		dataBase.close();

	}

	private void parseFile(String choosedFile) throws IOException
	    {

	    	//String path = "file:///android_asset/exer.txt";
	    	String endOfExer = "3.";

	    	//AssetManager asset = this.getAssets();

	    	//InputStreamReader istream = new InputStreamReader(choosedFile);

	    	//File choosedFilePath = new File (choosedFile);

	    	BufferedReader br = new BufferedReader(new FileReader(choosedFile));

	    	String line = null;

		// List<AExerForParsing> list = new ArrayList<AExerForParsing>();

	    	while((line = br.readLine()) != null)
	    	{

	    		String startComplexPrefix = "***start of complex***";
	    		String endComplexPrefix = "***end of complex***";
	    		String complexNamePrefix = "Complex name:";
	    		String exerNamePrefix = "1. Exercise name:";
	    		String exerDescrPrefix = "2. Exercise description:";
	    		String exerTimePrefix = "3. Exercise time:";
	    		String folderNamePrefix = "Folder name with pictures:";

	    		if (line.startsWith(startComplexPrefix)) // start of one complex
	    		 {

	    			 AExerForParsing  obj = new AExerForParsing();

	    			while (line != null) // parsing exercises of complex
	    			{

	    				if(line.startsWith(folderNamePrefix))
	    				{
	    					line = line.replaceAll(folderNamePrefix, ""); // folder with gifs
	    			    	//line = line.replaceAll(" ", ""); //
	    			    	obj.setFolder(line.trim());

	    				}

	    			    if (line.startsWith(complexNamePrefix))
	    			    {

	    			    	line = line.replaceAll(complexNamePrefix, ""); // complex name
	        				obj.setCompl(line);

	    			    }

	    			    if (line.startsWith(exerNamePrefix))
	    			    {
	    			    	line = line.replaceAll(exerNamePrefix, ""); // exer name
	    			    	obj.addExerNameToList(line);

	    			    }

	    			    if (line.startsWith(exerDescrPrefix))
	    			    {
	    			    	line = line.replaceAll(exerDescrPrefix, ""); //exer descr
	    			    	obj.addExerDescrToList(line);

	    			    }

	    			    if (line.startsWith(exerTimePrefix))
	    			    {
	    			    	line = line.replaceAll(exerTimePrefix, ""); // exer time
	    			    	//line.trim();
	    			    	//line = line.replaceAll(" ", ""); // exer time
	    			    	obj.addExerTimeToList(line.trim());
	    			    }

	    			    if (line.startsWith(endComplexPrefix))
	    					break;

	    			    line = br.readLine();

	    			}

	    			list.add(obj);





	    		 }



	    	}

		int x = 0;

	    }

	public void sendListToDb() {



	}





}
