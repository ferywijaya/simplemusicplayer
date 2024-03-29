package com.app.liviu.simpleMusciPlayer.playlist;

import java.util.ArrayList;

public class Song 
{
	private String    title;
	private String    filePath;
	private String    genre;
	private String    videoLink;
	private String    streamLink;
	private String    imageLink;
	private String    album;
	private String    imagePath;
	private Artist    artist;
	private int       rate;
	private int       id;	
	private int		  playedTime;
	private boolean   isIgnored;
	ArrayList<String> tags;
	
	
	public Song(int id_) //create a anonymous song
	{
		id 			= id_;
		title		= new String("no title");
		filePath 	= new String("1");
		genre	 	= new String("1");
		videoLink	= new String("1");
		streamLink 	= new String("1");
		imageLink 	= new String("1");
		imagePath 	= new String("1");
		album 		= new String("1");		
		artist  	= new Artist("Unknown",0); // to do : create artist class
		rate 		= 0;
		playedTime  = 0;		
		isIgnored 	= false;
		tags 		= new ArrayList<String>();		
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public void setAlbum(String album) 
	{
		this.album = album;
	}
	
	public void setArtist(Artist artist) 
	{
		this.artist = artist;
	}
	
	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}
	
	public void setGenre(String genre) 
	{
		this.genre = genre;
	}
	
	public void setIgnored(boolean isIgnored) 
	{
		this.isIgnored = isIgnored;
	}
	
	public void setImageLink(String imageLink) 
	{
		this.imageLink = imageLink;
	}
	
	public void ignoreSong(boolean value)
	{
		isIgnored = value;
	}
	
	public void setImagePath(String imagePath) 
	{
		this.imagePath = imagePath;
	}
	
	public void setRate(int rate) 
	{
		this.rate = rate;
	}
	
	public void setStreamLink(String streamLink) 
	{
		this.streamLink = streamLink;
	}
	
	public void setTags(ArrayList<String> tags) 
	{
		this.tags = tags;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public void setVideoLink(String videoLink) 
	{
		this.videoLink = videoLink;
	}
	
	public void setPlayedTime(int playedTime) 
	{
		this.playedTime = playedTime;
	}
	
	public int getPlayedTime() 
	{
		return playedTime;
	}
	
	public String getAlbum() 
	{
		return album;
	}
	
	public Artist getArtist() 
	{
		return artist;
	}
	
	public String getFilePath() 
	{
		return filePath;
	}
	
	public String getGenre() 
	{
		return genre;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public String getImageLink() 
	{
		return imageLink;
	}
	
	public String getImagePath() 
	{
		return imagePath;
	}
	
	public int getRate() 
	{
		return rate;
	}
	
	public String getStreamLink() 
	{
		return streamLink;
	}
	
	public ArrayList<String> getTags() 
	{
		return tags;
	}
	
	public String getVideoLink() 
	{
		return videoLink;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public boolean isIgnored()
	{
		return isIgnored;
	}
	
	@Override
	public String toString() 
	{
	
		return "id " + id + 
			   "\nTitle " + title + 
			   "\nFilePath " + filePath +
			   "\nAlbum " + album +
			   "\nGenre " + genre +
			   "\nImageLink "  + imageLink + 
			   "\nImagePath " + imagePath + 
			   "\nVideoLink " + videoLink + 
			   "\nStreamLink" + streamLink + 
			   "\nRate " + rate + 
			   "\nPlayedTime " + playedTime +  
			   "\nArtistId " + artist.getId() + "\n\n----------------------------------------------------------------------" ;
			   
	}
					
}
