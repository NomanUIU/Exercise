package com.noman.icaremyself;

import com.noman.icaremyself.database.DoctorDataSource;
import com.noman.icaremyself.model.DoctorModel;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorCreateProfileActivity extends ActionBarActivity {
	
	EditText mDName = null;
	EditText mDesignation = null;
	EditText mSpecializatioin = null;
	EditText mPhone = null;
	EditText mEmail = null;
	EditText mAddress = null;
	DoctorModel mDoctorModel = null;
	DoctorDataSource mDDSource = null;
	Button mBtnSave;
	
	String mId;
	String meDName;
	String meDesignation;
	String meSpecialization;
	String mePhone;
	String meEmail;
	String meAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_create_profile);

		mDName = (EditText) this.findViewById(R.id.eDName);
		mDesignation = (EditText) this.findViewById(R.id.eDesignation);
		mSpecializatioin = (EditText) this.findViewById(R.id.eSpecialization);
		mPhone = (EditText) this.findViewById(R.id.ePhone);
		mEmail = (EditText) this.findViewById(R.id.eEmail);
		mAddress = (EditText) this.findViewById(R.id.eAddress);
		mBtnSave = (Button) this.findViewById(R.id.btnSave);

  }
	public void insertData(View view) {

		meDName = mDName.getText().toString();
		meDesignation = mDesignation.getText().toString();
		meSpecialization = mSpecializatioin.getText().toString();
		mePhone = mPhone.getText().toString();
		meEmail = mEmail.getText().toString();
		meAddress = mAddress.getText().toString();
		mDoctorModel = new DoctorModel(meDName, meDesignation, meSpecialization, mePhone,
				meEmail, meAddress);
		mDDSource = new DoctorDataSource(this);
		if (mDDSource.insert(mDoctorModel) == true) {
			Toast toast = Toast.makeText(this, "Successfully Saved.",
					Toast.LENGTH_LONG);
			toast.show();
			startActivity(new Intent(getApplicationContext(),
					MenuActivity.class));
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
