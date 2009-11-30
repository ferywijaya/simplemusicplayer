package com.app.liviu.simpleMusicPlayer;

import java.io.File;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.liviu.simpleMusciPlayer.musicPlayer.MusicPlayer;
import com.app.liviu.simpleMusciPlayer.playlist.PlaylistManager;
import com.app.liviu.simpleMusciPlayer.scan.ScanManager;
import com.app.liviu.simpleMusicPlayer.Util.Constants;
import com.app.liviu.simpleMusicPlayer.gui.CustomButton;
import com.app.liviu.simpleMusicPlayer.gui.CustomProgressBar;
import com.app.liviu.simpleMusicPlayer.gui.ItemsBar;
import com.app.liviu.simpleMusicPlayer.gui.PlayButton;

public class MainActivity extends Activity 
{
		
	private final String      			TAG = "MainActivity";	
	public  static SharedPreferences 	settings;
	private RelativeLayout 				mainLayout;
	private RelativeLayout 				bottomLayout;
	private RelativeLayout 				topLayout;
	private PlayButton 					playButton;	
	private CustomButton				prevButton;
	private CustomButton				nextButton;
	private ProgressBar					pBar;
	private ItemsBar					itemsBar;		
	private RelativeLayout.LayoutParams mainLayoutParams;
	private RelativeLayout.LayoutParams topLayoutParams;
	private RelativeLayout.LayoutParams bottomLayoutParams;
	private RelativeLayout.LayoutParams playButtonParams;
	private RelativeLayout.LayoutParams prevButtonParams;
	private RelativeLayout.LayoutParams nextButtonParams;
	private RelativeLayout.LayoutParams progressBarParams;	
	private RelativeLayout.LayoutParams itemsBarParams;
	private ScanManager 				scanManager;
	private boolean 					scanAgain;
	private boolean						runFirstTime;
	public  static Activity				mainActivityHandler;
	public  static MusicPlayer          musicPlayer;
	private Handler						updateProgressBarHandle;
	private int 						progressPosition;
	
	public static  PlaylistManager pManager;
		
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
         super.onCreate(savedInstanceState);
        
         //make the activity full screen
		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		                      WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
        setContentView(R.layout.main);        
        
        
        //create the layout
        bottomLayout 	   = new RelativeLayout(this);
		playButton         = new PlayButton(this);
		prevButton		   = new CustomButton(this,R.drawable.skipbackward);
		nextButton		   = new CustomButton(this,R.drawable.skipforward);
		pBar 			   = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
		mainLayout 		   = new RelativeLayout(this);		
		topLayout 		   = new RelativeLayout(this);		
		itemsBar		   = new ItemsBar(this);
		
		playButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		prevButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		bottomLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  80);
		progressBarParams  = new RelativeLayout.LayoutParams(144,13);
		mainLayoutParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);		
		topLayoutParams    = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 80);
		itemsBarParams 	   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 60);
		
		bottomLayoutParams.topMargin = 410;
		bottomLayout.setBackgroundResource(R.drawable.bottom);			

		playButtonParams.topMargin  = 7;
		playButtonParams.leftMargin = 42;
		
		prevButtonParams.topMargin  = 13;
		prevButtonParams.leftMargin = 5;
		
		nextButtonParams.topMargin  = 12;
		nextButtonParams.leftMargin = 89;
		
		progressBarParams.leftMargin = 160;
		progressBarParams.topMargin  = 27;	
		
		pBar.setBackgroundResource(android.R.drawable.progress_horizontal);		
		pBar.setMax(100);
		pBar.setProgress(50);
				
		//add views to the bottom bar
		bottomLayout.addView(prevButton,prevButtonParams);
		bottomLayout.addView(nextButton,nextButtonParams);
		bottomLayout.addView(playButton, playButtonParams);
		bottomLayout.addView(pBar,progressBarParams);        					
		
		mainLayout.setLayoutParams(mainLayoutParams);
		mainLayout.setBackgroundResource(R.drawable.main);	
							
		itemsBarParams.topMargin = 320;									
		
		mainLayout.addView(topLayout, topLayoutParams);
		mainLayout.addView(bottomLayout,bottomLayoutParams);		
		mainLayout.addView(itemsBar, itemsBarParams);
		setContentView(mainLayout);
		

        //create instances
		mainActivityHandler = this;
        scanManager = ScanManager.getInstance(this);  
        settings    = getSharedPreferences(Constants.SETTINGS_PREFERENCES,0);
        pManager = new PlaylistManager(this);
        musicPlayer = new MusicPlayer(this, pBar);
        progressPosition = 0;                       
                
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
        
        //main 

        //TODO check is the play list file exists
                
        if(pManager.loadPlaylist("customPlaylist") == Constants.ERROR_NO_PLAYLIST_FOUND)
        {
        	Toast.makeText(this, "Playlist not found!", Toast.LENGTH_SHORT);
        }                

        playButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{					
				if(!playButton.isClicked())
				{									
					musicPlayer.pause();
					playButton.setClicked(true);
				}
				else
				{
					if(!musicPlayer.isUpdated())
					{
						musicPlayer.play(pManager.getFirstSong());
					}
					else						
						musicPlayer.start();
					
					playButton.setClicked(false);
				}								
			}
		});
        
        musicPlayer.setOnCompletionListener(new OnCompletionListener()
        {		
			@Override
			public void onCompletion(MediaPlayer mp) 
			{
				Log.e(TAG,"play next song from playlist!");
//				musicPlayer.pause();
//				musicPlayer.stop();
//				musicPlayer.release();				
//				musicPlayer = new MusicPlayer(getBaseContext(), pBar);
//				musicPlayer.playNextSong(pManager.getNextSong());
//				playButton.setClicked(false); //pause image		
			}
		});
        
        
		
		prevButton.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				//Toast.makeText(context, "prev", Toast.LENGTH_SHORT).show();
				//progressBar.setPosition(progressBar.getPosition()-1);
				musicPlayer.pause();
				musicPlayer.stop();
				musicPlayer.release();				
				musicPlayer = new MusicPlayer(getBaseContext(), pBar);
				musicPlayer.playPrevSong(pManager.getPrevSong());
				playButton.setClicked(false); //pause image
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{				
				//progressBar.setPosition(progressBar.getPosition()+10);
				
				musicPlayer.pause();
				musicPlayer.stop();
				musicPlayer.release();				
				musicPlayer = new MusicPlayer(getBaseContext(), pBar);
				musicPlayer.playNextSong(pManager.getNextSong());
				playButton.setClicked(false); //pause image				
			}
		}); 				
		
    }
}