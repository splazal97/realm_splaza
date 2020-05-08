package com.example.realm_splaza;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class ListarPersonas extends RealmBaseAdapter<Persona> implements ListAdapter {
    MainViewModel mainViewModel;
    NavController navController;

    public ListarPersonas(@Nullable OrderedRealmCollection<Persona> data) {
        super(data);

    }

    private static class ViewHolder{
        TextView dniTextView, nombreTextView,generoTextView,edadTextView;
        ImageButton deleteBTN;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_personas,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.dniTextView = convertView.findViewById(R.id.textViewDNI);
            viewHolder.nombreTextView = convertView.findViewById(R.id.textViewNombre);
            viewHolder.generoTextView = convertView.findViewById(R.id.textViewGenero);
            viewHolder.edadTextView = convertView.findViewById(R.id.textViewEdad);
            viewHolder.deleteBTN = convertView.findViewById(R.id.deleteBTN);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Persona item = adapterData.get(position);
        viewHolder.dniTextView.setText(item.getDni());
        viewHolder.nombreTextView.setText(item.getNombre());
        viewHolder.generoTextView.setText(item.getGenero());
        viewHolder.edadTextView.setText(String.valueOf(item.getEdad()));

        viewHolder.deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(item);
            }
        });

        final View finalConvertView =convertView;

        return convertView;
    }
    private  void delete(Persona item){
        Realm realm = io.realm.Realm.getDefaultInstance();
        final RealmResults<Persona> results = realm.where(Persona.class).equalTo("dni", item.getDni()).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
        notifyDataSetChanged();
    }
}
