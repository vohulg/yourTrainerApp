package com.example.your_trainer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class AComplexList extends Activity implements OnClickListener {
	
	final String LOG_TAG = "vh_tag";
	DBHelper dbhelper;
	SQLiteDatabase dataBase;
	EditText edTxt1;
	EditText edTxt2;
	Button btnAdd;
	Button btnShow;
	Button btnClear;
	
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.acomplexlist);          
	        
	        edTxt1 = (EditText)findViewById(R.id.edText1);
	        edTxt2 = (EditText)findViewById(R.id.edText2);
	        btnAdd =  (Button)findViewById(R.id.btnAdd);
	        btnShow =  (Button)findViewById(R.id.btnRead);
	        btnClear =  (Button)findViewById(R.id.btnClear);
	        
	        this.deleteDatabase("dbComplexList");
	       // this.deleteDatabase("complexList");
	        
	        dbhelper = new DBHelper(this, "dbComplexListNew"); 	        
	        SQLiteDatabase dataBase = dbhelper.getWritableDatabase();

	        
	        btnAdd.setOnClickListener(this);
	        btnShow.setOnClickListener(this);
	        btnClear.setOnClickListener(this);
	    }

	@Override
	public void onClick(View v) {
		
		//dbhelper = new DBHelper(this);
		ContentValues cv = new ContentValues();
		//SQLiteDatabase db = dbhelper.getWritableDatabase();
		
		String name = edTxt1.getText().toString();
		String email = edTxt1.getText().toString();
		
		String tableName = "tbComplexList";
		
		switch(v.getId())
    	{    	   		
	    	case R.id.btnAdd: 
	    		Log.d(LOG_TAG, "---Insert to db-----");
	    		cv.put("name", name);
	    		cv.put("email", email);
	    		
	    		long rowId = dataBase.insert(tableName, null, cv);
	    		Log.d(LOG_TAG, "---Inserted to table rowid-----" + rowId);	
	    		
	    		
	    		break;
	    	case R.id.btnRead: // чтение из таблицы результата запроса 
	    		Cursor c =  dataBase.query(tableName, null, null, null, null, null, null);
	    		if(c.moveToFirst())
		    		{
		    			int Colid = c.getColumnIndex("id");
		    			int ColName = c.getColumnIndex("name");
		    			int ColEmail = c.getColumnIndex("email");   	
		    			
		    			do  {
		    				String name1 = c.getString(ColName);
			    			String email1 = c.getString(ColEmail);
			    			Log.d(LOG_TAG, "Name =  " + name1 + "Email = " + email1 + "\n");
		    				
		    			} while(c.moveToNext());
		    		}
		    			
		    			else Log.d(LOG_TAG, "0 rows");		    			
		    			c.close();
	    		
	    		
	    		
	    		
	    		;break;
	    	case R.id.btnClear: 
	    		
	    		dataBase.delete(tableName, null, null);
	    		
	    		break;
	    	default: 
	    		break;
	    	
    	}
		
		dbhelper.close();
		
	}

	private void clearDb() {
		// TODO Auto-generated method stub
		
	}

	private void readFromDB() {
		// TODO Auto-generated method stub
		
	}

	private void saveToDB() {
		
	}

}

class DBHelper extends SQLiteOpenHelper
{

	final private String tbComplex = "tbComplexes";
	final private String tbExercise = "tbExercises";
	
	public DBHelper(Context context, String dbName) {
		super(context, dbName, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("vh", "----Create data base---");
		
		
		db.execSQL("create table tbComplexList ("
				+ "id integer primary key autoincrement,"
				+ "name text,"
				+"email text" + ");");
				
		
		/*
		db.execSQL("create table tbComplexes ("
				+ "id integer primary key autoincrement,"
				+ "comp_name text,"
				+ "comp_custom_music text,"
				+ "comp_totaltime text,"
				+ "comp_repeat integer,"
				+"comp_default_music text" + ");");
		
		db.execSQL("create table tbExercises ("
				+ "id integer primary key autoincrement,"
				+ "exes_name text,"
				+ "exes_complid integer,"
				+ "exes_descr text,"
				+ "exes_photourl text,"
				+ "exes_timeinsec integer,"
				+ "exes_order integer,"
				+"comp_istabata integer" + ");");
				
				*/
		
		fillDB(db);
		
	}

	private void fillDB(SQLiteDatabase db)
	{
		db.execSQL("insert into tbComplexes ('comp_name') values ('pushup')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('jumping')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('relax')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('kidExercis')");
		db.execSQL("insert into tbComplexes ('comp_name') values ('bodyBilding')");

		
		db.execSQL("insert into tbExercises ('exes_name', " +
				"'exec_complid'," +
				" 'exec_descr', " +
				"'exes_photourl'" +
				"'exes_timeinsec'" +
				"'exes_order'" +
				"'exes_istabata'" +
				")" +
				" values ('pushup')");

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
