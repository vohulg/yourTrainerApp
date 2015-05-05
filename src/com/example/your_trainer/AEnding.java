package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AEnding extends Activity implements OnClickListener 
{
	Button btnBack;
	
	@Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.a_end);
	        
	        btnBack = (Button)findViewById(R.id.btnBackToChooseCompl);
	        btnBack.setOnClickListener(this);
	        
	 }

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.btnBackToChooseCompl : 
			Intent intent = new Intent(this, ATrainer.class); // call activity  
			startActivity(intent);
			break;
		
		
		}
		
	}
	
	
}
