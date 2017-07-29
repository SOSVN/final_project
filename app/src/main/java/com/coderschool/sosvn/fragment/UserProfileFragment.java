package com.coderschool.sosvn.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.activity.MainActivity;
import com.coderschool.sosvn.adapter.BloodTypeAdapter;
import com.coderschool.sosvn.fragment.dialog.BloodTypeFragment;
import com.coderschool.sosvn.manager.UserManager;
import com.coderschool.sosvn.object.Blood;
import com.coderschool.sosvn.object.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileFragment extends Fragment{

    private final int REQUEST_CODE = 21;

    @BindView(R.id.ipt_first_name)
    TextInputEditText iptFirstName;
    @BindView(R.id.ipt_last_name)
    TextInputEditText iptLastName;
    @BindView(R.id.ipt_birthday)
    TextInputEditText iptBirthday;
    @BindView(R.id.sp_gender)
    Spinner spGender;
    @BindView(R.id.ipt_ice)
    TextInputEditText iptICE;
    @BindView(R.id.ipt_insurance_policy_number)
    TextInputEditText iptInsuranceNumber;
    @BindView(R.id.ipt_county)
    TextInputEditText iptCoutry;
    @BindView(R.id.ipt_blood_type)
    TextInputEditText iptBloodType;

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener callback;
    UserManager userManager = UserManager.getInstance();
    Blood blood;

    //
    ArrayAdapter<String> mGenderAdapter;
    List<String> mListGenders;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        loadDataToSpiner();

    }

    @OnClick({R.id.fl_finish})
    public void checkVerifyCode(View view) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.ipt_birthday)
    public void showDialogDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
//        datePickerDialog = new DatePickerDialog(getContext(),
//                android.R.style.Theme_Holo_Light_Dialog,
//                new c,year,month,day);
        datePickerDialog.setTitle("Choose Your Birthday");
        datePickerDialog.show();
        Toast.makeText(getContext(),calendar.getTime().toString(),Toast.LENGTH_SHORT).show();

    }
    @OnClick({R.id.iv_drop_down,R.id.ipt_blood_type})
    public void showBloodType() {
        BloodTypeFragment bloodTypeFragment = BloodTypeFragment.newInstance();
        bloodTypeFragment.setTargetFragment(this,REQUEST_CODE);
        bloodTypeFragment.show(getFragmentManager(),"");
    }

    private void loadDataToSpiner() {
        addGenders();
        mGenderAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,mListGenders);
        mGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(mGenderAdapter);
    }

    private void addGenders() {
        mListGenders = new ArrayList<>();
        mListGenders.add("Male");
        mListGenders.add("Female");
    }

    private void saveInfoUser() {
        User newUser = new User();
        newUser.setBirthday(iptBirthday.getText().toString());
        newUser.setBloodID(blood.getId());
        newUser.setFirstName(iptFirstName.getText().toString());
        newUser.setGender(spGender.getSelectedItem().toString());
        newUser.setIce(iptICE.getText().toString());
        newUser.setInsuranceNunmber(iptInsuranceNumber.getText().toString());
        newUser.setNationality(iptCoutry.getText().toString());
        newUser.setPhoneNumber("1234567");
        newUser.setUrlAvatar("adasdasda");
        newUser.setPassword("555555555555");
        userManager.writeUserToDB(newUser);

    }
    @OnClick(R.id.iv_user_avatar)
    public void clickSave() {
        saveInfoUser();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            blood = data.getBundleExtra("item").getParcelable("itemBlood");
            iptBloodType.setText(blood.getBloodType());
        }
    }
}