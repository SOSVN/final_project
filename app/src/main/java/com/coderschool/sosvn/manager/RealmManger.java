package com.coderschool.sosvn.manager;

import com.coderschool.sosvn.object.User;

import io.realm.Realm;

/**
 * Created by Admin on 7/30/2017.
 */

public class RealmManger {

    private static Realm realm;
    private static RealmManger mRealmManager;

    public static RealmManger getInstance() {
        if (mRealmManager == null)
            mRealmManager = new RealmManger();
        return mRealmManager;
    }


    public void writeUser(final User user) {
        realm = Realm.getDefaultInstance();

        try {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(user);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }


    public User loadUser() {

        realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        return user;
    }


}
