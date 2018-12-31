package com.wal.app;

import org.apache.cordova.DroidGap;

import com.wal.app.R;

import android.app.Activity;
import android.os.Bundle;

public class AppActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
        
        //Æô¶¯»­Ãæ
  		super.setIntegerProperty("splashscreen", R.drawable.t);
  		
  		super.loadUrl("file:///android_asset/www/html/login.html",4*1000);
    }
}