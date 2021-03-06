
package Copias;

import Snake.Board;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Snake22 extends Thread{
     Board board;    
    ArrayList<Point> snake;
        //Pointer point;
       
        int size = 10;
        PointerInfo a = MouseInfo.getPointerInfo();
        int speed = 10;
        Color color=Color.BLUE;
    @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                generateSnake();  
                //generateEnemy();
                //checkFood();
                //checkBorder();
                //deleteSnakes();
                }
        }
        
    public void generateSnake() {
        a = MouseInfo.getPointerInfo();
                Point p = a.getLocation();
                Point last = snake.get(snake.size()-1);
                Point n = new Point();
                //showLocationHead(last); 
                move(last, p, n);
    }

        public void move(Point last, Point p, Point n){   
                if(last.distance(p) > 1){
                    n = calculateCoord(last, p);
                    snake.add(n);
                    //count++;
                    if(snake.size() >= size){
                        for(int i = 0; i < snake.size() - size; i++){
                            snake.remove(i);
                        }
                    }
                }
               // board.repaint();
                board.fo deleteFood(n);
        }
        /*
        public void deleteFood(Point n){
            Iterator<Point> i = foods.iterator();
                while(i.hasNext()){
                    Point food = i.next();
                    if(food.distance(n) < 20){
                        i.remove();
                        count++;
                        size++;
                    }
                }
                board.repaint();
               // System.out.println("Puntaje por Comida "+count);
        }*/
        
        public Point calculateCoord(Point last, Point mouse){
            double degree = 0;
            if(last.x < mouse.x && last.y < mouse.y){
                degree = 360 - Math.toDegrees(Math.atan((double) (mouse.y - last.y) / (mouse.x - last.x)));
            }else if(last.x > mouse.x && last.y > mouse.y){
                degree = 180 - Math.toDegrees(Math.atan((double) (last.y - mouse.y) / (last.x - mouse.x)));
            }else if(last.y > mouse.y && last.x < mouse.x){
                degree = Math.toDegrees(Math.atan((double) (last.y - mouse.y) / (mouse.x - last.x)));
            }else if(last.y < mouse.y && last.x > mouse.x){
                degree = 180 + Math.toDegrees(Math.atan((double) (mouse.y - last.y) / (last.x - mouse.x)));
            }
            Point p = new Point((int) 
                    (last.x + Math.cos(Math.toRadians(degree)) * speed), (int) 
                    (last.y - Math.sin(Math.toRadians(degree)) * speed));
            return p;
        }
        
        public Color checkSpeed(){
           board.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                    speed=30;
                    color=Color.RED;
            }
                if(evt.getKeyCode()!=KeyEvent.VK_SPACE){
                    speed=10;
                    color=Color.BLUE;
            }   
                }
                }); 
           return color;
        }

        public ArrayList<Point> getSnake() {
        return snake;
        }

        public void setSnake(ArrayList<Point> serpiente) {
            this.snake = serpiente;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
