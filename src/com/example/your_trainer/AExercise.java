package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AExercise extends Activity 
{
	
	String choosedComplexName = null;
	String choosedComplexId = null;

	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.aexercise);	
	        
	        Intent intent = getIntent();
	        choosedComplexName =intent.getStringExtra("nameChoosedCompex");
	    	 choosedComplexId = intent.getStringExtra("nameChoosedId");
	        
	        Log.d("vh_tag", "Choosed complex:" + choosedComplexName);
	        Log.d("vh_tag", choosedComplexId);
	        
	        
	 }
	 
	 
	 @Override 
	    protected void onActivityResult(int requestCode, int resultCode, Intent data)
	    {
	    	// choosedComplexName = data.getStringExtra("nameChoosedCompex");
	    	 //choosedComplexId = data.getStringExtra("nameChoosedId");
	    	 
	    	
	    }  
	 
	 
	 
}
