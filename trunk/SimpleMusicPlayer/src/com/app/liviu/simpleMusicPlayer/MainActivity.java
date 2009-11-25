package com.app.liviu.simpleMusicPlayer;

import java.io.File;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.app.liviu.simpleMusciPlayer.musicPlayer.MusicPlayer;
import com.app.liviu.simpleMusciPlayer.playlist.CustomPlaylist;
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
	private CustomProgressBar		  	progressBar;
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
	public  static CustomPlaylist       customPlaylist;
	
	
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
		prevButton		   = new CustomButton(this,R.drawable.prev);
		nextButton		   = new CustomButton(this,R.drawable.next);
		progressBar        = new CustomProgressBar(this);
		mainLayout 		   = new RelativeLayout(this);		
		topLayout 		   = new RelativeLayout(this);		
		itemsBar		   = new ItemsBar(this);
		
		playButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		prevButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		bottomLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  80);
		progressBarParams  = new RelativeLayout.LayoutParams(144,13);
		mainLayoutParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  RelativeLayout.LayoutParams.FILL_PARENT);		
		topLayoutParams    = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  80);
		itemsBarParams 	   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 60);
		
		bottomLayoutParams.topMargin = 410;
		bottomLayout.setBackgroundResource(R.drawable.bottom);			

		playButtonParams.topMargin  = 7;
		playButtonParams.leftMargin = 42;
		
		prevButtonParams.topMargin  = 13;
		prevButtonParams.leftMargin = 5;
		
		nextButtonParams.topMargin  = 12;
		nextButtonParams.leftMargin = 89;
		
		progressBar.setMax(100);
		progressBar.setPosition(50);
		progressBarParams.leftMargin = 160;
		progressBarParams.topMargin  = 27;	
					
		
		//add views to the bottom bar
		bottomLayout.addView(prevButton,prevButtonParams);
		bottomLayout.addView(nextButton,nextButtonParams);
		bottomLayout.addView(playButton, playButtonParams);
		bottomLayout.addView(progressBar,progressBarParams);        					
		
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
        
        customPlaylist = new CustomPlaylist("customPlaylist", this);
        customPlaylist.loadPlaylist();
        musicPlayer = new MusicPlayer(this, customPlaylist);
        
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
						musicPlayer.play();
					else
						musicPlayer.start();
					
					playButton.setClicked(false);
				}
								
			}
		});
		
		prevButton.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				//Toast.makeText(context, "prev", Toast.LENGTH_SHORT).show();
				//progressBar.setPosition(progressBar.getPosition()-1);
				musicPlayer.stop();
				musicPlayer.release();
				int temp = musicPlayer.getCurrentPosition();
				musicPlayer = new MusicPlayer(getBaseContext(),customPlaylist, temp);
				musicPlayer.playPrevSong();
				playButton.setClicked(false); //pause image
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{				
				//progressBar.setPosition(progressBar.getPosition()+10);
				musicPlayer.stop();
				musicPlayer.release();
				int temp = musicPlayer.getCurrentPosition();
				musicPlayer = new MusicPlayer(getBaseContext(),customPlaylist, temp);
				musicPlayer.playNextSong();
				playButton.setClicked(false); //pause image
				
			}
		});
        
    }
}