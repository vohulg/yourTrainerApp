package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ATrainer extends Activity implements OnClickListener{
	private Button btnChooseComplex;
	Button btnStart;
	
	final String LOG_TAG = "vh_tag";
	TextView tvChoosedName;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrainer);
        
         tvChoosedName = (TextView)findViewById(R.id.tvChoosedComplex);
         
        // final android.app.ActionBar bar = getActionBar();
        //bar.hide();
         
        //android.app.ActionBar actBar = getActionBar();
        //actBar.hide();
        
        btnChooseComplex = (Button)findViewById(R.id.btChooseComplex);
        btnChooseComplex.setOnClickListener(this);
        
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        
       // startComplexChoose();
        
        
        
        
    }
    
    @Override
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
	    	case R.id.btChooseComplex:
	    		startComplexChoose();
	    		break;
	    		
	    	case R.id.btnStart:
	    		startExercice();
	    		break;	    		
	    	
	    	default: break;
    	
    	}
    	
    }
    
    private void startExercice() 
    {
    	Intent intent1 = new Intent(this, AExercise.class) ;
    			startActivity(intent1);
		
	}

	private void startComplexChoose()
    {
    	// sample of unexplicit call
    	//Intent intent = new Intent(this, AComplexList.class) ;
		//startActivity(intent);
    	
    	// explicit call 
    	Intent intent = new Intent(this, AComplexList.class) ;
    	startActivityForResult(intent, 13); 	
    	
    }
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
		Log.d(LOG_TAG, "requestCode= " + String.valueOf(requestCode) + " resultCode = " + String.valueOf(resultCode) );

    	String choosedComplexName = data.getStringExtra("extra_complexName");
    	String choosedComplexName_id = data.getStringExtra("extra_complexName_id");
    	tvChoosedName.setText("Choosed complex is " +  choosedComplexName + " ID: " + choosedComplexName_id);    	
    	
    }  
    
    
    
    
    
    
}
