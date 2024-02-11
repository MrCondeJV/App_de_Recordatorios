package com.anonproject.appfinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.anonproject.appfinal.db.dbFrases;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowFrase extends AppCompatActivity {

    EditText txtFrase;
    Button btnModificar, btnCancelar;

    ListaFrases listaFrases;
    FloatingActionButton fabEditarFrase;
    FloatingActionButton fabEliminarFrase;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_frase);

        txtFrase = findViewById(R.id.txtModificarFrase);
        btnModificar = findViewById(R.id.btnModificarFrase);
        btnCancelar = findViewById(R.id.btnCancelarModificarFrase);
        fabEditarFrase = findViewById(R.id.fabEditarFrase);
        fabEliminarFrase = findViewById(R.id.btnFloatEliminarFrase);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        dbFrases dbFrases = new dbFrases(ShowFrase.this);
        listaFrases = dbFrases.verFrases(id);
        if(listaFrases != null){
            txtFrase.setText(listaFrases.getFrase());
            btnModificar.setVisibility(View.INVISIBLE);
            txtFrase.setInputType(InputType.TYPE_NULL);
        }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        fabEditarFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowFrase.this, EditFrase.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminarFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowFrase.this);
                builder.setMessage("Desea eliminar la Frase?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbFrases.eliminarFrase(id);

                                if(dbFrases.eliminarFrase(id)){
                                    Toast.makeText(ShowFrase.this,"Eliminado con Exito", Toast.LENGTH_LONG).show();
                                    lista();

                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }
    private void lista(){
        Intent intent = new Intent(ShowFrase.this, ListaFrases.class);
        startActivity(intent);
    }
}