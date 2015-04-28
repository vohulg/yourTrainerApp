package com.example.your_trainer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class AExercise extends Activity 
{
	// for connect of database
	final String LOG_TAG = "vh_tag";
	DBHelper dbhelper;
	SQLiteDatabase dataBase;
	
	private int endTimer = 0;
	
	private String choosedComplexName = null;
	private String choosedComplexId = null;
	private int currentIndex = 0;
	private int countOfExersInComplex = 0;
	
	// widgets
	 private WebView wbPictur;
	 private TextView tvDescr;
	 private TextView tvExerName;
	 private TextView tvTimer;
	
	 
	 // values for timer	
	private CountDownTimer timer;
	private boolean isPaused = false;
	private long restMillisUntilFinished = 0;
	
	 private static final int SecVal = 30;
	 private static final int MillisInSec = 1000;
	 
	 // value for exercises content
	 private List<AExercisContent> listOfExer;
	
	

	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.aexercise);	
	        
	        Intent intent = getIntent();
	        choosedComplexName =intent.getStringExtra("nameChoosedCompex");
	    	choosedComplexId = intent.getStringExtra("nameChoosedId");
	    	
	    	 wbPictur = (WebView)findViewById(R.id.wvImgShow);
	    	 tvTimer = (TextView)findViewById(R.id.tvTimer);
	    	 tvDescr = (TextView)findViewById(R.id.tvDescr);
	    	 tvExerName = (TextView)findViewById(R.id.tvExerName);
	    	 
	    	
	    	 
	    	 
	    	 fill_List();
	    	 
	    	 showTimer(0);
	    	 
	    	//showTimer(10, "file:///android_asset/1/1.gif");
	    	 
	    	 
	    	 // start loop
	    	/*
	    	 int countOfExer = 2;
	    	 String gifPath = null;
	    	 int SecVal = 10;
	    	
	    	 int i = 0;
	    	 
	    	 while ( i < countOfExer)
	    	 {	    	 
	    		 
	    		 gifPath = "file:///android_asset/1/" + String.valueOf(i+1) +".gif";	    		 
	    		 wbPictur.loadUrl(gifPath);	 
	    		 showTimer(SecVal * MillisInSec);	
	    		 i++;
	    	 }
	    	 
	    	 */
	        
	       // Log.d("vh_tag", "Choosed complex:" + choosedComplexName);
	       // Log.d("vh_tag", choosedComplexId);
	        
	        
	 }



	 private boolean fill_List()
	 {
	
		 dbhelper = new DBHelper(this, "dbTrainer"); 	        
	     dataBase = dbhelper.getReadableDatabase();	      
	     listOfExer = new ArrayList<AExercisContent>();
	      
	      String column[] = {"exes_name", "exes_descr", "exes_photourl", "exes_timeinsec", "exes_order", "exes_istabata" };
		String selection = "exes_complid =" + choosedComplexId;
		String orderby = "exes_order desc";
	      
			Cursor c =  dataBase.query("tbExercises", column, selection, null, null, null, orderby);
			int rows = c.getCount();
			
			if (rows == 0)
			{
				Log.d(LOG_TAG, "0 rows");
				return false;
			}
			
			else
			{
				countOfExersInComplex = rows;
				
			}
			
			int ExerName = c.getColumnIndex("exes_name");
			int  ExerDescr = c.getColumnIndex("exes_descr");
			int  ExerPhotoUrl = c.getColumnIndex("exes_photourl");
			int  ExerTime = c.getColumnIndex("exes_timeinsec");
			
			// fill list of complexes
			c.moveToFirst();
			for (int i =0; i < rows; i++)
			{
				
				String name =  c.getString(ExerName );
				String url = c.getString(ExerPhotoUrl);
				String descr = c.getString(ExerDescr);
				String time = c.getString(ExerTime);	
						
				AExercisContent obj = new AExercisContent(name, url, descr, Integer.parseInt(time));
				listOfExer.add(obj);				
				c.moveToNext();
				
			}
			
			c.close();
		   dbhelper.close();
		   dataBase.close();			
			return true;		
	}



	private void showTimer(int indexOfList)
		{
		
		// 1. get count of time, description, url, name of exercises
		
		AExercisContent instan = listOfExer.get(indexOfList);
		
		int count = instan.getTime();
		//String gifPath = instan.getUrl();
		String gifPath = "file:///android_asset/" + choosedComplexId + "/" + (indexOfList + 1) + ".gif";
		String descr = instan.getDescr();
		String name = instan.getName();
		
		// 2. show values in widget
		 wbPictur.loadUrl(gifPath);
		 tvDescr.setText(descr); 
    	 tvExerName.setText(name);
		 
		
		
		 
		// 3. Show timer
		count = count *  MillisInSec;
		 
		   if (timer != null)
		   {
			   timer.cancel();
		
		   }
			   
			if (isPaused == true)
			{
				count = (int)restMillisUntilFinished; 
			}
			
			timer = new CountDownTimer(count, MillisInSec) {
				
				@Override
				public void onTick(long millisUntilFinished)
				{
					restMillisUntilFinished = millisUntilFinished;
					String res = Integer.toString((int)(millisUntilFinished / MillisInSec));
					tvTimer.setText(res );
					
				}
				
				@Override
				public void onFinish()
				{
					currentIndex ++;
					
					// finish of complex
					if (currentIndex > (countOfExersInComplex-1))
					{
						wbPictur.setVisibility(View.INVISIBLE);
						return;
					}
					// continue complex
					else					
					   showTimer(currentIndex);
					
					
				}
			};
			
			timer.start();
			
			
			
		}
	 
	 
	
	 
	 
}
