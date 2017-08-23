package com.example.giao_dien;

import java.text.DecimalFormat;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mainbmi extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

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

				if (kiemtra() == true) {
					double C = Double.parseDouble(editchieucao.getText() + "");
					double N = Double.parseDouble(editcannang.getText() + "");

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
					editbmi.setText(dcm.format(bmi));
					editchuandoan.setText(chuandoan);
				} else {
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

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	}

	public void Xoa() {
		AlertDialog.Builder xoa = new AlertDialog.Builder(Mainbmi.this);

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
				editcannang.setText("");
				editchieucao.setText("");
				editbmi.setText("");
				editchuandoan.setText("");

				editcannang.requestFocus();
				Toast t = Toast.makeText(Mainbmi.this, "Xóa thành công ", Toast.LENGTH_SHORT);

				t.show();

			}
		});

		xoa.create().show();
	}

	public boolean kiemtra() {
		String strchieucao = editchieucao.getText() + "";
		String strcannang = editcannang.getText() + "";

		if (strchieucao == NULL) {
			editchieucao.setError("Bạn chưa nhập chiều cao!");
			editchieucao.requestFocus();
			return false;
		} else if (strcannang == NULL) {
			editcannang.setError("Bạn chưa nhập cân nặng!");
			editcannang.requestFocus();
			return false;
		}
		return true;
	}

	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
				.commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			Intent i1 = new Intent(Mainbmi.this, Getting_start.class);
			startActivity(i1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(Mainbmi.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(Mainbmi.this, Huongdannauan.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(Mainbmi.this, Nguoi_beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(Mainbmi.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(Mainbmi.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(Mainbmi.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(Mainbmi.this, nguyen_lieu.class);
			startActivity(i8);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	public static class PlaceholderFragment extends Fragment {

		private static final String ARG_SECTION_NUMBER = "section_number";

		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Mainbmi) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
