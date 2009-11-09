package com.app.liviu.simpleMusciPlayer.database;

import java.util.ArrayList;

import com.app.liviu.simpleMusciPlayer.playlist.Artist;
import com.app.liviu.simpleMusciPlayer.playlist.Song;
import com.app.liviu.simpleMusicPlayer.Util.Constants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
		openAndCreateDatabase();
		//closeDatabaseManager();
	}
	
	public boolean openAndCreateDatabase()
	{
		try
		{
			db = context.openOrCreateDatabase(Constants.DATABASE_NAME,Context.MODE_PRIVATE, null);
			Log.e(TAG,"Database is ready!");
			db.execSQL(Constants.CREATE_TABLE_SONGS);		
			db.execSQL(Constants.CREATE_TABLE_TAGS);
			
			return true;
		}
		catch (SQLException e)
		{
			Log.e(TAG,"ERROR at accesing database!");
			Log.e(TAG,e.toString());
			return false;
		}
	}
	
	public boolean openDatabase()
	{
		try
		{
			db = context.openOrCreateDatabase(Constants.DATABASE_NAME,Context.MODE_PRIVATE, null);
			Log.e(TAG,"Database is ready!");						
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

		
		if(tempSong.isIgnored())
			values.put("isIgnored_field", "1");	
		else
			values.put("isIgnored_field", "0");
		
		try
		{
			if(db.isOpen())
			{
				db.insertOrThrow(Constants.SONGS_TABLE_NAME, null, values);
				Log.e(TAG,"Inserting...ok: " + tempSong.getTitle());					
			}
			else
				Log.e(TAG,"DATABASE is not open 35");
		}
		catch (SQLException e)
		{
			Log.e(TAG,"ERROR at insert!the value is possible to exist in database " + e.toString());
			return false;
		}
		
		return true;
	}
	
	public void listAllSongs()
	{
		try
		{				
			Cursor c = db.query(Constants.SONGS_TABLE_NAME, new String[]{"id_field",		//0
																		 "title_field", 	//1
																		 "filePath_field",	//2
																		 "genre_field",		//3
																		 "videoLink_field",	//4
																		 "streamLink_field",//5
																		 "imageLink_field",	//6
																		 "imagePath_field", //7
																		 "album_field",		//8
																		 "artistId_field",	//9
																		 "rate_field",		//10
																		 "playedTime_field",//11
																		 "isIgnored_field"  //12
																		 }
															,null,null , null, null, null);
	    	  
	    	  int num_row = c.getCount();
	    	  int i;
	    	  
	    	  c.moveToFirst();
	    	  for(i = 0; i < num_row; i++)
	    	  {
	    		  Song tempSong = new Song(c.getInt(0));
	    		  
	    		  tempSong.setTitle(c.getString(1));
	    		  tempSong.setFilePath(c.getString(2));
	    		  tempSong.setGenre(c.getString(3));
	    		  tempSong.setVideoLink(c.getString(4));
	    		  tempSong.setStreamLink(c.getString(5));
	    		  tempSong.setImageLink(c.getString(6));
	    		  tempSong.setImagePath(c.getString(7));
	    		  tempSong.setAlbum(c.getString(8));
	    		  tempSong.setArtist(new Artist("noname",c.getInt(9)));
	    		  tempSong.setRate(c.getInt(10));
	    		  tempSong.setPlayedTime(c.getInt(11));
	    		  
	    		  String s = c.getString(12);
	    		  if(s.equals("0"))
	    			  tempSong.ignoreSong(false);
	    		  else
	    			  if(s.equals("1"))
	    				  tempSong.ignoreSong(true);
	    		  Log.e(TAG,tempSong.toString());
	    		  c.moveToNext();	
	    	  }
	    	 	    	  
	    	  c.close();	    	 				
		}
		catch(SQLiteException  e)
		{
			Log.e("CustomSQL Constructor","error creating database" + e.toString());
		}  
	}
	//<updating...>
	public boolean updateSong(Song tempSong,int oldSongId)
	{
			Log.e(TAG,"updating...");
			String ignored;
			
			if(tempSong.isIgnored())
				ignored = "1";
			else
				ignored = "0";
			ContentValues values = new ContentValues();		
					
			values.put("title_field",      tempSong.getTitle());
			values.put("filePath_field",   tempSong.getFilePath());
			values.put("genre_field",      tempSong.getGenre());
			values.put("videoLink_field",  tempSong.getVideoLink());
			values.put("streamLink_field", tempSong.getStreamLink());
			values.put("imageLink_field",  tempSong.getImageLink());
			values.put("imagePath_field",  tempSong.getImagePath());
			values.put("album_field", 	   tempSong.getAlbum());
			values.put("artistId_field",   tempSong.getArtist().getId());
			values.put("rate_field", 	   tempSong.getRate());
			values.put("playedTime_field", tempSong.getPlayedTime());					
					
			if(db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + tempSong.getId(),null) < 0)		
			{
				Log.e(TAG,"Erorr at update ");
				return false;
			}
			else
			{
				Log.e(TAG,"update ok!");
				return true;
			}
			//listAllSongs();	
	}
	
	
	public boolean updateSongTitle(String songTitle,int songId)
	{
		Log.e(TAG,"updating title...");
		ContentValues values = new ContentValues();
		values.put("title_field", songTitle);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)	
		{
			Log.e(TAG,"Erorr at update title ");
			return false;
		}		
		else
		{
			Log.e(TAG,"update title ok!");
			return true;
		}
		//listAllSongs();
	}
	
	public boolean updateSongGenre(String songGenre,int songId)
	{
		Log.e(TAG,"updating genre...");
		ContentValues values = new ContentValues();
		values.put("genre_field", songGenre);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)		
		{
			Log.e(TAG,"Erorr at update genre ");
			return false;
		}
		else
		{
			Log.e(TAG,"update genre ok!");
			return true;
		}
		//listAllSongs();
	}
	
	public boolean updateSongVideoLink(String songVideoLink,int songId)	
	{
		Log.e(TAG,"updating videoLink...");
		ContentValues values = new ContentValues();
		values.put("videoLink_field", songVideoLink);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)
		{
			Log.e(TAG,"Erorr at update videoLink ");
			return false;
		}
		else
		{
			Log.e(TAG,"update videoLink ok!");
			return true;
		}
	//	listAllSongs();
	}
	
	public boolean updateSongStreamLink(String songStreamLink,int songId)
	{
		Log.e(TAG,"updating streamLink...");
		ContentValues values = new ContentValues();
		values.put("streanLink_field", songStreamLink);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)
		{
			Log.e(TAG,"Erorr at update streamLink ");
			return false;
		}
		else
		{
			Log.e(TAG,"update streanLink ok!");
			return true;
		}
	}

	public boolean updateSongImageLink(String songStreamLink,int songId)
	{
		Log.e(TAG,"updating imageLink...");
		ContentValues values = new ContentValues();
		values.put("imageLink_field", songStreamLink);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)
		{
			Log.e(TAG,"Erorr at update imageLink ");
			return false;
		}
		else
		{
			Log.e(TAG,"update imageLink ok!");
			return true;
		}
	}
	
	public boolean updateSongAlbum(String songAlbum,int songId)
	{
		Log.e(TAG,"updating album...");
		ContentValues values = new ContentValues();
		values.put("album_field", songAlbum);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)
		{
			Log.e(TAG,"Erorr at update album ");
			return false;
		}		
		else
		{
			Log.e(TAG,"update album ok!");
			return true;
		}
	}
	
	public boolean updateSongArtistId(int artistId,int songId)
	{
		Log.e(TAG,"updating artistId...");
		ContentValues values = new ContentValues();
		values.put("artistId_field", artistId);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)
		{
			Log.e(TAG,"Erorr at update artistId ");
			return false;
		}
		else
		{
			Log.e(TAG,"update artistId ok!");
			return true;
		}
	}
	
	public boolean updateSongRate(int songRate,int songId)
	{
		Log.e(TAG,"updating rate...");
		ContentValues values = new ContentValues();
		values.put("rate_field", songRate);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)	
		{
			Log.e(TAG,"Erorr at update rate ");
			return false;
		}
		else
		{	
			Log.e(TAG,"update rate ok!");
			return true;
		}
	}
	
	public boolean updateSongPlayedTime(int songPlayedTime,int songId)
	{
		Log.e(TAG,"updating playedTime...");
		ContentValues values = new ContentValues();
		values.put("playedTime_field", songPlayedTime);
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)	
		{
			Log.e(TAG,"Erorr at update playedTime ");
			return false;
		}
		else
		{
			Log.e(TAG,"update palyedTime ok!");
			return true;
		}
	}

	
	public boolean updateSongPlayedTime(boolean ignored,int songId)
	{
		Log.e(TAG,"updating ignore...");
		ContentValues values = new ContentValues();
				
		if(ignored == true)
			values.put("isIgnored_field", "1");	
		else
			values.put("isIgnored_field", "0");
		
		if( db.update(Constants.SONGS_TABLE_NAME, values, "id_field=" + songId,null) < 0)
		{
			Log.e(TAG,"Erorr at update ignore ");
			return false;
		}
		else
		{
			Log.e(TAG,"update ignore ok!");
			return true;
		}
	}					
	//</updating...>	
	
	//<deleting...>
	public boolean deleteSong(int songId)
	{
		if(db.delete(Constants.SONGS_TABLE_NAME, "id_field=" + songId, null) < 0)
		{
			Log.e(TAG,"deleting fail for id " + songId);
			return false;
		}
		else
		{
			Log.e(TAG,"deleting ok for id " + songId);
			return true;
		}				
	}
	
	//</deleting...>
	
	//<tags>
	
	public boolean addTag(int songId,String tagValue)
	{
		ContentValues values = new ContentValues();
		
		values.put("tagName_field", tagValue);
		values.put("tagId_field", songId);
		
		try
		{
			db.insertOrThrow(Constants.TAGS_TABLE_NAME, null, values);
			Log.e(TAG,"insert tag " + tagValue + " at id " + songId + " ...ok");
			return true;
		}
		catch (SQLiteException e)
		{
			Log.e(TAG,"Eror at inserting tag " + tagValue + " at id " + songId + "\n error is : " + e.toString());
			return false;
		}		
	}
	
	public boolean deleteTag(int songId, String tagValue)
	{
		if(db.delete(Constants.TAGS_TABLE_NAME, "(tagId_field="+songId+") AND (tagName_field='"+tagValue+"')", null) < 1)
		{
			Log.e(TAG, "ERROR at deleting tag " + tagValue + " from id " + songId);
			return false;
		}
		else
		{
			Log.e(TAG, "Deleting tag " + tagValue + " from id " + songId + " ...ok");
			return true;			
		}			
	}
	
	public ArrayList<String> getAllTagsFor(int songId)
	{
		ArrayList<String> tagsList = new ArrayList<String>();
		Cursor c = db.query(Constants.TAGS_TABLE_NAME, new String[]{"tagName_field"},"tagId_field="+songId,null , null, null, null);

		int num_row = c.getCount();
		int i;
		
		c.moveToFirst();
		for(i = 0; i < num_row; i++)
		{
			tagsList.add(c.getString(0));
			Log.e(TAG,"tag from database is " + c.getString(0));
			c.moveToNext();
		}
		
		c.close();				
		return tagsList;
	}
	
	//</tags>
	
	//<other>	
	public void deleteDatabase()
	{
		context.deleteDatabase(Constants.DATABASE_NAME);
	}
	
	//<other>	
}