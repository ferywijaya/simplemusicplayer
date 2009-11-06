package com.app.liviu.simpleMusicPlayer.Util;

public class Constants 
{

	//database
	public static final String DATA_BASE_NAME = "simpleMusicPlayerDB";
	public static final String SONGS_TABLE_NAME = "songs";
	public static final String CREATE_TABLE_SONGS = "create table if not exists songs(id integer not null primary key,"+
																					  "title_field text not null," + 
																					  "filePath_field text not null," +
																					  "genre_field text not null,"+
																					  "videoLink_field text not null,"+
																					  "streamLink_field text not null,"+
																					  "imageLink_field text not null,"+
																					  "album_field text not null,"+
																					  "artistId_field integer not null,"+
																					  "rate_field integer not null,"+
																					  "playedTime_field integer not null,"+
																					  "isIgnored_field integer not null,"+
																					  "tagsId_field integer not null);";
																					  
}
