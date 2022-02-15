package Juego;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Punto extends Thread{
    Frame frame;
    ArrayList<Point> serpiente;
    ArrayList<Point> foods;
    PointerInfo a = MouseInfo.getPointerInfo();
    int size = 10;
    int speed = 10;
    static final Random r = new Random();
        public void run(){
            System.out.println(foods);//***************
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(foods.size() < 500){
                    foods.add(new Point(r.nextInt(900), r.nextInt(900))); //Randómico de coordenadas
                }
                a = MouseInfo.getPointerInfo();
                Point p = a.getLocation();
                Point last = serpiente.get(serpiente.size() - 1);
                Point n = new Point();
                if(last.distance(p) > 1){
                    n = calcCoor(last, p);
                    serpiente.add(n);
                    if(serpiente.size() >= size){
                        for(int i = 0; i < serpiente.size() - size; i++){
                            serpiente.remove(i);
                        }
                    }
                    System.out.println(n+"prueba");
                }
                Iterator<Point> i = foods.iterator();
                while(i.hasNext()){
                    Point food = i.next();
                    if(food.distance(n) < 20){
                        i.remove();
                        size++;
                    }
                }
                frame.repaint();
            }

        }
        public Point calcCoor(Point last, Point mouse){
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

    public ArrayList<Point> getSerpiente() {
        return serpiente;
    }

    public void setSerpiente(ArrayList<Point> serpiente) {
        this.serpiente = serpiente;
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
        
    }
