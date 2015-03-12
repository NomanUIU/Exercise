package com.noman.icaremyself;

import com.noman.icaremyself.database.UserDataSource;
import com.noman.icaremyself.model.UserModel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	EditText mName = null;
	EditText mDOB = null;
	EditText mHeight = null;
	EditText mWeight = null;
	EditText mGender = null;
	UserModel mUserModel = null;
	UserDataSource mUDSource = null;
	Button mBtnSave;
	
	String mId;
	String meName;
	String meDOB;
	String meHeight;
	String meWeight;
	String meGender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mName = (EditText) this.findViewById(R.id.eName);
		mDOB = (EditText) this.findViewById(R.id.eDOB);
		mHeight = (EditText) this.findViewById(R.id.eHeight);
		mWeight = (EditText) this.findViewById(R.id.eWeight);
		mGender = (EditText) this.findViewById(R.id.eGender);
		mBtnSave = (Button) this.findViewById(R.id.btnSave);

	}

	public void insertData(View view) {

		meName = mName.getText().toString();
		meDOB = mDOB.getText().toString();
		meHeight = mHeight.getText().toString();
		meWeight = mWeight.getText().toString();
		meGender = mGender.getText().toString();
		mUserModel = new UserModel(meName, meDOB, meHeight, meWeight,
				meGender);
		mUDSource = new UserDataSource(this);
		if (mUDSource.insert(mUserModel) == true) {
			Toast toast = Toast.makeText(this, "Successfully Saved.",
					Toast.LENGTH_LONG);
			toast.show();
			startActivity(new Intent(getApplicationContext(),
					DoctorCreateProfileActivity.class));
		} else {
			Toast toast = Toast.makeText(this,
					"Error, Couldn't inserted data to database",
					Toast.LENGTH_LONG);
			toast.show();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
