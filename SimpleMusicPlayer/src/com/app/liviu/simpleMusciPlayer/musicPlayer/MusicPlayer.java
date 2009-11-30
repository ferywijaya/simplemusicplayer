package com.app.liviu.simpleMusciPlayer.musicPlayer;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.liviu.simpleMusciPlayer.database.DatabaseManager;
import com.app.liviu.simpleMusciPlayer.playlist.Playlist;
import com.app.liviu.simpleMusciPlayer.playlist.Song;

public class MusicPlayer extends MediaPlayer implements Runnable, OnSongIsFinishPlayed
{
	private final String 	TAG = "MUsicPlayer";
	private Context 		context;	
	private DatabaseManager dbManager;
	private boolean         updatedPlayingTime;	
	private ProgressBar     pBar;
	private MusicPlayer     instance;
	private int 			playedSeconds;
	private Thread 			t;
	private boolean 		isPlay;
	private boolean			stop;
	private int 			duration;
	private Handler 		handler = new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			pBar.setProgress(playedSeconds);
		};			
	};
		
	public MusicPlayer(Context ctx, ProgressBar p) 
	{
		super();		
		context = ctx;
		dbManager = new DatabaseManager(context);			
		updatedPlayingTime = false;
		pBar = p;
		instance = this;
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
			setDataSource(song.getFilePath());
			Toast.makeText(context,song.getTitle(), Toast.LENGTH_SHORT).show();
			prepare();
			start();					
			duration = getDuration();
			Log.e(TAG,"After start()");
			
			pBar.setMax(duration / 1000);
			playedSeconds = 0;
			pBar.setProgress(playedSeconds);
			
			t = new Thread(this);
			t.start();
			Log.e(TAG,"After t.start()");
			dbManager.openDatabase();			
			dbManager.updateSongPlayedTime(song.getPlayedTime() + 1, song.getId());
			//dbManager.listAllSongs();		
			dbManager.closeDatabaseManager();
			updatedPlayingTime = true;
			
		} catch (IllegalArgumentException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playNextSong(Song song)
	{
		pBar.setProgress(0);
		updatedPlayingTime = false;
		stop = true;
		play(song);
	}
	
	public void playPrevSong(Song song)
	{
		pBar.setProgress(0);
		updatedPlayingTime = false;		
		stop = true;
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
		while((playedSeconds < (duration / 1000)) && (stop == false))
		{			
			try 
			{
				Thread.sleep(1000);
				playedSeconds++;
				Log.e(TAG, "seconds " + playedSeconds);
				handler.sendEmptyMessage(0);					
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
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

	@Override
	public void OnSongIsFinishPlayed(Song song) 
	{
 
		
	}
	
	
}
