package com.example.giao_dien;

import java.text.DecimalFormat;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mainbmi extends Activity {
	
	EditText editchieucao, editcannang, editbmi, editchuandoan;
	Button btnthulai, btnlichsu, btntinhbmi;
	String NULL = ""; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.giaodien_bmi);
		
		editchieucao = (EditText) findViewById(R.id.editChieucao);
		editcannang = (EditText) findViewById(R.id.editCannang);
		editchuandoan = (EditText) findViewById(R.id.editChuanDoan);
		editbmi = (EditText) findViewById(R.id.editBMI);
		btnthulai = (Button) findViewById(R.id.btnThulai);
		btnlichsu = (Button) findViewById(R.id.btnlichsu);
		btntinhbmi = (Button) findViewById(R.id.btnTinhBMI);		
		
		btnlichsu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Mainbmi.this, Mainlsbmi.class);
				startActivity(intent);
			}
		});
		
		btntinhbmi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(kiemtra() == true){
					double C = Double.parseDouble(editchieucao.getText()+"");
					double N = Double.parseDouble(editcannang.getText()+"");
					
					double bmi = N/Math.pow(C, 2);
					
					String chuandoan = "";
					
					if(bmi < 18){
						chuandoan = "Bạn gầy";
					}
					else if(bmi <= 24.9){
						chuandoan = "bạn bình thường";
					}
					else if(bmi <= 29.9){
						chuandoan = "bạn béo phì cấp độ 1";
					}
					else if(bmi <= 34.9){
						chuandoan = "bạn béo phì cấp độ 2";
					}
					else {
						chuandoan = "bạn béo phì cấp độ 3";
					}
					
					DecimalFormat dcm = new DecimalFormat("#.0");
					editbmi.setText(dcm.format(bmi));
					editchuandoan.setText(chuandoan);
				}
				else {
					kiemtra();
				}
			}
		});
		
		btnthulai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Xoa();
			}
		});
	}
	
	public void Xoa() {
		AlertDialog.Builder xoa = new AlertDialog.Builder(Mainbmi.this) ; 
			
			xoa.setTitle("Xóa thông tin  ") ; 
			xoa.setMessage("Bạn muốn xóa thông tin  ? ") ; 
			
			xoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel() ; 
				}
			}) ; 
			
			xoa.setPositiveButton("Xóa thông tin ", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					editcannang.setText("") ; 
					editchieucao.setText("") ; 
					editbmi.setText("") ; 
					editchuandoan.setText("") ; 
					
					editcannang.requestFocus() ; 
					Toast t = Toast.makeText(Mainbmi.this, "Xóa thành công ", Toast.LENGTH_SHORT) ;

					t.show() ; 
					
				}
			}) ; 

			xoa.create().show() ; 
	}
	
	public boolean kiemtra(){
		String strchieucao = editchieucao.getText()+"";
		String strcannang = editcannang.getText()+"";

		if(strchieucao == NULL){
			editchieucao.setError("Bạn chưa nhập chiều cao!");
			editchieucao.requestFocus();
			return false;
		}
		else if(strcannang == NULL){
			editcannang.setError("Bạn chưa nhập cân nặng!");
			editcannang.requestFocus();
			return false;
		}
		return true;
	}
}
