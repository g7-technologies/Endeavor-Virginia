package com.g7tech.endeavorvirginia.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.g7tech.endeavorvirginia.Helper_Classes.AppConfig;
import com.g7tech.endeavorvirginia.Helper_Classes.SessionManager;
import com.g7tech.endeavorvirginia.R;

public class DashboardFragment extends Fragment {

    LinearLayout opt_rental_history,opt_report_history,opt_request_status,opt_report_problem,opt_maintenance,opt_payment,opt_utility_bills;
    SessionManager session;
    public DashboardFragment() {
        // Required empty public constructor
    }
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        getActivity().setTitle(R.string.bottom_navbar_1);
        session =  new SessionManager(getContext());
        opt_rental_history = view.findViewById(R.id.opt_rental_history);
        opt_report_history = view.findViewById(R.id.opt_report_history);
        opt_request_status = view.findViewById(R.id.opt_request_status);
        opt_report_problem = view.findViewById(R.id.opt_report_problem);
        opt_maintenance = view.findViewById(R.id.opt_maintenance);
        opt_payment = view.findViewById(R.id.opt_payment);
        opt_utility_bills = view.findViewById(R.id.opt_bills);

        opt_rental_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RentalHistoryActivity.class));
            }
        });

        opt_report_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReportHistoryActivity.class));
            }
        });

        opt_request_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RequestStatusActivity.class));
            }
        });

        opt_report_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ReportProblemActivity.class));
            }
        });

        opt_maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MaintenanceRequestActivity.class));
            }
        });

        opt_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = AppConfig.URL_PAYMENT+session.getUserId();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        opt_utility_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),UtilityBillsActivity.class));
            }
        });

        return view;
    }
}