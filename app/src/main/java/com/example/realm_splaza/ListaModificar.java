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
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaModificar extends Fragment {
MainViewModel mainViewModel;
NavController navController;
PersonaModAdapter personaModAdapter;
    public ListaModificar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_modificar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView personasRecicler = view.findViewById(R.id.item_list);
        personaModAdapter = new PersonaModAdapter();
        personasRecicler.setAdapter(personaModAdapter);

        mainViewModel.listarPersonas.observe(getViewLifecycleOwner(), new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                personaModAdapter.establecerListaPersonas(personas);
            }
        });
    }
    class PersonaModAdapter extends RecyclerView.Adapter<PersonaModAdapter.PersonasModViewHolder> {
        List<Persona> personaList;

        @NonNull
        @Override
        public PersonasModViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersonasModViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_modificar,parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PersonasModViewHolder holder, int position) {
            final Persona persona = personaList.get(position);

            holder.dniTextView.setText(persona.getDni());
            holder.nombreTextView.setText(persona.getNombre());
            //holder.edadTextView.setText(String.valueOf(persona.getEdad()));
            holder.generoTextView.setText(persona.getGenero());
            holder.numTelTextView.setText(String.valueOf(persona.getNumTel()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainViewModel.establecerPersona(persona);
                    navController.navigate(R.id.modificar);
                }
            });
        }

        @Override
        public int getItemCount() {
            return personaList == null ? 0 : personaList.size();
        }
    public void establecerListaPersonas(List<Persona> personas){
            this.personaList = personas;
            notifyDataSetChanged();
    }
        class PersonasModViewHolder extends RecyclerView.ViewHolder {
            TextView dniTextView, nombreTextView, generoTextView, edadTextView,numTelTextView;

            public PersonasModViewHolder(@NonNull View itemView) {
                super(itemView);
                dniTextView = itemView.findViewById(R.id.textViewDNImod);
                nombreTextView = itemView.findViewById(R.id.textViewNombremod);
                generoTextView = itemView.findViewById(R.id.textViewGeneromod);
                //edadTextView = itemView.findViewById(R.id.textViewEdadmod);
                numTelTextView = itemView.findViewById(R.id.textViewNumTelmod);
            }
        }
    }
}
