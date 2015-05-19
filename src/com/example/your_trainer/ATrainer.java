package com.example.your_trainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ATrainer extends Activity implements OnClickListener{
	private Button btnChooseComplex;
	Button btnStart;
	Button btnGoSet;
	Button btnAddCopmplex;

	final String LOG_TAG = "vh_tag";
	TextView tvChoosedName;

	String choosedComplexName = null;
	String choosedComplexId = null;
	String choosedComplexFolder = null;


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

        btnStart.setEnabled(false);

        btnGoSet = (Button)findViewById(R.id.btnGo2Setting);
        btnGoSet.setOnClickListener(this);

        btnAddCopmplex = (Button)findViewById(R.id.btnAddComplex);
        btnAddCopmplex.setOnClickListener(this);


       // btnStart.setActivated(false);

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

	    	case R.id.btnGo2Setting:
	    		goToSetting();
	    		break;

	    	case R.id.btnAddComplex:
	    		goToAddComplex();
	    		break;

	    	default: break;

    	}

    }

    private void goToAddComplex()
    {


    	Intent intent1 = new Intent(this, AAddCompl.class) ;
    	startActivity(intent1);

	}

	private void startExercice()
    {
    	Intent intent1 = new Intent(this, AExercise.class) ;
    	intent1.putExtra("nameChoosedCompex", choosedComplexName);
    	intent1.putExtra("nameChoosedId", choosedComplexId);
    	intent1.putExtra("nameChoosedFolder", choosedComplexFolder);

    	startActivity(intent1);

	}

	private void startComplexChoose()
    {

    	// explicit call
    	Intent intent = new Intent(this, AComplexList.class) ;
    	startActivityForResult(intent, 13);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    	if(resultCode != RESULT_OK)
    		return;

    	else
    		 btnStart.setEnabled(true);


    	choosedComplexName = data.getStringExtra("extra_complexName");
    	 choosedComplexId = data.getStringExtra("extra_complexId");
    	 choosedComplexFolder = data.getStringExtra("extra_complexFolder");

    	 tvChoosedName.setText("Choosed complex is " +  choosedComplexName + " ID: " + choosedComplexId);

    }

   public void goToSetting()
    {
	   Intent intent = new Intent(this, ASettings.class) ;
       startActivity(intent);
    }

    private void showToast(String str)
    {
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }






}
