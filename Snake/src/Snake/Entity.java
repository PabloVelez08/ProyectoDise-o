package Snake;

import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;
import java.util.Random;

public abstract class Entity extends Thread {

    Board board;
    Crash crash;
    Snake snake;
    Enemy enemy;
    int size = 10;
    int speed = 10;
    Color color = Color.BLUE;
    static final Random r = new Random();
    int count = 0;

    public abstract void generate();

    public abstract void drop();

    public void eatFood(Point n) {
        Iterator<Point> i = board.food.food.iterator();
        while (i.hasNext()) {
            Point food = i.next();
            if (food.distance(n) < 20) {
                i.remove();
                count++;
                size++;
            }
        }
        board.repaint();
        //System.out.println("Puntaje por Comida  ent"+count);
    }

    public int score() {
        return count;
    }

    public Point calculateCoord(Point last, Point mouse) {
        double degree = 0;
        if (last.x < mouse.x && last.y < mouse.y) {
            degree = 360 - Math.toDegrees(Math.atan((double) (mouse.y - last.y) / (mouse.x - last.x)));
        } else if (last.x > mouse.x && last.y > mouse.y) {
            degree = 180 - Math.toDegrees(Math.atan((double) (last.y - mouse.y) / (last.x - mouse.x)));
        } else if (last.y > mouse.y && last.x < mouse.x) {
            degree = Math.toDegrees(Math.atan((double) (last.y - mouse.y) / (mouse.x - last.x)));
        } else if (last.y < mouse.y && last.x > mouse.x) {
            degree = 180 + Math.toDegrees(Math.atan((double) (mouse.y - last.y) / (last.x - mouse.x)));
        }
        Point p = new Point((int) (last.x + Math.cos(Math.toRadians(degree)) * speed), (int) (last.y - Math.sin(Math.toRadians(degree)) * speed));
        return p;
    }

}
