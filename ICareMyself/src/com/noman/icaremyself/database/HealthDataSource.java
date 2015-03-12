package com.noman.icaremyself.database;

import java.util.ArrayList;
import java.util.List;

import com.noman.icaremyself.model.DoctorModel;
import com.noman.icaremyself.model.HealthModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class HealthDataSource {



		// Database fields
		private SQLiteDatabase mHealthDatabase;
		private SQLiteHelper mHealthDbHelper;

		List<HealthModel> mHealth = new ArrayList<HealthModel>();

		public HealthDataSource(Context context) {
			mHealthDbHelper = new SQLiteHelper(context);
		}

		/*
		 * open a method for writable database
		 */
		public void open() throws SQLException {
			mHealthDatabase = mHealthDbHelper.getWritableDatabase();
		}

		/*
		 * close database connection
		 */
		public void close() {
			mHealthDbHelper.close();
		}

		/*
		 * insert data into the database.
		 */

		public boolean insert(HealthModel healthInfo) {

			this.open();

			ContentValues cv = new ContentValues();

			cv.put(SQLiteHelper.COL_HEALTH_DATE, healthInfo.getmDate());
			cv.put(SQLiteHelper.COL_HEALTH_TIME, healthInfo.getmTime());
			cv.put(SQLiteHelper.COL_HEALTH_NOTE, healthInfo.getmNote());
			cv.put(SQLiteHelper.COL_IMAGE, healthInfo.getmPhotoPath());
			

			Long check = mHealthDatabase.insert(SQLiteHelper.TABLE_HEALTH_INFO, null, cv);
			mHealthDatabase.close();

			this.close();

			if (check < 0)
				return false;
			else
				return true;
		}

		// Updating database by id
		public boolean updateData(long eId, HealthModel eUpdateObject) {
			this.open();
			ContentValues cvUpdate = new ContentValues();

			cvUpdate.put(SQLiteHelper.COL_HEALTH_DATE, eUpdateObject.getmDate());
			cvUpdate.put(SQLiteHelper.COL_HEALTH_TIME, eUpdateObject.getmTime());
			cvUpdate.put(SQLiteHelper.COL_HEALTH_NOTE, eUpdateObject.getmNote());
			cvUpdate.put(SQLiteHelper.COL_IMAGE, eUpdateObject.getmPhotoPath());
			

			int check = mHealthDatabase.update(SQLiteHelper.TABLE_HEALTH_INFO, cvUpdate,
					SQLiteHelper.COL_HEALTH_ID + "=" + eId, null);
			mHealthDatabase.close();

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
				mHealthDatabase.delete(SQLiteHelper.TABLE_HEALTH_INFO,
						SQLiteHelper.COL_HEALTH_ID + "=" + eActivityId, null);
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
		public List<HealthModel> allHealth() {
			this.open();

			Cursor mCursor = mHealthDatabase.query(SQLiteHelper.TABLE_HEALTH_INFO,
					new String[] { 
							
							SQLiteHelper.COL_DOCTOR_ID,
							SQLiteHelper.COL_HEALTH_DATE,
							SQLiteHelper.COL_HEALTH_TIME,
							SQLiteHelper.COL_HEALTH_NOTE,
							SQLiteHelper.COL_IMAGE,

					}, null, null, null,null,null,null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						String mActivityId = mCursor.getString(mCursor
								.getColumnIndex("doctor_id"));
						String mActivityDate = mCursor.getString(mCursor
								.getColumnIndex("date"));
						String mActivityTime = mCursor.getString(mCursor
								.getColumnIndex("time"));
						String mActivityNote = mCursor.getString(mCursor
								.getColumnIndex("note"));
						String mImage = mCursor.getString(mCursor
								.getColumnIndex("image"));
						

						mHealth.add(new HealthModel(mActivityId, mImage, mActivityDate, mActivityTime, mActivityNote));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return mHealth;
		}

		public HealthModel singleHealthData(long mActivityId) {
			this.open();
			HealthModel informationObject;
			String mId;
			String mDate;
			String mTime;
			String mNote;
			String mPhotoPath;
			

			Cursor mUpdateCursor = mHealthDatabase.query(SQLiteHelper.TABLE_HEALTH_INFO,
					new String[] { SQLiteHelper.COL_HEALTH_ID,
							SQLiteHelper.COL_HEALTH_DATE,
							SQLiteHelper.COL_HEALTH_TIME,
							SQLiteHelper.COL_HEALTH_NOTE,
							SQLiteHelper.COL_IMAGE, },
					
							SQLiteHelper.COL_HEALTH_ID + "=" + mActivityId, null, null,
					null, null);

			mUpdateCursor.moveToFirst();

			mId = mUpdateCursor.getString(0);
			mDate = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_HEALTH_DATE));
			mTime = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_HEALTH_TIME));
			mNote = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_HEALTH_NOTE));
			mPhotoPath = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_IMAGE));
			

			mUpdateCursor.close();
			informationObject = new HealthModel(mId, mDate, mTime, mNote, mPhotoPath);
			this.close();
			return informationObject;
		}
		public HealthModel getHealthById(int id) {
			id=2;
			HealthModel healthProfile = null;
			SQLiteDatabase db = mHealthDbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from healthprofile where health_id='" + id
					+ "'", null);
			if (result.moveToFirst()) {
				do {
					String mId = result.getString(0);
					String mNote = result.getString(1);
					String mDate = result.getString(2);
					String mTime = result.getString(3);
					String mPhotoPath = result.getString(4);
				
					healthProfile = new HealthModel(mId, mDate, mTime, mNote, mPhotoPath);
				} while (result.moveToNext());
			}
			return healthProfile;
		}

	}


	


