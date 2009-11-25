package com.app.liviu.simpleMusciPlayer.playlist;

public interface BasePlaylist 
{

	public boolean loadPlaylist();
	public boolean addSongToPlaylist(Song s);
	public boolean deleteSongFromPlaylist(Song s);
	public boolean renameSongInPlaylist(Song s);
	public boolean rateSongInPlaylist(Song s, int rate);
	public Song    getSongFromPlaylist(int index);
	public Song	   getNextSongFromPlaylist(int index);
	public Song	   getPreviousSongFromPlaylist(int index);
	public Song	   getLastSongFromPlaylist();
	public Song	   getFirstSongFromPlaylist();			
}
