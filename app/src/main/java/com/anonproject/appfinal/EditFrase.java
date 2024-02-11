package com.anonproject.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anonproject.appfinal.db.dbFrases;
import com.anonproject.appfinal.ui.frases.FrasesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditFrase extends AppCompatActivity {

    EditText txtFrase;
    Button btnModificar, btnCancelar;
    FloatingActionButton fabEditarFrase;
    FloatingActionButton fabEliminarFrase;
    boolean correcto = false;

    ListaFrases listaFrases;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_frase);

        txtFrase = findViewById(R.id.txtModificarFrase);
        btnModificar = findViewById(R.id.btnModificarFrase);
        btnCancelar = findViewById(R.id.btnCancelarModificarFrase);
        fabEditarFrase = findViewById(R.id.fabEditarFrase);
        fabEditarFrase.setVisibility(View.INVISIBLE);
        fabEliminarFrase = findViewById(R.id.btnFloatEliminarFrase);
        fabEliminarFrase.setVisibility(View.INVISIBLE);

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
        dbFrases dbFrases = new dbFrases(EditFrase.this);
        listaFrases = dbFrases.verFrases(id);
        if(listaFrases != null){
            txtFrase.setText(listaFrases.getFrase());

        }

        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             if(!txtFrase.getText().toString().equals("")){
               correcto=dbFrases.modificarrFrase(id, txtFrase.getText().toString());

               if(correcto){
                   Toast.makeText(EditFrase.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                   verRegistro();
               }else {
                   Toast.makeText(EditFrase.this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
               }
             }else {
                 Toast.makeText(EditFrase.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();

             }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void verRegistro(){
        Intent intent = new Intent(this, FrasesFragment.class);

        startActivity(intent);
    }
}