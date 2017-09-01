package com.example.giao_dien;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class TweenAnimationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tween_animation);
		final TextView txtHello = (TextView) findViewById(R.id.txtHello);
		final Animation animation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.tween_animation);
		Button btnAnimate = (Button) findViewById(R.id.btnAnimate);
		btnAnimate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animate(txtHello, animation);
			}
		});
		
	}
	private void animate(TextView tv, Animation animation){
		tv.startAnimation(animation);
	}
}
