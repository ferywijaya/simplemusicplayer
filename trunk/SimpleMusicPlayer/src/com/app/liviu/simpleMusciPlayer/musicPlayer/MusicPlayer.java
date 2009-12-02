package com.app.liviu.simpleMusciPlayer.musicPlayer;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.liviu.simpleMusciPlayer.database.DatabaseManager;
import com.app.liviu.simpleMusciPlayer.playlist.Playlist;
import com.app.liviu.simpleMusciPlayer.playlist.Song;
import com.app.liviu.simpleMusicPlayer.MainActivity;
import com.app.liviu.simpleMusicPlayer.Util.Util;

public class MusicPlayer extends MediaPlayer implements Runnable
{
	private final String 	TAG = "MUsicPlayer";
	private Context 		context;	
	private DatabaseManager dbManager;
	private boolean         updatedPlayingTime;	
	private ProgressBar     pBar;	
	private int 			playedSeconds;
	private Thread 			t;
	private boolean 		isPlay;
	private boolean			stop;
	private int 			duration;
	private Song			currentSong;
	private OnSongIsFinishPlayedListener songFinishedListener = null;
	private Handler 		handler = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.what == 0)
			{
				pBar.setProgress(playedSeconds);
				MainActivity.songTime.setText(Util.intToStringTimeFormat(playedSeconds));
			}
			else
				if(msg.what == 1)								
					songFinishedListener.OnSongIsFinishPlayed();									
		};			
	};
		
	public MusicPlayer(Context ctx, ProgressBar p) 
	{
		super();		
		context = ctx;
		dbManager = new DatabaseManager(context);			
		updatedPlayingTime = false;
		pBar = p;		
		playedSeconds = 0;		
		duration = 0;
		isPlay = false;		
	}
	
	public MusicPlayer(Context ctx, Playlist pls, int currentPos)
	{
		super();		
		context = ctx;		
		dbManager = new DatabaseManager(context);
		updatedPlayingTime = false;
	}
	
	public void play(Song song)
	{
		
		try 
		{
			Toast.makeText(context,song.getTitle(), Toast.LENGTH_SHORT).show();
			
			setDataSource(song.getFilePath());			
			prepare();
			start();								
			
			duration      = getDuration();			
			currentSong   = song;
			playedSeconds = 0;
			
			pBar.setMax(duration / 1000);			
			pBar.setProgress(playedSeconds);
			
			t = new Thread(this);
			t.start();
						
			dbManager.openDatabase();			
			dbManager.updateSongPlayedTime(song.getPlayedTime() + 1, song.getId());				
			dbManager.closeDatabaseManager();
			
			updatedPlayingTime = true;
			
		}
		catch (IllegalArgumentException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{		
			e.printStackTrace();
		}
	}
	
	public void playNextSong(Song song)
	{		
		updatedPlayingTime = false;
		stop			   = true;
		
		pBar.setProgress(0);
		play(song);
	}
	
	public void playPrevSong(Song song)
	{
	
		updatedPlayingTime = false;		
		stop 			   = true;
		
		pBar.setProgress(0);
		play(song);		
	}
	
	public boolean isUpdated()
	{
		return updatedPlayingTime;
	}
	
	@Override
	public void run() 
	{
		Log.e(TAG,"Thread started");
		while((playedSeconds <= (duration / 1000)) && (stop == false))
		{			
			try 
			{
				Thread.sleep(1000);
				playedSeconds++;				
				handler.sendEmptyMessage(0);					
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}		
		
		if(playedSeconds >= (duration / 1000))
		{			
			if(songFinishedListener != null)
			{
				Log.e(TAG, "The listener is not null");
				handler.sendEmptyMessage(1);
			}
			else			
				Log.e(TAG, "The listener is null");									
		}
		
		Log.e(TAG,"Thread closed");
	}
	
	@Override
	public void pause() throws IllegalStateException 
	{	
		super.pause();
		stop = true;
	}
	
	@Override
	public void start() throws IllegalStateException 
	{	
		super.start();
		
		stop = false;
		if(isUpdated())
		{			
			Log.e(TAG,"update " + isUpdated());
			t.interrupt();
			t = new Thread(this);
			t.start();
		}
		else
			Log.e(TAG,"update " + isUpdated());
	}
	
	public void setOnSongIsFinishPlayedListener(OnSongIsFinishPlayedListener listener)
	{
		songFinishedListener = listener;
	}
	
	public Song getCurrentSong() 
	{
		return currentSong;
	}
	
	public int getPlayedSeconds() 
	{
		return playedSeconds;
	}
	
	public void setPlayedSeconds(int playedSeconds) 
	{
		this.playedSeconds = playedSeconds;
	}
	
	
	
}
