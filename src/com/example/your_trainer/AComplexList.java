package com.example.your_trainer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;


public class AComplexList extends Activity implements OnClickListener {
	
	final String LOG_TAG = "vh_tag";
	DBHelper dbhelper;
	EditText edTxt1;
	EditText edTxt2;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.acomplexlist);          
	        
	        edTxt1 = (EditText)findViewById(R.id.edText1);
	        edTxt2 = (EditText)findViewById(R.id.edText2);
	        dbhelper = new DBHelper(this); 
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
	    	case R.id.btnRead:
	    		
	    		;break;
	    	case R.id.btnClear: 
	    		
	    		break;
	    	default: 
	    		break;
	    	
    	}
		
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
		super(context, "complexList", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
