package com.noman.icaremyself;



	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.Button;
	
	
public class PrescriptionActivity extends Activity {
		Button mBtnRegister;
		Button mBtnRetrieve;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_prescription);

			mBtnRegister = (Button) findViewById(R.id.regBtn);
			mBtnRetrieve = (Button) findViewById(R.id.viewBtn);

			mBtnRegister.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent i = new Intent(getApplicationContext(),
							HealthActivity.class);
					startActivity(i);

				}
			});

			mBtnRetrieve.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
							PrescriptionListActivity.class);
					startActivity(i);

				}
			}); 

		}

	}

