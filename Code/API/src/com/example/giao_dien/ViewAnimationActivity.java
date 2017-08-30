package com.example.giao_dien;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewAnimationActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_view_animation);
		Button btnAnimate = (Button)findViewById(R.id.btnAnimate);
		final TextView txt = (TextView)findViewById(R.id.txtHello);
		btnAnimate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txt.setAnimation(new ViewAnimation());
			}
		});
	}
}
