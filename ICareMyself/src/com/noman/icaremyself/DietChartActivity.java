package com.noman.icaremyself;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class DietChartActivity extends Activity {

	   EditText bfast ;
	   EditText foodb;
	   EditText drinksb;
	   EditText launch;
	   EditText foodl;
	   EditText drinksl;
	   EditText dinner;
	   EditText foodd;
	   EditText drinksd;
	   
	   Editor editor;
	   
	   public static final String MyPREFERENCES = "MyPrefs" ;
	   public static final String BREAKFAST = "bfastKey"; 
	   public static final String FOODB = "foodbKey"; 
	   public static final String DRINKSB = "drinksbKey"; 
	   public static final String LAUNCH = "launchKey"; 
	   public static final String FOODL = "foodlKey";
	   public static final String DRINKSL = "drinkslKey";
	   public static final String DINNER = "dinnerKey";
	   public static final String FOODD = "fooddKey";
	   public static final String DRINKSD = "drinksdKey";

	   SharedPreferences sharedpreferences;

	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_diet_chart);

	      bfast = (EditText) findViewById(R.id.editText1);
	      foodb = (EditText) findViewById(R.id.editText2);
	      drinksb = (EditText) findViewById(R.id.editText3);
	      launch = (EditText) findViewById(R.id.editText4);
	      foodl = (EditText) findViewById(R.id.editText5);
	      drinksl = (EditText) findViewById(R.id.editText6);
	      dinner = (EditText) findViewById(R.id.editText7);
	      foodd = (EditText) findViewById(R.id.editText8);
	      drinksd = (EditText) findViewById(R.id.editText9);

      sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

      if (sharedpreferences.contains(BREAKFAST))
      {
    	  bfast.setText(sharedpreferences.getString(BREAKFAST, ""));

      }
      if (sharedpreferences.contains(FOODB))
      {
    	  foodb.setText(sharedpreferences.getString(FOODB, ""));

      }
      if (sharedpreferences.contains(DRINKSB))
      {
    	  drinksb.setText(sharedpreferences.getString(DRINKSB, ""));

      }
      if (sharedpreferences.contains(LAUNCH))
      {
    	  launch.setText(sharedpreferences.getString(LAUNCH, ""));

      }
      if (sharedpreferences.contains(FOODL))
      {
    	  foodl.setText(sharedpreferences.getString(FOODL, ""));
         
      }
      if (sharedpreferences.contains(DRINKSL))
      {
    	  drinksl.setText(sharedpreferences.getString(DRINKSL, ""));
      }
      if (sharedpreferences.contains(DINNER))
      {
    	  dinner.setText(sharedpreferences.getString(DINNER, ""));
      }
      if (sharedpreferences.contains(FOODD))
      {
    	  foodd.setText(sharedpreferences.getString(FOODD, ""));
      }
      if (sharedpreferences.contains(DRINKSD))
      {
    	  drinksd.setText(sharedpreferences.getString(DRINKSD, ""));
      }
   }

  

public void run(View view){
      String bfast1  = bfast.getText().toString();
      String foodb1  = foodb.getText().toString();
      String drinksb1  = drinksb.getText().toString();
      String launch1  = launch.getText().toString();
      String foodl1  = foodl.getText().toString();
      String drinksl1  = drinksl.getText().toString();
      String dinner1  = dinner.getText().toString();
      String foodd1  = foodd.getText().toString();
      String drinksd1  = drinksd.getText().toString();

      editor = sharedpreferences.edit();
      
      editor.putString(BREAKFAST, bfast1);
      editor.putString(FOODB, foodb1);
      editor.putString(DRINKSB, drinksb1);
      editor.putString(LAUNCH, launch1);
      editor.putString(FOODL, foodl1);
      editor.putString(DRINKSL, drinksl1);
      editor.putString(DINNER, dinner1);
      editor.putString(FOODD, foodd1);
      editor.putString(DRINKSD, drinksd1);

      editor.commit(); 
      
      Toast.makeText(this,"Saved",Toast.LENGTH_SHORT ).show();
      finish();
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.diet_chart, menu);
      return true;
   }

}