package com.app.liviu.simpleMusicPlayer.Util;

public class Constants 
{

	//database
	public static final String DATABASE_NAME 	  = "simpleMusicPlayerDB";
	public static final String SONGS_TABLE_NAME   = "songs";
	public static final String TAGS_TABLE_NAME    = "tags";
	public static final String CREATE_TABLE_SONGS = "create table if not exists songs( id_field integer not null primary key,"+
																					  "title_field text not null," + 
																					  "filePath_field text not null," +
																					  "genre_field text not null,"+
																					  "videoLink_field text not null,"+
																					  "streamLink_field text not null,"+
																					  "imageLink_field text not null,"+
																					  "imagePath_field text not null,"+
																					  "album_field text not null,"+
																					  "artistId_field integer not null,"+
																					  "rate_field integer not null,"+
																					  "playedTime_field integer not null,"+
																					  "isIgnored_field text not null);";
	
	public static final String CREATE_TABLE_TAGS = "create table if not exists tags(index_field integer not null primary key autoincrement,"+
																					 "tagName_field text not null,"+
																					 "tagId_field integer not null);";
	//settings
	public static final String SETTINGS_PREFERENCES = "simple_player_preferences";
	public static final String SETTINGS_CURRENT_SONG_ID = "current_song_id";
	public static final String SETTINGS_CURRENT_SONG_PLAYED_TIME  = "current_song_played_time";	

	//error codes	
	public static  int ERROR_NO_PLAYLIST_FOUND = 0;
}

