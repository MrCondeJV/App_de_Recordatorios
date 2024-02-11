package com.anonproject.appfinal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NOMBRE ="datos.db";
    public static final String TABLE_ACTIVIDADES ="t_actividades";
    public static final String TABLE_FRASES ="t_frases";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ACTIVIDADES +"(" +
             " id INTEGER PRIMARY KEY AUTOINCREMENT," +
             "nombre_actividad TEXT NOT NULL," +
             "descripcion_actividad TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_FRASES +"(" +
                " id INTEGER PRIMARY KEY," +
                "frase TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ACTIVIDADES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_FRASES);
        onCreate(sqLiteDatabase);
    }
}
