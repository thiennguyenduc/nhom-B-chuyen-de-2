package com.androidbegin.filterlistviewtutorial;

import java.util.ArrayList;
import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	// Declare Variables
	ListView list;
	ListViewAdapter adapter;
	EditText editsearch;
	String[] rank;
	String[] country;
	String[] population;
	int[] flag;
	ArrayList<WorldPopulation> arraylist = new ArrayList<WorldPopulation>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);

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
			WorldPopulation wp = new WorldPopulation(rank[i], country[i],
					population[i],flag[i]);
			// Binds all strings into an array
			arraylist.add(wp);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter(this, arraylist);
		
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
	}

	// Not using options menu in this tutorial
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
