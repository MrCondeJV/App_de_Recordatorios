package com.anonproject.appfinal.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.anonproject.appfinal.ListaFrases;

import java.util.ArrayList;

public class dbFrases extends DbHelper{

    Context context;
    public dbFrases(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long insertarFrase(String frase){
        long id=0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("frase", frase);

            id = db.insert(TABLE_FRASES, null, values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<ListaFrases> mostrarFrases(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<ListaFrases> listaFrases = new ArrayList<>();
        ListaFrases frases = null;
        Cursor cursorFrases = null;

        cursorFrases = db.rawQuery("SELECT * FROM " + TABLE_FRASES, null);
        if(cursorFrases.moveToFirst()){
            do{
                frases = new ListaFrases();
                frases.setId(cursorFrases.getInt(0));
                frases.setFrase(cursorFrases.getString(1));
                listaFrases.add(frases);

            }while (cursorFrases.moveToNext());
        }
        cursorFrases.close();
        return listaFrases;
    }

    public ListaFrases verFrases(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ListaFrases frases = null;
        Cursor cursorFrases = null;

        cursorFrases = db.rawQuery("SELECT * FROM " + TABLE_FRASES + " WHERE id = "+ id +" LIMIT 1", null);
        if(cursorFrases.moveToFirst()){

                frases = new ListaFrases();
                frases.setId(cursorFrases.getInt(0));
                frases.setFrase(cursorFrases.getString(1));
        }
        cursorFrases.close();
        return frases;
    }
    @SuppressLint("Range")
    public String obtenerFraseAleatoria() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String fraseAleatoria = "";

        Cursor cursorFrases = null;

        cursorFrases = db.rawQuery("SELECT frase FROM " + TABLE_FRASES + " ORDER BY RANDOM() LIMIT 1", null);


        if (cursorFrases != null && cursorFrases.moveToFirst()) {
            fraseAleatoria = cursorFrases.getString(cursorFrases.getColumnIndex("frase"));
            cursorFrases.close();
        }

        return fraseAleatoria;
    }

    public boolean modificarrFrase(int id,String frase){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_FRASES + " SET frase ='" + frase +"' WHERE id= '"+ id + "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarFrase(int id){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_FRASES + " WHERE id = '" + id + "'" );
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
