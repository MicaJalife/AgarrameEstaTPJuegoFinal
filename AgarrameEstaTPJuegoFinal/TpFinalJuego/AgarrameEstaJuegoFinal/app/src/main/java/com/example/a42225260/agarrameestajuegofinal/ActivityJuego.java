package com.example.a42225260.agarrameestajuegofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class ActivityJuego extends AppCompatActivity {
    CCGLSurfaceView VistaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_juego);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        VistaPrincipal = new CCGLSurfaceView(this);
        setContentView(VistaPrincipal);
    }
    @Override
    protected  void onStart(){
        super.onStart();
        clsJuego AgarrameEstaJuego;
        AgarrameEstaJuego= new clsJuego(VistaPrincipal);
        AgarrameEstaJuego.ComenzarJuego();
    }
}
