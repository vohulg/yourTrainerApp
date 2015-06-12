package com.example.your_trainer;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class ATrainer extends Activity implements OnClickListener{
	private Button btnChooseComplex;
	ImageButton btnStart;
	ImageButton btnGoSet;
	ImageButton btnAddCopmplex;

	final String LOG_TAG = "vh_tag";
	TextView tvChoosedName;

	String choosedComplexName = null;
	String choosedComplexId = null;
	String choosedComplexFolder = null;

	final int DialogAdapter = 2;
	DBHelper dbhelper;
	SQLiteDatabase dataBase;

	List<AComplexContainer> listComplex = null;

	AAdapter  adapter;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrainer);

        dbhelper = new DBHelper(this, "dbTrainer");
        dataBase = dbhelper.getWritableDatabase();


       // tvChoosedName = (TextView)findViewById(R.id.tvChoosedComplex);

        // final android.app.ActionBar bar = getActionBar();
        //bar.hide();

        //android.app.ActionBar actBar = getActionBar();
        //actBar.hide();


        btnStart = (ImageButton)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

       // btnStart.setEnabled(false);

        btnGoSet = (ImageButton)findViewById(R.id.btnGo2Setting);
        btnGoSet.setOnClickListener(this);

        btnAddCopmplex = (ImageButton)findViewById(R.id.btnAddComplex);
        btnAddCopmplex.setOnClickListener(this);

       // btnStart.setActivated(false);
       // startComplexChoose();

    }

    @Override
    public void onClick(View v)
    {
    	switch(v.getId())
    	{

	    	case R.id.btnStart:
	    		startComplexChoose();
	    		//startExercice();
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
		showDialog(DialogAdapter);// call dialog with return result
		// explicit call
    	//Intent intent = new Intent(this, AComplexList.class) ;
    	//startActivityForResult(intent, 13);

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
		adapter = new  AAdapter(this, listComplex);


		//final AAdapter  adapter = new  AAdapter(this, listComplex);
		adb.setAdapter(adapter, complexChooseClickListener);

		adb.setTitle("Choose complex");

		//adb.setCancelable(false);


		adb.setPositiveButton("Ok",new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				which = adapter.getSelectedPos();
				//adapter.setSelectedPos(which);
			 	choosedComplexName = listComplex.get(which).getName();
		    	 choosedComplexId = listComplex.get(which).getId();
		    	 choosedComplexFolder = listComplex.get(which).getFolder();
		    	 startExercice();

				//int pos = adapter.getSelectedPos();

			}


		});

		adb.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				//int pos = adapter.getSelectedPos();

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
			 	adapter.setSelectedPos(which);
			 	choosedComplexName = listComplex.get(which).getName();
		    	 choosedComplexId = listComplex.get(which).getId();
		    	 choosedComplexFolder = listComplex.get(which).getFolder();
		    	// tvChoosedName.setText("Choosed complex is " +  choosedComplexName + " ID: " + choosedComplexId);
		    	 startExercice();

		    }

     };




/*
	private List<AComplexContainer> getComplexName() throws IOException
	{

		List<AComplexContainer> listOfComplex =  new ArrayList<AComplexContainer>();

		String column[] = {"id", "comp_name", "comp_folder"};

		Cursor c =  dataBase.query("tbComplexes", column, null, null, null, null, null);
		int rows = c.getCount();

		if (rows == 0)
		{
			ALog.writeLog("no rows in Database");
			String[] noRow = {"norows"};
			return listOfComplex;
		}

		int ColName = c.getColumnIndex("comp_name");
		int id = c.getColumnIndex("id");
		int ColFolder = c.getColumnIndex("comp_folder");
		String[] arrayNameComplex = new String[rows];

		// fill list of complexes
		c.moveToFirst();
		for (int i =0; i < rows; i++)
		{

			String name =  c.getString(ColName);
			String folder =  c.getString(ColFolder);
			String idCom = String.valueOf(c.getInt(id));

			AComplexContainer obj = new AComplexContainer(name, idCom, folder);
			listOfComplex.add(obj);

			//arrayNameComplex[i] =  c.getString(ColName);
			c.moveToNext();

		}

		c.close();
		return listOfComplex;

	}

*/

/*
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

    */

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
