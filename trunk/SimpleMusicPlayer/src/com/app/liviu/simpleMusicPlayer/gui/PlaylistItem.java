package com.app.liviu.simpleMusicPlayer.gui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.liviu.simpleMusciPlayer.playlist.Song;

public class PlaylistItem extends RelativeLayout
{
	private final String TAG = "PlaylistItem";
	private TextView countTxt;
	private TextView titleTxt;
	private TextView timeTxt;
	private ItemLine line;
	private ItemLine line2;
	private Song     song;
	private ItemLineHorizontal hLine;
	private LinearLayout topLayout;

	
	private LinearLayout.LayoutParams countTxtParams;
	private LinearLayout.LayoutParams titleTxtParams;
	private LinearLayout.LayoutParams timeTxtParams;
	private LinearLayout.LayoutParams lineParams;
	private LinearLayout.LayoutParams line2Params;
	private RelativeLayout.LayoutParams topLayoutParams;
	private RelativeLayout.LayoutParams hLineParams;
 	
	public PlaylistItem(Context context, Song s, int count_) 
	{
		super(context);
				
		countTxt = new TextView(context);
		titleTxt = new TextView(context);
		timeTxt  = new TextView(context);
		line 	 = new ItemLine(context, 60);
		line2	 = new ItemLine(context, 60);
		hLine    = new ItemLineHorizontal(context, 320);
		song     = s;
		topLayout = new LinearLayout(context);
		
		countTxtParams  = new LinearLayout.LayoutParams(30, 20);
		titleTxtParams  = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 20);
		timeTxtParams   = new LinearLayout.LayoutParams(50, 20);
		lineParams      = new LinearLayout.LayoutParams(1, 40);
		line2Params      = new LinearLayout.LayoutParams(1, 40);
		topLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		hLineParams 	= new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,1);
		
		countTxt.setText(Integer.toString(count_));
		
		if(song.getTitle().length() > 34)
			titleTxt.setText(song.getTitle().subSequence(0, 34) + "..." );
		else
			
			titleTxt.setText(song.getTitle());			
		
		//text settings
		countTxt.setTextColor(Color.BLACK);
		titleTxt.setTextColor(Color.BLACK);
		timeTxt.setTextColor(Color.BLACK);
		
		countTxt.setTypeface(Typeface.DEFAULT_BOLD);
		titleTxt.setTypeface(Typeface.DEFAULT_BOLD);
		timeTxt.setTypeface(Typeface.DEFAULT_BOLD);
		
		
		//layout settings
		titleTxtParams.leftMargin = 5;
		titleTxtParams.topMargin  = 10;
		
		countTxtParams.leftMargin = 7;
		countTxtParams.topMargin  = 12;
		
		timeTxtParams.leftMargin  = 5;
		timeTxtParams.topMargin   = 10;		
		
		topLayout.setId(0);
		topLayout.setBackgroundColor(Color.WHITE);
		
		topLayout.addView(countTxt,countTxtParams);
		topLayout.addView(line,lineParams);
		topLayout.addView(titleTxt, titleTxtParams);
		
		hLineParams.addRule(RelativeLayout.BELOW,0);
		
		addView(topLayout, topLayoutParams);		
		addView(hLine,hLineParams);
	}

}
