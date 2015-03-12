package com.noman.icaremyself.model;

public class HealthModel {


		String mId;
		String mPhotoPath;
		String mDate;
		String mTime;
		String mNote;
	
		

		public String getmId() {
			return mId;
		}

		public void setmId(String mId) {
			this.mId = mId;
		}
		public String getmPhotoPath() {
			return mPhotoPath;
		}

		public void setmPhotoPath(String mPhotoPath) {
			this.mPhotoPath = mPhotoPath;
		}

		public String getmDate() {
			return mDate;
		}

		public void setmDate(String mDate) {
			this.mDate = mDate;
		}

		public String getmTime() {
			return mTime;
		}

		public void setmTime(String mTime) {
			this.mTime = mTime;
		}

		public String getmNote() {
			return mNote;
		}

		public void setmNote(String mNote) {
			this.mNote = mNote;
		}

	

	
		public HealthModel(String mId, String mPhotoPath,String mDate, String mTime,
				 String mNote)
				 {
		
			this.mId = mId;
			this.mPhotoPath = mPhotoPath;
			this.mDate = mDate;
			this.mTime = mTime;
			this.mNote = mNote;
			
			
		}

		public HealthModel(String mPhotoPath,String mDate, String mTime,
				 String mNote) {

			this.mPhotoPath = mPhotoPath;
			this.mDate = mDate;
			this.mTime = mTime;
			this.mNote = mNote;
		}

		public HealthModel() {
			// TODO Auto-generated constructor stub
		}

	
		

	}


