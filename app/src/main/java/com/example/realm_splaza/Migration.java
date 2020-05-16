package com.example.realm_splaza;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema = realm.getSchema();
        if (oldVersion == 0){
            Log.d("Migracion","Actualizacion 1.0");
            RealmObjectSchema schemaPersona = realmSchema.get("Persona");
            schemaPersona.addIndex("numTel");
            oldVersion++;
        }
    }
}
