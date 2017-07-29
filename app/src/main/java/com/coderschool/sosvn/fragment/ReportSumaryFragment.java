package com.coderschool.sosvn.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.manager.ReportManager;
import com.coderschool.sosvn.object.Report;

import butterknife.ButterKnife;

public class ReportSumaryFragment extends Fragment {

    private ReportManager reportManager;
    private Report report;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_sumary, parent, false);
        ButterKnife.bind(this, view);
        reportManager = ReportManager.getInstance();
        report = reportManager.getReport();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (report != null && isVisibleToUser) {
            report = null;
        }
    }
}