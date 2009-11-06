package com.app.liviu.simpleMusciPlayer.scan;

import java.io.File;
import java.util.ArrayList;

import com.app.liviu.simpleMusciPlayer.playlist.IdManager;
import com.app.liviu.simpleMusciPlayer.playlist.Song;

import android.app.Activity;
import android.os.Environment;
import android.text.InputFilter.LengthFilter;
import android.util.Log;


public class ScanManager 
{	
	private static final String TAG = "PICTURE_SCANNING";
	private static ScanManager instance = null;
	private ArrayList<Song> songList;	
	private IdManager idManager;
	
	private ScanManager()
	{
		songList = new ArrayList<Song>();		
		idManager = IdManager.getInstance();
	}
	
	public static ScanManager getInstance()
	{
			if(instance == null)
			{
				instance = new ScanManager();
			}
			
			return instance;
	}
	
	public void scanFiles()
	{		
		File f = Environment.getExternalStorageDirectory();
		
		scan(f);
		
		Log.e(TAG,"scan finnished with " + songList.size() + " music files");
	}
	
	private void scan(File f)
	{		
        if ( !f.exists() ) 
        	return;         
                        
        if(f.getAbsolutePath().contains(".mp3") || f.getAbsolutePath().contains(".aac") || f.getAbsolutePath().contains(".wma") || f.getAbsolutePath().contains(".wav"))
        {
        	Song tempSong = new Song(idManager.generateNextId());        
        	
        	if(f.getName().length() > 4)
        	{
        		tempSong.setTitle(f.getName().substring(0, f.getName().length()-4));
        		tempSong.setFilePath(f.getAbsolutePath());
        		tempSong.setRate(0);
        		tempSong.setPlayedTime(0);
        	}
        	else
        	{
            	tempSong.setTitle(f.getName());
        		tempSong.setFilePath(f.getAbsolutePath());
        		tempSong.setRate(0);
        		tempSong.setPlayedTime(0);
        	}
        		
        	songList.add(tempSong);
        }        
        
        if ( f.isDirectory()) 
        {
	        File[] files = f.listFiles();
	       
	        for( int i = 0 ; i < files.length; i++ ) 
	        {
	        	scan( files[i] );
	        }
        }
	}
	
	public ArrayList<Song> getFilesList()
	{
		return songList;
	}
}	