package com.example.your_trainer;


import java.util.ArrayList;
import java.util.List;


import android.R.bool;
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
	 List<AComplexContainer> listOfComplex;
	 AAdapter adapter;
	
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
	       // ArrComplName = getComplexName();
	        listOfComplex = new ArrayList<AComplexContainer>();
	        getComplexName();
	        
	       adapter = new  AAdapter(this,  listOfComplex);
	        
	        
	       // 2.1 close database
	        dbhelper.close();
	        dataBase.close();
	        
	        
	        // 3. Show List View
	        lvMain = (ListView)findViewById(R.id.lvComplexes);
	       // lvMain.setClickable(true);
	       // lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        
	        lvMain.setAdapter(adapter);
	        
	        
	        
	        
	        
	       // lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        	        
	       // ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
	        //		this, android.R.layout.simple_list_item_single_choice, ArrComplName );
	       
	      //  ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
	        	//	this, android.R.layout.simple_list_item_single_choice, ArrComplName );
	       
	        
	        
	       // lvMain.setAdapter(adapter); 
	        
	        
	      
	    }

       
	private  boolean getComplexName()
	{	
		String column[] = {"id", "comp_name"};
		
		Cursor c =  dataBase.query("tbComplexes", column, null, null, null, null, null);
		int rows = c.getCount();
		
		if (rows == 0)
		{
			Log.d(LOG_TAG, "0 rows");
			String[] noRow = {"norows"};
			return false;
		}
		
		int ColName = c.getColumnIndex("comp_name");
		int id = c.getColumnIndex("id");
		String[] arrayNameComplex = new String[rows];
		
		// fill list of complexes
		c.moveToFirst();
		for (int i =0; i < rows; i++)
		{
			
			String name =  c.getString(ColName);
			String idCom = String.valueOf(c.getInt(id));
			
			AComplexContainer obj = new AComplexContainer(name, idCom);
			listOfComplex.add(obj);
			
			//arrayNameComplex[i] =  c.getString(ColName);
			c.moveToNext();
			
		}
		
		c.close();
		
		
				
		return true;
		
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
		
		int selectedPos = adapter.getSelectedItem();
		
		String nameRet = listOfComplex.get(selectedPos).getName();
		String idRet = listOfComplex.get(selectedPos).getId();
		
		
		
		//String choosedComplex =  ArrComplName[lvMain.getCheckedItemPosition()];
		Intent intent = new Intent();
		//intent.putExtra("extra_complexName", choosedComplex);
		//intent.putExtra("extra_complexName_id", "44");
		setResult(RESULT_OK, intent);
		intent.putExtra("extra_complexName",nameRet);
		intent.putExtra("extra_complexId",idRet);
		finish();
		
		
	}

	

}

