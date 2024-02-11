package com.anonproject.appfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anonproject.appfinal.adaptadores.ListaFrasesAdapter;
import com.anonproject.appfinal.db.dbFrases;

import java.util.ArrayList;

public class ListaFrases extends AppCompatActivity {

    RecyclerView listaFrases;

    ArrayList<ListaFrases> listaArrayFrases;

    private int id;
    private String frase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_frases);

        listaFrases = findViewById(R.id.listaFrases);


        listaFrases.setLayoutManager(new LinearLayoutManager(ListaFrases.this));


        dbFrases dbFrases = new dbFrases(ListaFrases.this);


        listaArrayFrases = new ArrayList<>();


        ListaFrasesAdapter adapterFrases = new ListaFrasesAdapter(dbFrases.mostrarFrases());


        listaFrases.setAdapter(adapterFrases);


    }



}