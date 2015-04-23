package com.example.your_trainer;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



public class AComplexList extends Activity implements OnClickListener {
	
	final String LOG_TAG = "vh_tag";
	DBHelper dbhelper;
	SQLiteDatabase dataBase;	
	 ListView lvMain;
	 Button btnBack;
	 String ArrComplName[];
	
       @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.acomplexlist);	
	        
	        //0. Set event for button
	        btnBack = (Button)findViewById(R.id.btnBack);
	        btnBack.setOnClickListener(this);
	       
	        // 1. create Database if not created ealier
	        this.deleteDatabase("dbTrainer"); // for debug
	        dbhelper = new DBHelper(this, "dbTrainer"); 	        
	        dataBase = dbhelper.getWritableDatabase();
	        
	        // 2. Fill array of name complexes from database	        
	        ArrComplName = getComplexName();
	        
	       // 2.1 close database
	        dbhelper.close();
	        dataBase.close();
	        
	        
	        // 3. Show List View
	        lvMain = (ListView)findViewById(R.id.lvComplexes);
	        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        	        
	        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
	        		this, android.R.layout.simple_list_item_single_choice, ArrComplName );
	        lvMain.setAdapter(adapter); 
	        
	        
	      
	    }

       
	private  String[] getComplexName()
	{	
		String column[] = {"id", "comp_name"};
		
		Cursor c =  dataBase.query("tbComplexes", column, null, null, null, null, null);
		int rows = c.getCount();
		
		if (rows == 0)
		{
			Log.d(LOG_TAG, "0 rows");
			String[] noRow = {"norows"};
			Log.d(LOG_TAG, "0 rows");	
			return noRow;
		}
		
		int ColName = c.getColumnIndex("comp_name");
		int id = c.getColumnIndex("id");
		String[] arrayNameComplex = new String[rows];
		
		c.moveToFirst();
		
		for (int i =0; i < rows; i++)
		{
			arrayNameComplex[i] =  c.getString(ColName);
			c.moveToNext();
			
		}
		
		c.close();
		
		
				
		return arrayNameComplex;
		
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
		  case R.id.btnBack : 			
			  sendComplexName(); // send choosed complex name to main activity 
			  break;
		
		}
		
		
		
	}


	private void sendComplexName()
	{
		 // Log.d(LOG_TAG, "Choosed " + ArrComplName[lvMain.getCheckedItemPosition()]);
		
		String choosedComplex =  ArrComplName[lvMain.getCheckedItemPosition()];
		Intent intent = new Intent();
		intent.putExtra("extra_complexName", choosedComplex);
		intent.putExtra("extra_complexName_id", "44");
		setResult(RESULT_OK, intent);
		finish();
		
		
	}

	

}

class DBHelper extends SQLiteOpenHelper
{
	final String LOG_TAG = "vh_tag";
	private String DBName;
	final private String tbComplex = "tbComplexes";
	final private String tbExercise = "tbExercises";
	
	public DBHelper(Context context, String dbName) {
		super(context, dbName, null, 1);
		DBName = dbName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(LOG_TAG, "----Create data base---" + DBName);
		
		
		Log.d(LOG_TAG, "----Create table---tbComplexes");
		db.execSQL("create table tbComplexes ("
				+ "id integer primary key autoincrement,"
				+ "comp_name text,"
				+ "comp_custom_music text,"
				+ "comp_totaltime text,"
				+ "comp_repeat integer,"
				+"comp_default_music text" + ");");
		
		Log.d(LOG_TAG, "----Create table---tbExercises");
		db.execSQL("create table tbExercises ("
				+ "id integer primary key autoincrement,"
				+ "exes_name text,"
				+ "exes_complid integer,"
				+ "exes_descr text,"
				+ "exes_photourl text,"
				+ "exes_timeinsec integer,"
				+ "exes_order integer,"
				+"exes_istabata integer" + ");");
				
				
		
		fillDB(db);
		Log.d(LOG_TAG, "----Tables filled with data");
		
	}

	private void fillDB(SQLiteDatabase db)
	{
		db.execSQL("insert into tbComplexes ('comp_name') values ('pushup')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('jumping')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('relax')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('kidExercis')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('bodyBilding')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('arrrrrrr')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('pushup1')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('jumping1')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('relax1')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('kidExercis1')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('bodyBilding1')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('arrrrrrr1')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('pushup2')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('jumping2')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('relax2')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('kidExercis2')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('bodyBilding2')");
		
		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
				                     "values( 'jumping1', '1', 'from place jump and spring', '/jump.gif', '30', '1',  '0' )");
		
		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping1', '1', 'from place jump and spring', '/jump1.gif', '30', '1',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping2', '1', 'from place jump and spring', '/jump2.gif', '30', '2',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping3', '1', 'from place jump and spring', '/jump3.gif', '30', '3',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping4', '1', 'from place jump and spring', '/jump4.gif', '30', '4',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping5', '1', 'from place jump and spring', '/jump5.gif', '30', '5',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping6', '1', 'from place jump and spring', '/jump6.gif', '30', '6',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping7', '1', 'from place jump and spring', '/jump7.gif', '30', '7',  '0' )");

		
		/*
		db.execSQL("insert into tbExercises ('exes_name', " +
				"'exes_complid'," +
				" 'exes_descr', " +
				"'exes_photourl'" +
				"'exes_timeinsec'" +
				"'exes_order'" +
				"'exes_istabata'" +
				")" +
				" values ('pushup', '' ");
				*/

		/*
		 * + "id integer primary key autoincrement,"
				+ "exes_name text,"
				+ "exes_complid integer,"
				+ "exes_descr text,"
				+ "exes_photourl text,"
				+ "exes_timeinsec integer,"
				+ "exes_order integer,"
				+"comp_istabata integer
		 * 
		 * */
		//db.execSQL("insert into tbComplexList ('name') values ('konstantin')");

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
