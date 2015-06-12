package com.example.your_trainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ASettings extends Activity implements OnClickListener
{

	SetTimeAdapter adapter;
	DBHelper dbhelper;
	SQLiteDatabase dataBase;

	String choosedComplexName = null;
	String choosedComplexId = null;
	//TextView tvChoosedName;
	ListView lvMain;
	private ImageButton btnComplete ;
	private List<AExercisContent> ListOfExer;

	final int DialogAdapter = 2;
	List<AComplexContainer> listComplex = null;
	AAdapter  adapterComplex;



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.asetting);

	       // tvChoosedName = (TextView)findViewById(R.id.tvStatus);
	        btnComplete = (ImageButton)findViewById(R.id.ibtnComplete);
	        btnComplete.setOnClickListener(this);

	        dbhelper = new DBHelper(this, "dbTrainer");
		    dataBase = dbhelper.getWritableDatabase();






	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onStart()
	{
		 // show dialog
		showDialog(DialogAdapter);



		super.onStart();

	}

	@Override
	protected Dialog onCreateDialog(int id)
	{

		try {

			listComplex = Utils.getComplexName(dataBase);

		} catch (IOException e) {
			e.printStackTrace();
		}

		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adapterComplex = new  AAdapter(this, listComplex);


		//final AAdapter  adapter = new  AAdapter(this, listComplex);
		adb.setAdapter(adapterComplex, complexChooseClickListener);

		adb.setTitle("Choose complex");

		adb.setPositiveButton("Ok",new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				which = adapterComplex.getSelectedPos();
				choosedComplexName = listComplex.get(which).getName();
		    	choosedComplexId = listComplex.get(which).getId();
		    	 showSettingItems();

			}


		});

		adb.setNegativeButton("Cancel",new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{

			}

		});

		return adb.create();

	}

	// process click in item list
		 DialogInterface.OnClickListener complexChooseClickListener = new DialogInterface.OnClickListener()
		 {

			 @Override
			public void onClick(DialogInterface dialog, int which)
			    {
				 adapterComplex.setSelectedPos(which);
				 choosedComplexName = listComplex.get(which).getName();
			     choosedComplexId = listComplex.get(which).getId();
			     showSettingItems();

			    }

	     };

/*
	public void chooseComplex(View view)
	{
		Intent intent = new Intent(this, AComplexList.class) ;
    	startActivityForResult(intent, 14);

	}
*/
	private void showToast(String str)
    {
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
/*
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    	if(resultCode != RESULT_OK)
    		return;

    	choosedComplexName = data.getStringExtra("extra_complexName");
    	choosedComplexId = data.getStringExtra("extra_complexId");
   	   // tvChoosedName.setText("Choosed complex is " +  choosedComplexName + " ID: " + choosedComplexId);

   	    showSettingItems();

    }
*/
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
		ListOfExer =  fill_List(choosedComplexId);

		//2. Show list of exersises and time edit for each exersise
		 adapter = new SetTimeAdapter(this, ListOfExer);
		 lvMain = (ListView)findViewById(R.id.lvExers);
	     lvMain.setAdapter(adapter);


		//3. send new data to database


	}


	 private List<AExercisContent> fill_List(String ComplexId)
	 {

		// dbhelper = new DBHelper(this, "dbTrainer");
	    // dataBase = dbhelper.getReadableDatabase();
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
		  // dbhelper.close();
		  // dataBase.close();
			return listOfExer;
	}

	@Override
	public void onClick(View v)
	{
	   switch(v.getId())
	   {
	   case R.id.ibtnComplete:
		   updateTime(ListOfExer, dataBase);
		   break;

	   }

	}

	private void updateTime(List<AExercisContent> listOfExers, SQLiteDatabase db)
	{
		//dbhelper = new DBHelper(this, "dbTrainer");
	    //dataBase = dbhelper.getWritableDatabase();
	    dbhelper.updateTime(listOfExers, db);
	    showToast("Time updated");



	}




}
