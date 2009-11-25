package com.app.liviu.simpleMusicPlayer.gui;

import com.app.liviu.simpleMusicPlayer.R;

import android.content.Context;
import android.widget.Button;

public class PlayButton extends Button 
{
	private boolean isClicked;

	public PlayButton(Context context) 
	{
		super(context);			
		
		setBackgroundResource(R.drawable.play_img);
		isClicked = true; //apasat = play
	}
	
	public boolean isClicked()
	{
		return isClicked;
	}
	
	public void setClicked(boolean click)
	{
		if(click)
		{
			isClicked = true;
			setBackgroundResource(R.drawable.play_img);	
		}
		else
		{			
			isClicked = false;
			setBackgroundResource(R.drawable.pause_img);
		}
	
	}
	
	
	
	
	
	

}
