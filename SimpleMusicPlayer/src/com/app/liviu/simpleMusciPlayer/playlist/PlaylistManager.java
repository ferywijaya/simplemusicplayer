package com.app.liviu.simpleMusciPlayer.playlist;

import com.app.liviu.simpleMusicPlayer.Util.Constants;

import android.content.Context;

public class PlaylistManager 
{
	private Playlist currenntPlaylist;
	private Playlist tempPlaylist;
	private Context  context;
	private int      currentSongPosition;
	
	public PlaylistManager(Context ctx) 
	{
		context 		    = ctx;
		currenntPlaylist    = new Playlist(context);
		tempPlaylist        = new Playlist(context);
		currentSongPosition = 0;
	}
	
	public int loadPlaylist(String name)
	{
		boolean test;
		
		currenntPlaylist.setNamePlaylist(name);
		test = currenntPlaylist.loadPlaylist();
		
		if(!test)
			return Constants.ERROR_NO_PLAYLIST_FOUND;
		else			
			return 0;
	}
		
	public Song getFirstSong()
	{		
		return currenntPlaylist.getFirstSongFromPlaylist();
	}
	
	public Song getLastSong()
	{
		currentSongPosition = currenntPlaylist.getAllSongFiles().size() - 1;
		return currenntPlaylist.getLastSongFromPlaylist();
	}
	
	public Song getNextSong()
	{
		Song temp = currenntPlaylist.getNextSongFromPlaylist(currentSongPosition);
		
		if(temp == null)
		{
			currentSongPosition = 0;
			return currenntPlaylist.getFirstSongFromPlaylist();
		}
		else
		{
			currentSongPosition++;
			return temp;
		}
	}
	
	public Song getPrevSong()
	{
		Song temp = currenntPlaylist.getPreviousSongFromPlaylist(currentSongPosition);
		
		if(temp == null)					
			return getLastSong();		
		else
		{
			currentSongPosition--;
			return temp;
		}		
	}
	
	public Playlist getCurrentPlaylist()
	{
		return currenntPlaylist;
	}
	
	
	
	
	
}
