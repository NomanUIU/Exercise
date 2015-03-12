package com.noman.icaremyself.adapter;

import java.util.List;


import com.noman.icaremyself.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.noman.icaremyself.model.HealthModel;

public class CustomAdapter extends ArrayAdapter<HealthModel> {
	private static LayoutInflater mInflater = null;

	List<HealthModel> mHealth;
	public String mImage;
	String provider;
	

	public CustomAdapter(Activity context, List<HealthModel> eHealth) {
		super(context, R.layout.health_list, eHealth);
		this.mHealth = eHealth;

		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView mId;
		public TextView mDate;
		public TextView mTime;
		public TextView mNote;
		public ImageView mIvImage;

	}

	// convert from byte array to bitmap
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = mInflater.inflate(R.layout.health_list, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			
			holder.mDate = (TextView) vi.findViewById(R.id.tvDate);
			holder.mTime = (TextView) vi.findViewById(R.id.tvTime);
			holder.mNote = (TextView) vi.findViewById(R.id.tvNote);
			holder.mIvImage = (ImageView) vi.findViewById(R.id.ivImage);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		HealthModel Health;
		Health = mHealth.get(position);

		//holder.mId.setText(Health.getmId().toString());
		holder.mDate.setText("Date: " + Health.getmDate().toString());
		holder.mTime.setText("Time: " + Health.getmTime().toString());
		holder.mNote.setText("Description: "+ Health.getmNote().toString());
		

		
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap bitmap = BitmapFactory.decodeFile(Health.getmPhotoPath(),
				options);
		holder.mIvImage.setImageBitmap(bitmap);

		;

		return vi;
	}

}
