package com.noman.bankinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

  
	public static final String BANK_INFO = "bankinfo";
	public static final String COL_BANK_INFO_ID = "id";
    public static final String COL_BANK_INFO_BANK_NAME = "bankname";
	public static final String COL_BANK_INFO_BRANCH_NAME = "branchname";
	public static final String COL_BANK_INFO_ADDRESS = "address";
	public static final String COL_BANK_INFO_SERVICE = "service";
	public static final String COL_BANK_INFO_CPERSON = "cperson";
	public static final String COL_BANK_INFO_CPERSON_PH = "cpersonph";
	public static final String COL_BANK_INFO_OPEN_TIME = "opentime";
	public static final String COL_BANK_INFO_CLOSE_TIME = "closetime";
	public static final String COL_BANK_INFO_LAT = "lat";
	public static final String COL_BANK_INFO_LONG = "lang";
	
	 public static final String DATABASE_NAME = "bankinfo.db";





   public DBHelper(Context context)
   {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
      db.execSQL(
      "create table bankinfo " +
      "(id integer primary key, bankname text,branchname text,address text,service text,cperson text,cpersonph text,opentime text,closetime text,lat text,lang text)"
      );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS bankinfo");
      onCreate(db);
   }

   public boolean insertBankList  (String bankname, String branchname, String address, String service, String cperson, String cpersonph, String opentime, String closetime, String lat, String lang)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("bankname", bankname);
      contentValues.put("branchname", branchname);
      contentValues.put("address", address);	
      contentValues.put("service", service);
      contentValues.put("cperson", cperson);
      contentValues.put("cpersonph", cpersonph);
      contentValues.put("opentime", opentime);
      contentValues.put("closetime", closetime);
      contentValues.put("lat", lat);	
      contentValues.put("lang", lang);
      

      db.insert("bankinfo", null, contentValues);
      return true;
   }
   public Cursor getData(int id){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from bankinfo where id="+id+"", null );
      return res;
   }
   public int numberOfRows(){
      SQLiteDatabase db = this.getReadableDatabase();
      int numRows = (int) DatabaseUtils.queryNumEntries(db, BANK_INFO);
      return numRows;
   }
   public boolean updateBankList (Integer id, String bankname, String branchname, String address, String service,String cperson, String cpersonph, String opentime, String closetime, String lat, String lang)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      
      contentValues.put("bankname", bankname);
      contentValues.put("branchname", branchname);
      contentValues.put("address", address);
      contentValues.put("service", service);
      contentValues.put("cperson", cperson);
      contentValues.put("cpersonph", cpersonph);
      contentValues.put("opentime", opentime);
      contentValues.put("closetime", closetime);
      contentValues.put("lat", lat);
      contentValues.put("lang", lang);
      
      db.update("bankinfo", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }

   public Integer deleteBankList (Integer id)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("bankinfo", 
      "id = ? ", 
      new String[] { Integer.toString(id) });
   }
   public ArrayList getAllBankList()
   {
      ArrayList array_list = new ArrayList();
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from bankinfo", null );
      res.moveToFirst();
      while(res.isAfterLast() == false){
      array_list.add(res.getString(res.getColumnIndex(COL_BANK_INFO_BANK_NAME)));
      res.moveToNext();
      }
   return array_list;
   }
}