package com.example.giao_dien;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class FrameByFrameAnimationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_frame_by_frame_animation);
		ImageView img = (ImageView) findViewById(R.id.image);
		img.setVisibility(ImageView.VISIBLE);
		img.setBackgroundResource(R.drawable.frame_animation);
		AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
		if (frameAnimation != null) {
			frameAnimation.start();
		}

		Thread bamgio = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (Exception e) {

				} finally {
					Intent intent = new Intent(FrameByFrameAnimationActivity.this, Getting_start.class);
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
