package com.g7tech.endeavorvirginia.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.g7tech.endeavorvirginia.Helper_Classes.AppConfig;
import com.g7tech.endeavorvirginia.Model.Category;
import com.g7tech.endeavorvirginia.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceRequestActivity extends AppCompatActivity {

    Spinner spinner;
    private ArrayList<Category> categoriesList;
    ProgressDialog pDialog;
    TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_request);

        //____________Title Bar___________________________________
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.home_opt_5));
        mToolbar.setTitleTextColor(Color.parseColor("#000000"));
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //_____________Bottom Navigation Bar_______________________
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        spinner = findViewById(R.id.spinner);
        txtCategory =findViewById(R.id.SpinnerTxtView);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_dashboad:
                    Intent intent = new Intent(MaintenanceRequestActivity.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.action_messages:
                    Intent intent1 = new Intent(MaintenanceRequestActivity.this,HomeActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.action_profile:
                    Intent intent2 = new Intent(MaintenanceRequestActivity.this,HomeActivity.class);
                    startActivity(intent2);

                    return true;
            }
            return false;
        }
    };

//    private class GetCategories extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pDialog = new ProgressDialog(MaintenanceRequestActivity.this);
//            pDialog.setMessage("Fetching Request Type..");
//            pDialog.setCancelable(false);
//            pDialog.show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            ServiceHandler jsonParser = new ServiceHandler();
//            String json = jsonParser.makeServiceCall(AppConfig.URL_CATEGORIES, ServiceHandler.GET);
//
//            Log.e("Response: ", "> " + json);
//
//            if (json != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(json);
//                    if (jsonObj != null) {
//                        JSONArray categories = jsonObj
//                                .getJSONArray("categories");
//
//                        for (int i = 0; i < categories.length(); i++) {
//                            JSONObject catObj = (JSONObject) categories.get(i);
//                            Category cat = new Category(catObj.getInt("id"),
//                                    catObj.getString("name"));
//                            categoriesList.add(cat);
//                        }
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            } else {
//                Log.e("JSON Data", "Didn't receive any data from server!");
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            if (pDialog.isShowing())
//                pDialog.dismiss();
//            //populateSpinner();
//        }
//
//    }

    /**
     * Adding spinner data
     * */
//    private void populateSpinner() {
//        List<String> lables = new ArrayList<String>();
//
//        txtCategory.setText("");
//
//        for (int i = 0; i < categoriesList.size(); i++) {
//            lables.add(categoriesList.get(i).getType_name());
//        }
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, lables);
//
//        // Drop down layout style - list view with radio button
//        spinnerAdapter
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(spinnerAdapter);
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position,
//                               long id) {
//        Toast.makeText(
//                getApplicationContext(),
//                parent.getItemAtPosition(position).toString() + " Selected" ,
//                Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> arg0) {
//    }
}
