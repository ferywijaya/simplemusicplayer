package com.app.liviu.simpleMusicPlayer.gui;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.liviu.simpleMusciPlayer.playlist.Song;

public class PlaylistAdapter extends BaseAdapter 
{
	private final String            TAG = "PlaylistAdapter";
	private ArrayList<PlaylistItem> items;
	private Context         		context;
	
	public PlaylistAdapter(Context ctx) 
	{
		context = ctx;
		items 	= new ArrayList<PlaylistItem>();		
	}
	
	public void addItem(Song s)
	{
		items.add(new PlaylistItem(context, s, items.size()));
	}
		
	@Override
	public int getCount() 
	{	
		return items.size();
	}

	@Override
	public Object getItem(int position) 
	{			
		return items.get(position);
	}

	@Override
	public long getItemId(int position) 
	{	
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{	
		PlaylistItem itm = items.get(position);
		return itm;
	}

}
