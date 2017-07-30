package com.coderschool.sosvn.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.activity.MainActivity;
import com.coderschool.sosvn.fragment.dialog.BloodTypeFragment;
import com.coderschool.sosvn.manager.RealmManger;
import com.coderschool.sosvn.manager.UserManager;
import com.coderschool.sosvn.object.User;
import com.coderschool.sosvn.util.StringUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileFragment extends Fragment implements UserManager.Callback<User> {

    private final int REQUEST_CODE = 21;
    public static final int RESULT_GALLERY = 0;

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
    @BindView(R.id.iv_user_avatar)
    CircleImageView avatar;


    DatePickerDialog datePickerDialog;
    UserManager userManager = UserManager.getInstance();
    RealmManger realmManger = RealmManger.getInstance();
    //

    ArrayAdapter<String> mGenderAdapter;
    List<String> mListGenders;
    String urlAvatar = "";

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
        iptBirthday.setKeyListener(null);
        userManager.setCallback(this);
        loadDataToSpiner();


    }

    @OnClick({R.id.fl_finish})
    public void submitInfoOfUser() {
        saveInfoUser();
    }

    @OnClick(R.id.ipt_birthday)
    public void showDialogDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        datePickerDialog = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(Calendar.YEAR, i);
                        calendar.set(Calendar.MONTH, i1);
                        calendar.set(Calendar.DAY_OF_MONTH, i2);
                        iptBirthday.setText(format.format(calendar.getTime()).toString());
                    }
                }, year, month, day);
        datePickerDialog.setTitle("Choose Your Birthday");
        datePickerDialog.show();

    }

    @OnClick({R.id.iv_drop_down, R.id.ipt_blood_type})
    public void showBloodType() {
        BloodTypeFragment bloodTypeFragment = BloodTypeFragment.newInstance();
        bloodTypeFragment.setTargetFragment(this, REQUEST_CODE);
        bloodTypeFragment.show(getFragmentManager(), "");
    }

    private void loadDataToSpiner() {

        mListGenders = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.gender_array)));
        mGenderAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mListGenders);
        mGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(mGenderAdapter);
    }

    private void saveInfoUser() {
        User newUser = new User();
        newUser.setId(StringUtil.randomString());
        newUser.setBirthday(iptBirthday.getText().toString());
        newUser.setBloodType(iptBloodType.getText().toString());
        newUser.setFirstName(iptFirstName.getText().toString());
        newUser.setLastName(iptLastName.getText().toString());
        newUser.setGender(spGender.getSelectedItem().toString());
        newUser.setIce(iptICE.getText().toString());
        newUser.setInsuranceNunmber(iptInsuranceNumber.getText().toString());
        newUser.setNationality(iptCoutry.getText().toString());
        newUser.setPhoneNumber("1234567");
        newUser.setUrlAvatar(urlAvatar);
        newUser.setPassword("555555555555");
        userManager.writeUserToDB(newUser);
        realmManger.writeUser(newUser);

    }


    @OnClick(R.id.iv_update_timeline)
    public void clickSave() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE:
                    iptBloodType.setText(data.getStringExtra("item"));
                    break;
                case RESULT_GALLERY:
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                        urlAvatar = StringUtil.BitMapToString(bitmap);
                        avatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public void onSuccess(List<User> list) {

    }

    @Override
    public void onComplete(boolean check) {
        if (check == true) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "No success", Toast.LENGTH_SHORT).show();
        }
    }
}