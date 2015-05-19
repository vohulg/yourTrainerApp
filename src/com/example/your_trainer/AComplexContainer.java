package com.example.your_trainer;


public class AComplexContainer
{
	private final String nameComplex;
	private final String idComplex;
	private final String Folder;



	public  AComplexContainer(String name, String id, String folder)
	{
		super();
		this.nameComplex = name;
		this.idComplex = id;
		this.Folder = folder;
	};

	public String getName(){return this.nameComplex;}
	public String getId(){return this.idComplex;}
	public String getFolder(){return this.Folder;}


}
