package com.coderschool.sosvn.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.coderschool.sosvn.object.Blood;
import com.coderschool.sosvn.object.Country;
import com.coderschool.sosvn.object.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserManager {

    private static UserManager mUserManager;

    private UserManager() {}
    public static UserManager getInstance() {

        if (mUserManager == null) {
            mUserManager =  new UserManager();
        }
        return mUserManager;
    }

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private FirebaseUser mUser;
    private Callback mCallback;

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    public void getAllCountries() {

        myRef.child("Country").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Country> list = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Country country = new Country();
                    country.setName(child.child("name").getValue().toString());
                    country.setPhoneCode(child.child("phoneCode").getValue().toString());
                    country.setUrlFlag(child.child("urlFlag").getValue().toString());
                    country.setId(child.getKey());
                    list.add(country);
                }
                mCallback.onSuccess(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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


    public void getAllBloodType() {
        myRef.child("Blood").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Blood> list = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Blood blood = new Blood();
                    blood.setBloodType(child.child("bloodType").getValue().toString());
                    blood.setId(child.getKey());
                    list.add(blood);
                }
                mCallback.onSuccess(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void writeUserToDB(User user) {
        final boolean[] check = {false};
        myRef.child("User").push()
                .setValue(user, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        check[0] = true;
                    }
                });
        Log.d("KKK11111111",check.toString());
    }

    private void getInfoOfUser(String phoneNumber) {

    }
    public interface Callback<T> {
        void onSuccess(List<T> list);
        void onFail(boolean check);
    }
}
