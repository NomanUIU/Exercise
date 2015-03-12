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

import com.noman.icaremyself.database.UserDataSource;
import com.noman.icaremyself.model.UserModel;

public class UserProfileActivity extends ActionBarActivity {
	private EditText ename, edob, eheight, eweight, egender;
	private int selectedId;

	// public int mIsAlarmCecked = 0;

	private UserModel userModelObject;
	private UserDataSource userTableObject;
	// private Button mCreateButton;
	Context thiscontext;
	View rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
	initialize();
		setValues();
	}

	public void initialize() {
		ename = (EditText) findViewById(R.id.eName);
		edob = (EditText) findViewById(R.id.eDOB);
		eheight = (EditText) findViewById(R.id.eHeight);
		eweight = (EditText) findViewById(R.id.eWeight);
		egender = (EditText) findViewById(R.id.eGender);

		userTableObject = new UserDataSource(this);
		userModelObject=userTableObject.getUserById(2);
	}

	public void setValues() {

		ename.setText(userModelObject.getmName());
		edob.setText(userModelObject.getmDOB());
		eheight.setText(userModelObject.getmHeight());
		eweight.setText(userModelObject.getmWeight());
		egender.setText(userModelObject.getmGender());

	}

}
