package com.example.a42225260.agarrameestajuegofinal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Inicio extends AppCompatActivity {
    BaseDeDatos accesoBasedeDatos;
    public SQLiteDatabase baseDatos;
    public Boolean baseDeDatosAbierta()
    {
        Boolean responder;
        accesoBasedeDatos= new BaseDeDatos (this, "BaseDeDatos", null,1);
        baseDatos= accesoBasedeDatos.getWritableDatabase();
        if(baseDatos != null)
        {
            responder=true;
        }
        else
        {
            responder=false;
        }
        return responder;
    }
    public void BotonJugar(View vista)
    {   String NombreUsuario;
        String Sexo;
        EditText Nombre;
        Nombre= (EditText)findViewById(R.id.Username);

        NombreUsuario= Nombre.getText().toString();
        Spinner SpinnerUsuarios;
        SpinnerUsuarios= (Spinner)findViewById(R.id.Spinner);
        RadioButton RadioFem;
        RadioFem= (RadioButton)findViewById(R.id.RadioFem);
        RadioButton RadioMasc;
        RadioMasc= (RadioButton)findViewById(R.id.RadioMasc);

        if (NombreUsuario.length()!=0){

            if(baseDeDatosAbierta()== true) {
                Cursor conjuntoRegistro;
                conjuntoRegistro = baseDatos.rawQuery("select Nombre from Usuarios where Nombre='" + NombreUsuario , null);
                if (conjuntoRegistro.moveToFirst() == true)
                {
                    Toast.makeText(this, "El nombre de usuario ya existe.", Toast.LENGTH_LONG).show();
                }else{
                    if(RadioFem.isSelected() || RadioMasc.isSelected()){
                        if (RadioFem.isSelected()){
                            Sexo= "Femenino";
                        }else{
                            Sexo= "Masculino";
                        }
                        if (baseDeDatosAbierta()== true){
                            ContentValues nuevoRegistro;
                            nuevoRegistro = new ContentValues();
                            nuevoRegistro.put("Nombre", NombreUsuario);
                            nuevoRegistro.put("Sexo", Sexo);
                            nuevoRegistro.put("Puntaje",0 );
                            baseDatos.insert("Usuarios", null, nuevoRegistro);
                        }

                    }else{
                        Toast.makeText(this, "Seleccione su sexo", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }else{
            Toast.makeText(this, "Ingrese su nombre", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        if(baseDeDatosAbierta()== true) {
            Cursor conjuntoRegistro;
            conjuntoRegistro = baseDatos.rawQuery("select Nombre from Usuarios", null);
            if (conjuntoRegistro.moveToFirst() == true){

            }
        }
    }
}
