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

import static android.R.attr.value;

public class ReportWhatIsHappenedFragment extends Fragment {

    private final int BURN = R.color.burn;
    private final int FRACTURE = R.color.fracture;
    private final int BLEED = R.color.bleed;

    private final int HIGHLIGHT_IDLE = R.color.idle;
    private final int TEXT_IDLE = R.color.text_idle;
    private final int TEXT_HIGHLIGHTED = R.color.text_highlighted;

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

    private static String userSelected = "";

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
        //set default value for user
        setHighlight(vBleed, tvBleed, getResources().getColor(BLEED));
        userSelected = getResources().getString(R.string.bleed);
    }

    @OnClick({R.id.v_fracture, R.id.v_bleed, R.id.v_burn})
    public void setUpHighlight(View view) {
        resetHighlight();
        switch (view.getId()) {
            case R.id.v_burn:
                setHighlight(vBurn, tvBurn, getResources().getColor(BURN));
                userSelected = getResources().getString(R.string.burn);
                break;
            case R.id.v_fracture:
                setHighlight(vFracture, tvFracture, getResources().getColor(FRACTURE));
                userSelected = getResources().getString(R.string.fracture);
                break;
            case R.id.v_bleed:
                setHighlight(vBleed, tvBleed, getResources().getColor(BLEED));
                userSelected = getResources().getString(R.string.bleed);
                break;
        }
    }

    public void resetHighlight() {
        vBurn.setBackgroundColor(getResources().getColor(HIGHLIGHT_IDLE));
        vFracture.setBackgroundColor(getResources().getColor(HIGHLIGHT_IDLE));
        vBleed.setBackgroundColor(getResources().getColor(HIGHLIGHT_IDLE));

        tvBurn.setTextColor(getResources().getColor(TEXT_IDLE));
        tvFracture.setTextColor(getResources().getColor(TEXT_IDLE));
        tvBleed.setTextColor(getResources().getColor(TEXT_IDLE));
    }

    public void setHighlight(View view, TextView textView, int bgColor) {
        view.setBackgroundColor(bgColor);
        textView.setTextColor(getResources().getColor(TEXT_HIGHLIGHTED));
    }

    public static String getUserSelected() {
        return userSelected;
    }
}