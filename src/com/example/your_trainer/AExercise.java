package com.example.your_trainer;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class AExercise extends Activity 
{
	
	private int endTimer = 0;
	
	private String choosedComplexName = null;
	private String choosedComplexId = null;
	
	// widgets
	 private WebView wbPictur;
	 private TextView tvTimer;
	 
	 // values for timer	
	private CountDownTimer timer;
	private boolean isPaused = false;
	private long restMillisUntilFinished = 0;
	
	 private static final int SecVal = 30;
	 private static final int MillisInSec = 1000;
	 
	 // value for exercises content
	 private List<AExercisContent> exerList;
	
	

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
	    	 
	    	 fill_List();
	    	 
	    	 showTimer(10, "file:///android_asset/1/1.gif");
	    	 
	    	 
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



	 private void fill_List() {
		// TODO Auto-generated method stub
		
	}



	private void showTimer(int count, String gifPath)
		{
			
		  count = count *  MillisInSec;
		 // int count = 10;		 
		// String gifPath = "file:///android_asset/1/1.gif";
		 
		  wbPictur.loadUrl(gifPath);	
		 
				 
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
					 showTimer(10, "file:///android_asset/1/2.gif");
					
					
				}
			};
			
			timer.start();
			
			
			
		}
	 
	 
	
	 
	 
}
