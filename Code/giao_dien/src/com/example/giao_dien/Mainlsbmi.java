package com.example.giao_dien;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Mainlsbmi extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	ListView list;
	ListViewAdapter_lsbmi adapter;
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;
	ArrayList<WorldPopulation_lsbmi> arraylist = new ArrayList<WorldPopulation_lsbmi>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main_lsbmi);

		// Generate sample data
		rank = new String[] { "70kg", "60kg", "79kg"};

		country = new String[] { "1.70m", "1.60m", "1.50m" };

		population = new String[] { "Bạn bình thường", "Bạn bình thường",
				"Bạn béo phì cấp độ 1" };
		

		// Locate the ListView in listview_main.xml
		list = (ListView) findViewById(R.id.listview);

		for (int i = 0; i < rank.length; i++) 
		{
			WorldPopulation_lsbmi wp = new WorldPopulation_lsbmi(rank[i], country[i],
					population[i]);
			// Binds all strings into an array
			arraylist.add(wp);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter_lsbmi(this, arraylist);
		
		// Binds the Adapter to the ListView
		list.setAdapter(adapter);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
			Intent i1 = new Intent(Mainlsbmi.this, Getting_start.class);
			startActivity(i1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(Mainlsbmi.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Mainlsbmi.this, Huongdannauan.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Mainlsbmi.this, Nguoi_beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Mainlsbmi.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Mainlsbmi.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Mainlsbmi.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
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
			((Mainlsbmi) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
