package com.app.liviu.simpleMusicPlayer.gui;

import com.app.liviu.simpleMusicPlayer.R;

import android.R.bool;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GUIManager 
{
	private final String 				TAG = "GUIManager";
	private Context 				 	context;
	private RelativeLayout 				mainLayout;
	private RelativeLayout 				bottomLayout;
	private RelativeLayout 				topLayout;
	private PlayButton 					playButton;	
	private CustomButton				prevButton;
	private CustomButton				nextButton;
	
	private RelativeLayout.LayoutParams mainLayoutParams;
	private RelativeLayout.LayoutParams topLayoutParams;
	private RelativeLayout.LayoutParams bottomLayoutParams;
	private RelativeLayout.LayoutParams playButtonParams;
	private RelativeLayout.LayoutParams prevButtonParams;
	private RelativeLayout.LayoutParams nextButtonParams;
	
	
	public GUIManager(Context ctx) 
	{
		context 		   = ctx;
		
		mainLayout 		   = new RelativeLayout(context);
		bottomLayout 	   = new RelativeLayout(context);
		topLayout 		   = new RelativeLayout(context);
		playButton         = new PlayButton(context);
		prevButton		   = new CustomButton(context,R.drawable.prev);
		nextButton		   = new CustomButton(context,R.drawable.next);
		mainLayoutParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  RelativeLayout.LayoutParams.FILL_PARENT);
		bottomLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  80);
		topLayoutParams    = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,  80);
		playButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		prevButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		nextButtonParams   = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		
		mainLayout.setLayoutParams(mainLayoutParams);
		mainLayout.setBackgroundResource(R.drawable.main);
		
		bottomLayoutParams.topMargin = 410;
		bottomLayout.setBackgroundResource(R.drawable.buttom_bar);			

		playButtonParams.topMargin  = 7;
		playButtonParams.leftMargin = 42;
		
		prevButtonParams.topMargin = 13;
		prevButtonParams.leftMargin = 5;
		
		nextButtonParams.topMargin = 12;
		nextButtonParams.leftMargin = 89;
		
		playButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{			
				playButton.setClicked();
			}
		});
		
		prevButton.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(context, "prev", Toast.LENGTH_SHORT).show();
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(context,"next",Toast.LENGTH_SHORT).show();
				
			}
		});
		
		//add views to the bottom bar
		bottomLayout.addView(prevButton,prevButtonParams);
		bottomLayout.addView(nextButton,nextButtonParams);
		bottomLayout.addView(playButton, playButtonParams);
				
		mainLayout.addView(topLayout, topLayoutParams);
		mainLayout.addView(bottomLayout,bottomLayoutParams);
		
	}
	
	public RelativeLayout getMainLayout()
	{
		return mainLayout;
	}

}


