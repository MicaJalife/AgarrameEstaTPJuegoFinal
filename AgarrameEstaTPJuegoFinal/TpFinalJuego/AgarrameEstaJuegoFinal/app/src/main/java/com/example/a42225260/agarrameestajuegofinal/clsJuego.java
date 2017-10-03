package com.example.a42225260.agarrameestajuegofinal;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Switch;

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


import java.util.ArrayList;
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
    Sprite monx;
    Sprite banana;
    Sprite escorpion;
    Random AzarTiempoEsc;
    Integer AzarEscorpion;
    int TiempoSumando=0;

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

        Log.d("EscenaDelJuego", "Declaro e instancio la capa que va a contener el jugador y los enemigos ");
        CapaDelFrente MiCapaFrente;
        MiCapaFrente=new CapaDelFrente();

        EscenaADevolver.addChild(MiCapaFondo, -10);
        EscenaADevolver.addChild(MiCapaFrente, 10);

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
    class CapaDelFrente extends Layer{
        public CapaDelFrente() {
            Log.d("CapaDelFondo", "Comienzo el constructor de la capa del fondo");

            Log.d("CapaDelFondo", "Pongo la imagen del fondo del juego");
            PonerMono();

            super.schedule("PonerBananas", 2.0f);


            AzarTiempoEsc= new Random();
            AzarEscorpion= 10+AzarTiempoEsc.nextInt(20);

            super.schedule("PreguntarTiempoEscorpion",1.0f);
        }
        private void PonerMono(){

            int resultado= SexoJugador.compareTo("Femenino");

            if (resultado==0){
                Log.d("PonerMonx","Instancio el sprite");
                monx = Sprite.sprite("mona.png");
            }else{
                monx = Sprite.sprite("mono.png");
            }
                Log.d("PonerMonx", "Determino la posicion inicial");
                float PosicionInicialX, PosicionInicialY;

                Log.d("PonerMonx", "Determino la posicion");
                PosicionInicialX=PantallaDelDispositivo.width/2;
                PosicionInicialY=monx.getHeight()/2;


                Log.d("PonerMonx", "La ubico en las posiciones que determiné");
                monx.setPosition(PosicionInicialX, PosicionInicialY);

               Log.d("PonerMonx", "Agrego el sprite a la capa");
                super.addChild(monx);

        }

        public void PonerBananas(float DiferenciaTiempo)
        {
            Random AzarBananas;
            AzarBananas= new Random();
            Integer Numero;
            Numero= AzarBananas.nextInt(10);

            //Log.d("Colorbanana", "NumeroBanana "+ Integer.parseInt(AzarBananas.toString()));

          if (Numero == 0 || Numero==6||Numero==8)
          {
              switch(Numero){
                  case 0:
                      banana = Sprite.sprite("BananaTiempo.png");
                      break;
                  case 6:  banana = Sprite.sprite("BananaPuntos.png");
                      break;
                  case 8:  banana = Sprite.sprite("BananaPuntosMas.png");
                      break;
              }
          }
          else
            {
                Log.d("PonerBanana", "Instancio el sprite de la banana");
                banana = Sprite.sprite("BananaComun.png");
            }

            Log.d("PonerBanana", "Determino la posicion inicial");
            Float PosicionInicialX;
            Float PosicionInicialY;
            Float AlturaBanana;
            Float AnchoBanana;
            AlturaBanana= banana.getHeight();
            AnchoBanana= banana.getWidth();
            PosicionInicialY= PantallaDelDispositivo.height + AlturaBanana/2;
            PosicionInicialX= PantallaDelDispositivo.width + AnchoBanana/2;

            Log.d("PonerBanana", "Determino la posicion X al azar");
            Random GeneradorAzar;
            GeneradorAzar=new Random();
            int PosicionInicialInt;
            //PosicionInicialInt=(int) PosicionInicialX;
            PosicionInicialInt= GeneradorAzar.nextInt((int) (PantallaDelDispositivo.width - AnchoBanana));
            PosicionInicialInt += AnchoBanana/2;

            Log.d("PonerBanana", "La ubico en las posiciones que determiné");
            banana.setPosition(PosicionInicialInt, PosicionInicialY);

            Log.d("PonerBanana", "Determino la posicion final");
            Float PosicionFinalY;
            int PosicionFinalX;
            PosicionFinalX= PosicionInicialInt;
            PosicionFinalY= - AlturaBanana/2;

            Log.d("PonerEnemigo", "Doy la orden para que se mueva hasta la posicion final");
            banana.runAction(MoveTo.action(4,PosicionFinalX, PosicionFinalY));
            Log.d("PonerEnemigo", "Agrego el sprite a la capa");
            super.addChild(banana);
        }
        public void PonerEscorpion(){

            Log.d("PonerEscorpion","Instancio el sprite");
            escorpion = Sprite.sprite("escorpion.png");

            Log.d("PonerEscorpion", "Determino la posicion inicial");
            float PosicionInicialX, PosicionInicialY;

            Log.d("PonerEscorpion", "Determino la posicion");
            PosicionInicialY=escorpion.getHeight()/2;
            PosicionInicialX=escorpion.getWidth()- escorpion.getWidth()/2;


            Log.d("PonerEscorpion", "La ubico en las posiciones que determiné");
            escorpion.setPosition(PosicionInicialX, PosicionInicialY);

            Log.d("PonerEscorpion", "Determino la posicion final");
            Float PosicionFinalY;
            Float PosicionFinalX;
            PosicionFinalX= PantallaDelDispositivo.getWidth()+escorpion.getWidth()/2;
            PosicionFinalY= escorpion.getHeight()/2;

            Log.d("PonerEscorpion", "Doy la orden para que se mueva hasta la posicion final");
            escorpion.runAction(MoveTo.action(3,PosicionFinalX, PosicionFinalY));

            Log.d("PonerEscorpion", "Agrego el sprite a la capa");
            super.addChild(escorpion);
        }
        public void PreguntarTiempoEscorpion (float DiferenciaTiempo){

            if (TiempoSumando== Integer.parseInt(AzarEscorpion.toString())){
                AzarEscorpion= 10+AzarTiempoEsc.nextInt(20);
                TiempoSumando=0;
                PonerEscorpion();

            }
            else {
                TiempoSumando= TiempoSumando + 1;
            }
        }
    }

}
