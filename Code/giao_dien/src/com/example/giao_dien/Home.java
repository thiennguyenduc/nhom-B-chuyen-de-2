package com.example.giao_dien;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class Home extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	ListView list;
	ListViewAdapter_home adapter;
	EditText editsearch;
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;
	ArrayList<WorldPopulation_home> arraylist = new ArrayList<WorldPopulation_home>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_home);

		// Generate sample data
		rank = new String[] { "Bún thái tôm yum", "Bò bít tết", "Bò lúc lắc"};

		country = new String[] { "- Tôm sú: 300 gram - Mực: 300 gram- Thịt bò: 200 gram- Đậu hũ non: 2 cây- Nấm rơm: 200 gram- Cà chua: 3 quả- Ớt: 4 quả- Sả: 3 nhánh", "Thịt bò: 200 gram- Đậu hũ non: 2 cây- Nấm rơm: 200 gram- Cà chua: 3 quả- Ớt: 4 quả- Sả: 3 nhánh", "Thịt bò: 200 gram- Đậu hũ non: 2 cây- Nấm rơm: 200 gram- Cà chua: 3 quả- Ớt: 4 quả- Sả: 3 nhánh" };

		population = new String[] { "Bắc nồi lên bếp, cho dầu ăn vào, chờ dầu nóng cho sả, ớt, tỏi, sa tế vào phi thơm. Trút cà chua vào xào sơ cho đến khi cà ra nước màu vàng đẹp thì cho nước dùng cùng sả dập giập, riềng vào nấu cùng.", "Bắc nồi lên bếp, cho dầu ăn vào, chờ dầu nóng cho sả, ớt, tỏi, sa tế vào phi thơm. Trút cà chua vào xào sơ cho đến khi cà ra nước màu vàng đẹp thì cho nước dùng cùng sả dập giập, riềng vào nấu cùng.",
				"Bắc nồi lên bếp, cho dầu ăn vào, chờ dầu nóng cho sả, ớt, tỏi, sa tế vào phi thơm. Trút cà chua vào xào sơ cho đến khi cà ra nước màu vàng đẹp thì cho nước dùng cùng sả dập giập, riềng vào nấu cùng." };
		flag = new int[] { R.drawable.hinh1, R.drawable.hinh2,
				R.drawable.hinh3 };

		// Locate the ListView in listview_main.xml
		list = (ListView) findViewById(R.id.listview);

		for (int i = 0; i < rank.length; i++) 
		{
			WorldPopulation_home wp = new WorldPopulation_home(rank[i], country[i],
					population[i],flag[i]);
			// Binds all strings into an array
			arraylist.add(wp);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter_home(this, arraylist);
		
		// Binds the Adapter to the ListView
		list.setAdapter(adapter);
		
		// Locate the EditText in listview_main.xml
		editsearch = (EditText) findViewById(R.id.search);

		// Capture Text in EditText
		editsearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
				adapter.filter(text);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
			}
		});
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		
	}

	// Not using options menu in this tutorial
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
			Intent i1 = new Intent(Home.this, Getting_start.class);
			startActivity(i1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(Home.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Home.this, Home.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Home.this, Nguoi_Beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Home.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Home.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Home.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(Home.this, nguyen_lieu.class);
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
			((Home) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
