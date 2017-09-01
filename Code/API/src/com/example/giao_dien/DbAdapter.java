package com.example.giao_dien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

	public static final String KEY_TITLE = "chieuc";
	public static final String KEY_HEGHT = "cann";
	public static final String KEY_RESUT = "ketq";
	public static final String KEY_GUEST = "chuand";
	public static final String KEY_ROWID = "_id";

	private static final String TAG = "DbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_CREATE = "create table BMI (_id integer primary key autoincrement, "
			+ "chieuc text not null, cann text not null, ketq text not null, chuand text not null);";

	private static final String DATABASE_NAME = "value";
	private static final String DATABASE_TABLE = "BMI";
	private static final int DATABASE_VERSION = 2;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
					+ ", which will destroy all old value");
			db.execSQL("DROP TABLE IF EXISTS BMI");
			onCreate(db);
		}
	}

	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public DbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long create(String chieuc, String cann, String ketq, String chuand) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, chieuc);
		initialValues.put(KEY_HEGHT, cann);
		initialValues.put(KEY_RESUT, ketq);
		initialValues.put(KEY_GUEST, chuand);

		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean delete(long rowId) {

		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public Cursor fetchAll() {

		return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE, KEY_HEGHT, KEY_RESUT, KEY_GUEST }, null,
				null, null, null, null);
	}

	public Cursor fetch(long rowId) throws SQLException {

		Cursor mCursor =

				mDb.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE, KEY_HEGHT, KEY_RESUT, KEY_GUEST },
						KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public boolean update(long rowId, String chieuc, String cann, String ketq, String chuand) {
		ContentValues args = new ContentValues();
		args.put(KEY_TITLE, chieuc);
		args.put(KEY_HEGHT, cann);
		args.put(KEY_RESUT, ketq);

		// This lines is added for personal reason
		args.put(KEY_GUEST, chuand);

		// One more parameter is added for data
		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}
