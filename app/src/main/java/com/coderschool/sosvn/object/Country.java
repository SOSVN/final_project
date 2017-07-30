package com.coderschool.sosvn.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 7/26/2017.
 */

public class Country implements Parcelable {

    private String id;

    private String name;

    private String phoneCode;

    private int idFlag;

    public Country() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public int getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(int idFlag) {
        this.idFlag = idFlag;
    }

    protected Country(Parcel in) {
        id = in.readString();
        name = in.readString();
        phoneCode = in.readString();
        idFlag = in.readInt();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(phoneCode);
        parcel.writeInt(idFlag);
    }
}
