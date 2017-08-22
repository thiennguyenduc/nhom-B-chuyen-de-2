package com.example.sieuthi_listview;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.example.sieuthi_listview.SieuthiAdapter.GetImageThread;
import com.example.sieuthi_listview.SieuthiAdapter.ImageDownloadMessageHandler;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.widget.MediaController;
import android.widget.VideoView;

public class chitiet extends Activity {
	TextView textViewten , textViewmota;
	ImageView imagess;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chitiet);
		
		textViewten = (TextView) findViewById(R.id.textViewten);
		textViewmota = (TextView) findViewById(R.id.textViewmota);
		imagess = (ImageView) findViewById(R.id.imagesieuthi);
		
		
		
		
		Bundle b = getIntent().getExtras();
		if(b != null) {
			sieuthi st = (sieuthi) b.getSerializable("ct_st");
			
			String ten = st.getName();
			textViewten = (TextView)findViewById(R.id.textViewten);
			textViewten.setText(ten);
			
			
			String mota = st.getMota();
			textViewmota = (TextView)findViewById(R.id.textViewmota);
			textViewmota.setText(mota);
			
			String url = st.getHinh();
			ImageDownloadMessageHandler ImageloadHandler = new ImageDownloadMessageHandler(imagess);
			GetImageThread LoadImageThread = new GetImageThread(ImageloadHandler, url);
			LoadImageThread.start();
			
			
			VideoView vidView = (VideoView)findViewById(R.id.myVideo);
			MediaController vidControl = new MediaController(this);
			vidControl.setAnchorView(vidView);
			vidView.setMediaController(vidControl);

			String vidAddress = 
					"https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
			Uri vidUri = Uri.parse(vidAddress);
			vidView.setVideoURI(vidUri);
			vidView.start();
}
	
}
	
	
	
	class GetImageThread extends Thread {
		ImageDownloadMessageHandler mGetImageHandler;
		String mUrl;

		public GetImageThread(ImageDownloadMessageHandler getImageHandler,
				String ImageUrl) {
			this.mGetImageHandler = getImageHandler;
			this.mUrl = ImageUrl;
		}

		@Override
		public void run() {
			Drawable drawable = LoadImageFromWeb(mUrl);
			Message message = mGetImageHandler.obtainMessage(1, drawable);
			mGetImageHandler.sendMessage(message);
			System.out.println("Message sent");
		}
	}

	class ImageDownloadMessageHandler extends Handler {
		View imageTextView;

		public ImageDownloadMessageHandler(View imageTextView) {
			this.imageTextView = imageTextView;
		}

		@Override
		public void handleMessage(Message message) {
			imageTextView.setBackgroundDrawable(((Drawable) message.obj));
			imageTextView.setVisibility(View.VISIBLE);
		}
	}

	private Drawable LoadImageFromWeb(String url) {
		Drawable d = null;
		InputStream is = null;
		try {
			is = (InputStream) new URL(url).getContent();
			d = Drawable.createFromStream(is, "src name");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
}
