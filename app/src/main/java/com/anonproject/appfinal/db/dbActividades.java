package com.anonproject.appfinal.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anonproject.appfinal.ListaActividades;

import java.util.ArrayList;

public class dbActividades extends DbHelper{

    Context context;
    public dbActividades(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarActividad(String nombreActividad, String descripcionActividad){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_actividad", nombreActividad);
            values.put("descripcion_actividad", descripcionActividad);

           id = db.insert(TABLE_ACTIVIDADES, null, values);
        }catch (Exception ex){
            ex.toString();
        }
      return id;
    }

    @SuppressLint("Range")
    public String obtenerActividadAleatoria() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String actividadAleatoria = "";

        Cursor cursorFrases = null;

        cursorFrases = db.rawQuery("SELECT descripcion_actividad FROM " + TABLE_ACTIVIDADES + " ORDER BY RANDOM() LIMIT 1", null);


        if (cursorFrases != null && cursorFrases.moveToFirst()) {
            actividadAleatoria = cursorFrases.getString(cursorFrases.getColumnIndex("descripcion_actividad"));
            cursorFrases.close();
        }

        return actividadAleatoria;
    }
    public ArrayList<ListaActividades> mostrarActividades(){

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ArrayList<ListaActividades> listaActividades = new ArrayList<>();
            ListaActividades actividades = null;
            Cursor cursorActividades = null;

            cursorActividades = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDADES, null);
            if(cursorActividades.moveToFirst()){
                do{
                    actividades = new ListaActividades();
                    actividades.setId(cursorActividades.getInt(0));
                    actividades.setNombreActividad(cursorActividades.getString(1));
                    actividades.setDescripcionActividad(cursorActividades.getString(2));
                    listaActividades.add(actividades);

                }while (cursorActividades.moveToNext());
            }
            cursorActividades.close();
            return listaActividades;
    }

    public ListaActividades verActividades(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ListaActividades actividades = null;
        Cursor cursorActividades = null;

        cursorActividades = db.rawQuery("SELECT * FROM " + TABLE_ACTIVIDADES +" WHERE id = "+ id +" LIMIT 1", null);
        if(cursorActividades.moveToFirst()){

                actividades = new ListaActividades();
                actividades.setId(cursorActividades.getInt(0));
                actividades.setNombreActividad(cursorActividades.getString(1));
                actividades.setDescripcionActividad(cursorActividades.getString(2));
        }
        cursorActividades.close();
        return actividades;
    }

    public boolean modificarActividad(int id,String nombreActividad, String descripcionActividad){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_ACTIVIDADES + " SET nombre_actividad = '" + nombreActividad + "',descripcion_actividad = '" +descripcionActividad+"' WHERE id= '"+ id + "'" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }


    public boolean eliminarActividad(int id){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ACTIVIDADES + " WHERE id = '" + id + "'" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
