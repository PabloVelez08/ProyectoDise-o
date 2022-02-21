
package Snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
        ArrayList<Point> snake;
        Pointer point;
        int size = 10;

        public void move(Point last, Point p, Point n){   
                if(last.distance(p) > 1){
                    n = point.calculateCoord(last, p);
                    snake.add(n);
                    //count++;
                    if(snake.size() >= size){
                        for(int i = 0; i < snake.size() - size; i++){
                            snake.remove(i);
                        }
                    }
                }
                point.deleteFood(n);
        }

    public Point getSnake(int i) {
        return snake.get(i);
    }

    public void setSnake(ArrayList<Point> snake) {
        this.snake = snake;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
