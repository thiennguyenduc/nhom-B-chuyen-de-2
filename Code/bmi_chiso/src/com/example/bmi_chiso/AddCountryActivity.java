package com.example.bmi_chiso;

import java.text.DecimalFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCountryActivity extends Activity {

	private Button tinhbmi, luu, thulai, lichsu;
	private EditText chieucao, cannang, ketqua, chuandoanbmi;

	private DBManager dbManager;
	String NULL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_record);

		chieucao = (EditText) findViewById(R.id.editChieucao);
		cannang = (EditText) findViewById(R.id.editCannang);
		ketqua = (EditText) findViewById(R.id.editBMI);
		chuandoanbmi = (EditText) findViewById(R.id.editChuanDoan);

		tinhbmi = (Button) findViewById(R.id.btnTinhBMI);
		luu = (Button) findViewById(R.id.add_record);
		thulai = (Button) findViewById(R.id.btnThulai);
		lichsu = (Button) findViewById(R.id.btnlichsu);

		dbManager = new DBManager(this);
		dbManager.open();
		luu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.add_record:

					final String chieuc = chieucao.getText().toString();
					final String cann = cannang.getText().toString();
					final String ketq = ketqua.getText().toString();
					final String chuand = chuandoanbmi.getText().toString();

					dbManager.insert(chieuc, cann, ketq, chuand);

				}
			}
		});

		lichsu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent main = new Intent(AddCountryActivity.this, CountryListActivity.class);
				startActivity(main);
			}
		});

		tinhbmi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (kiemtra() == true) {
					double C = Double.parseDouble(chieucao.getText() + "");
					double N = Double.parseDouble(cannang.getText() + "");

					double bmi = N / Math.pow(C, 2);

					String chuandoan = "";

					if (bmi < 18) {
						chuandoan = "Bạn gầy";
					} else if (bmi <= 24.9) {
						chuandoan = "bạn bình thường";
					} else if (bmi <= 29.9) {
						chuandoan = "bạn béo phì cấp độ 1";
					} else if (bmi <= 34.9) {
						chuandoan = "bạn béo phì cấp độ 2";
					} else {
						chuandoan = "bạn béo phì cấp độ 3";
					}

					DecimalFormat dcm = new DecimalFormat("#.0");
					ketqua.setText(dcm.format(bmi));
					chuandoanbmi.setText(chuandoan);
				} else {
					kiemtra();
				}
			}
		});

		thulai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Xoa();
			}
		});
	}

	
	
	public void Xoa() {
		AlertDialog.Builder xoa = new AlertDialog.Builder(AddCountryActivity.this);

		xoa.setTitle("Xóa thông tin  ");
		xoa.setMessage("Bạn muốn xóa thông tin  ? ");

		xoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		xoa.setPositiveButton("Xóa thông tin ", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				cannang.setText("");
				chieucao.setText("");
				ketqua.setText("");
				chuandoanbmi.setText("");

				cannang.requestFocus();
				Toast t = Toast.makeText(AddCountryActivity.this, "Xóa thành công ", Toast.LENGTH_SHORT);

				t.show();

			}
		});

		xoa.create().show();
	}

	public boolean kiemtra() {
		String strchieucao = chieucao.getText() + "";
		String strcannang = cannang.getText() + "";

		if (strchieucao == NULL) {
			chieucao.setError("Bạn chưa nhập chiều cao!");
			chieucao.requestFocus();
			return false;
		} else if (strcannang == NULL) {
			cannang.setError("Bạn chưa nhập cân nặng!");
			cannang.requestFocus();
			return false;
		}
		return true;
	}

}