package com.noman.icaremyself.model;

public class UserModel {

	String mId;
	String mName;
	String mDOB;
	String mHeight;
	String mWeight;
	String mGender;

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmDOB() {
		return mDOB;
	}

	public void setmDOB(String mDOB) {
		this.mDOB = mDOB;
	}

	public String getmHeight() {
		return mHeight;
	}

	public void setmHeight(String mHeight) {
		this.mHeight = mHeight;
	}

	public String getmWeight() {
		return mWeight;
	}

	public void setmWeight(String mWeight) {
		this.mWeight = mWeight;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}

	public UserModel(String mId, String mName, String mDOB, String mHeight,
			String mWeight, String mGender) {

		this.mId = mId;
		this.mName = mName;
		this.mDOB = mDOB;
		this.mHeight = mHeight;
		this.mWeight = mWeight;
		this.mGender = mGender;

	}

	public UserModel(String mName, String mDOB, String mHeight, String mWeight,
			String mGender) {

		this.mName = mName;
		this.mDOB = mDOB;
		this.mHeight = mHeight;
		this.mWeight = mWeight;
		this.mGender = mGender;
	}

}
