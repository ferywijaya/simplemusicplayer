package com.app.liviu.simpleMusciPlayer.playlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;

public class CustomPlaylist implements BasePlaylist
{
	private String 			   TAG = "CustomPlaylist";
	private String 			   namePlaylist = "unknown";
	private ArrayList<Integer> songsList;
	private String  	       path;
	
	public CustomPlaylist(String playListName) 
	{
		songsList 	 = new ArrayList<Integer>();
		namePlaylist = playListName;
		path 		 = "//sdcard//simplePlayer//" + namePlaylist + ".pls"; 
		
	}
	
	public void savePlayList()
	{
		try
		{ 
		    FileWriter fstream = new FileWriter(path,false);
		    BufferedWriter out = new BufferedWriter(fstream);		    
		    
		    for(int i = 0; i < songsList.size(); i++)
		    	out.write(Integer.toString(songsList.get(i)) + "\n");
		    		    
		    out.close();
		    Log.e(TAG,"i wrote something in file " + songsList.size());
		    }catch (Exception e)
		    {
		    	
		      Log.e(TAG,"Error at write " + e.getMessage());
		    }
	}
	
	@Override
	public boolean loadPlaylist() 
	{			
		try 
		{
			FileInputStream file = new FileInputStream(path);		    
		    DataInputStream in   = new DataInputStream(file);
		    BufferedReader  br   = new BufferedReader(new InputStreamReader(in));		    
		    String strLine;
		    songsList = new ArrayList<Integer>();
		    
		    while ((strLine = br.readLine()) != null)   
		    {		    
		      Log.e(TAG,"i read from file " + strLine);
		      songsList.add(Integer.parseInt(strLine));
		    }
			
		} catch (FileNotFoundException e) 
		{
			Log.e(TAG,"error at opening file");
			e.printStackTrace();
			
		} catch (IOException e) 
		{
			Log.e(TAG,"error 1");
			e.printStackTrace();
		}
		finally
		{
			Log.e(TAG,"playlist " + namePlaylist + " have " + songsList.size() + " songs!");
		}
		

		return false;
	}

	@Override
	public boolean addSongToPlaylist(Song s) 
	{
		if(songsList.contains(s.getId()))
		{
			Log.e(TAG,"THE playlist " + namePlaylist + "contain the song " + s.toString());
			return false;
		}
		else
		{
			songsList.add(s.getId());
			Log.e(TAG,"Song "  + s.getId() + s.getTitle() + "was added successfully!");
			return true;	
		}		
	}

	
	@Override
	public boolean deleteSongFromPlaylist(Song s) 
	{
		if(!songsList.contains(s.getId()))
		{
			Log.e(TAG, "The song "  + s.getId() + s.getTitle() +  " do not exist in playlist");
			return false;
		}
		else
		{
			for(int i = 0; i < songsList.size(); i++)			
				if(songsList.get(i) == s.getId())
					{
						songsList.remove(i);
						Log.e(TAG,"The song " + s.getId() + s.getTitle() + "was deleted! " + songsList.size());					
						return true;
					}
		}
		
		return false;
	}

	@Override
	public int getFirstSongFromPlaylist() 
	{		
		Log.e(TAG,"First Song have id " + songsList.get(0));
		return songsList.get(0);
	}

	@Override
	public int getLastSongFromPlaylist() 
	{
		Log.e(TAG,"Last Song have id " + songsList.get(songsList.size() -1));
		return songsList.get(songsList.size() -1);
	}

	//i return -1 if not exists next song
	@Override
	public int getNextSongFromPlaylist(int index) 
	{	
		if((index + 1) < songsList.size() )
		{
			Log.e(TAG,"send next song at position " + (index + 1 ) + " with id " + songsList.get(index+1) );
			return songsList.get(index + 1);	
		}
		else
		{
			Log.e(TAG,"This is the last song!");
			return -1;
		}		
	}
	
	//i returned -1 if no previous
	@Override
	public int getPreviousSongFromPlaylist(int index) 
	{
		if((index - 1) < 0)
		{
			Log.e(TAG,"NO previous");
			return -1;
		}
		else
		{
			Log.e(TAG,"send previous song at position " + (index + 1 ) + " with id " + songsList.get(index+1) );
			return songsList.get(index - 1);
		}		
	}

	@Override
	public int getSongFromPlaylist(int index) 
	{
		if(index < songsList.size() && index >= 0)
		{
			Log.e(TAG,"its ok..we have the song,it exists in playlist.we sending it now " + songsList.get(index));
			return songsList.get(index);			
		}
		else
		{
			Log.e(TAG,"Error! the index is to big/small!in getSongFromPlaylist");
			return -1;
		}
	}

	@Override
	public boolean rateSongInPlaylist(Song s, int rate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean renameSongInPlaylist(Song s) {
		// TODO Auto-generated method stub
		return false;
	}
}
