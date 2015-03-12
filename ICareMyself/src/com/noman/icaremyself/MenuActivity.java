package com.noman.icaremyself;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends ActionBarActivity {
	
	
	Button mBtnUser, mBtnDoctor, mBtnDietChart, mBtnICare, mBtnHealth, mBtnAbout ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		mBtnUser = (Button) this.findViewById(R.id.btnUser);
		mBtnDoctor = (Button) this.findViewById(R.id.btnDoctor);
		mBtnDietChart = (Button) this.findViewById(R.id.btnDietChart);
		mBtnICare = (Button) this.findViewById(R.id.btnIcareCenter);
		mBtnHealth = (Button) this.findViewById(R.id.btnHealthInfo);
		mBtnAbout = (Button) this.findViewById(R.id.btnAbout);
		
		mBtnUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						UserProfileActivity.class);
				startActivity(i);
			}
		});
		 mBtnDoctor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						DoctorProfileActivity.class);
				startActivity(i);
			}
		}); 
		 mBtnDietChart.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v1) {
					Intent i = new Intent(getApplicationContext(),
							DietChartActivity.class);
					startActivity(i);
				}
			}); 
		 mBtnICare.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v2) {
					Intent i = new Intent(getApplicationContext(),
							LocationActivity.class);
					startActivity(i);
				}
			}); 
		 mBtnHealth.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v3) {
					Intent i = new Intent(getApplicationContext(),
							PrescriptionActivity.class);
					startActivity(i);
				}
			}); 
		 mBtnAbout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v7) {
					Intent i = new Intent(getApplicationContext(),
							AboutActivity.class);
					startActivity(i);
				}
			}); 
	}

  }


