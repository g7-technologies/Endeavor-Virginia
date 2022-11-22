package com.g7tech.endeavorvirginia.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.g7tech.endeavorvirginia.R;

public class RequestStatusActivity extends AppCompatActivity {

    TextView pending,approved,declined,completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status);

        pending = findViewById(R.id.pending);
        approved = findViewById(R.id.approved);
        declined = findViewById(R.id.declined);
        completed = findViewById(R.id.completed);

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpending = new Intent(RequestStatusActivity.this,PendingRequestActivity.class);
                startActivity(intentpending);
            }
        });

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentapproved = new Intent(RequestStatusActivity.this,ApprovedRequestActivity.class);
                startActivity(intentapproved);
            }
        });

        declined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdeclined = new Intent(RequestStatusActivity.this,DeclinedRequestActivity.class);
                startActivity(intentdeclined);
            }
        });

        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcompleted = new Intent(RequestStatusActivity.this,CompletedRequestActivity.class);
                startActivity(intentcompleted);
            }
        });

        //____________Title Bar___________________________________
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.home_opt_3));
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
                    Intent intent = new Intent(RequestStatusActivity.this,HomeActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.action_messages:
                    Intent intent1 = new Intent(RequestStatusActivity.this,HomeActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.action_profile:
                    Intent intent2 = new Intent(RequestStatusActivity.this,HomeActivity.class);
                    startActivity(intent2);

                    return true;
            }
            return false;
        }
    };
}
