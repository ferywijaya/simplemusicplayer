package com.app.liviu.simpleMusciPlayer.database;

import com.app.liviu.simpleMusciPlayer.playlist.Song;
import com.app.liviu.simpleMusicPlayer.Util.Constants;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager
{
	private final String TAG = "DatabaseManager";
	private       SQLiteDatabase db;
	private       Context context;
	
	public DatabaseManager(Context ctx) 
	{
		Log.e(TAG,"DatabaseManager Constructor");
		context = ctx;
		//deleteDatabase();
		openDatabase();
	}
	
	public boolean openDatabase()
	{
		try
		{
			db = context.openOrCreateDatabase(Constants.DATA_BASE_NAME,Context.MODE_PRIVATE, null);
			Log.e(TAG,"Database is ready!");
			db.execSQL(Constants.CREATE_TABLE_SONGS);	
			return true;
		}
		catch (SQLException e)
		{
			Log.e(TAG,"ERROR at accesing database!");
			Log.e(TAG,e.toString());
			return false;
		}
	}
			
	public void closeDatabaseManager()
	{
		if(db.isOpen())
			db.close();
		else
			Log.e(TAG,"Database is not open!");
	}
	
	public boolean insertSong(Song tempSong)
	{
		Log.e(TAG,"inserting...");
		ContentValues values = new ContentValues();		
		
		values.put("id_field", tempSong.getId());
		values.put("title_field", tempSong.getTitle());
		values.put("filePath_field", tempSong.getFilePath());
		values.put("genre_field", tempSong.getGenre());
		values.put("videoLink_field", tempSong.getVideoLink());
		values.put("streamLink_field", tempSong.getStreamLink());
		values.put("imageLink_field", tempSong.getImageLink());
		values.put("imagePath_field", tempSong.getImagePath());
		values.put("album_field", tempSong.getAlbum());
		values.put("artistId_field", tempSong.getArtist().getId());
		values.put("rate_field", tempSong.getRate());
		values.put("playedTime_field", tempSong.getPlayedTime());		
		values.put("tagsId_field", tempSong.getTagId());
		
		if(tempSong.isIgnored())
			values.put("isIgnored_field", "1");	
		else
			values.put("isIgnored_field", "0");
		
		try
		{
			db.insertOrThrow(Constants.SONGS_TABLE_NAME, null, values);
			Log.e(TAG,"Inserting...ok: " + tempSong.getTitle());					
		}
		catch (SQLException e)
		{
			Log.e(TAG,"ERROR at insert!the value is possible to exist in database " + e.toString());
			return false;
		}				
		
		return true;
	}
	
	public void deleteDatabase()
	{
		context.deleteDatabase(Constants.DATA_BASE_NAME);
	}	
}