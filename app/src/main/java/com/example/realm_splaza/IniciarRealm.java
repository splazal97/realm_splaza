package com.example.realm_splaza;

import android.app.Application;

import io.realm.Realm;

public class IniciarRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
