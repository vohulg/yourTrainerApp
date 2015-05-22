package com.example.your_trainer;

public class AExercisContent
{

	private final String nameExerc;
	private final String Url_Picture;
	private final String Description;
	private final String FolderPath;
	private int timeInSec;
	private int Id;


	public AExercisContent(String name, String url, String descr, int time, String folder)
	{
		this.nameExerc = name;
		this.Url_Picture = url;
		this.Description = descr;
		this.timeInSec = time;
		this.FolderPath = folder;

	}

	public void setId(int id)
	{
		this.Id = id;

	}

	public void setTime(int newTime)
	{
		this.timeInSec = newTime;

	}

	public int getId(){return this.Id;}

	public String getName(){return this.nameExerc;}
	public String getUrl(){return this.Url_Picture;}
	public String getDescr(){return this.Description;}
	public int getTime(){return this.timeInSec;}
	public String getFolderPath(){return this.FolderPath;}

}
