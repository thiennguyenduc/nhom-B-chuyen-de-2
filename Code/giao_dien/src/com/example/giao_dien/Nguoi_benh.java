package com.example.giao_dien;

import java.util.ArrayList;
import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class Nguoi_benh extends Activity {

	// Declare Variables
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
	}

	// Not using options menu in this tutorial
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
