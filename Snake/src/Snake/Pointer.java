package Snake;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Pointer extends Thread{
    Board board;
    ArrayList<Point> snake;
    ArrayList<Point> foods;
    PointerInfo a = MouseInfo.getPointerInfo();
    int size = 10;
    int speed = 10;
    Color color=Color.BLUE;
    static final Random r = new Random();
    int count=0;

    @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = MouseInfo.getPointerInfo();
                Point p = a.getLocation();
                Point last = snake.get(snake.size() - 1);
                Point n = new Point();
                showLocationHead(last); 
                move(last, p, n);  
                checkVelocity();
                checkFood();
            }
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
                delete(n);
        }
        
        public Color checkVelocity(){
           board.addKeyListener(new java.awt.event.KeyAdapter() {
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
        
        public void showLocationHead(Point last){
            System.out.println(last.x +" | "+last.y);
        }
        
        public void checkFood(){
            if(foods.size() < 100){
                    foods.add(new Point(r.nextInt(900), r.nextInt(900))); //Randómico de coordenadas
                }
        }
        public void delete(Point n){
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

    public ArrayList<Point> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Point> foods) {
        this.foods = foods;
    }

    public PointerInfo getA() {
        return a;
    }

    public void setA(PointerInfo a) {
        this.a = a;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    }

