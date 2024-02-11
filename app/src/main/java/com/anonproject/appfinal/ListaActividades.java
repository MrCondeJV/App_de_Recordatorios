package com.anonproject.appfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anonproject.appfinal.adaptadores.ListaActividadesAdapter;
import com.anonproject.appfinal.db.dbActividades;

import java.util.ArrayList;

public class ListaActividades extends AppCompatActivity {


    RecyclerView listaActividades;
    ArrayList<ListaActividades> listaArrayActividades;
    private int id;
    private String nombreActividad;
    private String descripcionActividad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_actividades);

        listaActividades = findViewById(R.id.listaActividades);
        listaActividades.setLayoutManager(new LinearLayoutManager(ListaActividades.this));
        dbActividades dbActividades = new dbActividades(ListaActividades.this);
        listaArrayActividades = new ArrayList<>();
        ListaActividadesAdapter adapterActividades =new ListaActividadesAdapter(dbActividades.mostrarActividades());
        listaActividades.setAdapter(adapterActividades);
    }
}