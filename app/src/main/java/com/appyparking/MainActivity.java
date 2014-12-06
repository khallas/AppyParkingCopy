package com.appyparking;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.SearchView;
import android.widget.Toast;



import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {

    public double lat;
    public double lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final android.widget.Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                //setContentView(R.layout.activity_maps);
            }
        });

       // final android.widget.Button c = (Button) findViewById()


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }


    //geoLocate method
    public void geoLocate(View view) throws IOException {
        hideSoftKeyboard(view);

        EditText et = (EditText) findViewById(R.id.searchBar);
        //SearchView sv = (SearchView) findViewById(R.id.searchView);
        //EditText et = et.setText(sv.getQuery());
        String location = et.getText().toString();
        //String location = (String) sv.getQuery();

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address add = list.get(0);

        //String locality = add.getLocality();


        lat =add.getLatitude();
        lng = add.getLongitude();

        Toast.makeText(this, lat + ", " + lng, Toast.LENGTH_LONG).show();
    }
    private void hideSoftKeyboard(View v){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);

    }

}
