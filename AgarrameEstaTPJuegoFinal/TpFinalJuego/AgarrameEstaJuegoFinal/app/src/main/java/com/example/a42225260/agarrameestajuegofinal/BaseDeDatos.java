package com.example.a42225260.agarrameestajuegofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by 42225260 on 19/9/2017.
 */

public class BaseDeDatos extends SQLiteOpenHelper {
    public BaseDeDatos(Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version)
    {
        super(contexto, nombre, fabrica, version);
    }
    @Override public void onCreate(SQLiteDatabase BasedeDatos)
    {
        String sqlCrearTabla;
        sqlCrearTabla= "create table Usuarios (Nombre text, Sexo text, Puntaje int)";
        BasedeDatos.execSQL(sqlCrearTabla);
    }
    @Override public void onUpgrade (SQLiteDatabase BasedeDatos, int versionAnterior, int versionNueva)
    {

    }
}
