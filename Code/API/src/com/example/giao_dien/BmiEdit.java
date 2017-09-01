package com.example.giao_dien;

import java.text.DecimalFormat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BmiEdit extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	public static int numTitle = 1;
	private EditText mchieucText;
	private EditText mcannText;
	private EditText mketqText;
	private EditText mchuandText;
	private Button tinhbmi;
	private Button thulai;
	private Button luu;
	private Button lichsu;
	private Long mRowId;

	private Cursor BMI;

	private DbAdapter mDbHelper;
	String NULL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDbHelper = new DbAdapter(this);
		mDbHelper.open();

		setContentView(R.layout.giaodien_bmi_edit);

		mchieucText = (EditText) findViewById(R.id.editChieucao);
		mcannText = (EditText) findViewById(R.id.editCannang);
		mketqText = (EditText) findViewById(R.id.editBMI);
		mchuandText = (EditText) findViewById(R.id.editChuanDoan);
		thulai = (Button) findViewById(R.id.btnThulai);
		lichsu = (Button) findViewById(R.id.btnlichsu);
		tinhbmi = (Button) findViewById(R.id.btnTinhBMI);
		luu = (Button) findViewById(R.id.btnluu);

		mRowId = (savedInstanceState == null) ? null : (Long) savedInstanceState.getSerializable(DbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DbAdapter.KEY_ROWID) : null;
		}

		populateFields();

		tinhbmi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (kiemtra() == true) {
					double C = Double.parseDouble(mchieucText.getText() + "");
					double N = Double.parseDouble(mcannText.getText() + "");

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
					mketqText.setText(dcm.format(bmi));
					mchuandText.setText(chuandoan);
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
		
		luu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String chieuc = mchieucText.getText().toString();
				String cann = mcannText.getText().toString();
				String ketq = mketqText.getText().toString();
				String chuand = mchuandText.getText().toString();

				if (mRowId == null) {
					long id = mDbHelper.create(chieuc, cann, ketq, chuand);
					Toast.makeText(BmiEdit.this,"Đã lưu",Toast.LENGTH_SHORT).show();
					if (id > 0) {
						mRowId = id;
					} else {
						Log.e("saveState", "failed to create BMI");
					}
				} else {
					if (!mDbHelper.update(mRowId, chieuc, cann, ketq, chuand)) {
						Log.e("saveState", "failed to update BMI");
					}
				}
			}
		});

		lichsu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BmiEdit.this, BmiList.class);
				startActivity(intent);
			}
		});

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	public void Xoa() {
		AlertDialog.Builder xoa = new AlertDialog.Builder(BmiEdit.this);

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
				mchieucText.setText("");
				mcannText.setText("");
				mketqText.setText("");
				mchuandText.setText("");

				mcannText.requestFocus();
				Toast t = Toast.makeText(BmiEdit.this, "Xóa thành công ", Toast.LENGTH_SHORT);

				t.show();

			}
		});

		xoa.create().show();
	}

	public boolean kiemtra() {
		String strchieucao = mchieucText.getText() + "";
		String strcannang = mcannText.getText() + "";

		if (strchieucao == NULL) {
			mchieucText.setError("Bạn chưa nhập chiều cao!");
			mchieucText.requestFocus();
			return false;
		} else if (strcannang == NULL) {
			mcannText.setError("Bạn chưa nhập cân nặng!");
			mcannText.requestFocus();
			return false;
		}
		return true;
	}


	private void populateFields() {
		if (mRowId != null) {
			BMI = mDbHelper.fetch(mRowId);
			startManagingCursor(BMI);
			mchieucText.setText(BMI.getString(BMI.getColumnIndexOrThrow(DbAdapter.KEY_TITLE)));
			mcannText.setText(BMI.getString(BMI.getColumnIndexOrThrow(DbAdapter.KEY_HEGHT)));
			mketqText.setText(BMI.getString(BMI.getColumnIndexOrThrow(DbAdapter.KEY_RESUT)));
			mchuandText.setText(BMI.getString(BMI.getColumnIndexOrThrow(DbAdapter.KEY_GUEST)));
		}
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
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			Intent i2 = new Intent(BmiEdit.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(BmiEdit.this, Home.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(BmiEdit.this, Nguoi_Beo.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(BmiEdit.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(BmiEdit.this, Nguoi_Benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(BmiEdit.this, BmiEdit.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(BmiEdit.this, nguyen_lieu.class);
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
			((BmiEdit) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

}
