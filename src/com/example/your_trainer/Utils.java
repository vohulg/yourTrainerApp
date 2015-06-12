package com.example.your_trainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Utils
{

	public static List<AComplexContainer> getComplexName( SQLiteDatabase dataBase) throws IOException
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

}
