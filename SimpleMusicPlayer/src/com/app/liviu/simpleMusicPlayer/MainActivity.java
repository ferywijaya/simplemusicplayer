package com.app.liviu.simpleMusicPlayer;

import com.app.liviu.simpleMusciPlayer.scan.ScanManager;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	ScanManager scanManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        scanManager = ScanManager.getInstance();
        scanManager.scanFiles();
        
    }
}