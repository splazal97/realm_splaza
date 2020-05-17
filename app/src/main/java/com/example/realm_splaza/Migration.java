package com.example.realm_splaza;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.annotations.Required;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        //He realizado dos migraciones, en la primera creavamos el numero de telefono y en la segunda eliminamos la edad
        if (oldVersion == 2){
            Log.d("Migracion","Actualizacion 1.0");
            RealmSchema realmSchema = realm.getSchema();
        RealmObjectSchema schemaPersona = realmSchema.get("Persona");
        schemaPersona
            //.addIndex("numTel")
            .removeField("edad");
            oldVersion++;
        }
    }
}
