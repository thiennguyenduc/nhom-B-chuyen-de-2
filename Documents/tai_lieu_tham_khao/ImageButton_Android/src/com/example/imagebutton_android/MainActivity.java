package com.example.imagebutton_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	Button imageButton;
	Button imageButton1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageButton1 = (Button) findViewById(R.id.imageButtonSelector);
		imageButton1.setFocusableInTouchMode(true);
		addListenerOnButton();
	}

	public void addListenerOnButton() {

		imageButton = (Button) findViewById(R.id.imageButtonSelector);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(MainActivity.this,
						"Button clicked!",
						Toast.LENGTH_SHORT).show();
				imageButton.setFocusableInTouchMode(false);
				imageButton.setFocusable(false);
			}
		});

	}

}