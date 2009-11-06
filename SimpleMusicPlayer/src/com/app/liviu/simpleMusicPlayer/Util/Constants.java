package com.app.liviu.simpleMusicPlayer.Util;

public class Constants 
{

	//database
	public static final String DATA_BASE_NAME = "simpleMusicPlayerDB";
	public static final String SONGS_TABLE_NAME = "songs";
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
																					  "isIgnored_field text not null,"+
																					  "tagsId_field integer not null);";
																					  
}

/*		values.put("id", tempSong.getId());
		values.put("title_field", "test");
		values.put("filePath_field", "sfdgfdhgdf");
		values.put("genre_field", tempSong.getGenre());
		values.put("videoLink_field", tempSong.getVideoLink());
		values.put("streamLink_field", tempSong.getStreamLink());
		values.put("imageLink_field", tempSong.getImageLink());
		values.put("imagePath_field", tempSong.getImagePath());
		values.put("album_field", tempSong.getAlbum());
		values.put("artistId_field", tempSong.getArtist().getArtistId());
		values.put("rate_field", tempSong.getRate());
		values.put("playedTime_field", tempSong.getPlayedTime());
		values.put("isIgnored_field", "test");
		values.put("tagsId_field", tempSong.getTagId());
 * 
 */
