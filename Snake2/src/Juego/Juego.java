package Juego;


import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;


public class Juego {
    Frame frame;
    Punto punto;
    Random r;
    public void startGame(){
        
        punto = new Punto();    
        r = new Random();
        frame = new Frame("slitherio");
        
        frame.punto = punto;    //Vinculación
        punto.frame = frame;
        punto.serpiente = new ArrayList<>();
        
        punto.foods = new ArrayList<>();
        punto.serpiente.add(new Point(500, 500));
        //Punto s = ;
        punto.start();
    }
}
