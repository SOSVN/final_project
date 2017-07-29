package com.coderschool.sosvn.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.fragment.MainScreenFragment;
import com.coderschool.sosvn.fragment.SignUpOTPFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main_screen, new MainScreenFragment());
        ft.commit();
        //end
    }
}
