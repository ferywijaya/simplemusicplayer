package com.app.liviu.simpleMusicPlayer.gui;

import android.content.Context;
import android.widget.Button;

public class CustomButton extends Button
{
	private Context context;
	private int image;
	
	public CustomButton(Context ctx,int resImg) 
	{
		super(ctx);
		
		context = ctx;
		image   = resImg;		
		
		setBackgroundResource(image);		
	}
}
