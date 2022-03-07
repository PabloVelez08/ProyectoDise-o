package Snake;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Enemy extends Entity {

    ArrayList<Point> enemy;
    static final Random r = new Random();
    int count = 0;

    @Override
    public void run() {
        crash = new Crash();
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void generate() {
        Point pEn = new Point(r.nextInt(900) + 50, r.nextInt(900) + 50);
        Point lastEn = enemy.get(enemy.size() - 1);
        Point nEn = new Point();
        move(enemy, lastEn, pEn, nEn);
    }

    public void move(ArrayList b, Point last, Point p, Point n) {
        if (last.distance(p) > 1) {
            n = super.calculateCoord(last, p);
            b.add(n);
            if (b.size() >= size) {
                for (int i = 0; i < b.size() - size; i++) {
                    b.remove(i);
                }
            }
        }
        board.repaint();
        super.eatFood(n);
    }

    public void drop() {
        if (crash.checkEnemyCrash(board.snake.getSnake(), enemy) == true) {
            for (int i = 0; i < enemy.size(); i++) {
                Point dead = new Point();
                dead = enemy.get(i);
                board.food.food.add(dead);
            }
            enemy.clear();
            size = 10;
            enemy.add(new Point(r.nextInt(900), r.nextInt(900)));
        }
    }

    /*public void showLocationHead(Point last){
            System.out.println(last.x +" | "+last.y);
        }*/
    public ArrayList<Point> getEnemy() {
        return enemy;
    }

    public void setEnemy(ArrayList<Point> enemy) {
        this.enemy = enemy;
    }

}


