package com.example.sieuthi_listview;

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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ListView;

public class Nguoi_beo extends Activity {
    ListView listView;
    ArrayList<getset_NguoiBenh> dsST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main_nguoibenh);
        listView = (ListView)findViewById(R.id.listView);
        dsST = new ArrayList<getset_NguoiBenh>();
     
        
        //lấy data khi click item trong listview:
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				Intent i1 = new Intent(getApplicationContext(), SingleItemView_Nguoibenh.class);
            	getset_NguoiBenh st = (getset_NguoiBenh) parent.getItemAtPosition(position);
            	Bundle b = new Bundle();
            	b.putSerializable("ct_st", st);
            	i1.putExtras(b);
				startActivity(i1);
				
			}
		});
        
     //   https://hienlth.info/mdata/insert.php?ma="1"&ten=""
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute("https://lequangsang.000webhostapp.com/sieuthi_tinh.php");
            }
        });*/
        new docJSON().execute("http://lohishop.com/api/index.php?route=product/category&path=94");
    }

    class docJSON extends AsyncTask<String, Integer, String> {

    	ProgressDialog dialog;
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Nguoi_beo.this);
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
            dsST = new ArrayList<getset_NguoiBenh>();
            try {
                JSONArray mang = new JSONArray(s);
                for(int i = 0; i < mang.length(); i++)
                {
                    JSONObject sthi = mang.getJSONObject(i);
                    dsST.add(
                            new getset_NguoiBenh(
                                    sthi.getInt("product_id"),
                                    sthi.getString("name"),
                                    sthi.getString("description"),
                                    sthi.getString("thumb")
                            )
                    );
                }

              ListViewAdapter_Nguoibenh adapter = new ListViewAdapter_Nguoibenh(
                        Nguoi_beo.this,
                        R.layout.listview_item_nguoibenh,
                        dsST
                );
                
         /*       ArrayAdapter<String> adapter = new ArrayAdapter<String>(HoaActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, dsHH);*/
            
             //Sort theo tên: 
              Collections.sort(dsST, new Comparator<getset_NguoiBenh>() {

				@Override
				public int compare(getset_NguoiBenh lhs, getset_NguoiBenh rhs) {
					// TODO Auto-generated method stub
					return lhs.getName().compareTo(rhs.getName());
				}
            	  
              });
             
                
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

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

}



