package Snake;


import Snake.Board;
import static Snake.Pointer.r;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    Board board;
    Pointer point; 
    Crash crash;
    
    public void startGame(){
        point = new Pointer();    
        board = new Board("Slither.io");
        crash = new Crash();

        board.point = point; 
        point.board = board;
        
        point.snake = new ArrayList<>();
        point.enemy = new ArrayList<>();
        point.foods = new ArrayList<>();
        
        point.snake.add(new Point(500, 500));
        point.enemy.add(new Point(r.nextInt(900), r.nextInt(900)));

        point.start();
        finishGame();
    }
    
    public void getScore(){
        System.out.println("Puntaje por comida" + point.count);
    }
    
    public void finishGame(){
        board.controlSize();
    }
    
}
