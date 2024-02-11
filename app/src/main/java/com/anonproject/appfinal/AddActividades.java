package com.anonproject.appfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anonproject.appfinal.db.dbActividades;

public class AddActividades extends AppCompatActivity {

    Button btnGuardarActividad,btnCancelarActividad;
    EditText txtNombreActividad, txtDescripcionActividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_actividades);

        btnGuardarActividad = findViewById(R.id.btnGuardarActividad);
        btnCancelarActividad = findViewById(R.id.btnCancelarActividad);

        txtNombreActividad = findViewById(R.id.txtNombreActividad);
        txtDescripcionActividad = findViewById(R.id.txtDescripcionActividad);

        btnGuardarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbActividades dbactividades = new dbActividades(AddActividades.this);
                long id = dbactividades.insertarActividad(txtNombreActividad.getText().toString(), txtDescripcionActividad.getText().toString());

                if(id>0){
                    Toast.makeText(AddActividades.this, "ACTIVIDAD GUARDADO", Toast.LENGTH_LONG).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(AddActividades.this, "ERROR AL GUARDAR ACTIVIDAD", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnCancelarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void limpiarCampos(){
        txtNombreActividad.setText("");
        txtDescripcionActividad.setText("");
    }
}