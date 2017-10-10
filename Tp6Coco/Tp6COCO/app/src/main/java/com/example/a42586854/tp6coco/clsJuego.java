package com.example.a42586854.tp6coco;

import android.util.Log;

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
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

/**
 * Created by 42586854 on 1/8/2017.
 */

public class clsJuego {
    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDelDispositivo;
    Sprite NaveJugador;
    Sprite NaveEnemiga;

    public clsJuego(CCGLSurfaceView VistaDelJuego){
        Log.d("Bob", "Comienza el construtor de la clase");
        _VistaDelJuego=VistaDelJuego;


    }
    public void ComenzarJuego(){
        Log.d("Comenzar", "Comienza el juego");
        Director.sharedDirector().attachInView(_VistaDelJuego);

        PantallaDelDispositivo=Director.sharedDirector().displaySize();
        Log.d("Comenzar", "Pantalla del dispositivo - Ancho:"+PantallaDelDispositivo.width+" - Alto: "+PantallaDelDispositivo.height);

        Log.d("Comenzar", "Le digo al director que ejecute la escena");
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego(){
        Log.d("EscenaDelJuego", "Comienza el armado de la escena del juego");

        Log.d("EscenaDelJuego", "Declaro e instancio la escena en si");
        Scene EscenaADevolver;
        EscenaADevolver= Scene.node();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener la imagen de fondo");
        CapaDeFondo MiCapaFondo;
        MiCapaFondo=new CapaDeFondo();

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener el jugador y los enemigos ");
        CapaDelFrente MiCapaFrente;
        MiCapaFrente=new CapaDelFrente();

        Log.d("EscenaDelJuego", "Agrego a la escena la capa del fondo mas atras, y la del frente mas adelante");
        EscenaADevolver.addChild(MiCapaFondo, -10);
        EscenaADevolver.addChild(MiCapaFrente, 10);

        Log.d("EscenaDelJuego", "Devuelvo la escena ya armada");
        return EscenaADevolver;


    }

    class CapaDeFondo extends Layer{

        public CapaDeFondo(){
            Log.d("CapaDelFondo", "Comienzo el constructor de la capa del fondo");

            Log.d("CapaDelFondo", "Pongo la imagen del fondo del juego");
            PonerImagenFondo();
        }

        private void PonerImagenFondo(){
            Log.d("PonerImagenFondo", "Comienzo a poner la imagen del fondo");

            Log.d("PonerImagenFondo", "Intancio el sprite");
            Sprite ImagenFondo;
            ImagenFondo= Sprite.sprite("Fondo.png");


            Log.d("PonerImagenFondo", "La ubico en el cenro de la pantalla");
            ImagenFondo.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height/2);

            Float FactorAncho, FactorAlto;
            FactorAncho= PantallaDelDispositivo.width / ImagenFondo.getWidth();
            FactorAlto= PantallaDelDispositivo.height / ImagenFondo.getHeight();

            Log.d("PonerImagenFondo", "Agrando la imagen al doble de su tamaño actual");
            ImagenFondo.runAction(ScaleBy.action(0.1f, FactorAncho, FactorAlto));

            Log.d("PonerImagenFondo", "Lo agrego a la capa");
            super.addChild(ImagenFondo);
        }
    }
    class CapaDelFrente extends Layer{

        public CapaDelFrente(){
            Log.d("CapaDelFrente", "Comienza el constructor de la capa del frente");

            Log.d("CapaDelFrente", "Pongo el jugador en su posicion inicial");
            PonerNaveJugadorPosicionInicial();
            PonerUnEnemigo();
            PonerTituloJuego();
        }

        void PonerUnEnemigo()
        {
            Log.d("PonerEnemigo", "Instancio el sprite del enemigo");
            NaveEnemiga = Sprite.sprite("BombaEnemiga.png");

            Log.d("PonerEnemigo", "Determino la posicion inicial");
            Float PosicionInicialX, PosicionInicialY;
            Float AlturaEnemigo;
            AlturaEnemigo= NaveEnemiga.getHeight();
            PosicionInicialY= PantallaDelDispositivo.height + AlturaEnemigo/2;
            PosicionInicialX= PantallaDelDispositivo.width/2;

            Log.d("PonerEnemigo", "La ubico en las posiciones que determiné");
            NaveEnemiga.setPosition(PosicionInicialX, PosicionInicialY);

            Log.d("PonerEnemigo", "La roto para que caiga mirando hacia abajo");
            NaveEnemiga.runAction(RotateTo.action(0.01f,180));

            Log.d("PonerEnemigo", "Determino la posicion final");
            Float PosicionFinalX, PosicionFinalY;
            PosicionFinalX= PosicionInicialX;
            PosicionFinalY=- AlturaEnemigo/2;

            Log.d("PonerEnemigo", "Doy la orden para que se mueva hasta la posicion final");
            NaveEnemiga.runAction(MoveTo.action(4,PosicionFinalX, PosicionFinalY));
            Log.d("PonerEnemigo", "Agrego el sprite a la capa");
            super.addChild(NaveEnemiga);
        }

        private void PonerNaveJugadorPosicionInicial(){
            Log.d("PonerNaveJudaor", "Comienzo a poner la nave del jugador en su posicion inicial");

            Log.d("PonerNaveJugador", "Intancio el sprite");
            NaveJugador = Sprite.sprite("NaveJugador.png");

            float PosicionInicialX, PosicionInicialY;
            Log.d("PonerNaveJugador", "Obtengo la mital del ancho de la pantalla");
            PosicionInicialX=PantallaDelDispositivo.width/2;

            Log.d("PonerNaveJugador", "Obtengo lamitad de la altura de la nave");
            PosicionInicialY=NaveJugador.getHeight()/2;

            Log.d("PonerNaveJugador", "Lo ubico en X: "+PosicionInicialX+ " - Y: "+PosicionInicialY);
            NaveJugador.setPosition(PosicionInicialX,PosicionInicialY);

            Log.d("PonerNaveJugador", "Lo agrego a la capa");
            super.addChild(NaveJugador);
        }

        Label lblTituloJuego;
       private void PonerTituloJuego(){
           CCColor3B ColorTitulo = new CCColor3B(128,100,200);

           Log.d("PonerTitulo", "Comienzo a poner el titulo");
           lblTituloJuego= Label.label("Juegazo de Mica y Rochi, las mas capitas","Verdana", 30);

           lblTituloJuego.setColor(ColorTitulo);
           float AltoDelTitulo;
           AltoDelTitulo=lblTituloJuego.getHeight();

           lblTituloJuego.setPosition(PantallaDelDispositivo.width/2, PantallaDelDispositivo.height-AltoDelTitulo);
           super.addChild(lblTituloJuego);

       }


    }


}
