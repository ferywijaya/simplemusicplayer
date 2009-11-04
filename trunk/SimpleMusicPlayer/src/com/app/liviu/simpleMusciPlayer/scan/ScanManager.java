package com.app.liviu.simpleMusciPlayer.scan;

import java.io.File;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;


public class ScanManager 
{
	
	private static final String TAG = "PICTURE_SCANNING";
	private static ScanManager instance = null;
	private ArrayList<File> filesList;
	
	private ScanManager()
	{
		filesList = new ArrayList<File>();
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
		filesList = new ArrayList<File>();
		File f = Environment.getExternalStorageDirectory();
		scan(f);
		Log.e(TAG,"scan finnished with " + filesList.size() + " music files");
	}
	
	private void scan(File f)
	{		
        if ( !f.exists() ) 
        	return;         
                        
        if(f.getAbsolutePath().contains(".mp3") || f.getAbsolutePath().contains(".aac") || f.getAbsolutePath().contains(".wma") || f.getAbsolutePath().contains(".wav"))
        {
        	filesList.add(f);
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
	
	public ArrayList<File> getFilesList()
	{
		return filesList;
	}
}	
