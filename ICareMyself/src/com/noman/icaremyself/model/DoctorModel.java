package com.noman.icaremyself.model;

public class DoctorModel {


	String mId;
	String mDoctorName;
	String mDesignation;
	String mSpecialization;
	String mEmail;
	String mPhone;
	String mAddress;

	
	
	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmDoctorName() {
		return mDoctorName;
	}

	public void setmDoctorName(String mDoctorName) {
		this.mDoctorName = mDoctorName;
	}

	public String getmDesignation() {
		return mDesignation;
	}

	public void setmDesignation(String mDesignation) {
		this.mDesignation = mDesignation;
	}

	public String getmSpecialization() {
		return mSpecialization;
	}

	public void setmSpecialization(String mSpecialization) {
		this.mSpecialization = mSpecialization;
	}

	
	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}

	


	

	

	public DoctorModel(String mId, String mDoctorName, String mDesignation,
			String mSpecialization, String mPhone, String mEmail, String mAddress) {

		this.mId = mId;
		this.mDoctorName = mDoctorName;
		this.mDesignation = mDesignation;
		this.mSpecialization = mSpecialization;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
		

	
	}

	public DoctorModel(String mDoctorName, String mDesignation,
			String mSpecialization, String mPhone, String mEmail,String mAddress) {

		this.mDoctorName = mDoctorName;
		this.mDesignation = mDesignation;
		this.mSpecialization = mSpecialization;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mAddress = mAddress;
	

	
	}
	

}
