package com.example.your_trainer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ATrainer extends ActionBarActivity implements OnClickListener{
	private Button btnChooseComplex;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrainer);
        
        //android.app.ActionBar actBar = getActionBar();
        //actBar.hide();
        
        btnChooseComplex = (Button)findViewById(R.id.btChooseComplex);
        btnChooseComplex.setOnClickListener(this);
        
        
        
        
    }
    
    @Override
    public void onClick(View v)
    {
    	switch(v.getId())
    	{
    	case R.id.btChooseComplex:
    		Intent intent = new Intent(this, AComplexList.class) ;
    		startActivity(intent);
    		break;
    		
    	case R.id.btnAdd: break;
    	default: break;
    	
    	}
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.atrainer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
