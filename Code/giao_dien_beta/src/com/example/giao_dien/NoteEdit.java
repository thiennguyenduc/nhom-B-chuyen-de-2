package com.example.giao_dien;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NoteEdit extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	
	public static int numTitle = 1;
	public static String curDate = "";
	public static String curText = "";
	private EditText mTitleText;
	private EditText mBodyText;
	private TextView mDateText;
	private Long mRowId;

	private Cursor note;

	private NotesDbAdapter mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDbHelper = new NotesDbAdapter(this);
		mDbHelper.open();

		setContentView(R.layout.note_edit);

		mTitleText = (EditText) findViewById(R.id.title);
		mBodyText = (EditText) findViewById(R.id.body);
		mDateText = (TextView) findViewById(R.id.notelist_date);

		long msTime = System.currentTimeMillis();
		Date curDateTime = new Date(msTime);

		SimpleDateFormat formatter = new SimpleDateFormat("d'/'M'/'y");
		curDate = formatter.format(curDateTime);

		mDateText.setText("" + curDate);

		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID) : null;
		}

		populateFields();

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

	}

	public static class LineEditText extends EditText {
		// we need this constructor for LayoutInflater
		public LineEditText(Context context, AttributeSet attrs) {
			super(context, attrs);
			mRect = new Rect();
			mPaint = new Paint();
			mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			mPaint.setColor(Color.BLACK);
		}

		private Rect mRect;
		private Paint mPaint;

		@Override
		protected void onDraw(Canvas canvas) {

			int height = getHeight();
			int line_height = getLineHeight();

			int count = height / line_height;

			if (getLineCount() > count)
				count = getLineCount();

			Rect r = mRect;
			Paint paint = mPaint;
			int baseline = getLineBounds(0, r);

			for (int i = 0; i < count; i++) {

				canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
				baseline += getLineHeight();

				super.onDraw(canvas);
			}

		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.noteedit_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_delete:
			if (note != null) {
				note.close();
				note = null;
			}
			if (mRowId != null) {
				mDbHelper.deleteNote(mRowId);
			}
			// finish();
			Intent i = new Intent(this, NoteList.class);
			startActivity(i);
			return true;
		case R.id.menu_save:
			saveState();
			// finish();
			Intent i2 = new Intent(this, NoteList.class);
			startActivity(i2);
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void saveState() {
		String title = mTitleText.getText().toString();
		String body = mBodyText.getText().toString();

		if (mRowId == null) {
			long id = mDbHelper.createNote(title, body, curDate);
			if (id > 0) {
				mRowId = id;
			} else {
				Log.e("saveState", "failed to create note");
			}
		} else {
			if (!mDbHelper.updateNote(mRowId, title, body, curDate)) {
				Log.e("saveState", "failed to update note");
			}
		}
	}

	private void populateFields() {
		if (mRowId != null) {
			note = mDbHelper.fetchNote(mRowId);
			startManagingCursor(note);
			mTitleText.setText(note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
			mBodyText.setText(note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
			curText = note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY));
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
			Intent i2 = new Intent(NoteEdit.this, NoteEdit.class);
			startActivity(i2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			Intent i3 = new Intent(NoteEdit.this, Home_giaodien.class);
			startActivity(i3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			Intent i4 = new Intent(NoteEdit.this, Nguoi_Beo_giaodien.class);
			startActivity(i4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			Intent i5 = new Intent(NoteEdit.this, Nguoi_Gay.class);
			startActivity(i5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			Intent i6 = new Intent(NoteEdit.this, Nguoi_benh.class);
			startActivity(i6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			Intent i7 = new Intent(NoteEdit.this, Mainbmi.class);
			startActivity(i7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			Intent i8 = new Intent(NoteEdit.this, nguyen_lieu.class);
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
			((NoteEdit) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

}
