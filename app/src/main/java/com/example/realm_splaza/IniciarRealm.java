package com.example.realm_splaza;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class IniciarRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("realm.realm")
                .schemaVersion(2)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
