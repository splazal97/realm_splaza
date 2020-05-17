package com.example.realm_splaza;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Insert extends Fragment {
    Realm realm;
    MainViewModel mainViewModel;
    NavController navController;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
   private EditText nombreEdittext,dniEditext, edadEditText,numEdittext;
   private Button insert;
    public Insert() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        nombreEdittext =view.findViewById(R.id.editTextNombre);
        dniEditext = view.findViewById(R.id.editTextDNI);
        //edadEditText = view.findViewById(R.id.editTextEdad);
        numEdittext = view.findViewById(R.id.editTextNumTel);
        insert = view.findViewById(R.id.insertBTN);
        radioGroup = view.findViewById(R.id.radioGenero);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreEdittext.getText().toString().isEmpty()){
                    Toasty.error(getContext(),"El nombre no puede estar vacio",Toast.LENGTH_SHORT, true).show();
                } else if (dniEditext.getText().toString().isEmpty()){
                    Toasty.error(getContext(), "El DNI no pued estar vacio", Toast.LENGTH_SHORT, true).show();
                }
                /*else if (edadEditText.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "La edad no pued estar vacio", Toast.LENGTH_SHORT, true).show();
                }
                 */
                else if (numEdittext.getText().toString().isEmpty()){
                    Toasty.error(getContext(), "El telefono no ùede estar vacio", Toast.LENGTH_SHORT, true).show();
                }
                else {
                    int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    if (selectedRadioButtonId !=-1) {
                        selectedRadioButton = view.findViewById(selectedRadioButtonId);
                        String genero = selectedRadioButton.getText().toString();
                        mainViewModel.insertarPersona(nombreEdittext.getText().toString(), /*Integer.parseInt(edadEditText.getText().toString()),*/ dniEditext.getText().toString(), genero,Integer.parseInt(numEdittext.getText().toString( )));
                        Toasty.success(getContext(), "Insertado correctamente", Toast.LENGTH_SHORT, true).show();
                        Navigation.findNavController(view).navigate(R.id.insert);
                    }
                }
                }
        });

    }
}
