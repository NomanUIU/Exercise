package com.noman.icaremyself;

import com.noman.icaremyself.util.GPSTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LocationActivity extends Activity {
	
	Button btnShowLocation;
	Button btnShowMap;
	
	// GPSTracker class
	GPSTracker gps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnShowMap = (Button) findViewById(R.id.button1);
        
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {		
				// create class object
		        gps = new GPSTracker(LocationActivity.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	// \n is for new line
		        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
			}
		});
        btnShowMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v5) {
				Intent i = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(i);
			}
		}); 
    }
    
}
