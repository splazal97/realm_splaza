package com.example.realm_splaza;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class Modificar extends Fragment {
MainViewModel mainViewModel;
NavController navController;
RadioGroup radioGroup;
RadioButton selectedRadioButton;
    public Modificar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modificar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        final EditText nombreEdidtext = view.findViewById(R.id.editTextNombreMody);
        final EditText edadEdidText = view.findViewById(R.id.editTextEdadMody);
        final EditText dniEdidText = view.findViewById(R.id.editTextDNIMody);
        final EditText numEditText = view.findViewById(R.id.editTextNumTelMody);
        final RadioButton m = view.findViewById(R.id.generoMMody);
        final RadioButton f = view.findViewById(R.id.generoFMody);
        Button modificar = view.findViewById(R.id.modificarBTN);
        radioGroup = view.findViewById(R.id.radioGeneroMody);



        mainViewModel.personaSeleccionado.observe(getViewLifecycleOwner(), new Observer<Persona>() {
            @Override
            public void onChanged(Persona persona) {
                nombreEdidtext.setText(persona.getNombre());
                edadEdidText.setText(String.valueOf(persona.getEdad()));
                dniEdidText.setText(persona.getDni());
                if (persona.getGenero().equals("M")){
                    m.setChecked(true);
                } else {
                    f.setChecked(true);
                }
                numEditText.setText(String.valueOf(persona.getNumTel()));
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dniEdidText.getText().toString().isEmpty()){
                    Toasty.error(getContext(), "El DNI no pued estar vacio", Toast.LENGTH_SHORT, true).show();
                } else {
                    int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    if (selectedRadioButtonId !=-1){
                        selectedRadioButton = view.findViewById(selectedRadioButtonId);
                        String genero = selectedRadioButton.getText().toString();

                        mainViewModel.modificarPersona(nombreEdidtext.getText().toString(),Integer.parseInt(edadEdidText.getText().toString()),dniEdidText.getText().toString(),genero,Integer.parseInt(numEditText.getText().toString()));
                        Navigation.findNavController(view).navigate(R.id.listaModificar);
                    }


                }
            }
        });

    }
}
