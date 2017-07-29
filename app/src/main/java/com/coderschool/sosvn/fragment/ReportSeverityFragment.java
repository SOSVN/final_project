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

    private final int HIGH = R.color.severity_high_highlighted;
    private final int MEDIUM = R.color.severity_medium_highlighted;
    private final int SLIGHT = R.color.severity_slight_highlighted;
    private final int NORMAL = R.color.severity_normal_highlighted;
    private final int HIGHLIGHT_IDLE = R.color.idle;
    private final int TEXT_IDLE = R.color.text_idle;
    private final int TEXT_HIGHLIGHTED = R.color.text_highlighted;

    private static String userSelected = "";

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
        //set default value for user
        setHighlight(vSlightSeverity, tvSlightSeverity, getResources().getColor(SLIGHT));
        userSelected = getResources().getString(R.string.slight);

    }

    @OnClick({R.id.v_high_severity, R.id.v_medium_severity, R.id.v_slight_severity})
    public void setUpHighlight(View view) {
        resetHighlight();
        switch (view.getId()) {
            case R.id.v_high_severity:
                setHighlight(vHighSeverity, tvHighSeverity, getResources().getColor(HIGH));
                userSelected = getResources().getString(R.string.high);
                break;
            case R.id.v_medium_severity:
                setHighlight(vMediumSeverity, tvMediumSeverity, getResources().getColor(MEDIUM));
                userSelected = getResources().getString(R.string.medium);
                break;
            case R.id.v_slight_severity:
                setHighlight(vSlightSeverity, tvSlightSeverity, getResources().getColor(SLIGHT));
                userSelected = getResources().getString(R.string.slight);
                break;
        }
    }

    public void resetHighlight() {
        vHighSeverity.setBackgroundColor(getResources().getColor(HIGHLIGHT_IDLE));
        vMediumSeverity.setBackgroundColor(getResources().getColor(HIGHLIGHT_IDLE));
        vSlightSeverity.setBackgroundColor(getResources().getColor(HIGHLIGHT_IDLE));

        tvHighSeverity.setTextColor(getResources().getColor(TEXT_IDLE));
        tvMediumSeverity.setTextColor(getResources().getColor(TEXT_IDLE));
        tvSlightSeverity.setTextColor(getResources().getColor(TEXT_IDLE));
    }

    public void setHighlight(View view, TextView textView, int bgColor) {
        view.setBackgroundColor(bgColor);
        textView.setTextColor(getResources().getColor(TEXT_HIGHLIGHTED));
    }

    public static String getUserSelected() {
        return userSelected;
    }
}