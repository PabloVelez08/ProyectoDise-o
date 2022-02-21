package Snake;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;

public class Pointer extends Thread{
    Board board;
    Crash crash;
    ArrayList<Point> snake;
    ArrayList<Point> foods;
    ArrayList<Point> enemy;
    PointerInfo a = MouseInfo.getPointerInfo();
    int size = 10;
    int speed = 10;
    Color color=Color.BLUE;
    static final Random r = new Random();
    int count=0;

    @Override
        public void run(){
            crash = new Crash();
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                generateSnake();  
                generateEnemy();
                checkFood();
                checkBorder();
                deleteSnakes();
                }
        }
        
        public void generateSnake(){
            a = MouseInfo.getPointerInfo();
                Point p = a.getLocation();
                Point last = snake.get(snake.size() - 1);
                Point n = new Point();
                showLocationHead(last); 
                move(snake, last, p, n); 
        }
        
        public void generateEnemy(){
                Point pEn = new Point(r.nextInt(900)+50, r.nextInt(900)+50);
                Point lastEn = enemy.get(enemy.size() - 1);
                Point nEn = new Point(); 
                move(enemy,lastEn, pEn, nEn);  
        }
        
        public void deleteSnakes(){
          if(crash.checkCrashSnake(snake, enemy)==true){
                            for(int i=0; i<enemy.size();i++){
                                Point dead = new Point();
                                dead = enemy.get(i);
                                foods.add(dead);
                            }
                            enemy.clear();
                            enemy.add(new Point(r.nextInt(900), r.nextInt(900)));
                }  
          if(crash.checkMyCrash(snake, enemy)==true){
              for(int i=0; i<snake.size();i++){
                                Point dead = new Point();
                                dead = snake.get(i);
                                foods.add(dead);
                            }
                            snake.clear();
                            snake.add(new Point(r.nextInt(900), r.nextInt(900)));
          }
        }
        
        public void checkBorder(){
            if(snake.get(snake.size() - 1).x<15 || snake.get(snake.size() - 1).y<44
                    ||snake.get(snake.size() - 1).x>996||snake.get(snake.size() - 1).y>985){
                System.exit(0);
            }
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
                
        public void move(ArrayList b,Point last, Point p, Point n){   
                if(last.distance(p) > 1){
                    n = calculateCoord(last, p);
                    b.add(n);
                    //count++;
                    if(b.size() >= size){
                        for(int i = 0; i < b.size() - size; i++){
                            b.remove(i);
                        }
                    }
                }
                deleteFood(n);
        }
        
        public void showLocationHead(Point last){
            System.out.println(last.x +" | "+last.y);
        }
        
        public void checkFood(){
            if(foods.size() < 100){
                    foods.add(new Point(r.nextInt(900), r.nextInt(900))); //Randómico de coordenadas
                }
        }
        
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
        }
        
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

    public ArrayList<Point> getSnake() {
        return snake;
    }

    public void setSnake(ArrayList<Point> serpiente) {
        this.snake = serpiente;
    }
    public ArrayList<Point> getEnemy() {
        return enemy;
    }

    public void setEnemy(ArrayList<Point> serpiente) {
        this.enemy = enemy;
    }



    }

