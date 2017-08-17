package com.example.giao_dien;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

//import com.example.sieuthi_listview.sieuthi;


//import com.example.sieuthi_listview.R;
//import com.example.sieuthi_listview.sieuthi;


//import com.example.sieuthi_listview.chitiet;
//import com.example.sieuthi_listview.sieuthi;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.sieuthi_listview.MainActivity;
//import com.example.sieuthi_listview.R;
//import com.example.sieuthi_listview.SieuthiAdapter;
//import com.example.sieuthi_listview.sieuthi;
//import com.example.sieuthi_listview.MainActivity.docJSON;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Home_giaodien extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	ListView listHome;  //List view home  - khai bao
	ArrayList<getsetHome> danhsach_home;  //danh sach
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
		setContentView(R.layout.listview_home); //id list view home
		listHome = (ListView)findViewById(R.id.listview_home); //id list view home
		danhsach_home = new ArrayList<getsetHome>();
		
		
		
		  //lấy data khi click item trong listview:
			listHome.setOnItemClickListener(new OnItemClickListener() {
	
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					
					Intent i1 = new Intent(getApplicationContext(), ListViewAdapter_home.class);
	            	getsetHome st = (getsetHome) parent.getItemAtPosition(position);
	            	Bundle b = new Bundle();
	            	b.putSerializable("ct_home", st);
	            	i1.putExtras(b);
					startActivity(i1);
					
				}
			});
			
			new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php?tinh=1");
		
		// Generate sample data
		rank = new String[] { "Bún thái tôm yum", "Bò bít tết", "Bò lúc lắc"};

		country = new String[] { "- Tôm sú: 300 gram - Mực: 300 gram- Thịt bò: 200 gram- Đậu hũ non: 2 cây- Nấm rơm: 200 gram- Cà chua: 3 quả- Ớt: 4 quả- Sả: 3 nhánh", "Thịt bò: 200 gram- Đậu hũ non: 2 cây- Nấm rơm: 200 gram- Cà chua: 3 quả- Ớt: 4 quả- Sả: 3 nhánh", "Thịt bò: 200 gram- Đậu hũ non: 2 cây- Nấm rơm: 200 gram- Cà chua: 3 quả- Ớt: 4 quả- Sả: 3 nhánh" };

		population = new String[] { "Bắc nồi lên bếp, cho dầu ăn vào, chờ dầu nóng cho sả, ớt, tỏi, sa tế vào phi thơm. Trút cà chua vào xào sơ cho đến khi cà ra nước màu vàng đẹp thì cho nước dùng cùng sả dập giập, riềng vào nấu cùng.", "Bắc nồi lên bếp, cho dầu ăn vào, chờ dầu nóng cho sả, ớt, tỏi, sa tế vào phi thơm. Trút cà chua vào xào sơ cho đến khi cà ra nước màu vàng đẹp thì cho nước dùng cùng sả dập giập, riềng vào nấu cùng.",
				"Bắc nồi lên bếp, cho dầu ăn vào, chờ dầu nóng cho sả, ớt, tỏi, sa tế vào phi thơm. Trút cà chua vào xào sơ cho đến khi cà ra nước màu vàng đẹp thì cho nước dùng cùng sả dập giập, riềng vào nấu cùng." };
		flag = new int[] { R.drawable.hinh1, R.drawable.hinh2,
				R.drawable.hinh3 };

		// Locate the ListView in listview_main.xml
		listHome = (ListView) findViewById(R.id.listview_home);

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
		listHome.setAdapter(adapter);
		
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
	
///////////////////////////////////////////////////////////////////////////////////////
	////class json//
	class docJSON extends AsyncTask<String, Integer, String> {
    	ProgressDialog dialog;
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Home_giaodien.this);
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
        	danhsach_home = new ArrayList<getsetHome>();
            try {
                JSONArray mang = new JSONArray(s);
                for(int i = 0; i < mang.length(); i++)
                {
                    JSONObject sthi = mang.getJSONObject(i);
                    danhsach_home.add(
                            new getsetHome(
                                    sthi.getInt("st_id"),
                                    sthi.getString("st_ten"),
                                    sthi.getString("st_diachi"),
                                    sthi.getString("st_tinh"),
                                    sthi.getString("st_sdt"),
                                    sthi.getString("st_time"),
                                    sthi.getString("st_thongtin"),
                                    sthi.getString("st_hinhanh"),
                                    sthi.getString("st_icon")
                            )
                    );
                }

              SieuthiAdapter adapter = new SieuthiAdapter(
                        MainActivity.this,
                        R.layout.list_row,
                        dsST
                );
                
         /*       ArrayAdapter<String> adapter = new ArrayAdapter<String>(HoaActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, dsHH);*/
            
             //Sort theo tên: 
              Collections.sort(dsST, new Comparator<sieuthi>() {

				@Override
				public int compare(sieuthi lhs, sieuthi rhs) {
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
	///end class json/
	
	
//// String docNoiDung_Tu_URL
	private String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();

    }
	
	////end String docNoiDung_Tu_URL

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
			Intent i2 = new Intent(Home_giaodien.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Home_giaodien.this, Home_giaodien.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Home_giaodien.this, Nguoi_Beo_giaodien.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Home_giaodien.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Home_giaodien.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Home_giaodien.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(Home_giaodien.this, nguyen_lieu.class);
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
			((Home_giaodien) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
