package slither;


import slither.Frame;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;


public class Game {
    Frame frame;
    Pointer point;
    Random r;
    public void startGame(){
        
        point = new Pointer();    
        r = new Random();
        frame = new Frame("slitherio");
        
        frame.point = point;    //Vinculación
        point.frame = frame;
        point.snake = new ArrayList<>();
        
        point.foods = new ArrayList<>();
        point.snake.add(new Point(500, 500));
        //Punto s = ;
        point.start();
    }
}
