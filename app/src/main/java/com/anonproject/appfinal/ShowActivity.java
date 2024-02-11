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

import com.anonproject.appfinal.db.dbActividades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShowActivity extends AppCompatActivity {

    EditText txtNombre, txtDescripcion;
    Button btnModificar, btnCancelar;

    ListaActividades actividades;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
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
        fabEliminar = findViewById(R.id.btnFloatEliminarActividad);

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

        dbActividades dbActividades = new dbActividades(ShowActivity.this);
       //VER ACTIVIDADES ES LA DE EDITAR
       actividades = dbActividades.verActividades(id);

       if(actividades != null){
           txtNombre.setText(actividades.getNombreActividad());
           txtDescripcion.setText(actividades.getDescripcionActividad());
           btnModificar.setVisibility(View.INVISIBLE);
           txtNombre.setInputType(InputType.TYPE_NULL);
           txtDescripcion.setInputType(InputType.TYPE_NULL);
       }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                }
        });

       fabEditar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ShowActivity.this, EditActivity.class);
               intent.putExtra("ID", id);
               startActivity(intent);
           }
       });

       fabEliminar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(ShowActivity.this);
               builder.setMessage("Desea eliminar la Actividad?")
                       .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                           dbActividades.eliminarActividad(id);

                           if(dbActividades.eliminarActividad(id)){
                               Toast.makeText(ShowActivity.this,"Eliminado con Exito", Toast.LENGTH_LONG).show();
                               lista();

                           }
                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                           }
                       }).show();
           }
       });
    }
    private void lista(){
        Intent intent = new Intent(ShowActivity.this, ListaActividades.class);
        startActivity(intent);
    }

}