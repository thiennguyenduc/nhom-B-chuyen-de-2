package com.example.giao_dien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Mainsrceen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wait_screen);
		
		Thread bamgio=new Thread(){
			public void run(){
				try{
					sleep(5000);
				}catch(Exception e){
					
				}finally{
					Intent intent = new Intent(Mainsrceen.this, Getting_start.class);
					startActivity(intent);
					
				}
			}
		};
		bamgio.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
