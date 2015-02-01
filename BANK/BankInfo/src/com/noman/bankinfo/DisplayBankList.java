package com.noman.bankinfo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayBankList extends Activity {

   int begin = 0;
   private DBHelper mydb ;
   TextView bankname ;
   TextView branchname;
   TextView address;
   TextView service;
   TextView cperson;
   TextView cpersonph ;
   TextView opentime;
   TextView closetime;
   TextView lat;
   TextView lang;
   int id_To_Update = 0;
   


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_display_bank_info);
      bankname = (TextView) findViewById(R.id.editTextBankName);
      branchname = (TextView) findViewById(R.id.editTextBranchName);
      address = (TextView) findViewById(R.id.editTextAddress);
      service = (TextView) findViewById(R.id.editTextService);
      cperson = (TextView) findViewById(R.id.editTextCPerson);
      cpersonph = (TextView) findViewById(R.id.editTextCPersonPh);
      opentime = (TextView) findViewById(R.id.editTextOpenTime);
      closetime = (TextView) findViewById(R.id.editTextCloseTime);
      lat = (TextView) findViewById(R.id.editTextLat);
      lang = (TextView) findViewById(R.id.editTextLang);

      mydb = new DBHelper(this);

      Bundle extras = getIntent().getExtras(); 
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            //means this is the view part not the add contact part.
            Cursor rs = mydb.getData(Value);
            id_To_Update = Value;
            rs.moveToFirst();
            
            String eBankName = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_BANK_NAME));
            String eBranchName = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_BRANCH_NAME));
            String eAddress = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_ADDRESS));
            String eService = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_SERVICE));
            String eCPerson = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_CPERSON));
            String eCPersonPh = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_CPERSON_PH));
            String eOpenTime = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_OPEN_TIME));
            String eCloseTime = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_CLOSE_TIME));
            String eLat = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_LAT));
            String eLang = rs.getString(rs.getColumnIndex(DBHelper.COL_BANK_INFO_LONG));
            
            if (!rs.isClosed()) 
            {
               rs.close();
            }
            Button b = (Button)findViewById(R.id.button1);
            b.setVisibility(View.INVISIBLE);

            bankname.setText((CharSequence)eBankName);
            bankname.setFocusable(false);
            bankname.setClickable(false);

            branchname.setText((CharSequence)eBranchName);
            branchname.setFocusable(false); 
            branchname.setClickable(false);

            address.setText((CharSequence)eAddress);
            address.setFocusable(false);
            address.setClickable(false);

            service.setText((CharSequence)eService);
            service.setFocusable(false); 
            service.setClickable(false);

            cperson.setText((CharSequence)eCPerson);
            cperson.setFocusable(false);
            cperson.setClickable(false);
            
            cpersonph.setText((CharSequence)eCPersonPh);
            cpersonph.setFocusable(false);
            cpersonph.setClickable(false);

            opentime.setText((CharSequence)eOpenTime);
            opentime.setFocusable(false); 
            opentime.setClickable(false);

            closetime.setText((CharSequence)eCloseTime);
            closetime.setFocusable(false);
            closetime.setClickable(false);

            lat.setText((CharSequence)eLat);
            lat.setFocusable(false); 
            lat.setClickable(false);

            lang.setText((CharSequence)eLang);
            lang.setFocusable(false);
            lang.setClickable(false);
           }
      }
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      Bundle extras = getIntent().getExtras(); 
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            getMenuInflater().inflate(R.menu.display_list, menu);
         }
         else{
            getMenuInflater().inflate(R.menu.bank_info, menu);
         }
      }
      return true;
   }

   public boolean onOptionsItemSelected(MenuItem item) 
   { 
      super.onOptionsItemSelected(item); 
      switch(item.getItemId()) 
   { 
      case R.id.Edit_BankList: 
      Button button = (Button)findViewById(R.id.button1);
      button.setVisibility(View.VISIBLE);
      
      bankname.setEnabled(true);
      bankname.setFocusableInTouchMode(true);
      bankname.setClickable(true);

      branchname.setEnabled(true);
      branchname.setFocusableInTouchMode(true);
      branchname.setClickable(true);

      address.setEnabled(true);
      address.setFocusableInTouchMode(true);
      address.setClickable(true);

      service.setEnabled(true);
      service.setFocusableInTouchMode(true);
      service.setClickable(true);

      opentime.setEnabled(true);
      opentime.setFocusableInTouchMode(true);
      opentime.setClickable(true);
      
      closetime.setEnabled(true);
      closetime.setFocusableInTouchMode(true);
      closetime.setClickable(true);

      cperson.setEnabled(true);
      cperson.setFocusableInTouchMode(true);
      cperson.setClickable(true);

      cpersonph.setEnabled(true);
      cpersonph.setFocusableInTouchMode(true);
      cpersonph.setClickable(true);

      lat.setEnabled(true);
      lat.setFocusableInTouchMode(true);
      lat.setClickable(true);

      lang.setEnabled(true);
      lang.setFocusableInTouchMode(true);
      lang.setClickable(true);

      return true; 
      case R.id.Delete_BankList:

      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setMessage(R.string.deleteBankList)
     .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            mydb.deleteBankList(id_To_Update);
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();  
            Intent intent = new Intent(getApplicationContext(),com.noman.bankinfo.BankInfoActivity.class);
            startActivity(intent);
         }
      })
     .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            // User cancelled the dialog
         }
      });
      AlertDialog d = builder.create();
      d.setTitle("Are you sure");
      d.show();

      return true;
      default: 
      return super.onOptionsItemSelected(item); 

      } 
   } 

   public void run(View view)
   {	
      Bundle extras = getIntent().getExtras();
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            if(mydb.updateBankList(id_To_Update, bankname.getText().toString(), branchname.getText().toString(), address.getText().toString(), service.getText().toString(),cperson.getText().toString(),cpersonph.getText().toString(), opentime.getText().toString(),closetime.getText().toString(),lat.getText().toString(),lang.getText().toString())){
               Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();	
               Intent intent = new Intent(getApplicationContext(),com.noman.bankinfo.BankInfoActivity.class);
               startActivity(intent);
             }		
            else{
               Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
            }
		 }
         else{
            if(mydb.insertBankList(bankname.getText().toString(), branchname.getText().toString(), address.getText().toString(), service.getText().toString(), cperson.getText().toString(), cpersonph.getText().toString(), opentime.getText().toString(), closetime.getText().toString(), lat.getText().toString(), lang.getText().toString())){
               Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
            }		
            else{
               Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
            }
            Intent intent = new Intent(getApplicationContext(),com.noman.bankinfo.BankInfoActivity.class);
            startActivity(intent);
            }
      }
   }
}
