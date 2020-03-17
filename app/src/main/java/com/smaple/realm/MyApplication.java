package com.smaple.realm;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.smaple.realm.ViewModel.ListItemViewModel;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .initialData(realm -> {
                    // Load from file "cities.json" first time
                    List<ListItemViewModel> pets = loadList();
                    if (pets != null) {
                        // Use insertOrUpdate() to convert the objects into proper RealmObjects managed by Realm.
                        realm.insertOrUpdate(pets);
                    }
                })
                .deleteRealmIfMigrationNeeded()
                .build()
        );
    }

    private List<ListItemViewModel> loadList() {
        List<ListItemViewModel> list = new LinkedList<>();

        ListItemViewModel dog = new ListItemViewModel();
        ListItemViewModel cat = new ListItemViewModel();

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.notepad);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        dog.setmName("댕댕이");
        dog.setmAge(1);
        dog.setmImage(byteArray);

        cat.setmName("냥냥이");
        cat.setmAge(2);
        cat.setmImage(byteArray);

        list.add(dog);
        list.add(cat);

        System.out.println("YH"+list.size());
        return list;
    }
}
