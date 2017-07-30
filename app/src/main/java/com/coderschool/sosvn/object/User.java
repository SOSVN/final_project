package com.coderschool.sosvn.object;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Admin on 7/26/2017.
 */

public class User extends RealmObject implements Parcelable {

    @PrimaryKey
    private String id;

    private String birthday;
    private String bloodType;
    private String gender;
    private String ice;
    private String insuranceNunmber;
    private String firstName;
    private String lastName;
    private String nationality;
    private String urlAvatar;
    private String phoneNumber;
    private String password;

    public User() {
    }

    public User(Parcel in) {
        id = in.readString();
        birthday = in.readString();
        bloodType = in.readString();
        gender = in.readString();
        ice = in.readString();
        insuranceNunmber = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        nationality = in.readString();
        urlAvatar = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
    }


    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(birthday);
        parcel.writeString(bloodType);
        parcel.writeString(gender);
        parcel.writeString(ice);
        parcel.writeString(insuranceNunmber);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(nationality);
        parcel.writeString(urlAvatar);
        parcel.writeString(password);
        parcel.writeString(phoneNumber);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public void setInsuranceNunmber(String insuranceNunmber) {
        this.insuranceNunmber = insuranceNunmber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getGender() {
        return gender;
    }

    public String getIce() {
        return ice;
    }

    public String getInsuranceNunmber() {
        return insuranceNunmber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }
}
