package com.app.liviu.simpleMusciPlayer.playlist;

import java.util.ArrayList;

import android.util.Log;

/**
 * this class will generate automaticaly new ids for
 * every new song when it is scan for the first time.
 *  
 * @author Liviu
 *
 */

public class IdManager 
{
	private static IdManager instance = null;
	private final String TAG = "IdManager"; 
	private ArrayList<Integer> idList;
		
	private IdManager() 
	{
		Log.e(TAG,"IdManager constructor");
		idList = new ArrayList<Integer>();
	}
	
	public static IdManager getInstance()
	{
		if(instance == null)
		{
			instance = new IdManager();
		}
		
		return instance;		
	}
	
	public int generateNextId()
	{
		if(idList.isEmpty())
		{
			idList.add(0);			
			return 0;
		}
		else
		{
			idList.add(idList.get(idList.size()-1) + 1);			
			return idList.get(idList.size()-1);
		}
	}
	
	
}
