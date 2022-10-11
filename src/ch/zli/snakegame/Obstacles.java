package ch.zli.snakegame;

import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.zli.snakegame.util.Coord;

import javax.imageio.ImageIO;

public class Obstacles {
    private List<Coord> obstaclesPos = new ArrayList<>();
    private Random rn = new Random();
    private BufferedImage obstacleImg;

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
        /*for (int i = 0; i < 10; i++) {

            this.obstaclesPos.add(new Coord(0, i));
            this.obstaclesPos.add(new Coord(10, i));
            this.obstaclesPos.add(new Coord(i, 0));
            this.obstaclesPos.add(new Coord(i, 10));
        }*/
        // this.obstaclesPos.add(new Coord(10, 10));

        for (int i = 0; i < numberOfRandomObstacles; i++) {

            Coord potentialCoord = null;
            do {
                potentialCoord = new Coord(rn.nextInt(9 - 1 + 1) + 1, rn.nextInt(9 - 1 + 1) + 1);
            } while (snake.intersects(potentialCoord) || intersectsWith(potentialCoord));

            this.obstaclesPos.add(potentialCoord);
        }
        try{
            obstacleImg = ImageIO.read(new File("src/ch/zli/snakegame/imgs/obstacle.png"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Draws the obstacle as a black rect
     * @param g2d is the graphics2D to paint
     * @param fieldSizeWidth is the width of a fieldSizeWidth in the panel
     * @param offsetx is the amount of pixel we have left after all squares
     */
    public void draw(Graphics2D g2d, int fieldSizeWidth, int fieldSizeHeight, int offsetx, int offsety){
        g2d.setColor(Color.BLACK);
        for (Coord obstacle : obstaclesPos) {
            g2d.drawImage(obstacleImg.getScaledInstance(
                            fieldSizeWidth,
                            fieldSizeHeight, 1),
                    obstacle.getX() * fieldSizeWidth + (offsetx / 2),
                    obstacle.getY() * fieldSizeHeight + (offsety / 2),
                    null
            );
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
