package com.app.liviu.simpleMusicPlayer.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

public class ItemLineHorizontal extends View {
	 private static final String TAG = "ItemLine";

	private ShapeDrawable mDrawable;

	public ItemLineHorizontal(Context context, int width) {
		super(context);

		int x = 0;
		int y = 0;
		int height = 1;

		mDrawable = new ShapeDrawable(new RectShape());
		mDrawable.getPaint().setColor(Color.LTGRAY);
		mDrawable.setBounds(x, y, x + width, y + height);
	}

	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);
	}
}