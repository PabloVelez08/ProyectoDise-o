package Snake;

import Copias.Snake22;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Food extends Thread {

    ArrayList<Point> food;

    static final Random r = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            generateFood();
        }
    }

    public void generateFood() {
        if (food.size() < 100) {
            food.add(new Point(r.nextInt(900), r.nextInt(900))); //Randómico de coordenadas
        }
    }
}
