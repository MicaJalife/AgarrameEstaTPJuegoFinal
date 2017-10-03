package com.example.a42225260.agarrameestajuegofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class ActivityJuego extends AppCompatActivity {
    CCGLSurfaceView VistaPrincipal;
    String Jugador;
    String Sexo;
    Integer Puntaje;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_juego);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        VistaPrincipal = new CCGLSurfaceView(this);
        setContentView(VistaPrincipal);
        Bundle Mandotodo;
        Mandotodo= this.getIntent().getExtras();
        Jugador = Mandotodo.getString("Nombre");
        Sexo= Mandotodo.getString("Sexo");
        Puntaje= Mandotodo.getInt("Puntaje");
    }
    @Override
    protected  void onStart(){
        super.onStart();
        clsJuego AgarrameEstaJuego;
        AgarrameEstaJuego= new clsJuego(VistaPrincipal, Jugador, Sexo, Puntaje);
        AgarrameEstaJuego.ComenzarJuego();
    }
}
