package com.coderschool.sosvn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.activity.UserProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.coderschool.sosvn.R.id.fl_virtual_backspace;
import static com.coderschool.sosvn.R.id.tv_virtual_num_0;
import static com.coderschool.sosvn.R.id.tv_virtual_num_1;
import static com.coderschool.sosvn.R.id.tv_virtual_num_2;
import static com.coderschool.sosvn.R.id.tv_virtual_num_3;
import static com.coderschool.sosvn.R.id.tv_virtual_num_4;
import static com.coderschool.sosvn.R.id.tv_virtual_num_5;
import static com.coderschool.sosvn.R.id.tv_virtual_num_6;
import static com.coderschool.sosvn.R.id.tv_virtual_num_7;
import static com.coderschool.sosvn.R.id.tv_virtual_num_8;
import static com.coderschool.sosvn.R.id.tv_virtual_num_9;

public class OTPVerificationFragment extends Fragment {

    @BindView(R.id.tv_first_code)
    TextView tvFirstCode;
    @BindView(R.id.tv_second_code)
    TextView tvSecondCode;
    @BindView(R.id.tv_third_code)
    TextView tvThirdCode;
    @BindView(R.id.tv_fourth_code)
    TextView tvFourthCode;
    @BindView(R.id.tv_submit_text)
    TextView tvSubmitText;

    private int curPos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_verification, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.fl_submit_code})
    public void checkVerifyCode(View view) {
        String verifyCodeStr = tvFirstCode.getText().toString() + tvSecondCode.getText().toString() + tvThirdCode.getText().toString() + tvFourthCode.getText().toString();
        if (verifyCodeStr != "") {
            int verifyCode = Integer.parseInt(verifyCodeStr);
            if (verifyCode == 1111) {
                Intent intent = new Intent(getContext(), UserProfileActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }
    }

    @OnClick({tv_virtual_num_1, tv_virtual_num_2, tv_virtual_num_3
            , tv_virtual_num_4, tv_virtual_num_5, tv_virtual_num_6
            , tv_virtual_num_7, tv_virtual_num_8, tv_virtual_num_9
            , tv_virtual_num_0, fl_virtual_backspace})
    public void setUpVirtualKeyboardInput(View view) {
        switch (view.getId()) {
            case tv_virtual_num_1:
                calcuNumberInput(1, false);
                break;
            case tv_virtual_num_2:
                calcuNumberInput(2, false);
                break;
            case tv_virtual_num_3:
                calcuNumberInput(3, false);
                break;
            case tv_virtual_num_4:
                calcuNumberInput(4, false);
                break;
            case tv_virtual_num_5:
                calcuNumberInput(5, false);
                break;
            case tv_virtual_num_6:
                calcuNumberInput(6, false);
                break;
            case tv_virtual_num_7:
                calcuNumberInput(7, false);
                break;
            case tv_virtual_num_8:
                calcuNumberInput(8, false);
                break;
            case tv_virtual_num_9:
                calcuNumberInput(9, false);
                break;
            case tv_virtual_num_0:
                calcuNumberInput(0, false);
                break;
            case fl_virtual_backspace:
                calcuNumberInput(curPos - 1, true);
                break;
            default:
                break;
        }
    }

    private void calcuNumberInput(int input, boolean isRemove) {
        if (!isRemove) {
            switch (curPos) {
                case 0:
                    tvFirstCode.setText(String.valueOf(input));
                    break;
                case 1:
                    tvSecondCode.setText(String.valueOf(input));
                    break;
                case 2:
                    tvThirdCode.setText(String.valueOf(input));
                    break;
                case 3:
                    tvFourthCode.setText(String.valueOf(input));
                    tvSubmitText.setText(R.string.check);
                    break;
                default:
                    break;
            }
            if (curPos < 4)
                curPos++;
        } else {
            if (curPos > 0)
                curPos--;
            switch (curPos) {
                case 0:
                    tvFirstCode.setText("");
                    break;
                case 1:
                    tvSecondCode.setText("");
                    break;
                case 2:
                    tvThirdCode.setText("");
                    break;
                case 3:
                    tvFourthCode.setText("");
                    tvSubmitText.setText(R.string.resend);
                    break;
                default:
                    break;
            }
        }
    }
}