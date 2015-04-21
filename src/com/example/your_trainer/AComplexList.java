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
	        
	        dbhelper = new DBHelper(this); 
	        
	        btnAdd.setOnClickListener(this);
	        btnShow.setOnClickListener(this);
	        btnClear.setOnClickListener(this);
	    }

	@Override
	public void onClick(View v) {
		
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		
		String name = edTxt1.getText().toString();
		String email = edTxt1.getText().toString();
		
		switch(v.getId())
    	{    	   		
	    	case R.id.btnAdd: 
	    		Log.d(LOG_TAG, "---Insert to db-----");
	    		cv.put("name", name);
	    		cv.put("email", email);
	    		
	    		long rowId = db.insert("tbComplexList", null, cv);
	    		Log.d(LOG_TAG, "---Inserted to table rowid-----" + rowId);	
	    		
	    		
	    		break;
	    	case R.id.btnRead: // чтение из таблицы результата запроса 
	    		Cursor c =  db.query("tbComplexList", null, null, null, null, null, null);
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
	    		
	    		db.delete("tbComplexList", null, null);
	    		
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

	public DBHelper(Context context) {
		super(context, "dbComplexList", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("vh", "----Create data base---");
		db.execSQL("create table tbComplexList ("
				+ "id integer primary key autoincrement,"
				+ "name text,"
				+"email text" + ");");
				
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
