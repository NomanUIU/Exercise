package com.example.bankinfo.database;

import com.example.bankinfo.database.BankInfoSQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BankInfoSQLiteHelper extends SQLiteOpenHelper {

	public static final String BANK_INFO = "bankinfo";
	public static final String COL_BANK_INFO_ID = "id";
	public static final String COL_BANK_INFO_BANK_NAME = "bankname";
	public static final String COL_BANK_INFO_BRANCH_NAME = "branchname";
	public static final String COL_BANK_INFO_ADDRESS = "address";
	public static final String COL_BANK_INFO_SERVICE = "service";
	public static final String COL_BANK_INFO_CPERSON = "cperson";
	public static final String COL_BANK_INFO_CPERSON_PH = "cpersonph";
	public static final String COL_BANK_INFO_OPEN_TIME = "opentime";
	public static final String COL_BANK_INFO_CLOSE_TIME = "closetime";
	public static final String COL_BANK_INFO_LAT = "lat";
	public static final String COL_BANK_INFO_LONG = "long";

	private static final String DATABASE_NAME = "bankinfo.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE_BANK_INFO = "create table "
			+ BANK_INFO + "(" + COL_BANK_INFO_ID
			+ " integer primary key autoincrement, " + " "
			+ COL_BANK_INFO_BANK_NAME + " text not null, " + " "
			+ COL_BANK_INFO_BRANCH_NAME + " text not null, "+ " "
			+ COL_BANK_INFO_ADDRESS + " text not null, " + " "
			+ COL_BANK_INFO_SERVICE + "text not null, " + " " 
			+ COL_BANK_INFO_CPERSON + " text not null, " + " " 
			+ COL_BANK_INFO_CPERSON_PH + " text not null, " + " "
			+ COL_BANK_INFO_OPEN_TIME + " text not null, " + " "
			+ COL_BANK_INFO_CLOSE_TIME + " text not null, " + " "
			+ COL_BANK_INFO_LAT + " text not null, " + " " 
			+ COL_BANK_INFO_LONG + " text not null);";

	public BankInfoSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_BANK_INFO);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * Log.w(CityProjectSQLiteHelper.class.getName(),
		 * "Upgrading database from version " + oldVersion + " to " + newVersion
		 * + ", which will destroy all old data");
		 */db.execSQL("DROP TABLE IF EXISTS " + BANK_INFO);

		onCreate(db);
	}

}
