package com.anonproject.appfinal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anonproject.appfinal.ListaActividades;
import com.anonproject.appfinal.R;
import com.anonproject.appfinal.ShowActivity;

import java.util.ArrayList;

public class ListaActividadesAdapter extends RecyclerView.Adapter<ListaActividadesAdapter.ActividadesViewHolder> {

    ArrayList<ListaActividades> listaActividades;

    public ListaActividadesAdapter(ArrayList<ListaActividades> listaActividades) {
        this.listaActividades = listaActividades;
    }

    @NonNull
    @Override
    public ActividadesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_actividades, null, false);
        return new ActividadesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ActividadesViewHolder holder, int position) {
     holder.viewNombreActividad.setText(listaActividades.get(position).getNombreActividad());
     holder.viewDescripcionActividad.setText(listaActividades.get(position).getDescripcionActividad());
    }

    @Override
    public int getItemCount() {
     return listaActividades.size();

    }

    public class ActividadesViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombreActividad, viewDescripcionActividad;
        public ActividadesViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreActividad = itemView.findViewById(R.id.viewNombreActividad);
            viewDescripcionActividad = itemView.findViewById(R.id.viewDescripcionActividad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ShowActivity.class);
                    intent.putExtra("ID", listaActividades.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
