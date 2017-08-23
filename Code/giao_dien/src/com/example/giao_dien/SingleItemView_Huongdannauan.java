package com.example.giao_dien;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView_Huongdannauan extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{
	TextView textViewten, textViewmota;
	ImageView imagess;
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleitemview_huongdannauan);

		textViewten = (TextView) findViewById(R.id.textViewten);
		textViewmota = (TextView) findViewById(R.id.textViewmota);
		imagess = (ImageView) findViewById(R.id.imagesieuthi);

		Bundle b = getIntent().getExtras();
		if (b != null) {
			getset_Huongdannauan st = (getset_Huongdannauan) b.getSerializable("ct_st");

			String ten = st.getName();
			textViewten = (TextView) findViewById(R.id.textViewten);
			textViewten.setText(ten);

			String mota = st.getMota();
			textViewmota = (TextView) findViewById(R.id.textViewmota);
			textViewmota.setText(mota);

			String url = st.getHinh();
			ImageDownloadMessageHandler ImageloadHandler = new ImageDownloadMessageHandler(imagess);
			GetImageThread LoadImageThread = new GetImageThread(ImageloadHandler, url);
			LoadImageThread.start();

		}
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));


	}

	class GetImageThread extends Thread {
		ImageDownloadMessageHandler mGetImageHandler;
		String mUrl;

		public GetImageThread(ImageDownloadMessageHandler getImageHandler, String ImageUrl) {
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

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
				.commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			Intent i1 = new Intent(SingleItemView_Huongdannauan.this, Getting_start.class);
			startActivity(i1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(SingleItemView_Huongdannauan.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(SingleItemView_Huongdannauan.this, Huongdannauan.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(SingleItemView_Huongdannauan.this, Nguoi_beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(SingleItemView_Huongdannauan.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(SingleItemView_Huongdannauan.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(SingleItemView_Huongdannauan.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(SingleItemView_Huongdannauan.this, nguyen_lieu.class);
			startActivity(i8);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	public static class PlaceholderFragment extends Fragment {

		private static final String ARG_SECTION_NUMBER = "section_number";

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((SingleItemView_nguyenlieu) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
	
}
