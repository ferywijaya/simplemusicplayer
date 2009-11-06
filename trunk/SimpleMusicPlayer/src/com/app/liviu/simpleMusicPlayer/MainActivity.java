package com.app.liviu.simpleMusicPlayer;

import com.app.liviu.simpleMusciPlayer.database.DatabaseManager;
import com.app.liviu.simpleMusciPlayer.playlist.IdManager;
import com.app.liviu.simpleMusciPlayer.scan.ScanManager;

import android.R.id;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class MainActivity extends Activity {

	ScanManager scanManager;	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        scanManager = ScanManager.getInstance(this);        
                
        scanManager.scanFiles();               
    }
}