package com.example.bmi_chiso;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class CountryListActivity extends Activity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.CHIEUCAO, DatabaseHelper.CANNANG,
            DatabaseHelper.KETQUA, DatabaseHelper.CHUANDOAN };

    final int[] to = new int[] {R.id.add_record ,R.id.editChieucao, R.id.editCannang,R.id.editBMI, R.id.editChuanDoan };
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        //Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        //adapter = new SimpleCursorAdapter(this, R.layout.menu, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
            	TextView idTextView = (TextView) view.findViewById(R.id.add_record);
                TextView titleTextView = (TextView) view.findViewById(R.id.subject_edittext);
                TextView descTextView = (TextView) view.findViewById(R.id.description_edittext);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        });*/
    }

}