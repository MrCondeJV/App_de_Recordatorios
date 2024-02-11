package com.anonproject.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anonproject.appfinal.db.dbFrases;

public class AddFrases extends AppCompatActivity {

    //Declaro el boton o la imagen
    Button btnGuardarFrase,btnCancelarFrase;
    EditText txtfrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_frases);

        //hago la referencia del boton con el layout

        btnCancelarFrase = findViewById(R.id.btnCancelarFrase);
        btnGuardarFrase = findViewById(R.id.btnGuardarFrase);
        txtfrase = findViewById(R.id.txtFrase);

        // creo el evento setOnclickListener

        btnGuardarFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbFrases dbfrase = new dbFrases(AddFrases.this);
                long id = dbfrase.insertarFrase(txtfrase.getText().toString());

                if(id>0){
                    Toast.makeText(AddFrases.this,"FRASE GUARDADA", Toast.LENGTH_LONG).show();
                    limpiarCampos();
                    lista();
                }else{
                    Toast.makeText(AddFrases.this,"ERROR AL GUARDAR FRASE", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancelarFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void limpiarCampos(){
        txtfrase.setText("");
    }
    private void lista(){
        Intent intent = new Intent(AddFrases.this, MainActivity.class);
        startActivity(intent);
    }


}