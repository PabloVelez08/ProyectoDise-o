package Snake;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Board extends JFrame{
    Pointer point;
    Image OSC;
    Image head;
    JLabel label;
    JPanel panel;
    JFrame v;
        public Board(String s){
            setLocationRelativeTo(this);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(10, 10, 1000, 1000);
            setLayout(new FlowLayout());
            setBackground(Color.ORANGE);//Color Fondo
            setVisible(true);

        }

        
        public void paint(Graphics g) {
            Dimension d = getSize();
            checkOffscreenImage();
            Graphics offG = OSC.getGraphics();
            offG.setColor(Color.ORANGE); //Color Fondo
            offG.fillRect(0, 0, d.width, d.height);
            paintOffscreen(OSC.getGraphics(), OSC.getGraphics(), Color.GREEN);
            g.drawImage(OSC, 0, 0, null);
        }
        
        private void checkOffscreenImage(){
            Dimension d = getSize();
            if (OSC == null || OSC.getWidth(null) != d.width
                    || OSC.getHeight(null) != d.height) {
                OSC = createImage(d.width, d.height);
            }
        }

        
        public void paintOffscreen(Graphics gSn, Graphics gEn, Color colorFood) {
            gSn.clearRect(0, 0, 1000, 1000);
            //snake
            Point first = new Point();  
            Point last = point.snake.get(0);
            gSn.setColor(point.checkSpeed()); // color punto
            
            //enemy
            Point firstEn = new Point();
            Point lastEn = point.enemy.get(0);
            gEn.setColor(Color.black);

            Graphics2D g2 = (Graphics2D) gSn;
            Graphics2D g3 = (Graphics2D) gEn;
            
            g2.setStroke(new BasicStroke(9 + (float) point.getSnake().size() /60)); // aumenta el grosor
            g3.setStroke(new BasicStroke(9 + (float) point.getEnemy().size() /20)); // aumenta el grosor
            //snake
            for(int i = 1; i < point.snake.size(); i++){
                first = point.snake.get(i);
                g2.drawLine(first.x, first.y, last.x, last.y);
                last = new Point(first);
            } 
            //enemy
            for(int i = 1; i < point.enemy.size(); i++){
                firstEn = point.enemy.get(i);
                g3.drawLine(firstEn.x, firstEn.y, lastEn.x, lastEn.y);
                lastEn = new Point(firstEn);
            }
            
            //comida
            g2.setColor(colorFood);//color de la comida
            for(int i = 0; i < point.foods.size(); i++){
                g2.fillOval(point.foods.get(i).x, point.foods.get(i).y,  20, 20); //ancho y alto de la comida
            }
            
            
        }
        
        public void controlSize(){
        if(this.point.snake.size()>10){
            this.setVisible(false);
        }
    }
        
    }

