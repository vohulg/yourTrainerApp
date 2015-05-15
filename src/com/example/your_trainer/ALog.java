package com.example.your_trainer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.os.Environment;

public class ALog {

	final static String filename = "vhlog.txt";

	public static void writeLog(String logStr) throws IOException
	{

		String ret = Environment.getExternalStorageState();

		String path = null;
		String pathDir = null;

		// 1. Check mounting of sdcard
		if (!ret.equals(Environment.MEDIA_MOUNTED) )
		{
			//Toast.makeText(cont, "sdcard not mounted", Toast.LENGTH_SHORT).show();
			return;

		}

	    //2. Create log file if not existed
	     	  File sdDir = Environment.getExternalStorageDirectory();
	    	  pathDir = sdDir.getAbsolutePath() + "/" + "train";
	    	  File fd = new File(pathDir, filename);

	    	  if(!fd.exists())
	    	      fd.createNewFile();

	    	  else
	    		 ;// Toast.makeText(cont, "file exists", Toast.LENGTH_SHORT).show();

	    	  //3. Build string
	    	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	  dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Almaty"));
	    	  Date date = new Date();



	    	  String resString = "\r\n" + dateFormat.format(date) + " : " + logStr + "\r\n";


	     //3. Write to file string
	    	  FileWriter fw = new FileWriter(fd, true);
	    	  BufferedWriter bw = new BufferedWriter(fw);
	    	  bw.write(resString);
	    	  bw.close();







	}


}
