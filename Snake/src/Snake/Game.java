package Snake;


import Snake.Board;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;


public class Game {
    Board board;
    Pointer point;
    Random r;
    public void startGame(){
        
        point = new Pointer();    
        r = new Random();
        board = new Board("slitherio");
        
        board.point = point; 
        point.board = board;
        
        point.snake = new ArrayList<>();
        point.foods = new ArrayList<>();
        point.snake.add(new Point(500, 500));

        point.start();
    }
    
    public void obtenerPuntaje(){
        System.out.println("Puntaje por comida" + point.count);
    }
    
    public void finishGame(){
        board.controlSize();
    }
    
}
