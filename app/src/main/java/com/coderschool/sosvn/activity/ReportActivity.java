package com.coderschool.sosvn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.adapter.pager.ReportPagerAdapter;
import com.coderschool.sosvn.fragment.ReportSeverityFragment;
import com.coderschool.sosvn.fragment.ReportSumaryFragment;
import com.coderschool.sosvn.fragment.ReportWhatIsHappenedFragment;
import com.coderschool.sosvn.fragment.ReportWhereItIsFragment;
import com.coderschool.sosvn.manager.ReportManager;
import com.coderschool.sosvn.object.Report;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends AppCompatActivity {

    private final int STATE_END_LINE_IDLE = R.drawable.ic_custom_arrow_3;
    private final int STATE_END_LINE_COVER = R.drawable.ic_custom_arrow_1;
    private final int STATE_END_LINE_PASSED = R.drawable.ic_custom_arrow_2;
    private int currentStep = 0;
    private ReportManager reportManager;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_end_line_process_step_1)
    ImageView ivEndLineProcessStep1;
    @BindView(R.id.iv_end_line_process_step_2)
    ImageView ivEndLineProcessStep2;
    @BindView(R.id.iv_end_line_process_step_3)
    ImageView ivEndLineProcessStep3;
    @BindView(R.id.v_process_step_1)
    View vEndLineProcessStep1;
    @BindView(R.id.v_process_step_2)
    View vEndLineProcessStep2;
    @BindView(R.id.v_process_step_3)
    View vEndLineProcessStep3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        reportManager = ReportManager.getInstance();

        viewpager.setAdapter(new ReportPagerAdapter(getSupportFragmentManager(), 4));
        viewpager.setCurrentItem(currentStep);
        viewpager.setOffscreenPageLimit(4);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                // Check if this is the page you want.
                if(currentStep < position)
                    changeNextView();
                else if (currentStep > position)
                    changePrevView();
                currentStep = position;
            }
        });
    }

    @OnClick(R.id.tv_confirm_report)
    public void nextStep(View view) {
        changeNextView();
        currentStep++;
        viewpager.setCurrentItem(currentStep);
    }

    private void changeNextView() {
        switch (currentStep) {
            case 0:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_COVER);
                vEndLineProcessStep1.setVisibility(View.VISIBLE);
                break;
            case 1:
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_COVER);
                vEndLineProcessStep2.setVisibility(View.VISIBLE);
                break;
            case 2:
                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_PASSED);
                vEndLineProcessStep3.setVisibility(View.VISIBLE);
                reportManager.setReport(ReportSeverityFragment.getUserSelected()
                        , ReportWhatIsHappenedFragment.getUserSelected()
                        , ReportWhereItIsFragment.getWhere());

                break;
            default:
                break;
        }
    }

    private void changePrevView() {
        switch (currentStep) {
            case 3:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_COVER);
                vEndLineProcessStep3.setVisibility(View.GONE);
                currentStep--;
                viewpager.setCurrentItem(currentStep);
                break;
            case 2:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_COVER);
                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_IDLE);
                vEndLineProcessStep2.setVisibility(View.GONE);
                currentStep--;
                viewpager.setCurrentItem(currentStep);
                break;
            case 1:
                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_COVER);
                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_IDLE);
                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_IDLE);
                vEndLineProcessStep1.setVisibility(View.GONE);
                currentStep--;
                viewpager.setCurrentItem(currentStep);
                break;
            case 0:
                finish();
                break;
            default:
                break;
        }
    }

//    @OnClick({R.id.iv_previous_step, R.id.tv_previous_step})
//    public void previousStep(View view) {
//        switch (currentStep) {
//            case 3:
//                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
//                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_PASSED);
//                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_COVER);
//                vEndLineProcessStep3.setVisibility(View.GONE);
//                currentStep--;
//                viewpager.setCurrentItem(currentStep);
//                break;
//            case 2:
//                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_PASSED);
//                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_COVER);
//                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_IDLE);
//                vEndLineProcessStep2.setVisibility(View.GONE);
//                currentStep--;
//                viewpager.setCurrentItem(currentStep);
//                break;
//            case 1:
//                ivEndLineProcessStep1.setImageResource(STATE_END_LINE_COVER);
//                ivEndLineProcessStep2.setImageResource(STATE_END_LINE_IDLE);
//                ivEndLineProcessStep3.setImageResource(STATE_END_LINE_IDLE);
//                vEndLineProcessStep1.setVisibility(View.GONE);
//                currentStep--;
//                viewpager.setCurrentItem(currentStep);
//                break;
//            case 0:
//                finish();
//                break;
//            default:
//                break;
//        }
//    }
}
