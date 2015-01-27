package com.example.bankinfo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bankinfo.util.BankInfoActivityChart;

public class BankInfoDataSource {

	private SQLiteDatabase BankInfoDatabase;
	private BankInfoSQLiteHelper BankInfoDbHelper;


	public BankInfoDataSource(Context context) {
		BankInfoDbHelper = new BankInfoSQLiteHelper(context);
	}

	public void open() throws SQLException {
		BankInfoDatabase = BankInfoDbHelper.getWritableDatabase();
	}

	public void close() {
		BankInfoDbHelper.close();
	}

	public boolean insert(BankInfoActivityChart eActivityChart) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_BANK_NAME,
				eActivityChart.getBankName());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_BRANCH_NAME,
				eActivityChart.getBranchName());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_ADDRESS,
				eActivityChart.getAddress());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_SERVICE,
				eActivityChart.getService());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_CPERSON,
				eActivityChart.getCPerson());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_CPERSON_PH,
				eActivityChart.getCPersonPh());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_OPEN_TIME,
				eActivityChart.getOpenTime());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_CLOSE_TIME,
				eActivityChart.getCloseTime());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_LAT,
				eActivityChart.getHoliday());
		cv.put(BankInfoSQLiteHelper.COL_BANK_INFO_LONG,
				eActivityChart.getWUnion());

		try {
			BankInfoDatabase.insert(BankInfoSQLiteHelper.BANK_INFO, null,
					cv);
			BankInfoDatabase.close();
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		this.close();
		return true;
	}
}
