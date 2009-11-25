package com.app.liviu.simpleMusciPlayer.musicPlayer;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.app.liviu.simpleMusciPlayer.database.DatabaseManager;
import com.app.liviu.simpleMusciPlayer.playlist.CustomPlaylist;
import com.app.liviu.simpleMusciPlayer.playlist.Song;

public class MusicPlayer extends MediaPlayer
{
	private Context context;
	private CustomPlaylist currentPlaylist;
	private Song currentSong;
	private DatabaseManager dbManager;
	private boolean updatedPlayingTime;
	private int     currentPositionInPlaylist;
	
	public MusicPlayer(Context ctx, CustomPlaylist pls) 
	{
		super();		
		context = ctx;
		currentPlaylist = pls;
		dbManager = new DatabaseManager(context);
		currentSong = currentPlaylist.getFirstSongFromPlaylist();
		currentPositionInPlaylist = 0;
		updatedPlayingTime = false;
		
	}
	
	public MusicPlayer(Context ctx, CustomPlaylist pls, int currentPos)
	{
		super();		
		context = ctx;
		currentPlaylist = pls;
		dbManager = new DatabaseManager(context);
		currentSong = null;
		currentPositionInPlaylist = currentPos;
		updatedPlayingTime = false;
	}
	
	public void play()
	{
		try 
		{
			setDataSource(currentSong.getFilePath());
			Toast.makeText(context,currentSong.getTitle(), Toast.LENGTH_SHORT).show();
			prepare();
			start();			
			dbManager.openDatabase();			
			dbManager.updateSongPlayedTime(currentSong.getPlayedTime() + 1, currentSong.getId());
			dbManager.closeDatabaseManager();
			updatedPlayingTime = true;
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playNextSong()
	{
		updatedPlayingTime = false;
		currentSong = currentPlaylist.getNextSongFromPlaylist(currentPositionInPlaylist);
		play();
		currentPositionInPlaylist++;
	}
	
	public void playPrevSong()
	{

		updatedPlayingTime = false;
		currentSong = currentPlaylist.getPreviousSongFromPlaylist(currentPositionInPlaylist);		
		play();
		currentPositionInPlaylist--;
		
	}
	
	public boolean isUpdated()
	{
		return updatedPlayingTime;
	}
	
	public int getCurrentPosition()
	{
		return currentPositionInPlaylist;
	}
}
