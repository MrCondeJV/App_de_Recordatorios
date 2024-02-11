package com.anonproject.appfinal;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anonproject.appfinal.db.dbActividades;
import com.anonproject.appfinal.db.dbFrases;

public class NotificationActivity extends AppCompatActivity {

    TextView txtFrase,txtActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        txtFrase = findViewById(R.id.txtNotificacionFrase);
        txtActividad = findViewById(R.id.txtNotificacionActividad);

        dbActividades actividades = new dbActividades(NotificationActivity.this);
        dbFrases dbfrase = new dbFrases(NotificationActivity.this);
        txtFrase.setText(dbfrase.obtenerFraseAleatoria().toString());
        txtActividad.setText(actividades.obtenerActividadAleatoria());




    }
}