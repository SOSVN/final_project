package com.coderschool.sosvn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.activity.ReportActivity;
import com.coderschool.sosvn.activity.ShowHospitalActivity;
import com.coderschool.sosvn.activity.UserProfileActivity;
import com.coderschool.sosvn.activity.VerificationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainScreenFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @OnClick({R.id.v_call_now, R.id.tv_call_now})
    public void goToReport(View view) {
        Intent intent = new Intent(getContext(), ReportActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.iv_profile, R.id.tv_profile})
    public void goToProfile(View view) {
        Intent intent = new Intent(getContext(), UserProfileActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.iv_discovery, R.id.tv_discovery})
    public void goToDiscoveryScreen(View view) {
        Intent intent = new Intent(getContext(), ShowHospitalActivity.class);
        startActivity(intent);
    }
}