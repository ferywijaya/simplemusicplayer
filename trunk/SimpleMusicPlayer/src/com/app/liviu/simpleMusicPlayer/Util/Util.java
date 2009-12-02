package com.app.liviu.simpleMusicPlayer.Util;

public class Util 
{
	public static String intToStringTimeFormat(int time)
	{
		String strTemp = new String();		
		int minutes    = time / 60;
		int seconds    = time % 60;
		
		if(minutes < 10)
			strTemp = "0" + Integer.toString(minutes) + ":";
		else
			strTemp = Integer.toString(minutes) + ":";
		
		if(seconds < 10)
			strTemp = strTemp + "0" + Integer.toString(seconds);
		else
			strTemp = strTemp + Integer.toString(seconds);
		
		return strTemp;
	}
}
