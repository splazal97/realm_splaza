package com.example.realm_splaza;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class MainViewModel extends AndroidViewModel {
    Realm realm;
    Context context;
    RealmResults<Persona> personas;


    public MainViewModel(@NonNull Application application) {
        super(application);
        crearPersona();
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e){
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
        }
        context = getApplication();
        cargarlista();

    }
    void insertarPersona(final String nombre, final int edad,final String DNI,final String genero,final int numtel){
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Persona persona = realm.createObject(Persona.class, DNI);
        persona.setNombre(nombre);
        persona.setEdad(edad);
        persona.setGenero(genero);
        persona.setNumTel(numtel);
        realm.commitTransaction();
    }
    void modificarPersona(final String nombre, final int edad, final String dni, final String genero,final int numTel){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Persona> personas = realm.where(Persona.class).equalTo("dni", dni).findAll();
                personas.setValue("nombre", nombre);
                personas.setValue("edad", edad);
                personas.setValue("genero", genero);
                personas.setValue("numTel",numTel);
            }
        });

    }

    void crearPersona (){
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            Persona persona = realm.createObject(Persona.class, UUID.randomUUID().toString());
            persona.setNombre("Sergio");
            persona.setEdad(21);
            persona.setGenero("M");
            persona.setNumTel(658556985);
            realm.commitTransaction();
        }
    public MutableLiveData<Persona> personaSeleccionado = new MutableLiveData<>();
    MutableLiveData<List<Persona>> listarPersonas = new MutableLiveData<>();
    public void cargarlista() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> results;
        results =  realm.where(Persona.class).findAll();
        List<Persona> personas = new ArrayList<>();
        listarPersonas.setValue(results);
    }
    public void establecerPersona(Persona persona) {
        personaSeleccionado.setValue(persona);
    }
}



