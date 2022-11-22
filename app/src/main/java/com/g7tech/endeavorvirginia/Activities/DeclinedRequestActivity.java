package com.g7tech.endeavorvirginia.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.g7tech.endeavorvirginia.Helper_Classes.AppConfig;
import com.g7tech.endeavorvirginia.Helper_Classes.PendingRequestsAdapter;
import com.g7tech.endeavorvirginia.Helper_Classes.SessionManager;
import com.g7tech.endeavorvirginia.Model.PendingRequests;
import com.g7tech.endeavorvirginia.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeclinedRequestActivity extends AppCompatActivity {

    ProgressBar progressBar;
    List<PendingRequests> PendingRequestsList;
    RecyclerView recyclerView;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declined_request);
        sessionManager = new SessionManager(getApplicationContext());
        progressBar = findViewById(R.id.progress_circular);
        PendingRequestsList = new ArrayList<>();
        loadPendingRequests();
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //____________Title Bar___________________________________
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.request_opt3));
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
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_dashboad:
                    Intent intent = new Intent(DeclinedRequestActivity.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.action_messages:
                    Intent intent1 = new Intent(DeclinedRequestActivity.this,HomeActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.action_profile:
                    Intent intent2 = new Intent(DeclinedRequestActivity.this,HomeActivity.class);
                    startActivity(intent2);

                    return true;
            }
            return false;
        }
    };

    private void loadPendingRequests() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_DECLINED_REQUESTS+"?id="+sessionManager.getUserId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject pendingRequestsList = array.getJSONObject(i);

                                PendingRequestsList.add(new PendingRequests(
                                        pendingRequestsList.getInt("id"),
                                        pendingRequestsList.getString("title"),
                                        pendingRequestsList.getString("message"),
                                        pendingRequestsList.getString("status"),
                                        pendingRequestsList.getString("created_at")
                                ));
                            }

                            PendingRequestsAdapter adapter = new PendingRequestsAdapter(DeclinedRequestActivity.this, PendingRequestsList);
                            progressBar.setVisibility(View.INVISIBLE);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
