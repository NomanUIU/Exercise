package com.noman.icaremyself.database;

import java.util.ArrayList;
import java.util.List;

import com.noman.icaremyself.model.UserModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDataSource {

	// Database fields
	private SQLiteDatabase mUserDatabase;
	private SQLiteHelper mUserDbHelper;

	List<UserModel> mUser = new ArrayList<UserModel>();

	public UserDataSource(Context context) {
		mUserDbHelper = new SQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		mUserDatabase = mUserDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		mUserDbHelper.close();
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(UserModel userProfile) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(SQLiteHelper.COL_USER_NAME, userProfile.getmName());
		cv.put(SQLiteHelper.COL_USER_DOB, userProfile.getmDOB());
		cv.put(SQLiteHelper.COL_USER_HEIGHT, userProfile.getmHeight());
		cv.put(SQLiteHelper.COL_USER_WEIGHT, userProfile.getmWeight());
		cv.put(SQLiteHelper.COL_USER_GENDER, userProfile.getmGender());

		Long check = mUserDatabase.insert(SQLiteHelper.TABLE_USER_PROFILE,
				null, cv);
		mUserDatabase.close();

		this.close();

		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eId, UserModel eUpdateObject) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.COL_USER_NAME, eUpdateObject.getmName());
		cvUpdate.put(SQLiteHelper.COL_USER_DOB, eUpdateObject.getmDOB());
		cvUpdate.put(SQLiteHelper.COL_USER_HEIGHT, eUpdateObject.getmHeight());
		cvUpdate.put(SQLiteHelper.COL_USER_WEIGHT, eUpdateObject.getmWeight());
		cvUpdate.put(SQLiteHelper.COL_USER_GENDER, eUpdateObject.getmGender());

		int check = mUserDatabase.update(SQLiteHelper.TABLE_USER_PROFILE,
				cvUpdate, SQLiteHelper.COL_USER_ID + "=" + eId, null);
		mUserDatabase.close();

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long eActivityId) {
		this.open();
		try {
			mUserDatabase.delete(SQLiteHelper.TABLE_USER_PROFILE,
					SQLiteHelper.COL_USER_ID + "=" + eActivityId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<UserModel> allUser() {
		this.open();

		Cursor mCursor = mUserDatabase.query(SQLiteHelper.TABLE_USER_PROFILE,
				new String[] { SQLiteHelper.COL_USER_ID,
						SQLiteHelper.COL_USER_NAME, SQLiteHelper.COL_USER_DOB,
						SQLiteHelper.COL_USER_HEIGHT,
						SQLiteHelper.COL_USER_WEIGHT,
						SQLiteHelper.COL_USER_GENDER

				}, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("user_id"));
					String mActivityName = mCursor.getString(mCursor
							.getColumnIndex("name"));
					String mActivityDOB = mCursor.getString(mCursor
							.getColumnIndex("dob"));
					String mActivityHeight = mCursor.getString(mCursor
							.getColumnIndex("height"));
					String mActivityWeight = mCursor.getString(mCursor
							.getColumnIndex("weight"));
					String mActivityGender = mCursor.getString(mCursor
							.getColumnIndex("gender"));

					mUser.add(new UserModel(mActivityId, mActivityName,
							mActivityDOB, mActivityHeight, mActivityWeight,
							mActivityGender));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mUser;
	}

	public UserModel singleUserData(long mActivityId) {
		this.open();
		UserModel informationObject;
		String mId;
		String mName;
		String mDOB;
		String mHeight;
		String mWeight;
		String mGender;

		Cursor mUpdateCursor = mUserDatabase.query(
				SQLiteHelper.TABLE_USER_PROFILE, new String[] {
						SQLiteHelper.COL_USER_ID, SQLiteHelper.COL_USER_NAME,
						SQLiteHelper.COL_USER_DOB,
						SQLiteHelper.COL_USER_HEIGHT,
						SQLiteHelper.COL_USER_WEIGHT,
						SQLiteHelper.COL_USER_GENDER, },
				SQLiteHelper.COL_USER_ID + "=" + mActivityId, null, null, null,
				null);

		mUpdateCursor.moveToFirst();

		mId = mUpdateCursor.getString(0);
		mName = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_USER_NAME));
		mDOB = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_USER_DOB));
		mHeight = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_USER_HEIGHT));
		mWeight = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_USER_WEIGHT));
		mGender = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(SQLiteHelper.COL_USER_GENDER));

		mUpdateCursor.close();
		informationObject = new UserModel(mId, mName, mDOB, mHeight, mWeight,
				mGender);
		this.close();
		return informationObject;
	}

	public UserModel getUserById(int id) {
		id=2;
		UserModel userProfile = null;
		SQLiteDatabase db = mUserDbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from userprofile where user_id='" + id
				+ "'", null);
		if (result.moveToFirst()) {
			do {
				String userId = result.getString(0);
				String mName = result.getString(1);
				String mDOB = result.getString(2);
				String mHeight = result.getString(3);
				String mWeight = result.getString(4);
				String mGender = result.getString(5);
				userProfile = new UserModel(userId, mName, mDOB, mHeight,
						mWeight, mGender);
			} while (result.moveToNext());
		}
		return userProfile;
	}

}
