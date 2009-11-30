package com.app.liviu.simpleMusciPlayer.scan;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.app.liviu.simpleMusciPlayer.database.DatabaseManager;
import com.app.liviu.simpleMusciPlayer.playlist.Playlist;
import com.app.liviu.simpleMusciPlayer.playlist.IdManager;
import com.app.liviu.simpleMusciPlayer.playlist.Song;
import com.app.liviu.simpleMusicPlayer.MainActivity;


public class ScanManager 
{	
	private static final String TAG = "PICTURE_SCANNING";
	private static ScanManager instance = null;
	private ArrayList<Song> songList;	
	private IdManager idManager;
	private DatabaseManager databaseManager;
	private Context context;
	
	private ScanManager(Context ctx)
	{
		context 		= ctx;
		songList		= new ArrayList<Song>();		
		idManager 	    = IdManager.getInstance();
		databaseManager = new DatabaseManager(context);			

	}
	
	public static ScanManager getInstance(Context ctx)
	{
			if(instance == null)
			{
				instance = new ScanManager(ctx);
			}
			
			return instance;
	}
	
	public void scanFiles()
	{				
		File f = Environment.getExternalStorageDirectory();		
		SharedPreferences.Editor editor = MainActivity.settings.edit();
		editor.putBoolean("scanned", true);
		editor.commit();
		scan(f);
		
		Log.e(TAG,"scan finnished with " + songList.size() + " music files");
		databaseManager.openDatabase();
		insertValuesInDatabase();
		databaseManager.closeDatabaseManager();
	}
	
	private void scan(File f)
	{		
        if ( !f.exists() ) 
        	return;         
                        
        if(f.getAbsolutePath().contains(".mp3") || f.getAbsolutePath().contains(".aac") || f.getAbsolutePath().contains(".wma") || f.getAbsolutePath().contains(".wav"))
        {
        	Song tempSong = new Song(idManager.generateNextId());        
        	
        	if(f.getName().length() > 4)
        	{
        		tempSong.setTitle(f.getName().substring(0, f.getName().length()-4));
        		tempSong.setFilePath(f.getAbsolutePath());
        		tempSong.setRate(0);
        		tempSong.setPlayedTime(0);
        	}
        	else
        	{
            	tempSong.setTitle(f.getName());
        		tempSong.setFilePath(f.getAbsolutePath());
        		tempSong.setRate(0);
        		tempSong.setPlayedTime(0);
        	}
        		
        	songList.add(tempSong);
        }        
        
        if ( f.isDirectory()) 
        {
	        File[] files = f.listFiles();
	       
	        for( int i = 0 ; i < files.length; i++) 	        
	        	scan( files[i] );	        
        }
	}
	
	public void testUpdate()
	{
		databaseManager.openDatabase();
		
		Log.e(TAG,"---------------------------------------------");
		//databaseManager.listAllSongs();
		
		Playlist customPlaylist = new Playlist("customPlayList", context);		
		//customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(1));	
		
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(0));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(1));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(2));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(3));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(4));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(5));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(6));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(7));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(8));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(9));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(10));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(11));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(20));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(21));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(22));
		customPlaylist.addSongToPlaylist(databaseManager.getSongAtIndex(23));
				
		customPlaylist.savePlayList();
		
		//customPlaylist.loadPlaylist();
		/*
		Log.e(TAG,customPlaylist.getFirstSongFromPlaylist().toString());
		Log.e(TAG,customPlaylist.getLastSongFromPlaylist().toString());
		Log.e(TAG,customPlaylist.getSongFromPlaylist(3).toString());
		Log.e(TAG,"---------------------------------------------");
		customPlaylist.listPlaylistSongs();
		Log.e(TAG,"---------------------------------------------");		
		*/
		
		/*
		databaseManager.updateSongTitle("liviu.mp3",songList.get(0).getId());
		databaseManager.updateSongAlbum("test album",songList.get(0).getId());
		databaseManager.addTag(songList.get(0).getId(),"tag test");
		databaseManager.addTag(songList.get(0).getId(),"tag test2");
		databaseManager.addTag(songList.get(0).getId(),"tag test21423");
		databaseManager.addTag(songList.get(0).getId(),"tag test32523");
		databaseManager.listAllSongs();	
		databaseManager.deleteTag(songList.get(0).getId(), "tag test");
		databaseManager.listAllSongs();
		databaseManager.getAllTagsFor(songList.get(0).getId());
		*/
		
		databaseManager.closeDatabaseManager();
	}
	
	public void insertValuesInDatabase()
	{
		int i;
		
		for(i = 0; i < songList.size(); i++)
			Log.e(TAG," " + databaseManager.insertSong(songList.get(i)));
		
	}
}	
