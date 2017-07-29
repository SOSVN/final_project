package com.coderschool.sosvn.object;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Admin on 7/29/2017.
 */

public class Blood extends RealmObject implements Parcelable {

    private String id;
    private String bloodType;

    public Blood() {}


    protected Blood(Parcel in) {
        id = in.readString();
        bloodType = in.readString();
    }

    public static final Creator<Blood> CREATOR = new Creator<Blood>() {
        @Override
        public Blood createFromParcel(Parcel in) {
            return new Blood(in);
        }

        @Override
        public Blood[] newArray(int size) {
            return new Blood[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(bloodType);
    }
}
