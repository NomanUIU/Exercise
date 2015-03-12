package com.noman.icaremyself;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.noman.icaremyself.database.DoctorDataSource;
import com.noman.icaremyself.model.DoctorModel;

public class DoctorProfileActivity extends ActionBarActivity {
	private EditText edname, edesignation, especialization, ephone, eemail, eaddress;
	private int selectedId;

	// public int mIsAlarmCecked = 0;

	private DoctorModel doctorModelObject;
	private DoctorDataSource doctorTableObject;
	// private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_profile);
	initialize();
		setValues();
	}

	public void initialize() {
		edname = (EditText) findViewById(R.id.eDName);
		edesignation = (EditText) findViewById(R.id.eDesignation);
		especialization = (EditText) findViewById(R.id.eSpecialization);
		ephone = (EditText) findViewById(R.id.ePhone);
		eemail = (EditText) findViewById(R.id.eEmail);
		eaddress = (EditText) findViewById(R.id.eAddress);

		doctorTableObject = new DoctorDataSource(this);
		doctorModelObject = doctorTableObject.getDoctorById(1);
	}

	public void setValues() {

		edname.setText(doctorModelObject.getmDoctorName());
		edesignation.setText(doctorModelObject.getmDesignation());
		especialization.setText(doctorModelObject.getmSpecialization());
		ephone.setText(doctorModelObject.getmPhone());
		eemail.setText(doctorModelObject.getmEmail());
		eaddress.setText(doctorModelObject.getmAddress());

	}

}

