package com.coderschool.sosvn.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.fragment.OTPVerificationFragment;

import butterknife.ButterKnife;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        ButterKnife.bind(this);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_verify_otp, new OTPVerificationFragment());
        ft.commit();
        //end
    }
}