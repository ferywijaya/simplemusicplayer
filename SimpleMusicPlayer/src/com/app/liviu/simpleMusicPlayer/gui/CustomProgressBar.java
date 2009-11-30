package com.app.liviu.simpleMusicPlayer.gui;

import com.app.liviu.simpleMusicPlayer.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomProgressBar extends View {
	
	public final static int HORIZONTAL  =0;
	public final static int VERTICAL = 0;

	private Paint   paint;
	private String  processingText;
	private int 	textColor;
	private int 	position;
	private int 	min;
	private int 	max;
	private int 	orientation;
	private int 	xText;
	private int 	yText;
	private Context context;
	private Bitmap  backgroundBmp;
	private Bitmap  foregroundBmp;

	/**
	 * @param context
	 */
	public CustomProgressBar(Context ctx) {
		super(ctx);
		context = ctx;		
		initProgressBar();		
	}
	/**
	 * @param context
	 * @param attrs
	 * @param inflateParams
	 */
	public CustomProgressBar(Context context, AttributeSet attrs, int inflateParams) 
	{
		super(context, attrs, inflateParams);		
		initProgressBar();
	}
	
	private void initProgressBar() 
	{	
		BitmapFactory.Options options = new BitmapFactory.Options();
		
		options.inSampleSize = 5;
		backgroundBmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.pbarbackground);
		foregroundBmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.pbarforeground);
		paint		  = new Paint();		
		paint.setTextSize(12);
		paint.setColor(0xFF668800);
		paint.setStyle(Paint.Style.FILL);
		this.orientation = this.HORIZONTAL;
		this.min=0;
		this.max=100;
		this.textColor = 0xFFFFFFFF;
		this.position=0;

		
	}
	
	protected void onDrawBackground(Canvas canvas)
	{
				
		canvas.drawBitmap(backgroundBmp, 0, 0, null);		
	}
	@Override
	protected void onDraw(Canvas canvas) 
	{				
		canvas.drawBitmap(backgroundBmp, 0, 0, null);
        int truepos = (int) (((float)position/(float)(max-min)) * this.getWidth());
        canvas.drawBitmap(foregroundBmp, null, new RectF(0,1,truepos,this.getHeight()-2), null);
		
	}
	
	public final String getProcessingText() 
	{
		return processingText;
	}
	public final void setProcessingText(String processingText) 
	{
		this.processingText = processingText;
	}
	
	public final int getTextColor() 
	{
		return textColor;
	}
	
	public final void setTextColor(int textColor) 
	{
		this.textColor = textColor;
	}
	
	public final int getPosition() 
	{
		return position;
	}
	
	public final void setPosition(int position) 
	{
		this.position = position;
		postInvalidate();		
	}
	
	public final int getMin() 
	{
		return min;
	}
	
	public final void setMin(int min) 
	{
		this.min = min;
	}
	
	public final int getMax() 
	{
		return max;
	}
	
	public final void setMax(int max) 
	{
		this.max = max;
	}
	
	public final void setOrientation(int orientation) 
	{
		this.orientation = orientation;
	}
}
