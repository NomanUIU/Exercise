package com.noman.icaremyself;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.noman.icaremyself.adapter.CustomAdapter;
import com.noman.icaremyself.database.HealthDataSource;
import com.noman.icaremyself.database.SQLiteHelper;
import com.noman.icaremyself.model.HealthModel;
import com.noman.icaremyself.PrescriptionListActivity;

//import com.nazmul.mymeetingplace.MyMeetingPlaceActivity;
//import com.nazmul.mymeetingplace.PlaceUpdateActivity;

public class PrescriptionListActivity extends Activity { 


		List<String> mIdList = new ArrayList<String>();

		SQLiteHelper mDBHelper;
		HealthDataSource mHealthDataSource;
		ListView mListView;
		TextView mId_tv = null;
		String mId = "";
		Button mBackButton;
		Integer mPosition = 0;
		List<HealthModel> galery_list;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_prescriptionlist);
			
		
			mDBHelper = new SQLiteHelper(this);
			mHealthDataSource = new HealthDataSource(this);
			setListData();
			List<HealthModel> galery_list = mHealthDataSource.allHealth();
			CustomAdapter arrayAdapter = new CustomAdapter(this, galery_list);
			
			final String[] option = new String[] { "View Contact Details",
					"Edit Data", "Delete Data" };
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.select_dialog_item, option);
			/*AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Select Option");
			builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					Log.e("Selected Item", String.valueOf(which));
					if (which == 0) {
						viewContact(mPosition);
					}

					if (which == 1) {
						editData(mPosition);
					}

					if (which == 2) {
						deleteData(mPosition);
					}
				}

			});
			final AlertDialog dialog = builder.create();*/

			mListView = (ListView) findViewById(R.id.lvImageList);
			mListView.setAdapter(arrayAdapter);
		/*	mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					mPosition = position;
					dialog.show();
				}
			});*/

			mBackButton = (Button) findViewById(R.id.backBtn);
			mBackButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
							PrescriptionActivity.class);
					startActivity(i);
				}
			});
		}

		public void viewContact(Integer ePosition) {
			Intent mViewIntent = new Intent(getApplicationContext(),
					ContactDetails.class);
			Long eActivityId = Long.parseLong(mIdList.get(ePosition));
			mViewIntent.putExtra("id", eActivityId.toString());
			startActivity(mViewIntent);
		}

		public void editData(Integer ePosition) {
			Intent mEditIntent = new Intent(getApplicationContext(),
					HealthUpdateActivity.class);
			Long eActivityId = Long.parseLong(mIdList.get(ePosition));
			mEditIntent.putExtra("id", eActivityId.toString());
			startActivity(mEditIntent);
		}

		private void setListData() {
			HealthDataSource mHealthDataSource = new HealthDataSource(this);
			List<HealthModel> galery_list = mHealthDataSource.allHealth();
			for (int i = 0; i < galery_list.size(); i++) {
				HealthModel mPlaces = galery_list.get(i);
				mIdList.add(mPlaces.getmId());

			}

		}

		public void deleteData(Integer ePosition) {
			mHealthDataSource = new HealthDataSource(this);
			long eActivityId = Long.parseLong(mIdList.get(ePosition));
			mHealthDataSource.deleteData(eActivityId);
			Intent i = new Intent(getApplicationContext(), PrescriptionListActivity.class);
			startActivity(i);
		}

	}

