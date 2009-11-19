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
		isClicked = false;
	}
	
	public void setClicked()
	{
		if(isClicked )
		{
			isClicked = false;
			setBackgroundResource(R.drawable.play_img);	
		}
		else
		{
			setBackgroundResource(R.drawable.pause_img);
			isClicked = true;
		}
	
	}
	
	
	
	
	
	

}
