package com.anonproject.appfinal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anonproject.appfinal.ListaFrases;
import com.anonproject.appfinal.R;
import com.anonproject.appfinal.ShowFrase;

import java.util.ArrayList;

public class ListaFrasesAdapter extends RecyclerView.Adapter<ListaFrasesAdapter.FrasesViewHolder> {

    ArrayList<ListaFrases> listaFrases;

    public ListaFrasesAdapter(ArrayList<ListaFrases> listaFrases) {
        this.listaFrases = listaFrases;
    }

    @NonNull
    @Override
    public FrasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_frases, null, false);
       return new FrasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrasesViewHolder holder, int position) {
     holder.viewFrase.setText(listaFrases.get(position).getFrase());
    }

    @Override
    public int getItemCount() {
        return listaFrases.size();
    }

    public class FrasesViewHolder extends RecyclerView.ViewHolder {

        TextView viewFrase;
        public FrasesViewHolder(@NonNull View itemView) {
            super(itemView);

            viewFrase = itemView.findViewById(R.id.viewFrase);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ShowFrase.class);
                    intent.putExtra("ID", listaFrases.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
