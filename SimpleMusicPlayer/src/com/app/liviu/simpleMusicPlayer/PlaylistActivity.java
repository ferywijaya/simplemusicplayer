package com.app.liviu.simpleMusicPlayer;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.app.liviu.simpleMusciPlayer.playlist.CustomPlaylist;
import com.app.liviu.simpleMusciPlayer.playlist.Song;
import com.app.liviu.simpleMusicPlayer.gui.CustomButton;
import com.app.liviu.simpleMusicPlayer.gui.CustomProgressBar;
import com.app.liviu.simpleMusicPlayer.gui.PlayButton;
import com.app.liviu.simpleMusicPlayer.gui.PlaylistAdapter;

public class PlaylistActivity extends Activity
{	
	private final String 				TAG = "PlaylistActivity";
	private RelativeLayout				playlistLayout;
	private RelativeLayout 				bottomLayout;
	private ListView 					songsList;
	private PlaylistAdapter 			playlistAdapter;
	private PlayButton 					playButton;	
	private CustomButton				prevButton;
	private CustomButton				nextButton;
	private CustomProgressBar		  	progressBar;
	
	private RelativeLayout.LayoutParams playlistLayoutParams;
	private RelativeLayout.LayoutParams songsListParams;
	private RelativeLayout.LayoutParams bottomLayoutParams;
	private RelativeLayout.LayoutParams playButtonParams;
	private RelativeLayout.LayoutParams prevButtonParams;
	private RelativeLayout.LayoutParams nextButtonParams;
	private RelativeLayout.LayoutParams progressBarParams;	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        //make the activity full screen	
		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		                      WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
        setContentView(R.layout.main);
        
        //<create layout>
        
		playlistLayout 	= new RelativeLayout(this);
		songsList 		= new ListView(this);		
		playlistAdapter = new PlaylistAdapter(this);
		bottomLayout 	= new RelativeLayout(this);
		playButton      = new PlayButton(this);
		prevButton		= new CustomButton(this,R.drawable.prev);
		nextButton		= new CustomButton(this,R.drawable.next);
		progressBar     = new CustomProgressBar(this);
		
		playlistLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		songsListParams 	 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,412);
		playButtonParams     = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		prevButtonParams     = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextButtonParams     = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		bottomLayoutParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  80);
		progressBarParams  	 = new RelativeLayout.LayoutParams(144,13);
		
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
		
		
		playButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{			
				if(playButton.isClicked())
					playButton.setClicked(false);
				else
					playButton.setClicked(true);
			}
		});
		
		prevButton.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				//Toast.makeText(context, "prev", Toast.LENGTH_SHORT).show();
				progressBar.setPosition(progressBar.getPosition()-1);
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{
				//Toast.makeText(context,"next",Toast.LENGTH_SHORT).show();
				progressBar.setPosition(progressBar.getPosition()+10);
				
			}
		});
		
		//add views to the bottom bar
		bottomLayout.addView(prevButton,prevButtonParams);
		bottomLayout.addView(nextButton,nextButtonParams);
		bottomLayout.addView(playButton, playButtonParams);
		bottomLayout.addView(progressBar,progressBarParams);        
		
		//songsList.setAdapter(playlistAdapter);
		//songsList.setBackgroundColor(Color.WHITE);
		
		playlistLayout.setLayoutParams(playlistLayoutParams);
		playlistLayout.addView(songsList, songsListParams);
		playlistLayout.addView(bottomLayout, bottomLayoutParams);				
        //</create layout>
		setContentView(playlistLayout);
		

        
        ArrayList<Song> itemsFromPlaylist = MainActivity.customPlaylist.getAllSongFiles();
        Log.e(TAG,"Items in playlist : " + itemsFromPlaylist.size());
        
        for (int i = 0; i < itemsFromPlaylist.size(); i++)
        {
        	Log.e(TAG,"------------------------------------\n" + itemsFromPlaylist.get(i).toString());
        	playlistAdapter.addItem(itemsFromPlaylist.get(i));
        }
        
        songsList.setDividerHeight(0);                
        songsList.setAdapter(playlistAdapter);
        
        
       
    }
}
