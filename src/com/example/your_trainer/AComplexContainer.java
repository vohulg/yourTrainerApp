package com.example.your_trainer;


public class AComplexContainer
{
	private String nameComplex;
	private String idComplex;
	
	
	public  AComplexContainer(String name, String id)
	{		
		super();
		this.nameComplex = name;
		this.idComplex = id;		
	};
	
	public String getName(){return this.nameComplex;}
	public String getId(){return this.idComplex;}
	
	
}
