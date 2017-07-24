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

public class ReportWhatIsHappenedFragment extends Fragment {

    @BindView(R.id.v_burn)
    View vBurn;
    @BindView(R.id.tv_burn)
    TextView tvBurn;
    @BindView(R.id.v_fracture)
    View vFracture;
    @BindView(R.id.tv_fracture)
    TextView tvFracture;
    @BindView(R.id.v_bleed)
    View vBleed;
    @BindView(R.id.tv_bleed)
    TextView tvBleed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_what_happened, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.v_fracture, R.id.v_bleed, R.id.v_burn})
    public void setUpHighlight(View view) {
        resetHighlight();
        switch (view.getId()) {
            case R.id.v_burn:
                setHighlight(vBurn, tvBurn, getResources().getColor(R.color.burn));
                break;
            case R.id.v_fracture:
                setHighlight(vFracture, tvFracture, getResources().getColor(R.color.fracture));
                break;
            case R.id.v_bleed:
                setHighlight(vBleed, tvBleed, getResources().getColor(R.color.bleed));
                break;
        }
    }

    public void resetHighlight() {
        vBurn.setBackgroundColor(getResources().getColor(R.color.idle));
        vFracture.setBackgroundColor(getResources().getColor(R.color.idle));
        vBleed.setBackgroundColor(getResources().getColor(R.color.idle));

        tvBurn.setTextColor(getResources().getColor(R.color.text_idle));
        tvFracture.setTextColor(getResources().getColor(R.color.text_idle));
        tvBleed.setTextColor(getResources().getColor(R.color.text_idle));
    }

    public void setHighlight(View view, TextView textView, int bgColor) {
        view.setBackgroundColor(bgColor);
        textView.setTextColor(getResources().getColor(R.color.text_highlighted));
    }
}