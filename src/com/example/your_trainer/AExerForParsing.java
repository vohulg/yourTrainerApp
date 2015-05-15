package com.example.your_trainer;

import java.util.ArrayList;

public class AExerForParsing
{
	/*
	private String nameExerc;
	private String Url_Picture;
	private String Description;
	private int timeInSec;
	*/


	private String nameCompl;
	private String folderName;

	private final ArrayList<String> listExerName;
	private final ArrayList<String> listExerDescr;
	private final ArrayList<String> listExerTimeInSec;

	public AExerForParsing()
	{
		listExerName = new ArrayList<String>();
		listExerDescr = new ArrayList<String>();
		listExerTimeInSec = new ArrayList<String>();

	}

	/*
	public String getName(){return this.nameExerc;}
	public String getUrl(){return this.Url_Picture;}
	public String getDescr(){return this.Description;}
	public int getTime(){return this.timeInSec;}

	public void setName(String name){this.nameExerc = name;}

	public void setDescr(String descr){this.Description = descr;}
	public void setTime(int time){this.timeInSec = time;}

	*/
	public void setCompl(String nameCompl){this.nameCompl = nameCompl;}

	public void setFolder(String folderName){this.folderName = folderName;}

	public void addExerNameToList(String name)
	{
		listExerName.add(name);

	}

	public void addExerDescrToList(String descr)
	{
		listExerDescr.add(descr);

	}

	public void addExerTimeToList(String time)
	{
		listExerTimeInSec.add(time);

	}

	public String getComplName() {

		return nameCompl;

	}

	public String getFolderName() {

		return folderName;

	}

	public ArrayList<String> getlistExerNamet() {

		return listExerName;

	}

	public ArrayList<String> getlistExerDescr() {

		return listExerName;

	}

	public ArrayList<String> getlistExerTimeInSec() {

		return listExerTimeInSec;

	}

	public int getCountOfExer()
	{
		return listExerName.size();

	}

}

