package com.example.your_trainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper
{
	final String LOG_TAG = "vh_tag";
	private final String DBName;
	final private String tbComplex = "tbComplexes";
	final private String tbExercise = "tbExercises";

	public DBHelper(Context context, String dbName) {
		super(context, dbName, null, 1);
		DBName = dbName;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(LOG_TAG, "----Create data base---" + DBName);


		Log.d(LOG_TAG, "----Create table---tbComplexes");
		db.execSQL("create table tbComplexes ("
				+ "id integer primary key autoincrement,"
				+ "comp_name text,"
				+ "comp_folder text,"  // folder must be in /sdcard/train
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



		// fillDB(db);
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
                "values( 'прыжеки вверх', '1', 'from place jump and spring', '/jump1.gif', '5', '1',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'прыжеки вниз', '1', 'from place jump and spring', '/jump2.gif', '5', '2',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping3', '1', 'from place jump and spring', '/jump3.gif', '5', '3',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping4', '1', 'from place jump and spring', '/jump4.gif', '5', '4',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping5', '1', 'from place jump and spring', '/jump5.gif', '5', '5',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping6', '1', 'from place jump and spring', '/jump6.gif', '5', '6',  '0' )");

		db.execSQL("insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_photourl', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) " +
                "values( 'jumping7', '1', 'from place jump and spring', '/jump7.gif', '5', '7',  '0' )");


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

	public void insertData(List<AExerForParsing> list, SQLiteDatabase db) throws IOException {

		for (int i = 0; i < list.size(); i++) {

			//1. Write complex info
			AExerForParsing obj = list.get(i);
			String sql = null;
			sql = "insert into tbComplexes ('comp_name' , 'comp_folder') values ('"+ obj.getComplName() + "','" + obj.getFolderName() + "')";
			db.execSQL(sql);




			//2. Get id of this complex (last writed)
			int idCompl = getIdLastComplexId(db);

			if (idCompl == -1)
			{
				ALog.writeLog("new complex name not writed to database Error vh_35");
			}

			//3.Get arrays of exersises info
			 ArrayList<String> listExerName = obj.getlistExerNamet();
			 ArrayList<String> listExerDescr  = obj.getlistExerDescr();
			 ArrayList<String> listExerTimeInSec  = obj.getlistExerTimeInSec();

			//4. Write exersices info
			for (int j = 0; j < obj.getCountOfExer(); j++)
			{
					String exName = listExerName.get(j);
					String exDescr = listExerDescr.get(j);
					String exTime = listExerTimeInSec.get(j);
					String isTabata = "0";

					String sqlStr = "insert into tbExercises ('exes_name', 'exes_complid', 'exes_descr', 'exes_timeinsec', 'exes_order', 'exes_istabata' ) values('" + exName + "', '" + String.valueOf(idCompl) +"', '" + exDescr + "',  '" + exTime + "', '" + String.valueOf(j+1) +  "',  '" + isTabata + "')";
					db.execSQL(sqlStr);

			}

		}

	}

	private int getIdLastComplexId(SQLiteDatabase dataBase) throws IOException
	{
	     String column[] = {"id" };
		 String orderby = " id desc";

			Cursor c =  dataBase.query("tbComplexes", column, null, null, null, null, orderby);
			int rows = c.getCount();

			if (rows == 0)
			{
				ALog.writeLog("tbComplexes is empty Error vh_34");
				return -1;
			}

			// if row != 0
			int IdName = c.getColumnIndex("id");
			c.moveToFirst();
			int IdCompl =  c.getInt(IdName);
			return IdCompl;

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
