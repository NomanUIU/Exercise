package com.noman.icaremyself.database;

import java.util.ArrayList;
import java.util.List;

import com.noman.icaremyself.model.DoctorModel;
import com.noman.icaremyself.model.UserModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DoctorDataSource {



		// Database fields
		private SQLiteDatabase mDoctorDatabase;
		private SQLiteHelper mDoctorDbHelper;

		List<DoctorModel> mDoctor = new ArrayList<DoctorModel>();

		public DoctorDataSource(Context context) {
			mDoctorDbHelper = new SQLiteHelper(context);
		}

		/*
		 * open a method for writable database
		 */
		public void open() throws SQLException {
			mDoctorDatabase = mDoctorDbHelper.getWritableDatabase();
		}

		/*
		 * close database connection
		 */
		public void close() {
			mDoctorDbHelper.close();
		}

		/*
		 * insert data into the database.
		 */

		public boolean insert(DoctorModel doctorProfile) {

			this.open();

			ContentValues cv = new ContentValues();

			cv.put(SQLiteHelper.COL_DOCTOR_NAME, doctorProfile.getmDoctorName());
			cv.put(SQLiteHelper.COL_DOCTOR_DESIGNATION, doctorProfile.getmDesignation());
			cv.put(SQLiteHelper.COL_DOCTOR_SPECIALIZATION, doctorProfile.getmSpecialization());
			cv.put(SQLiteHelper.COL_DOCTOR_EMAIL, doctorProfile.getmEmail());
			cv.put(SQLiteHelper.COL_DOCTOR_PHONE, doctorProfile.getmPhone());
			cv.put(SQLiteHelper.COL_DOCTOR_ADDRESS, doctorProfile.getmAddress());
			
			

			Long check = mDoctorDatabase.insert(SQLiteHelper.TABLE_DOCTOR_PROFILE, null, cv);
			mDoctorDatabase.close();

			this.close();

			if (check < 0)
				return false;
			else
				return true;
		}

		// Updating database by id
		public boolean updateData(long eId, DoctorModel eUpdateObject) {
			this.open();
			ContentValues cvUpdate = new ContentValues();

			cvUpdate.put(SQLiteHelper.COL_DOCTOR_NAME, eUpdateObject.getmDoctorName());
			cvUpdate.put(SQLiteHelper.COL_DOCTOR_DESIGNATION, eUpdateObject.getmDesignation());
			cvUpdate.put(SQLiteHelper.COL_DOCTOR_SPECIALIZATION, eUpdateObject.getmSpecialization());
			cvUpdate.put(SQLiteHelper.COL_DOCTOR_EMAIL, eUpdateObject.getmEmail());
			cvUpdate.put(SQLiteHelper.COL_DOCTOR_PHONE, eUpdateObject.getmPhone());
			cvUpdate.put(SQLiteHelper.COL_DOCTOR_ADDRESS, eUpdateObject.getmAddress());
			
			

			int check = mDoctorDatabase.update(SQLiteHelper.TABLE_DOCTOR_PROFILE, cvUpdate,
					SQLiteHelper.COL_DOCTOR_ID + "=" + eId, null);
			mDoctorDatabase.close();

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
				mDoctorDatabase.delete(SQLiteHelper.TABLE_DOCTOR_PROFILE,
						SQLiteHelper.COL_DOCTOR_ID + "=" + eActivityId, null);
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
		public List<DoctorModel> allDoctor() {
			this.open();

			Cursor mCursor = mDoctorDatabase.query(SQLiteHelper.TABLE_DOCTOR_PROFILE,
					new String[] { SQLiteHelper.COL_DOCTOR_ID,
							SQLiteHelper.COL_DOCTOR_NAME,
							SQLiteHelper.COL_DOCTOR_DESIGNATION,
							SQLiteHelper.COL_DOCTOR_SPECIALIZATION,
							SQLiteHelper.COL_DOCTOR_PHONE,
							SQLiteHelper.COL_DOCTOR_EMAIL,
							SQLiteHelper.COL_DOCTOR_ADDRESS,
							

					}, null, null, null, null, null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						String mActivityId = mCursor.getString(mCursor
								.getColumnIndex("doctor_id"));
						String mActivityName = mCursor.getString(mCursor
								.getColumnIndex("name"));
						String mActivityDesignation = mCursor.getString(mCursor
								.getColumnIndex("designation"));
						String mActivitySpecialization = mCursor.getString(mCursor
								.getColumnIndex("specialization"));
						String mActivityPhone = mCursor.getString(mCursor
								.getColumnIndex("phone"));
						String mActivityEmail = mCursor.getString(mCursor
								.getColumnIndex("email"));
						String mActivityAddress = mCursor.getString(mCursor
								.getColumnIndex("address"));
						
						

						mDoctor.add(new DoctorModel(mActivityId, mActivityName,
								mActivityDesignation, mActivitySpecialization,
								mActivityPhone, mActivityEmail, mActivityAddress));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return mDoctor;
		}

		public DoctorModel singleDoctorData(long mActivityId) {
			this.open();
		DoctorModel informationObject;
			String mId;
			String mDoctorName;
			String mDesignation;
			String mSpecialization;
			String mEmail;
			String mPhone;
			String mAddress;
		
			

			Cursor mUpdateCursor = mDoctorDatabase.query(SQLiteHelper.TABLE_DOCTOR_PROFILE,
					new String[] { SQLiteHelper.COL_DOCTOR_ID,
							SQLiteHelper.COL_DOCTOR_NAME,
							SQLiteHelper.COL_DOCTOR_DESIGNATION,
							SQLiteHelper.COL_DOCTOR_SPECIALIZATION,
							SQLiteHelper.COL_DOCTOR_PHONE,
							SQLiteHelper.COL_DOCTOR_EMAIL,
							SQLiteHelper.COL_DOCTOR_ADDRESS,},
							SQLiteHelper.COL_DOCTOR_ID + "=" + mActivityId, null, null,
					null, null, null);

			mUpdateCursor.moveToFirst();

			mId = mUpdateCursor.getString(0);
			mDoctorName = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_DOCTOR_NAME));
			mDesignation = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_DOCTOR_DESIGNATION));
			mSpecialization = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_DOCTOR_SPECIALIZATION));
			mPhone = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_DOCTOR_PHONE));
			mEmail = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_DOCTOR_EMAIL));
			mAddress = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(SQLiteHelper.COL_DOCTOR_ADDRESS));
			
			

			mUpdateCursor.close();
			informationObject = new DoctorModel(mId, mDoctorName, mDesignation, mSpecialization,
					mPhone, mEmail, mAddress);
			this.close();
			return informationObject;
		}
		public DoctorModel getDoctorById(int id) {
			id=2;
			DoctorModel doctorProfile = null;
			SQLiteDatabase db = mDoctorDbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from doctorprofile where doctor_id='" + id
					+ "'", null);
			if (result.moveToFirst()) {
				do {
					String doctorId = result.getString(0);
					String mDoctorName = result.getString(1);
					String mDesignation = result.getString(2);
					String mSpecialization = result.getString(3);
					String mPhone = result.getString(4);
					String mEmail = result.getString(5);
					String mAddress = result.getString(6);
					doctorProfile = new DoctorModel(doctorId, mDoctorName, mDesignation, mSpecialization,
							mPhone, mEmail, mAddress);
				} while (result.moveToNext());
			}
			return doctorProfile;
		}

	}


