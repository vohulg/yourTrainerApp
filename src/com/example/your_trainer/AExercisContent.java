package com.example.your_trainer;

public class AExercisContent 
{
	
	private String nameExerc;
	private String Url_Picture;
	private String Description;
	private int timeInSec;
	
	
	public AExercisContent(String name, String url, String descr, int time)
	{
		this.nameExerc = name;
		this.Url_Picture = url;
		this.Description = descr;
		this.timeInSec = time;
		
	}
	
	public String getName(){return this.nameExerc;}
	public String getUrl(){return this.Url_Picture;}
	public String getDescr(){return this.Description;}
	public int getTime(){return this.timeInSec;}

}
