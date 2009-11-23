package com.app.liviu.simpleMusicPlayer.gui;

import com.app.liviu.simpleMusicPlayer.R;

import android.app.Dialog;
import android.content.Context;

public class CustomAlert extends Dialog 
{
	private Context context;
	public CustomAlert(Context ctx) 
	{
		super(ctx);
		context = ctx;
		setContentView(R.layout.alertdialog);			
	}

}
