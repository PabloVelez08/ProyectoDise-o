import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;

public class Controller {

    MyFrame frame;
    ArrayList<Point> serpiente;
    ArrayList<Point> foods;
    Image OSC;
    PointerInfo a = MouseInfo.getPointerInfo();
    int size = 10;
    int speed = 10;
    Random r;
    
    public static void main(String[] args){
        Controller c = new Controller();
        c.startGame();
    }

    public void startGame(){
        frame = new MyFrame("slitherio");
        serpiente = new ArrayList<Point>();
        foods = new ArrayList<Point>();
        r = new Random();
        serpiente.add(new Point(500, 500));
        Runner r = new Runner();
        r.start();
    }

    class Runner extends Thread {
        public void run(){
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
    }
/*
    class MyMouseSensor extends MouseAdapter {
        boolean mouseDown = false;
        public void mousePressed(MouseEvent e){
            speed = 200;
            mouseDown = true;
            size /= 2;
            new Thread(){
                public void run() {
                    while (mouseDown) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        size--;
                    }
                }
            }.start();
        }
        public void mouseReleased(MouseEvent e){
            speed = 10;
            size *= 5;
            mouseDown = false;
        }
    }*/

    class MyFrame extends JFrame {
        public MyFrame(String s){
            super(s);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(0, 0, 1200, 1200);
            setLayout(new FlowLayout());
            setBackground(Color.blue);
            //addMouseListener(new MyMouseSensor());

            setVisible(true);
        }
        public void paint(Graphics g) {
            Dimension d = getSize();
            checkOffscreenImage();
            Graphics offG = OSC.getGraphics();
            offG.setColor(Color.BLUE);
            offG.fillRect(0, 0, d.width, d.height);
            paintOffscreen(OSC.getGraphics());
            g.drawImage(OSC, 0, 0, null);
        }

        private void checkOffscreenImage() {
            Dimension d = getSize();
            if (OSC == null || OSC.getWidth(null) != d.width
                    || OSC.getHeight(null) != d.height) {
                OSC = createImage(d.width, d.height);
            }
        }

        public void paintOffscreen(Graphics g) {
            g.clearRect(0, 0, 900, 900);
            Point first = new Point();
            Point last = serpiente.get(0);
            g.setColor(Color.black); // color serpiente
            Graphics2D g2 = (Graphics2D) g;
            //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(9 + (float) size /20)); // aumenta el grosor
            for(int i = 1; i < serpiente.size(); i++){
                first = serpiente.get(i);
                g2.drawLine(first.x, first.y, last.x, last.y);
                last = new Point(first);
            }
            g2.setColor(Color.GREEN);//color de la comida
            for(int i = 0; i < foods.size(); i++){
                g2.fillOval(foods.get(i).x, foods.get(i).y,  20, 20); //ancho y alto de la comida
            }
        }
    }

}