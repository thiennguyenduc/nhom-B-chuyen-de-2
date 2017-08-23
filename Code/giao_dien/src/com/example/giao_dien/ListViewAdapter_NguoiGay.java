package com.example.giao_dien;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter_NguoiGay extends ArrayAdapter<getset_NguoiGay> {
	LayoutInflater inflater;
	Activity context = null;
	int itemlayout;
	List<getset_NguoiGay> worldpopulationlist = null;
	ArrayList<getset_NguoiGay> arrNews = null;

	public ListViewAdapter_NguoiGay(Activity context, int motSanPham, ArrayList<getset_NguoiGay> objects) {
		super(context, -1, objects);
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arrNews = new ArrayList<getset_NguoiGay>();
		this.arrNews.addAll(worldpopulationlist);
		arrNews = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.listview_item_nguoigay, null);
		}
		ImageView imagess = (ImageView) convertView.findViewById(R.id.imagesieuthi);
		
		TextView txtten = (TextView) convertView.findViewById(R.id.txtten);
		
		
		getset_NguoiGay st = getItem(position);
		if (st != null) {
			
			String url = st.getHinh();
			ImageDownloadMessageHandler ImageloadHandler = new ImageDownloadMessageHandler(imagess);
			GetImageThread LoadImageThread = new GetImageThread(ImageloadHandler, url);
			LoadImageThread.start();
			txtten.setText(st.getName());
			
		}
		return convertView;
	}
	
	
	//load hình lên:
	
	class GetImageThread extends Thread {
		ImageDownloadMessageHandler mGetImageHandler;
		String mUrl;

		public GetImageThread(ImageDownloadMessageHandler getImageHandler,
				String ImageUrl) {
			this.mGetImageHandler = getImageHandler;
			this.mUrl = ImageUrl;
		}

		@Override
		public void run() {
			Drawable drawable = LoadImageFromWeb(mUrl);
			Message message = mGetImageHandler.obtainMessage(1, drawable);
			mGetImageHandler.sendMessage(message);
			System.out.println("Message sent");
		}
	}

	class ImageDownloadMessageHandler extends Handler {
		View imageTextView;

		public ImageDownloadMessageHandler(View imageTextView) {
			this.imageTextView = imageTextView;
		}

		@Override
		public void handleMessage(Message message) {
			imageTextView.setBackgroundDrawable(((Drawable) message.obj));
			imageTextView.setVisibility(View.VISIBLE);
		}
	}

	private Drawable LoadImageFromWeb(String url) {
		Drawable d = null;
		InputStream is = null;
		try {
			is = (InputStream) new URL(url).getContent();
			d = Drawable.createFromStream(is, "src name");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public class ViewHolder {
		ImageView images;
		TextView txtten;
	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public getset_NguoiGay getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arrNews);
		} 
		else 
		{
			for (getset_NguoiGay wp : arrNews) 
			{
				if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) 
				{
					worldpopulationlist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
	}
	
}
