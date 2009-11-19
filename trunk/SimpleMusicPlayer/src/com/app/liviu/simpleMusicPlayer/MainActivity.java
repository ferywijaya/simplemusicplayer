package com.app.liviu.simpleMusicPlayer;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.app.liviu.simpleMusciPlayer.scan.ScanManager;
import com.app.liviu.simpleMusicPlayer.Util.Constants;
import com.app.liviu.simpleMusicPlayer.gui.GUIManager;

public class MainActivity extends Activity 
{
		
	private final String      		 TAG = "MainActivity";	
	private ScanManager              scanManager;	
	public  static SharedPreferences settings;
	private boolean			  		 scanAgain;
	private boolean			  		 runFirstTime;
	private GUIManager 				 guiManager;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        //make the activity full screen
		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		                      WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
        setContentView(R.layout.main);        
        
        guiManager = new GUIManager(this);
        setContentView(guiManager.getMainLayout());
        
        //create instances
        scanManager = ScanManager.getInstance(this);  
        settings    = getSharedPreferences(Constants.SETTINGS_PREFERENCES,0);
        
                
        // check if i have to do a new scan
        scanAgain    = settings.getBoolean("scanned", false);
        runFirstTime = settings.getBoolean("runFirstTime", true);
        
        //create directory for playlists
        if(runFirstTime)
        {
    		SharedPreferences.Editor editor = MainActivity.settings.edit();
    		editor.putBoolean("runFirstTime", false);
    		editor.commit();
    		
    		boolean success = (new File("//sdcard//simplePlayer")).mkdir();
    	    
    		if (success) 
    	    {
    	      Log.e(TAG,"Directory was created");
    	    }
    		else
    			Log.e(TAG,"Directory not created");
        }
        
        Log.e(TAG, " " + scanAgain);
        
        if(scanAgain == false)
        	scanManager.scanFiles();
        else
        	Log.e(TAG, "I don't have a reason to scan again");    
        
        //scanManager.testUpdate();	
    }
}