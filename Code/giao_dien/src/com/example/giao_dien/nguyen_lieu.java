package com.example.giao_dien;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

public class nguyen_lieu extends Activity {

	// Declare Variables
	ListView list;
	ListViewAdapter_nguyenlieu adapter;
	EditText editsearch;
	String[] tencuahang;
	String[] diachi;
	String[] sdt;
	int[] flag;
	ArrayList<DiaChiAll> arraylist = new ArrayList<DiaChiAll>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main_nguyenlieusach);

		// Generate sample data
		tencuahang = new String[] { "Cửa hàng thực phẩm hữu cơ", "Cửa hàng thực phẩm DaLat", "Cửa hàng thực phẩm sạch Cầu Dất Farm", "Cửa hàng thực phẩm sạch Rau cườii Việt Nhật", "Cửa hàng thực phẩm sạch Orifarm" };

		diachi = new String[] { "Organnica Nguyễn Đình Chiểu", "08 Thảo Điền, Khu phố 1", "313 Nguyễn Thị Thập",
				"25 Nguyễn Hữu Cảnh", "51 Lầu 2 Triệu Quang Phục" };

		sdt = new String[] { "016852245", "017854758",
				"0145764572", "018458102", "014584723" };
		
		flag = new int[] { R.drawable.cuahang1, R.drawable.cuahang2,
				R.drawable.cuahang3, R.drawable.cuahang4,
				R.drawable.cuahang5 };

		// Locate the ListView in listview_main.xml
		list = (ListView) findViewById(R.id.listview);

		for (int i = 0; i < tencuahang.length; i++) 
		{
			DiaChiAll wp = new DiaChiAll(tencuahang[i], diachi[i],
					sdt[i], flag[i]);
			// Binds all strings into an array
			arraylist.add(wp);
		}

		// Pass results to ListViewAdapter Class
		adapter = new ListViewAdapter_nguyenlieu(this, arraylist);
		
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
}
