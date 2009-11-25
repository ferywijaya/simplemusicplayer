package com.app.liviu.simpleMusicPlayer.gui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.liviu.simpleMusicPlayer.MainActivity;
import com.app.liviu.simpleMusicPlayer.PlaylistActivity;
import com.app.liviu.simpleMusicPlayer.R;

public class ItemsBar extends HorizontalScrollView 
{
	private final String TAG = "ItemsBar";	
	private LinearLayout.LayoutParams viewParams;
	private LinearLayout.LayoutParams layoutParams;
	private LinearLayout layout;
	private Context context;
	
	public ItemsBar(Context ctx) 
	{
		super(ctx);
		
		context      = ctx;
		viewParams   = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);		
		layout 		 = new LinearLayout(context);
		layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);		
		
		layout.setOrientation(LinearLayout.HORIZONTAL);		
		viewParams.topMargin = 5;			
		
		addView(layout,layoutParams);		
		setHorizontalScrollBarEnabled(false);
		
		setBackgroundResource(R.drawable.items);
		
		ImageView songsButton = new ImageView(context);
		ImageView playLists   = new ImageView(context);
		ImageView albums 	  = new ImageView(context);
		ImageView favorite    = new ImageView(context);
		ImageView twitter     = new ImageView(context);
		ImageView youtube     = new ImageView(context);
		ImageView lastfm      = new ImageView(context);
		ImageView bookmark    = new ImageView(context);
		ImageView ignoreList  = new ImageView(context);
				
		//setting attributes
		songsButton.setImageResource(R.drawable.songs);		
		playLists.setImageResource(R.drawable.playlists);
		albums.setImageResource(R.drawable.albums);
		favorite.setImageResource(R.drawable.fav);
		twitter.setImageResource(R.drawable.twitter);
		youtube.setImageResource(R.drawable.yout);
		lastfm.setImageResource(R.drawable.lastfm);
		bookmark.setImageResource(R.drawable.bookmark);
		ignoreList.setImageResource(R.drawable.ignore);
		
		layout.addView(favorite, viewParams);
		layout.addView(ignoreList, viewParams);
		layout.addView(lastfm, viewParams);
		layout.addView(youtube, viewParams);
		layout.addView(bookmark, viewParams);
		layout.addView(albums, viewParams);
		layout.addView(twitter, viewParams);
		layout.addView(playLists, viewParams);
		layout.addView(songsButton, viewParams);
		
				
		
		//setting listeners
		
		songsButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{
				Intent toPlaylist = new Intent(MainActivity.mainActivityHandler,PlaylistActivity.class);
				MainActivity.mainActivityHandler.startActivity(toPlaylist);
				
			}
		});

		
		
		
		
	}
		

}
