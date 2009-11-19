package com.app.liviu.simpleMusciPlayer.playlist;

public interface BasePlaylist 
{

	public boolean loadPlaylist();
	public boolean addSongToPlaylist(Song s);
	public boolean deleteSongFromPlaylist(Song s);
	public boolean renameSongInPlaylist(Song s);
	public boolean rateSongInPlaylist(Song s, int rate);
	public int     getSongFromPlaylist(int index);
	public int 	   getNextSongFromPlaylist(int index);
	public int 	   getPreviousSongFromPlaylist(int index);
	public int	   getLastSongFromPlaylist();
	public int	   getFirstSongFromPlaylist();			
}
