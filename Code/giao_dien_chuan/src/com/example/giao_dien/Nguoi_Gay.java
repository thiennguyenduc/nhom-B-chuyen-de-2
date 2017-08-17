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

public class Nguoi_Gay extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	ListView list;
	ListViewAdapter_nguoigay adapter;
	EditText editsearch;
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;
	ArrayList<WorldPopulation_nguoigay> arraylist = new ArrayList<WorldPopulation_nguoigay>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main_nguoigay);

		// Generate sample data
		rank = new String[] {
				"Rau Cu Kho Quet", 
				"Nhan Sam Ham Ga", 
				"Muc Xao Nam", 
				"Ngoc Truc Tim Heo"};

		country = new String[] { 
				"Cải ngọt, cải thìa, cải xanh, cải thảo, rau muống, cà rốt …", 
				"1 con gà làm sạch, 1 củ sâm, 2 củ tỏi",  
				"1 đầu mực, 200g nạc ngêu, 200g nấm đông cô tươi, 1 củ hành tây, 100g đầu hành lá cắt khúc, 1 muỗng cà phê muối, 2 muỗng cà phê tiêu, 2 muỗng canh bột bắp …",
				"Tim heo 100g, ngọc trúc 500g, gừng tười 1 củ, vài tép hành lá, tiêu, dầu mè" };
		
		population = new String[] { 
				"Luột các loại rau củ theo nguyên tắc loại nào cứng thì luột trước, đợi cho nước thật sôi rồi bỏ rau vào.", 
				"Cho vào nồi kính, châm nước ngập gà, đun sôi và vặn lửa thật nhỏ. Hầm đến khi gà thật sự mềm sau đó bỏ tất cả nguyên liệu đi kèm vào.",
				"Cho đầu mực vào lửa lớn đun sôi 1 phút với gừng đập dập, nạc ngêu rửa sạch, hành tây bóc vỏ cắt sợi, cho tất cả gia vị vào xào 2 phút trên lửa lớn sau đó nhắc xuống bếp trang trí .",
				"Ngọc trúc cho vào nồi, cho 2 lít nước vào nấu, để lửa vừa quan sát thấy sắc lại còn khoảng 1 lít nước, các bạn vớt bỏ bã lấy nước, cho tiếp gừng, tiêu, hành và tim heo nấu chín, nêm chút dầu mè, nên vừa ăn là được.", };
		
		flag = new int[] { 
				R.drawable.hinh1,
				R.drawable.hinh2,
				R.drawable.hinh3,
				R.drawable.hinh4};

		// Locate the ListView in listview_main.xml
		list = (ListView) findViewById(R.id.listview);

		for (int i = 0; i < rank.length; i++) 
		{
			WorldPopulation_nguoigay wp = new WorldPopulation_nguoigay(rank[i], country[i],
					population[i],flag[i]);
			// Binds all strings into an array
			arraylist.add(wp);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter_nguoigay(this, arraylist);
		
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
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(Nguoi_Gay.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Nguoi_Gay.this, Home.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Nguoi_Gay.this, Nguoi_Beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Nguoi_Gay.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Nguoi_Gay.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Nguoi_Gay.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(Nguoi_Gay.this, nguyen_lieu.class);
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
			((Nguoi_Gay) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
