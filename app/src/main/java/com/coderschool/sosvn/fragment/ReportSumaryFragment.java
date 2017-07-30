package com.coderschool.sosvn.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.manager.ReportManager;
import com.coderschool.sosvn.object.Report;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportSumaryFragment extends Fragment {

    private ReportManager reportManager;
    private Report report;

    @BindView(R.id.tv_first_name)
    TextView tvFirstName;
    @BindView(R.id.tv_last_name)
    TextView tvLastName;
    @BindView(R.id.tv_ice)
    TextView tvICE;
    @BindView(R.id.tv_ipn)
    TextView tvIPN;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_blood_type)
    TextView tvBloodType;
    @BindView(R.id.tv_severity_report_summary)
    TextView tvSeverityReportSummary;
    @BindView(R.id.tv_what_happened_report_summary)
    TextView tvWhatHappenedReportSummary;
    @BindView(R.id.iv_where_report_summary)
    ImageView ivWhereReportSummary;

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
            tvSeverityReportSummary.setText(report.getSeverity());
            tvWhatHappenedReportSummary.setText(report.getWhat());
            ivWhereReportSummary.setImageBitmap(report.getWhere());
        }
    }
}