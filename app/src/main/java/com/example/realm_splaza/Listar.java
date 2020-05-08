package com.example.realm_splaza;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class Listar extends Fragment implements RealmModel {
MainViewModel mainViewModel;
NavController navController;

ListView listView;
    public Listar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        Button filtrarBTN = view.findViewById(R.id.filtrarBTN);

        filtrarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<Persona> personas;

                ListView listView = view.findViewById(R.id.persona_list);
                RadioButton f = view.findViewById(R.id.radioButtonF);

                EditText desdeEditText = view.findViewById(R.id.desdeEditext);
                EditText hastaEditText = view.findViewById(R.id.hastaEditext);


                String desde = desdeEditText.getText().toString();
                String hasta =  hastaEditText.getText().toString();

                if (f.isChecked()) {
                    if (!desde.isEmpty() && hasta.isEmpty()) {
                        personas = realm.where(Persona.class).equalTo("genero", "F").greaterThanOrEqualTo("edad", Integer.parseInt(desde)).lessThanOrEqualTo("edad", 99).findAll();
                    } else if (desde.isEmpty() && !hasta.isEmpty()) {
                        personas = realm.where(Persona.class).equalTo("genero", "F").greaterThanOrEqualTo("edad", 0).lessThanOrEqualTo("edad", Integer.parseInt(hasta)).findAll();
                    } else if (!desde.isEmpty() && !hasta.isEmpty()) {
                        personas = realm.where(Persona.class).equalTo("genero", "F").greaterThanOrEqualTo("edad", Integer.parseInt(desde)).lessThanOrEqualTo("edad", Integer.parseInt(hasta)).findAll();
                    } else {
                        personas = realm.where(Persona.class).equalTo("genero", "F").findAll();
                    }
                } else {
                    if (!desde.isEmpty() && hasta.isEmpty()) {
                        personas = realm.where(Persona.class).equalTo("genero", "M").greaterThanOrEqualTo("edad", Integer.parseInt(desde)).lessThanOrEqualTo("edad", 99).findAll();
                    } else if (desde.isEmpty() && !hasta.isEmpty()) {
                        personas = realm.where(Persona.class).equalTo("genero", "M").greaterThanOrEqualTo("edad", 0).lessThanOrEqualTo("edad", Integer.parseInt(hasta)).findAll();
                    } else if (!desde.isEmpty() && !hasta.isEmpty()) {
                        personas = realm.where(Persona.class).equalTo("genero", "M").greaterThanOrEqualTo("edad", Integer.parseInt(desde)).lessThanOrEqualTo("edad", Integer.parseInt(hasta)).findAll();
                    } else {
                        personas = realm.where(Persona.class).equalTo("genero", "M").findAll();
                    }
                }

                ListarPersonas listarPersonas = new ListarPersonas(personas);
                listView.setAdapter(listarPersonas);
                listarPersonas.notifyDataSetChanged();
            }
        });
    }
}
