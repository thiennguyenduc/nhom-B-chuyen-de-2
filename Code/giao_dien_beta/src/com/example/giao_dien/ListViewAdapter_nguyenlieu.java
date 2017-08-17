package com.example.giao_dien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ListViewAdapter_nguyenlieu extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<DiaChiAll> worldpopulationlist = null;
	private ArrayList<DiaChiAll> arraylist;

	public ListViewAdapter_nguyenlieu(Context context,
			List<DiaChiAll> worldpopulationlist) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<DiaChiAll>();
		this.arraylist.addAll(worldpopulationlist);
	}

	public class ViewHolder {
		TextView tencuahang;
		TextView diachi;
		TextView sdt;
		ImageView flag;
	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public DiaChiAll getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item_nguyenlieusach, null);
			// Locate the TextViews in listview_item.xml
			holder.tencuahang = (TextView) view.findViewById(R.id.rank);
			holder.diachi = (TextView) view.findViewById(R.id.country);
			holder.sdt = (TextView) view.findViewById(R.id.population);
			// Locate the ImageView in listview_item.xml
			holder.flag = (ImageView) view.findViewById(R.id.flag);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.tencuahang.setText(worldpopulationlist.get(position).getTencuahang());
		holder.diachi.setText(worldpopulationlist.get(position).getDiachi());
		holder.sdt.setText(worldpopulationlist.get(position)
				.getSdt());
		// Set the results into ImageView
		holder.flag.setImageResource(worldpopulationlist.get(position)
				.getFlag());
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(mContext, SingleItemView_nguoigay.class);
				// Pass all data rank
				intent.putExtra("tencuahang",
						(worldpopulationlist.get(position).getTencuahang()));
				// Pass all data country
				intent.putExtra("diachi",
						(worldpopulationlist.get(position).getDiachi()));
				// Pass all data population
				intent.putExtra("sdt",
						(worldpopulationlist.get(position).getSdt()));
				// Pass all data flag
				intent.putExtra("flag",
						(worldpopulationlist.get(position).getFlag()));
				// Start SingleItemView Class
				mContext.startActivity(intent);
			}
		});

		return view;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
		} else {
			for (DiaChiAll wp : arraylist) {
				if (wp.getDiachi().toLowerCase(Locale.getDefault())
						.contains(charText)) {
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}

}
