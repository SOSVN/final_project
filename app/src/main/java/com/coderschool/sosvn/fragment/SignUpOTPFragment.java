package com.coderschool.sosvn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.activity.VerificationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpOTPFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_otp, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.fl_send_code, R.id.v_send_code, R.id.tv_send_code})
    public void goOTPVerification(View view) {
        Intent intent = new Intent(getContext(), VerificationActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick({R.id.fl_pick_contry, R.id.ipt_pick_contry, R.id.iv_drop_down})
    public void showEditDialog() {
    }
}