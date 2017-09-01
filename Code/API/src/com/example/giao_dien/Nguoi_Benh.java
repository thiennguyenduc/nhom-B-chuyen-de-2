package com.example.giao_dien;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;

public class Nguoi_Benh extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	ListView listViewsieuthi;
	ArrayList<WorldPopulation_nguoibenh> dsST;
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_nguoibenh);
		listViewsieuthi = (ListView) findViewById(R.id.listViewsieuthi);
		dsST = new ArrayList<WorldPopulation_nguoibenh>();

		// lấy data khi click item trong listview:
		listViewsieuthi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub

				Intent i1 = new Intent(getApplicationContext(), SingleItemView_nguoibenh.class);
				WorldPopulation_nguoibenh st = (WorldPopulation_nguoibenh) parent.getItemAtPosition(position);
				Bundle b = new Bundle();
				b.putSerializable("ct_st", st);
				i1.putExtras(b);
				startActivity(i1);

			}
		});

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		new docJSON().execute("http://lohishop.com/api/index.php?route=product/category&path=94");
	}

	class docJSON extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Nguoi_Benh.this);
			dialog.setMessage("Đang xử lý. Vui lòng chờ trong giây lát...");
			dialog.show();
		}

		@Override
		protected String doInBackground(String... strings) {
			return docNoiDung_Tu_URL(strings[0]);
		}

		@Override
		protected void onPostExecute(String s) {
			dialog.dismiss();
			dsST = new ArrayList<WorldPopulation_nguoibenh>();
			try {
				JSONArray mang = new JSONArray(s);
				for (int i = 0; i < mang.length(); i++) {
					JSONObject sthi = mang.getJSONObject(i);
					dsST.add(new WorldPopulation_nguoibenh(sthi.getInt("product_id"), sthi.getString("name"),
							sthi.getString("description"), sthi.getString("thumb")));
				}

				ListViewAdapter_nguoibenh adapter = new ListViewAdapter_nguoibenh(Nguoi_Benh.this,
						R.layout.listview_item_nguoibenh, dsST);

				/*
				 * ArrayAdapter<String> adapter = new
				 * ArrayAdapter<String>(HoaActivity.this,
				 * android.R.layout.simple_list_item_1, android.R.id.text1,
				 * dsHH);
				 */

				// Sort theo tên:
				Collections.sort(dsST, new Comparator<WorldPopulation_nguoibenh>() {

					@Override
					public int compare(WorldPopulation_nguoibenh lhs, WorldPopulation_nguoibenh rhs) {
						// TODO Auto-generated method stub
						return lhs.getName().compareTo(rhs.getName());
					}

				});

				listViewsieuthi.setAdapter(adapter);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	private String docNoiDung_Tu_URL(String theUrl) {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();

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
			Intent i2 = new Intent(Nguoi_Benh.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Nguoi_Benh.this, Home.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Nguoi_Benh.this, Nguoi_Beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Nguoi_Benh.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Nguoi_Benh.this, Nguoi_Benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Nguoi_Benh.this, BmiEdit.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(Nguoi_Benh.this, nguyen_lieu.class);
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
			((Nguoi_Benh) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

}
