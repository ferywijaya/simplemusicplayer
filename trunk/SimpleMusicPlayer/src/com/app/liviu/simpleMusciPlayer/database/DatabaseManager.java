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
	private SQLiteDatabase db;
	private Context context;
	
	public DatabaseManager(Context ctx) 
	{
		Log.e(TAG,"DatabaseManager Constructor");
		context = ctx;
		openDatabase();
	}
	
	public boolean openDatabase()
	{
		try
		{
			db = context.openOrCreateDatabase(Constants.DATA_BASE_NAME, Context.MODE_PRIVATE, null);
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
		ContentValues values = new ContentValues();		
		
		
		return true;
	}
	
}
