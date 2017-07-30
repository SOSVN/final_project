package com.coderschool.sosvn.manager;

import android.app.Activity;

import com.coderschool.sosvn.R;
import com.coderschool.sosvn.object.Country;
import com.coderschool.sosvn.object.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserManager {

    private static UserManager mUserManager;

    private UserManager() {
    }

    public static UserManager getInstance() {

        if (mUserManager == null) {
            mUserManager = new UserManager();
        }
        return mUserManager;
    }

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private FirebaseUser user;
    private Callback mCallback;

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public List<Country> getAllCountries() {
        String[] nameCoutry = new String[]{
                "Australia",
                "Brazil",
                "Canada",
                "China",
                "France",
                "Germany",
                "India",
                "Italy",
                "Japan",
                "South Korea",
                "Russia",
                "Singapore",
                "Spain",
                "Thailand",
                "United States",
                "United Kingdom",
                "Viet Nam"
        };

        int[] flags = new int[]{
                R.drawable.australia,
                R.drawable.brazil,
                R.drawable.canada,
                R.drawable.china,
                R.drawable.france,
                R.drawable.germany,
                R.drawable.idia,
                R.drawable.italy,
                R.drawable.japan,
                R.drawable.southkorea,
                R.drawable.russia,
                R.drawable.singapore,
                R.drawable.spain,
                R.drawable.thailand,
                R.drawable.unitedstates,
                R.drawable.unitedkingdom,
                R.drawable.vietnam

        };

        String[] phoneCodes = new String[]{
                "+61",
                "+55",
                "+1",
                "+86",
                "+33",
                "+49",
                "+91",
                "+39",
                "+81",
                "+82",
                "+7",
                "+65",
                "+34",
                "+66",
                "+1",
                "+44",
                "+84"
        };

        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < phoneCodes.length; i++) {
            Country country = new Country();
            country.setId(String.valueOf(i + 1));
            country.setName(nameCoutry[i]);
            country.setPhoneCode(phoneCodes[i]);
            country.setIdFlag(flags[i]);
            countries.add(country);
        }
        return countries;
    }

    public void sendVertificationCode(
            Activity activity,
            PhoneAuthProvider.OnVerificationStateChangedCallbacks callback, String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                activity,
                callback
        );
    }


    public void writeUserToDB(User user) {

        myRef.child("User").child(user.getId())
                .setValue(user, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            mCallback.onComplete(true);
                        } else {
                            mCallback.onComplete(false);
                        }
                    }
                });

    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    private void getInfoOfUser() {
        myRef.child("SQEHx7Mplc0KAK66dped")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }


    public interface Callback<T> {
        void onSuccess(List<T> list);

        void onComplete(boolean check);
    }
}
