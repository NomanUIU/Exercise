package com.noman.bankinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends Activity {

	Button location_btn;
	private GoogleMap mGoogleMap;
	static final LatLng t1 = new LatLng(23, 90);

	// private GoogleMap mGoogleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		location_btn = (Button) findViewById(R.id.button2);

		try {
			if (mGoogleMap == null) {
				mGoogleMap = ((MapFragment) getFragmentManager()
						.findFragmentById(R.id.map)).getMap();

			}
			mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			Marker TP = mGoogleMap.addMarker(new MarkerOptions().position(t1)
					.title("MyLocation"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank_info, menu);
		return true;
	}

	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
