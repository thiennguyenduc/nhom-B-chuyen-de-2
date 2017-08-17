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

public class Nguoi_benh extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	ListView list;
	ListViewAdapter_Nguoibenh adapter;
	EditText editsearch;
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;
	ArrayList<WorldPopulation_Nguoibenh> arraylist = new ArrayList<WorldPopulation_Nguoibenh>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_nguoibenh);

		// Generate sample data
		rank = new String[] { "Cháo bồ câu hầm hạt sen", "Cháo bồ câu hầm nấm đông cô", "Cháo bồ câu"};

		country = new String[] { "-Chim bồ câu -Hạt sen -Gạo nếp -Gạo tẻ-Gia vị: nước mắm, hạt nêm, bột ngọt …", "-Chim bồ câu -Nấm đông cô -Gạo nếp -Gạo tẻ-Gia vị: nước mắm, hạt nêm, bột ngọt …", "-Chim bồ câu-Gạo nếp -Gạo tẻ-Gia vị: nước mắm, hạt nêm, bột ngọt …" };

		population = new String[] { "Thịt chim bồ câu và gạo đã chuẩn bị bạn cho vào nồi, đổ ngập nước ninh cùng cho ngọt cháo.Khi cháo nhừ bạn nêm hạt nêm, nước mắm cho vừa ăn, sau đấy đổ hạt sen vào ninh thêm 5 phút thì tắt bếp.", "Thịt chim bồ câu và gạo đã chuẩn bị bạn cho vào nồi, đổ ngập nước ninh cùng cho ngọt cháo.Khi cháo nhừ bạn nêm hạt nêm, nước mắm cho vừa ăn, sau đấy đổ hạt sen vào ninh thêm 5 phút thì tắt bếp.",
				"Thịt chim bồ câu và gạo đã chuẩn bị bạn cho vào nồi, đổ ngập nước ninh cùng cho ngọt cháo.Khi cháo nhừ bạn nêm hạt nêm, nước mắm cho vừa ăn, sau đấy đổ hạt sen vào ninh thêm 5 phút thì tắt bếp." };
		flag = new int[] { R.drawable.hinh4, R.drawable.hinh5,
				R.drawable.hinh6 };

		// Locate the ListView in listview_main.xml
		list = (ListView) findViewById(R.id.listview);

		for (int i = 0; i < rank.length; i++) 
		{
			WorldPopulation_Nguoibenh wp = new WorldPopulation_Nguoibenh(rank[i], country[i],
					population[i],flag[i]);
			// Binds all strings into an array
			arraylist.add(wp);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter_Nguoibenh(this, arraylist);
		
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
			Intent i1 = new Intent(Nguoi_benh.this, Getting_start.class);
			startActivity(i1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(Nguoi_benh.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Nguoi_benh.this, Home.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Nguoi_benh.this, Nguoi_Beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Nguoi_benh.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Nguoi_benh.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Nguoi_benh.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(Nguoi_benh.this, nguyen_lieu.class);
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
			((Nguoi_benh) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