/*
public class MainActivity extends Activity {
	
	SieuthiAdapter adapter;
	ListView listViewsieuthi;
	private ArrayList<sieuthi> listData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//1. lấy đối tượng listview từ file xml bằng id:
		listViewsieuthi = (ListView) findViewById(R.id.listViewsieuthi);
		
		//2.khởi tạo danh sách các siêu thị:
		createData();
		//3. Khởi tạo adapter:
		adapter = new SieuthiAdapter(this, listData);
		//4. đưa adapter vào listview:
		listViewsieuthi.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
		
		
		listViewsieuthi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				Intent i1 = new Intent(getApplicationContext(), chitiet.class);
            	sieuthi st = (sieuthi) parent.getItemAtPosition(position);
            	Bundle b = new Bundle();
            	b.putSerializable("ct_st", st);
            	i1.putExtras(b);
				startActivity(i1);
				
			}
		});
		
	}
	
	private void createData(){
		listData = new ArrayList<sieuthi>();
		listData.add(new sieuthi(0,"Siêu Thị BigC Miền Đông" , "138A Tô Hiến Thành, P.15, Q.10, TP.HCM ( Ngã ba Tô Hiến Thành - Sư Vạn Hạnh)" , "02838632990" , "cskh@bigc-vietnam.com" , "http://www.bigc.vn/" , "Bãi đậu xe: - Xe máy: 2.000đ/xe. - Xe đạp: 1.000đ/xe. - Hoàn tiền gửi xe 2.000đ cho Khách hàng có hóa đơn từ 250.000đ trở lên. Giao hàng: Miễn phí giao hàng trong phạm vi 6km với hoá đơn từ 200.000đ trở lên"));
		listData.add(new sieuthi(1,"Siêu Thị BigC An Lạc" , "Quận 6, TP.HCM" , "02838632990" , "cskh@bigc-vietnam.com" , "http://www.bigc.vn/" , "Bãi đậu xe: - Xe máy: 2.000đ/xe. - Xe đạp: 1.000đ/xe. - Hoàn tiền gửi xe 2.000đ cho Khách hàng có hóa đơn từ 1.000.000đ trở lên. Giao hàng: Miễn phí giao hàng trong phạm vi 6km với hoá đơn từ 300.000đ trở lên"));
		listData.add(new sieuthi(2,"Hệ thống siêu thị Co.opmart" , "Trụ sở chính tại 199-205 Nguyễn Thái Học, Phường Phạm Ngũ Lão, Quận 1, Tp.HCM" , "1900.5555.68" , "chamsockhachhang@coopmart.vn" , "http://www.co-opmart.com.vn/" , "Khởi nghiệp từ năm 1989, sau đại hội Đảng lần thứ VI, nền kinh tế đất nước chuyển từ cơ chế bao cấp sang nền kinh tế thị trường theo định hướng XHCN, mô hình kinh tế HTX kiểu cũ thật sự khó khăn và lâm vào tình thế khủng hoảng phải giải thể hàng loạt. Trong bối cảnh như thế, ngày 12/5/1989 - UBND Thành phố Hồ Chí Minh có chủ trương chuyển đổi Ban Quản lý HTX Mua Bán Thành phố trở thành Liên hiệp HTX Mua bán Thành phố Hồ Chí Minh - Saigon Co.op với 2 chức năng trực tiếp kinh doanh và tổ chức vận động phong trào HTX. Saigon Co.op là tổ chức kinh tế HTX theo nguyên tắc xác lập sở hữu tập thể, hoạt động sản xuất kinh doanh tự chủ và tự chịu trách nhiệm.\n\nTừ năm 1992 - 1997, cùng với sự phát triển của nền kinh tế đất nước, các nguồn vốn đầu tư nước ngoài vào Việt Nam làm cho các Doanh nghiệp phải năng động và sáng tạo để nắm bắt các cơ hội kinh doanh, học hỏi kinh nghiệm quản lý từ các đối tác nước ngoài. Saigon Co.op đã khởi đầu bằng việc liên doanh liên kết với các công ty nước ngoài để gia tăng thêm nguồn lực cho hướng phát triển của mình. Là một trong số ít đơn vị có giấy phép XNK trực tiếp của Thành phố, hoạt động XNK phát triển mạnh mẽ mang lại hiệu quả cao, góp phần xác lập uy tín, vị thế của Saigon Co.op trên thị trường trong và ngoài nước.\n\nSự kiện nổi bật nhất là sự ra đời siêu thị đầu tiên của hệ thống siêu thị Co.opmart là Co.opmart Cống Quỳnh vào ngày 09/02/1996, với sự giúp đỡ của các phong trào HTX quốc tế đến từ Nhật, Singapore và Thụy Điển. Từ đấy loại hình kinh doanh bán lẻ mới, văn minh phù hợp với xu hướng phát triển của Thành phố Hồ Chí Minh đánh dấu chặng đường mới của Saigon Co.op."));
	//	for (int i = 0; i<10; i++){
			
	//		listData.add(new sieuthi(i,"Siêu Thị BigC Miền Đông" , "138A Tô Hiến Thành, P.15, Q.10, TP.HCM ( Ngã ba Tô Hiến Thành - Sư Vạn Hạnh)" , "02838632990" , "cskh@bigc-vietnam.com" , "http://www.bigc.vn/" , "Bãi đậu xe: - Xe máy: 2.000đ/xe. - Xe đạp: 1.000đ/xe. - Hoàn tiền gửi xe 2.000đ cho Khách hàng có hóa đơn từ 250.000đ trở lên. Giao hàng: Miễn phí giao hàng trong phạm vi 6km với hoá đơn từ 200.000đ trở lên"));
	//	}
	}

	
	
	
}

*/
