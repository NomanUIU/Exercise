package com.example.bankinfo;


import com.example.bankinfo.util.BankInfoActivityChart;
import com.example.bankinfo.database.BankInfoDataSource;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BankInfoActivity extends Activity {

	EditText mBankNameEditText = null;
	EditText mBranchNameEditText = null;
	EditText mAddressEditText = null;
	EditText mServiceEditText = null;
	EditText mCPersonEditText = null;
	EditText mCPersonPhEditText = null;
	EditText mOpenTimeEditText = null;
	EditText mCloseTimeEditText = null;
	EditText mLatEditText = null;
	EditText mLongEditText = null;
	
	String mBankName = "";
	String mBranchName = "";
	String mAddress = "";
	String mService = "";
	String mCPerson = "";
	String mCPersonPh = "";
	String mOpenTime = "";
	String mCloseTime = "";
	String mLat = "";
	String mLong = "";
	Button btnSave = null;
	
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);
        bank();
       
    }
	
	public void LocationActivity(View v)
	{
		startActivity(new Intent(BankInfoActivity.this,
		 LocationActivity.class));
	}
	
	 public void saveData(View v) {
			
		 mBankName = mBankNameEditText.getText().toString();
		 mBranchName = mBranchNameEditText.getText().toString();
		 mAddress = mAddressEditText.getText().toString();
		 mService = mServiceEditText.getText().toString();
		 mCPerson = mCPersonEditText.getText().toString();
		 mCPersonPh = mCPersonPhEditText.getText().toString();
		 mOpenTime = mOpenTimeEditText.getText().toString();
		 mCloseTime =  mCloseTimeEditText.getText().toString();
		 mLat = mLatEditText.getText().toString();
		 mLong =  mLongEditText.getText().toString();
		 
		BankInfoActivityChart mBankModel = new BankInfoActivityChart(mBankName, mBranchName, mAddress, mService
				, mCPerson, mCPersonPh, mOpenTime, mCloseTime, mLat, mLong);
		    
		
		BankInfoDataSource mBankData = new BankInfoDataSource(this);
		    mBankData.insert( mBankModel);
			
		    Toast.makeText(this,"Saved", 1000).show();
		 
	 }
	 
	 public void bank(){
		   
		    mBankNameEditText = (EditText) findViewById(R.id.editTextBankName);
	        mBranchNameEditText = (EditText) findViewById(R.id.editTextBranchName);
	        mAddressEditText = (EditText) findViewById(R.id.editTextAddress);
	        mServiceEditText = (EditText) findViewById(R.id.editTextService);
	        mCPersonEditText = (EditText) findViewById(R.id.editTextCPerson);
	        mCPersonPhEditText = (EditText) findViewById(R.id.editTextCPersonPh);
	        mOpenTimeEditText = (EditText) findViewById(R.id.editTextOpenTime);
	        mCloseTimeEditText = (EditText) findViewById(R.id.editTextCloseTime);
	        mLatEditText = (EditText) findViewById(R.id.editTextLat);
	        mLongEditText = (EditText) findViewById(R.id.editTextLong);
	   	 
	        btnSave = (Button) findViewById(R.id.button1);
	        
	 }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bank_info, menu);
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
