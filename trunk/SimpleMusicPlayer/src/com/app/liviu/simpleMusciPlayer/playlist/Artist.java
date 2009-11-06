package com.app.liviu.simpleMusciPlayer.playlist;

public class Artist 
{
	private String name;
	private int id;
	
	public Artist(String name_,int id_) 
	{
		name = name_;
		id 	 = id_;
		
	
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
}
