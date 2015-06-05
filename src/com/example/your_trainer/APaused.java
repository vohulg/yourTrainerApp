package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class APaused extends Activity implements OnClickListener
{

	private static final int STARTINDEX = 0;
	private  ImageButton btnPrev;
	private ImageButton btnNext;
	private ImageButton btnContinue;
	private int currentIndex = 0;
	private final int choosedIndex = 0;
	private int maxCountOfExer = 0;
	ImageButton imgStartAgain;


	@Override
    protected void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.apaused);

		 imgStartAgain = (ImageButton)findViewById(R.id.ibtnStartAgain);
		 imgStartAgain.setOnClickListener(this);

		 btnPrev = (ImageButton)findViewById(R.id.btnPrev);
		 btnNext = (ImageButton)findViewById(R.id.btnNext);
		 btnContinue = (ImageButton)findViewById(R.id.btnContinue);

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

			case R.id.ibtnStartAgain:
				sendChoosedExerIndex(STARTINDEX);
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
