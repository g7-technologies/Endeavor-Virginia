package com.g7tech.endeavorvirginia.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.g7tech.endeavorvirginia.Helper_Classes.AppConfig;
import com.g7tech.endeavorvirginia.Helper_Classes.AppController;
import com.g7tech.endeavorvirginia.Helper_Classes.SessionManager;
import com.g7tech.endeavorvirginia.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReportProblemActivity extends AppCompatActivity {

    EditText title,description;
    Button btn_submit;
    private ProgressDialog pDialog;
    SessionManager sessionManager;
    private static final String TAG = ReportProblemActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        sessionManager = new SessionManager(getApplicationContext());
        //____________Title Bar___________________________________
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.home_opt_4));
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

        title = findViewById(R.id.input_edt1);
        description = findViewById(R.id.input_edt2);
        btn_submit = findViewById(R.id.btn_submit);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string_title = title.getText().toString().trim();
                String string_description = description.getText().toString().trim();
                if (!string_title.isEmpty() && !string_description.isEmpty()) {
                    reportProblem(string_title, string_description);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_dashboad:
                    Intent intent = new Intent(ReportProblemActivity.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.action_messages:
                    Intent intent1 = new Intent(ReportProblemActivity.this,HomeActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.action_profile:
                    Intent intent2 = new Intent(ReportProblemActivity.this,HomeActivity.class);
                    startActivity(intent2);

                    return true;
            }
            return false;
        }
    };

    private void reportProblem(final String title, final String description) {
        // Tag used to cancel the request
        String tag_string_req = "req_report_problem";

        pDialog.setMessage("Reporting ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REPORT_PROBLEM, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Report Response: " + response.toString());
                hideDialog();

                try {
                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Toast.makeText(getApplicationContext(),jObj.getString("error_msg"),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),jObj.getString("error_msg"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Network Error");
                Toast.makeText(getApplicationContext(),"Network Error", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", description);
                params.put("id", String.valueOf(sessionManager.getUserId()));
                params.put("property_id", String.valueOf(sessionManager.getPropertyId()));

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}