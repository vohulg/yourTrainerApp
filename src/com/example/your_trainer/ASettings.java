package com.example.your_trainer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ASettings extends Activity
{

	DBHelper dbhelper;
	SQLiteDatabase dataBase;

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


		// Setting for each exercise
		//1. Get name, time and id of all exercises of complex
		List<AExercisContent> listOfExer =  fill_List(choosedComplexId);

		//2. Show list of exersises and time edit for each exersise



		//3. send new data to database


	}


	 private List<AExercisContent> fill_List(String ComplexId)
	 {

		 dbhelper = new DBHelper(this, "dbTrainer");
	     dataBase = dbhelper.getReadableDatabase();
	     List<AExercisContent> listOfExer = new ArrayList<AExercisContent>();

	    String column[] = {"id", "exes_name", "exes_descr", "exes_photourl", "exes_timeinsec", "exes_order", "exes_istabata" };
		String selection = "exes_complid =" + ComplexId;
		String orderby = "exes_order";

			Cursor c =  dataBase.query("tbExercises", column, selection, null, null, null, orderby);
			int rows = c.getCount();

			if (rows == 0)
				return null;

			int ExerId = c.getColumnIndex("id");
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
				int id = c.getInt(ExerId);

				AExercisContent obj = new AExercisContent(name, url, descr, Integer.parseInt(time), "" );
				obj.setId(id);
				listOfExer.add(obj);
				c.moveToNext();

			}

			c.close();
		   dbhelper.close();
		   dataBase.close();
			return listOfExer;
	}


}
