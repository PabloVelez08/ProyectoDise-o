package Juego;

///PRUEBAKEVIN**********
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class Frame extends JFrame{
    Punto punto;
    Image OSC;
    PointerInfo a = MouseInfo.getPointerInfo();

        public Frame(String s){
            super(s);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(0, 0, 1200, 1200);
            setLayout(new FlowLayout());
            setBackground(Color.blue);
            //addMouseListener(new MyMouseSensor());
            setVisible(true);
            
        }
        
    @Override
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
            Point last = punto.snake.get(0);
            g.setColor(Color.black); // color punto
            Graphics2D g2 = (Graphics2D) g;
            //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(9 + (float) punto.getSerpiente().size() /20)); // aumenta el grosor
            for(int i = 1; i < punto.snake.size(); i++){
                first = punto.snake.get(i);
                g2.drawLine(first.x, first.y, last.x, last.y);
                last = new Point(first);
            }
            g2.setColor(Color.red);//color de la comida
            for(int i = 0; i < punto.foods.size(); i++){
                g2.fillOval(punto.foods.get(i).x, punto.foods.get(i).y,  20, 20); //ancho y alto de la comida
            }
        }
    }

