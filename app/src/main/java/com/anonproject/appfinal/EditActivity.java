package com.anonproject.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anonproject.appfinal.db.dbActividades;
import com.anonproject.appfinal.ui.actividades.ActividadesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

    EditText txtNombre, txtDescripcion;
    Button btnModificar, btnCancelar;
    boolean correcto = false;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;

    ListaActividades actividades;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        txtNombre = findViewById(R.id.txtModificarNombreActividad);
        txtDescripcion = findViewById(R.id.txtModificarDescripcionActividad);
        btnModificar = findViewById(R.id.btnModificarActividad);
        btnCancelar = findViewById(R.id.btnCancelarModificarActividad);
        fabEditar = findViewById(R.id.btnFlotanteEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.btnFloatEliminarActividad);
        fabEliminar.setVisibility(View.INVISIBLE);

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

        dbActividades dbActividades = new dbActividades(EditActivity.this);
        //VER ACTIVIDADES ES LA DE EDITAR
        actividades = dbActividades.verActividades(id);

        if(actividades != null){
            txtNombre.setText(actividades.getNombreActividad());
            txtDescripcion.setText(actividades.getDescripcionActividad());

        }

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(!txtNombre.getText().toString().equals("") && !txtDescripcion.getText().toString().equals("")){
              correcto = dbActividades.modificarActividad(id, txtNombre.getText().toString(), txtDescripcion.getText().toString() );

              if(correcto){
                  Toast.makeText(EditActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                  verRegistro();
              }else {
                  Toast.makeText(EditActivity.this, "ERROR AL MODIFICAR", Toast.LENGTH_LONG).show();
              }
             }else {
                 Toast.makeText(EditActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(EditActivity.this, ActividadesFragment.class);

        startActivity(intent);
    }
}