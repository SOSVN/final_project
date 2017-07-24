package com.coderschool.sosvn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.adapter.pager.ReportPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends AppCompatActivity {

    private final int STATE_END_LINE_IDLE = R.drawable.ic_custom_arrow_3;
    private final int STATE_END_LINE_COVER = R.drawable.ic_custom_arrow_1;
    private final int STATE_END_LINE_PASSED = R.drawable.ic_custom_arrow_2;
    private int currentStep = 0;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_end_line_process_step_1)
    ImageView ivEndLineProcessStep1;
    @BindView(R.id.iv_end_line_process_step_2)
    ImageView ivEndLineProcessStep2;
    @BindView(R.id.v_process_step_1)
    View vEndLineProcessStep1;
    @BindView(R.id.v_process_step_2)
    View vEndLineProcessStep2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        viewpager.setAdapter(new ReportPagerAdapter(getSupportFragmentManager(), 3));
        viewpager.setCurrentItem(currentStep);
        viewpager.setOffscreenPageLimit(3);
    }

    @OnClick(R.id.iv_next_step)
    public void nextStep(View view) {
        switch (currentStep) {
            case 0:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_COVER);
                vEndLineProcessStep1.setVisibility(View.VISIBLE);
                currentStep++;
                viewpager.setCurrentItem(currentStep);
                break;
            case 1:
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_PASSED);
                vEndLineProcessStep2.setVisibility(View.VISIBLE);
                currentStep++;
                viewpager.setCurrentItem(currentStep);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_previous_step, R.id.tv_previous_step})
    public void previousStep(View view) {
        switch (currentStep) {
            case 2:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_COVER);
                vEndLineProcessStep2.setVisibility(View.GONE);
                currentStep--;
                viewpager.setCurrentItem(currentStep);
                break;
            case 1:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_COVER);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_IDLE);
                vEndLineProcessStep1.setVisibility(View.GONE);
                currentStep--;
                viewpager.setCurrentItem(currentStep);
                break;
            case 0:
                break;
            default:
                break;
        }
    }
}
