package com.smaple.realm.ViewModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListItemViewModel extends RealmObject {
    @PrimaryKey
    private String mName;
    private int mAge;
    private byte[] mImage;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }
}
