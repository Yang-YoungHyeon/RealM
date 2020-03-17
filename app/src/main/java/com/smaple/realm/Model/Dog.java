package com.smaple.realm.Model;

public class Dog extends PetProfile {
    public Dog(String name, int age, byte[] image) {
        super.mName = name;
        super.mAge = age;
        super.mImage = image;
    }

    public String getName() {
        return super.mName;
    }

    public int getAge() {
        return super.mAge;
    }

    public byte[] getImage() {
        return super.mImage;
    }
}
