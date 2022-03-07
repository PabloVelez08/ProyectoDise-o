package Snake;

import Snake.Board;
import static Snake.Snake.r;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    Board board;
    Snake snake; 
    Food food;
    Enemy enemy;
    Entity entity;
    public void startGame(){
        snake = new Snake();    
        board = new Board("Slither.io");
        food = new Food();
        enemy = new Enemy();

        board.snake = snake; 
        snake.board = board;
        
        board.food = food; 
        
        board.enemy = enemy;
        enemy.board = board;
        
        snake.snake = new ArrayList<>();
        enemy.enemy = new ArrayList<>();
        food.food = new ArrayList<>();
        
        snake.snake.add(new Point(500, 500));
        enemy.enemy.add(new Point(r.nextInt(900), r.nextInt(900)));

        snake.start();
        food.start();
        enemy.start();
        //finishGame();
        while(true){
             getScore();
        }
       
    }
    
    public void getScore(){
        System.out.println("Puntaje por comida" + snake.score());
    }
    
    /*
    public void finishGame()
        board.controlSize();
    }*/
    
}
