package com.coderschool.sosvn.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderschool.sosvn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportSeverityFragment extends Fragment {

    private final int HIGH = 0;
    private final int MEDIUM = 1;
    private final int SLIGHT = 2;
    private final int NORMAL = 3;

    private int userSelected = -1;

    @BindView(R.id.v_high_severity)
    View vHighSeverity;
    @BindView(R.id.tv_high_severity)
    TextView tvHighSeverity;
    @BindView(R.id.v_medium_severity)
    View vMediumSeverity;
    @BindView(R.id.tv_medium_severity)
    TextView tvMediumSeverity;
    @BindView(R.id.v_slight_severity)
    View vSlightSeverity;
    @BindView(R.id.tv_slight_severity)
    TextView tvSlightSeverity;
    @BindView(R.id.v_normal_severity)
    View vNormalSeverity;
    @BindView(R.id.tv_normal_severity)
    TextView tvNormalSeverity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_severity, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.v_high_severity, R.id.v_medium_severity, R.id.v_slight_severity, R.id.v_normal_severity})
    public void setUpHighlight(View view) {
        resetHighlight();
        switch (view.getId()) {
            case R.id.v_high_severity:
                setHighlight(vHighSeverity, tvHighSeverity, getResources().getColor(R.color.severity_high_highlighted));
                break;
            case R.id.v_medium_severity:
                setHighlight(vMediumSeverity, tvMediumSeverity, getResources().getColor(R.color.severity_medium_highlighted));
                break;
            case R.id.v_slight_severity:
                setHighlight(vSlightSeverity, tvSlightSeverity, getResources().getColor(R.color.severity_slight_highlighted));
                break;
            case R.id.v_normal_severity:
                setHighlight(vNormalSeverity, tvNormalSeverity, getResources().getColor(R.color.severity_normal_highlighted));
                break;
        }
    }

    public void resetHighlight() {
        vHighSeverity.setBackgroundColor(getResources().getColor(R.color.idle));
        vMediumSeverity.setBackgroundColor(getResources().getColor(R.color.idle));
        vSlightSeverity.setBackgroundColor(getResources().getColor(R.color.idle));
        vNormalSeverity.setBackgroundColor(getResources().getColor(R.color.idle));

        tvHighSeverity.setTextColor(getResources().getColor(R.color.text_idle));
        tvMediumSeverity.setTextColor(getResources().getColor(R.color.text_idle));
        tvSlightSeverity.setTextColor(getResources().getColor(R.color.text_idle));
        tvNormalSeverity.setTextColor(getResources().getColor(R.color.text_idle));
    }

    public void setHighlight(View view, TextView textView, int bgColor) {
        view.setBackgroundColor(bgColor);
        textView.setTextColor(getResources().getColor(R.color.text_highlighted));
    }
}