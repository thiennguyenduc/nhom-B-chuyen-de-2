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

public class Mainlsbmi extends Activity {

	// Declare Variables
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
		rank = new String[] { "70kg", "60kg", "79kgc"};

		country = new String[] { "170cm", "160cm", "150cm" };

		population = new String[] { "Báº¡n cÃ³ chá»‰ sá»‘ BMI bÃ¬nh thÆ°á»�ng", "Báº¡n cÃ³ chá»‰ sá»‘ BMI bÃ¬nh thÆ°á»�ng",
				"Báº¡n cÃ³ chá»‰ sá»‘ BMI bÃ¬nh thÆ°á»�ng" };
		

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
		
		// Locate the EditText in listview_main.xml
		

		// Capture Text in EditText

	}

	// Not using options menu in this tutorial
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
