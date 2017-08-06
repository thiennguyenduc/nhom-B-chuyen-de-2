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

public class Nguoi_Gay extends Activity {

	// Declare Variables
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
	}

	// Not using options menu in this tutorial
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
