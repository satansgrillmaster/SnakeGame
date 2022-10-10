package ch.zli.snakegame;

import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.zli.snakegame.util.Coord;

public class Obstacles {
    private List<Coord> obstaclesPos = new ArrayList<>();
    private Random rn = new Random();

    /**
     * The constructor for the obstacles
     */
    public Obstacles(){
        for (int i = 0; i < 10; i++) {

            this.obstaclesPos.add(new Coord(0, i));
            this.obstaclesPos.add(new Coord(10, i));
            this.obstaclesPos.add(new Coord(i, 0));
            this.obstaclesPos.add(new Coord(i, 10));
        }
        this.obstaclesPos.add(new Coord(10, 10));
    }

    /**
     * The constructor fo the obstacles with additionally placed obstacles
     * @param numberOfRandomObstacles number of additionally placed obstacles
     * @param snake the snake to be sure an random obstacle wont be placed on the snake
     */
    public Obstacles(int numberOfRandomObstacles, Snake snake){
        for (int i = 0; i < 10; i++) {

            this.obstaclesPos.add(new Coord(0, i));
            this.obstaclesPos.add(new Coord(10, i));
            this.obstaclesPos.add(new Coord(i, 0));
            this.obstaclesPos.add(new Coord(i, 10));
        }
        this.obstaclesPos.add(new Coord(10, 10));

        for (int i = 0; i < numberOfRandomObstacles; i++) {

            Coord potentialCoord = null;
            do {
                potentialCoord = new Coord(rn.nextInt(9 - 1 + 1) + 1, rn.nextInt(9 - 1 + 1) + 1);
            } while (snake.intersects(potentialCoord) || intersectsWith(potentialCoord));

            this.obstaclesPos.add(potentialCoord);
        }
    }

    /**
     * Draws the obstacle as a black rect
     * @param g is the graphics2D to paint
     * @param square is the width of a square in the panel
     * @param offset is the amount of pixel we have left after all squares
     */
    public void draw(Graphics2D g, int square, int offset){
        g.setColor(Color.BLACK);
        for (Coord obstacle : obstaclesPos) {
            g.fillRect(obstacle.getX() * square + 5 + (offset / 2), obstacle.getY() * square + 5 + (offset / 2), square - 10, square - 10);
        }
    }

    /**
     * To check a position if theres an obstacle
     * @param position the position we want to check
     * @return wether there is an obstacle or not
     */
    public boolean intersectsWith(Coord position){
        return obstaclesPos.contains(position);
    }
}
