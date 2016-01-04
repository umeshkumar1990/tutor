package com.app.studio.tutor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

	private static final int SPLASH_TIME	=	2000;
	
	private Handler  handler	=	new Handler();
	private Runnable r;
	private Context  context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		context		=	this;
		
		handler.postDelayed(r = new Runnable() {
			
			@Override
			public void run() {
				
				Intent intent	=	new Intent(context, RegistrationActivity.class);
				startActivity(intent);
				finish();
			}
		}, SPLASH_TIME);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(handler != null) {
			handler.removeCallbacks(r);
		}
	}
}
