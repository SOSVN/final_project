package com.coderschool.sosvn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.activity.MainActivity;
import com.coderschool.sosvn.activity.VerificationActivity;
import com.coderschool.sosvn.fragment.dialog.CountryFragment;
import com.coderschool.sosvn.manager.UserManager;
import com.coderschool.sosvn.object.Country;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpOTPFragment extends Fragment {

    private int REQUEST_CODE = 20;

    @BindView(R.id.ipt_pick_contry)
    TextInputEditText iptCountry;
    @BindView(R.id.ipt_enter_phone_number)
    TextInputEditText iptPhoneNumber;


    Country country;
    UserManager userManager = UserManager.getInstance();
    private String mPhoneNumber;

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
        iptCountry.setKeyListener(null);

    }

    @OnClick({R.id.fl_send_code, R.id.v_send_code, R.id.tv_send_code})
    public void goOTPVerification(View view) {
        if (!TextUtils.isEmpty(iptPhoneNumber.getText())) {
            mPhoneNumber = iptPhoneNumber.getText().toString().trim();
            if (mPhoneNumber.startsWith("0")) {
                mPhoneNumber = mPhoneNumber.substring(1);
            }
            if (country != null) {
                mPhoneNumber = country.getPhoneCode() + mPhoneNumber;
                userManager.sendVertificationCode(getActivity(), mCallback, mPhoneNumber);
            }

        } else {

        }

    }

    @OnClick({R.id.fl_pick_contry, R.id.ipt_pick_contry, R.id.iv_drop_down})
    public void showEditDialog() {
        CountryFragment countryFragment = CountryFragment.newInstance();
        countryFragment.setTargetFragment(this, REQUEST_CODE);
        countryFragment.show(getFragmentManager(), "");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            country = data.getBundleExtra("item").getParcelable("country");
            iptCountry.setText(country.getName() + "(" + country.getPhoneCode() + ")");
        }

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                    Log.d("KKK", phoneAuthCredential.toString());
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Log.d("KKK", e.toString());
                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                    Intent intent = new Intent(getContext(), VerificationActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ResendToken", forceResendingToken);
                    bundle.putString("VerificationId", s);
                    bundle.putString("phoneNumber", mPhoneNumber);
                    intent.putExtra("item", bundle);
                    startActivity(intent);
                    getActivity().finish();
                }
            };

    @Override
    public void onStart() {
        super.onStart();
        if (userManager.getUser() != null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }
}