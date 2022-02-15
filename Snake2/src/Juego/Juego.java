package Juego;


import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;


public class Juego {
    Frame frame;
    Punto point;
    Random r;
    public void startGame(){
        
        point = new Punto();    
        r = new Random();
        frame = new Frame("slitherio");
        
        frame.punto = point;    //Vinculación
        point.frame = frame;
        point.snake = new ArrayList<>();
        
        point.foods = new ArrayList<>();
        point.snake.add(new Point(500, 500));
        //Punto s = ;
        point.start();
    }
}
