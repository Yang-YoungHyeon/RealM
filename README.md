# RealM : You can develop apps with RealM DataBase instead of Shared and SQLite.

![realmImage](https://user-images.githubusercontent.com/58409497/76819645-21acd700-684c-11ea-90ba-d8d58af3bd65.png)

## Preparation : You must fill out the following items before developing the app.
#### build.gradle - buildscript (Add RealM Plugin)
```
buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.3'

        /** Add Realm Plugin */
        classpath "io.realm:realm-gradle-plugin:3.5.0"
    }
}
...
```
#### build.gradle - apply (Set RealM Plugin)
```
apply plugin: 'com.android.application'

/** Set Realm Plugin */
apply plugin: 'realm-android'
...
```

#### RealM init ( Part of Code )
```
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
```

#### RealM Change Listener ( Part of Code )
```
private RealmResults<ListItemViewModel> pets;
private RealmChangeListener<RealmResults<ListItemViewModel>> realmChangeListener = pets -> {
    mRecyclerViewAdapter.setData(pets);
};
```

#### RealM Instance ( Part of Code )
```
private Realm realm;
realm = Realm.getDefaultInstance();
pets = realm.where(ListItemViewModel.class).findAllAsync();
pets.addChangeListener(realmChangeListener);
```

#### RealM Remove ( Part of Code )
```
@Override
protected void onDestroy() {
    super.onDestroy();
    pets.removeAllChangeListeners(); // Remove change listeners to prevent updating views not yet GCed.
    realm.close(); // Remember to close Realm when done.
}
```
