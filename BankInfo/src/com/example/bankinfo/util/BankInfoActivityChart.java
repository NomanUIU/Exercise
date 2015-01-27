package com.example.bankinfo.util;

public class BankInfoActivityChart {

	
	String mBankName = "";
	String mBranchName = "";
	String mAddress = "";
	String mService = "";
	String mCPerson = "";
	String mCPersonPh = "";
	String mOpenTime = "";
	String mCloseTime = "";
	String mLat = "";
	String mLong = "";

	public String getBankName() {
		return mBankName;
	}

	public String getBranchName() {
		return mBranchName;
	}

	public String getAddress() {
		return mAddress;
	}

	public String getService() {
		return mService;
	}
	

	public String getCPerson() {
		return mCPerson;
	}

	public String getCPersonPh() {
		return mCPersonPh;
	}
	public String getOpenTime() {
		return mOpenTime;
	}

	public String getCloseTime() {
		return mCloseTime;
	}
	public String getHoliday() {
		return mLat;
	}
	public String getWUnion() {
		return mLong;
	}
	

	public BankInfoActivityChart(String eBankName, String eBranchName,
			String eAddress, String eService, String eCPerson,
			String eCPersonPh, String eOpenTime, String eCloseTime,
			String eLat, String eLong) {

		mBankName = eBankName;
		mBranchName = eBranchName;
		mAddress = eAddress;
		mService = eService;
		mCPerson = eCPerson;
		mCPersonPh = eCPersonPh;
		mOpenTime = eOpenTime;
		mCloseTime = eCloseTime;
		mLat = eLat;
		mLong = eLong;

	}
}
