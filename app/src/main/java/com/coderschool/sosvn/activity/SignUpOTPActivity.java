package com.coderschool.sosvn.activity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.ahoy.AhoyOnboarderActivity;
import com.coderschool.sosvn.ahoy.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class SignUpOTPActivity extends AhoyOnboarderActivity {

    private final int FIND_TITLE = R.string.find_title;
    private final int CALL_TITLE = R.string.call_title;
    private final int REPORT_TITLE = R.string.report_title;
    private final int FIND_MESS = R.string.find_mess;
    private final int CALL_MESS = R.string.call_mess;
    private final int REPORT_MESS = R.string.report_mess;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard(getResources().getString(FIND_TITLE)
                , getResources().getString(FIND_MESS)
                , R.drawable.ic_find_hospital);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard(getResources().getString(CALL_TITLE)
                , getResources().getString(CALL_MESS)
                , R.drawable.ic_call_doctor);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard(getResources().getString(REPORT_TITLE)
                , getResources().getString(REPORT_MESS)
                , R.drawable.ic_report_checker);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
    }
}