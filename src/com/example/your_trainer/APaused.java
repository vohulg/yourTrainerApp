package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class APaused extends Activity implements OnClickListener
{
	private  Button btnPrev;
	private Button btnNext;
	private Button btnContinue;
	private int currentIndex = 0;
	private int choosedIndex = 0;
	private int maxCountOfExer = 0;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.apaused);
		 
		 btnPrev = (Button)findViewById(R.id.btnPrev);
		 btnNext = (Button)findViewById(R.id.btnNext);
		 btnContinue = (Button)findViewById(R.id.btnContinue);
		 
		 btnPrev.setOnClickListener(this);
		 btnNext.setOnClickListener(this);
		 btnContinue.setOnClickListener(this);
		 
		 Intent intent = getIntent();	
		 
		 int defaultVal = 0;
		 currentIndex = intent.getIntExtra("currentExerIndex",  defaultVal);
		// String ret = intent.getStringExtra("currentExerIndex");
		// currentIndex = Integer.parseInt(ret);	 
		 
		 maxCountOfExer =  intent.getIntExtra("maxCountOfExer",  defaultVal);
			 
		 
	 }
	
	
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.btnPrev: 
				sendChoosedExerIndex(--currentIndex); 
				break;
			case R.id.btnNext: 
				sendChoosedExerIndex(++currentIndex); 
				break;
			case R.id.btnContinue:
				sendChoosedExerIndex(currentIndex); 
				break;
			
			default: break;
			
		
		}
		
	}
	
	void sendChoosedExerIndex(int choosedIndex)
	{
		//1. Check if choosedIndex more of maxIndexExer or less
		
		if(choosedIndex < 0)
			choosedIndex = 0;
		
		if(choosedIndex > (maxCountOfExer-1))
			choosedIndex = 0;
		
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		intent.putExtra("choosedIndex", choosedIndex);		
		finish();
		
		
	}
	
	
	

}
