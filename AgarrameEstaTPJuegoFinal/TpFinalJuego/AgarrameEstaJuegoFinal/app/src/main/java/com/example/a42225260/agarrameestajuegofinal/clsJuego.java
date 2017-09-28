package com.example.a42225260.agarrameestajuegofinal;
import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCSize;


import java.util.Random;

/**
 * Created by devandroid on 26/9/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    String NombreJugador;
    String SexoJugador;
    Integer PuntajeJugador;
    Sprite mona;

    public clsJuego(CCGLSurfaceView VistaDelJuego, String Nombre, String Sexo, Integer Puntaje) {
        Log.d("BananMono", "Comienza el constructor de la clase");
        _VistaDelJuego = VistaDelJuego;
        NombreJugador= Nombre;
        SexoJugador= Sexo;
        PuntajeJugador=Puntaje;
    }

    public void ComenzarJuego() {
        Log.d("Comenzar", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo = Director.sharedDirector().displaySize();
        Log.d("Comenzar", "Pantalla del dispositivo - Ancho:" + PantallaDelDispositivo.width + " - Alto: " + PantallaDelDispositivo.height);

        Log.d("Comenzar", "Le digo al director que ejecute la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego() {
        Log.d("EscenaDelJuego", "Comienza el armado de la escena del juego");

        Log.d("EscenaDelJuego", "Declaro e instancio la escena en si");
        Scene EscenaADevolver;
        EscenaADevolver = Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen de fondo");
        CapaDeFondo MiCapaFondo;
        MiCapaFondo = new CapaDeFondo();

        EscenaADevolver.addChild(MiCapaFondo, -10);
        Log.d("EscenaDelJuego", "Devuelvo la escena ya armada");
        return EscenaADevolver;
    }


    class CapaDeFondo extends Layer {

        public CapaDeFondo() {
            Log.d("CapaDelFondo", "Comienzo el constructor de la capa del fondo");

            Log.d("CapaDelFondo", "Pongo la imagen del fondo del juego");
            PonerImagenFondo();
        }

        private void PonerImagenFondo() {
            Log.d("PonerImagenFondo", "Comienzo a poner la imagen del fondo");

            Log.d("PonerImagenFondo", "Intancio el sprite");
            Sprite ImagenFondo;
            ImagenFondo = Sprite.sprite("fondo.png");

            Log.d("PonerImagenFondo", "La ubico en el cenro de la pantalla");
            ImagenFondo.setPosition(PantallaDelDispositivo.width / 2, PantallaDelDispositivo.height / 2);

            Float FactorAncho, FactorAlto;
            FactorAncho = PantallaDelDispositivo.width / ImagenFondo.getWidth();
            FactorAlto = PantallaDelDispositivo.height / ImagenFondo.getHeight();

            Log.d("PonerImagenFondo", "Agrando la imagen al doble de su tamaño actual");
            ImagenFondo.runAction(ScaleBy.action(0.1f, FactorAncho, FactorAlto));

            Log.d("PonerImagenFondo", "Lo agrego a la capa");
            super.addChild(ImagenFondo);
        }
    }
    class CapaDelFrente{
        public CapaDelFrente() {
            Log.d("CapaDelFondo", "Comienzo el constructor de la capa del fondo");

            Log.d("CapaDelFondo", "Pongo la imagen del fondo del juego");
            PonerJugador();
        }
        private void PonerJugador(){
            if (SexoJugador== "Femenino"){
                Log.d("PonerMona","Instancio el sprite");
                mona = Sprite.sprite("mono.png");

                Log.d("PonerMona", "Determino la posicion inicial");
                Float PosicionInicialX;
                Float PosicionInicialY;
                Float AlturaMona;
                Float AnchoMona;
                AlturaMona= mona.getHeight();
                AnchoMona= mona.getWidth();
                PosicionInicialY= PantallaDelDispositivo.height + AlturaMona/2;
                PosicionInicialX= PantallaDelDispositivo.width + AnchoMona/2;

                Log.d("PonerMona", "Determino la posicion");
                PosicionInicialX= 350f;
                PosicionInicialY=5f;
                PosicionInicialX= PosicionInicialX - (mona.getWidth()/2);
                PosicionInicialY= PosicionInicialY-(mona.getHeight()/2);

                Log.d("PonerMona", "La ubico en las posiciones que determiné");
                mona.setPosition(PosicionInicialX, PosicionInicialY);

               /* ERROR ACAAAAAAAA Log.d("PonerMona", "Agrego el sprite a la capa");
                super.addChild(mona);*/

            }else{

            }
        }
    }
}
