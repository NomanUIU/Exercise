package com.noman.icaremyself.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "icare.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER_PROFILE = "userprofile";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_USER_NAME = "name";
	public static final String COL_USER_DOB = "dob";
	public static final String COL_USER_HEIGHT = "height";
	public static final String COL_USER_WEIGHT = "weight";
	public static final String COL_USER_GENDER = "gender";


	// Database creation sql statement
	//Table for User
	private static final String TABLE_CREATE_USER = "create table "
			+ TABLE_USER_PROFILE + "(" + COL_USER_ID
			+ " integer primary key autoincrement, "
			+ COL_USER_NAME + " text not null," 
			+ COL_USER_DOB + " text not null," 
			+ COL_USER_HEIGHT + " text not null,"
			+ COL_USER_WEIGHT + " text not null,"
			+ COL_USER_GENDER + "  text not null);";
	
	
	
	public static final String TABLE_DOCTOR_PROFILE = "doctorprofile";
	public static final String COL_DOCTOR_ID = "doctor_id";
	public static final String COL_DOCTOR_NAME = "name";
	public static final String COL_DOCTOR_DESIGNATION = "designation";
	public static final String COL_DOCTOR_SPECIALIZATION = "specialization";
	public static final String COL_DOCTOR_EMAIL = "email";
	public static final String COL_DOCTOR_PHONE = "phone";
	public static final String COL_DOCTOR_ADDRESS = "address";
	

	
	
	//Table for doctor
	private static final String TABLE_CREATE_DOCTOR = "create table "
			+ TABLE_DOCTOR_PROFILE + "(" + COL_DOCTOR_ID
			+ " integer primary key autoincrement, "
			+ COL_DOCTOR_NAME + " text not null," 
			+ COL_DOCTOR_DESIGNATION + " text not null,"
			+ COL_DOCTOR_SPECIALIZATION + " text not null ,"
			+ COL_DOCTOR_EMAIL + " text not null ," 
			+ COL_DOCTOR_PHONE + "  text not null, "
			+ COL_DOCTOR_ADDRESS + " text not null);";
	
	
	public static final String TABLE_HEALTH_INFO = "healthinfo";
	public static final String COL_HEALTH_ID = "health_id";
	public static final String COL_HEALTH_DATE = "date";
	public static final String COL_HEALTH_TIME = "time";
	public static final String COL_HEALTH_NOTE = "note";
	public static final String COL_IMAGE = "image";



	
	
	
	// Database creation sql statement
	//Table for health Info
	private static final String TABLE_CREATE_HEALTH = "create table "
			+ TABLE_HEALTH_INFO + "(" + COL_HEALTH_ID
			+ " integer primary key autoincrement, " 
			+ COL_HEALTH_DATE + " text not null," 
			+ COL_HEALTH_TIME + " text not null,"
			+ COL_HEALTH_NOTE + " text not null ,"
			+ COL_IMAGE + " text not null);";
	
	
	
	

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		database.execSQL(TABLE_CREATE_USER);
		database.execSQL(TABLE_CREATE_DOCTOR);
		database.execSQL(TABLE_CREATE_HEALTH);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTH_INFO);
		onCreate(db);
	}

}

