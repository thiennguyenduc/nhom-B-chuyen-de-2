package com.example.giao_dien;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
 
public class SingleItemView_Nguoibeo extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{
	
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	TextView txtrank;
	TextView txtcountry;
	TextView txtpopulation;
	ImageView imgflag;
	String rank;
	String country;
	String population;
	int flag;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleitemview_nguoibeo);
		// Get the intent from ListViewAdapter
		Intent i = getIntent();
		// Get the results of rank
		rank = i.getStringExtra("rank");
		// Get the results of country
		country = i.getStringExtra("country");
		// Get the results of population
		population = i.getStringExtra("population");
		// Get the results of flag
		flag = i.getIntExtra("flag", flag);
 
		// Locate the TextViews in singleitemview.xml
		txtrank = (TextView) findViewById(R.id.rank);
		txtcountry = (TextView) findViewById(R.id.country);
		txtpopulation = (TextView) findViewById(R.id.population);
 
		// Locate the ImageView in singleitemview.xml
		imgflag = (ImageView) findViewById(R.id.flag);
 
		// Load the results into the TextViews
		txtrank.setText(rank);
		txtcountry.setText(country);
		txtpopulation.setText(population);
 
		// Load the image into the ImageView
		imgflag.setImageResource(flag);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

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
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(SingleItemView_Nguoibeo.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(SingleItemView_Nguoibeo.this, Home.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(SingleItemView_Nguoibeo.this, Nguoi_Beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(SingleItemView_Nguoibeo.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(SingleItemView_Nguoibeo.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(SingleItemView_Nguoibeo.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(SingleItemView_Nguoibeo.this, nguyen_lieu.class);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
			((SingleItemView_Nguoibeo) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}