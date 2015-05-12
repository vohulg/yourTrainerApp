package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ASettings extends Activity
{
	
	String choosedComplexName = null;
	String choosedComplexId = null;
	TextView tvChoosedName;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.asetting);
	        
	        tvChoosedName = (TextView)findViewById(R.id.tvStatus);
	        
	 }
	
	public void chooseComplex(View view)
	{
		Intent intent = new Intent(this, AComplexList.class) ;
    	startActivityForResult(intent, 14); 
		
	}
	
	private void showToast(String str)
    {    	
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();  	
    }
	
	@Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	
    	if(resultCode != RESULT_OK)
    		return;    	
    	
    	choosedComplexName = data.getStringExtra("extra_complexName");
    	choosedComplexId = data.getStringExtra("extra_complexId");  
   	    tvChoosedName.setText("Choosed complex is " +  choosedComplexName + " ID: " + choosedComplexId);    	
   	    
   	    showSettingItems();
   	
    }

	private void showSettingItems() 
	{
		//1. set timeout for each exercise
		//2. set timeout for all exercise
		//3. set melody for ending exercises list
		//4. set melody of ending complex
		
		TextView tvDefaultTimeOut = new TextView(this);
		//tvDefaultTimeOut.setText(text)
		
	}  
	

}
