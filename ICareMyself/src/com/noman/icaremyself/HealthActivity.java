package com.noman.icaremyself;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.noman.icaremyself.database.HealthDataSource;
import com.noman.icaremyself.model.HealthModel;

public class HealthActivity <MaunActivity>extends ActionBarActivity {
	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView mIvPhotoView = null;
	static String mCurrentPhotoPath = "";
	EditText mEtDate = null;
	EditText mEtTime = null;
	EditText mEtNote = null;
	
	Boolean mCam = false;
	
	Button mBtnInsert;
	String mDate = "";
	String mTime = "";
	String mNote = "";
	String mImage;
	HealthModel mGalery = null;
	String mID = "";
	Long mLId;
	HealthModel mUpdateHealthInfo = null;
	HealthDataSource mImageDS = null;
	LocationManager lm;

	String provider;
	private Uri mFileUri = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_info);
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

	
		mEtNote = (EditText) this.findViewById(R.id.etNote);
		mEtDate = (EditText) this.findViewById(R.id.etDate);
		mEtTime = (EditText) this.findViewById(R.id.etTime);
		mBtnInsert = (Button) this.findViewById(R.id.btnInsert);
		mIvPhotoView = (ImageView) this.findViewById(R.id.ivTakePhoto);
		
	

		// create class object

		if (mID != null) {
			updateHealthInfo();
		}
		
	}

	private void updateHealthInfo() {
		mLId = Long.parseLong(mID);

		/*
		 * get the activity which include all data from database according
		 * profileId of the clicked item.
		 */
		mImageDS = new HealthDataSource(this);
		mUpdateHealthInfo = mImageDS.singleHealthData(mLId);

		String mNote = mUpdateHealthInfo.getmNote();
		String mDate = mUpdateHealthInfo.getmDate();
		String mTime = mUpdateHealthInfo.getmTime();
		String mPhotoPath = mUpdateHealthInfo.getmPhotoPath();
		mCurrentPhotoPath = mPhotoPath;
		
		if (mPhotoPath != null) {
			File f = new File(mPhotoPath);
			BitmapFactory.Options option = new BitmapFactory.Options();
			option.inSampleSize = 16;

			try {
				Bitmap image = BitmapFactory.decodeStream(
						new FileInputStream(f), null, option);

				mIvPhotoView.setImageBitmap(image);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	
		mEtNote.setText(mNote);
		mEtDate.setText(mDate);
		mEtTime.setText(mTime);
		

		/*
		 * change button name
		 */
		mBtnInsert.setText("Update");
	}

	public void insertData(View view) {
		if (mCam) {
			Intent mActivityIntent = getIntent();
			mID = mActivityIntent.getStringExtra("id");
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();

			mDate = today.monthDay + "/" + today.month + "/" + today.year;
			mTime = today.format("%k:%M:%S");
			mNote = mEtNote.getText().toString();
			

			HealthModel mGalery = new HealthModel();
			mGalery.setmDate(mDate);
			mGalery.setmTime(mTime);
			mGalery.setmNote(mNote);
			mGalery.setmPhotoPath(mCurrentPhotoPath);
		

			mImageDS = new HealthDataSource(this);
			if (mID != null) {

				mLId = Long.parseLong(mID);

				if (mImageDS.updateData(mLId, mGalery) == true) {
					Toast toast = Toast.makeText(this, "Successfully Updated.",
							Toast.LENGTH_LONG);
					toast.show();
					startActivity(new Intent(getApplicationContext(),
							PrescriptionActivity.class));
					finish();
				} else {
					Toast toast = Toast.makeText(this, "Not Updated.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				if (mImageDS.insert(mGalery) == true) {
					Toast toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(getApplicationContext(),
							PrescriptionActivity.class));

				} else {
					Toast toast = Toast.makeText(this,
							"Error, Couldn't inserted data to database",
							Toast.LENGTH_LONG);
					toast.show();

				}
			}

		} else {
			Toast toast = Toast.makeText(this, "Take Picture, Please!",
					Toast.LENGTH_LONG);
			toast.show();

		}
	}

	public void dispatchTakePictureIntent(View v) {
		captureImage();
	}

	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		mFileUri = getOutputMediaFileUri(1);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);

		// start the image capture Intent
		startActivityForResult(intent, 100);

	}

	/**
	 * Here we store the file url as it will be null after returning from camera
	 * app
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on screen orientation
		// changes
		outState.putParcelable("file_uri", mFileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		mFileUri = savedInstanceState.getParcelable("file_uri");
	}

	/**
	 * Receiving activity result method will be called after closing the camera
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				// successfully captured the image
				// display it in image view
				previewCapturedImage();
				mCam = true;
				// carry out the crop operation

			} // user is returning from cropping the image
			else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			} else {
				// failed to capture image
				Toast.makeText(getApplicationContext(),
						"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * Display image from a path to ImageView
	 */
	private void previewCapturedImage() {
		try {
			// mImgPreview.setVisibility(View.VISIBLE);
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * downsizing image as it throws OutOfMemory Exception for larger
			 * images
			 */
			options.inSampleSize = 8; // should be power of 2.
			Bitmap bitmap = BitmapFactory.decodeFile(mFileUri.getPath(),
					options);

			mIvPhotoView.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ------------ Helper Methods ----------------------
	 * */

	/**
	 * Creating file uri to store image/video
	 */
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/**
	 * returning image / video
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard mLocation
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Places Gallery");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("Places Gallery", "Oops! Failed create "
						+ "Places Gallery" + " directory");
				return null;
			}
		}

		// Create a media file mName
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());

		File mediaFile;

		if (type == 1) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
			mCurrentPhotoPath = mediaFile.getAbsolutePath();

		} else if (type == 2) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + "CROPPED_" + timeStamp + ".jpg");

			// Save a file: path for use with ACTION_VIEW intents
			mCurrentPhotoPath = mediaFile.getAbsolutePath();
		} else {
			return null;
		}

		return mediaFile;
	}

}
