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

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		this.setMeasuredDimension(MeasureWidth(widthMeasureSpec), 
								MeasureHeight(heightMeasureSpec));		
	}

	private int MeasureHeight(int heightMeasureSpec) 
	{
		int result	 = 0;
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
    
        if (this.orientation==HORIZONTAL) 
        {
	        int heightText = (int)(paint.descent()- paint.ascent()) + this.getPaddingTop()+this.getPaddingBottom() +5;
	        result         = Math.min(specSize, heightText);
	        yText          = (int)((result - paint.ascent())/2);
        }
        else 
        {
        	yText  = 0;
        	result = Math.min(specSize, max - min);
        }
        
        return result;
	}
	private int MeasureWidth(int widthMeasureSpec) 
	{
		int result   = 0;
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        
        if (this.orientation==HORIZONTAL) 
        {
        	if (specMode == MeasureSpec.EXACTLY)
        	{
        		result = specSize;
        	}
        	else 
        	{
		    	int widthText = (int) paint.measureText(processingText) + this.getPaddingLeft()+this.getPaddingRight() + 20;
		    	result        = Math.min(widthText, specSize);
        	}
        	
	    	xText = (int) ((result - paint.measureText(processingText))/2);
	    	
        } else 
        {
        	xText = 0;
        	result = Math.min(specSize, 20);
        }
        
    	return result;
 	}
	private void initProgressBar() 
	{	
		backgroundBmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.pbarbackground ,null);
		foregroundBmp = BitmapFactory.decodeResource(context.getResources(),R.drawable.pbarforeground ,null);
		paint		  = new Paint();
		
		paint.setTextSize(12);
		paint.setColor(0xFF668800);
		paint.setStyle(Paint.Style.FILL);
		this.orientation = this.HORIZONTAL;
		this.min=0;
		this.max=100;
		this.textColor = 0xFFFFFFFF;
		this.position=0;
		this.processingText=" ";
		
	}
	
	protected void onDrawBackground(Canvas canvas)
	{
				
		canvas.drawBitmap(backgroundBmp, 0, 0, null);		
	}
	@Override
	protected void onDraw(Canvas canvas) 
	{	
		this.onDrawBackground(canvas);
		
		if (this.orientation==HORIZONTAL) 
		{			
	        paint.setColor(Color.WHITE);        
	        int truepos = (int) (((float)position/(float)(max-min)) * this.getWidth());
	        //canvas.drawRect(0, 0, truepos, this.getHeight(), paint);	
	        //canvas.drawBitmap(foregroundBmp, 4, 3, null);	   
	        canvas.drawBitmap(foregroundBmp, null, new RectF(0,1,truepos,this.getHeight()-2), null);
	        paint.setColor(this.textColor);
	        canvas.drawText(processingText, xText, yText, paint);
		} else 
		{
			//VERTICAL
			
	        paint.setColor(Color.WHITE);        
	        int truepos = (int) (((float)position/(float)(max-min)) * this.getHeight());
	        //canvas.drawRect(0, 0, this.getWidth(), truepos, paint);
	        canvas.drawBitmap(foregroundBmp, null, new RectF(0,1,truepos,this.getHeight()-2), null);
		}        
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
		invalidate();		
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
