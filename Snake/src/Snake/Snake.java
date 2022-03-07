package Snake;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import static javafx.scene.paint.Color.color;

public class Snake extends Entity {

    ArrayList<Point> snake;

    PointerInfo a = MouseInfo.getPointerInfo();
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
            generate();
            board.enemy.generate();
            checkOutOfBorder();
            drop();
        }
    }

    public void generate() {
        a = MouseInfo.getPointerInfo();
        Point p = a.getLocation();
        Point last = snake.get(snake.size() - 1);
        Point n = new Point();
        //showLocationHead(last); 
        move(snake, last, p, n);
    }

    public void drop() {
        if (crash.checkSnakeCrash(snake, board.enemy.getEnemy()) == true) {
            for (int i = 0; i < snake.size(); i++) {
                Point dead = new Point();
                dead = snake.get(i);
                board.food.food.add(dead);
            }
            snake.clear();
            size = 10;
            count = 0;
            snake.add(new Point(r.nextInt(900), r.nextInt(900)));
        }
        if (crash.checkEnemyCrash(snake, board.enemy.getEnemy()) == true) {
            for (int i = 0; i < board.enemy.enemy.size(); i++) {
                Point dead = new Point();
                dead = board.enemy.enemy.get(i);
                board.food.food.add(dead);
            }
            board.enemy.enemy.clear();
            board.enemy.size = 10;
            count = 0;
            board.enemy.enemy.add(new Point(r.nextInt(900), r.nextInt(900)));
        }
    }

    public void checkOutOfBorder() {
        if (snake.get(snake.size() - 1).x < 15 || snake.get(snake.size() - 1).y < 44
                || snake.get(snake.size() - 1).x > 996 || snake.get(snake.size() - 1).y > 985) {
            System.exit(0);
        }
    }

    public Color checkSpeed() {
        board.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                    speed = 30;
                    color = Color.RED;
                }
                if (evt.getKeyCode() != KeyEvent.VK_SPACE) {
                    speed = 10;
                    color = Color.BLUE;
                }
            }
        });
        return color;
    }

    public void move(ArrayList b, Point last, Point p, Point n) {
        if (last.distance(p) > 1) {
            n = super.calculateCoord(last, p);
            b.add(n);
            //count++;
            if (b.size() >= size) {
                for (int i = 0; i < b.size() - size; i++) {
                    b.remove(i);
                }
            }
        }
        board.repaint();
        super.eatFood(n);
    }

    /*public void showLocationHead(Point last){
            System.out.println(last.x +" | "+last.y);
        }*/
    public ArrayList<Point> getSnake() {
        return snake;
    }

    public void setSnake(ArrayList<Point> snake) {
        this.snake = snake;
    }

}

