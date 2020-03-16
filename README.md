# RealM

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
