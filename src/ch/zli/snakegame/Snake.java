package ch.zli.snakegame;

import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;

import javax.imageio.ImageIO;


public class Snake {
    private List<Coord> snakePos;
    private Direction direction;
    private Direction oldDirection;
    private BufferedImage headImg;
    private boolean eat;

    /**
     * The constructor of the snake with default direction down at the beginning
     */
    public Snake (){
        this.snakePos = new ArrayList<>();
        this.direction = Direction.down;
        this.oldDirection = Direction.down;
        this.eat = false;
        for (int $i = 0; $i < 4; $i++){
            this.snakePos.add(new Coord(5, 5 - $i));
        }

        /*load the image for the head*/
        try
        {
            headImg = ImageIO.read(new File("src/ch/zli/snakegame/imgs/snakehead.png"));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            headImg = null;
        }
    }

    /**
     * Draws the snake
     * @param g2d is the graphics2D to paint
     * @param fieldSizeWidth is the width of a field in the panel
     * @param fieldSizeHeight is the height of a field in the panel
     * @param offsetx is the amount of pixel we have left after all fields
     * @param offsety is the amount of pixel we have left after all fields
     */
    public void draw(Graphics2D g2d, int fieldSizeWidth,int fieldSizeHeight, int offsetx, int offsety){
        g2d.setColor(Color.GREEN);

        for (Coord bodypart : snakePos) {

            if (bodypart != this.snakePos.get(0)) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(
                        bodypart.getX() * fieldSizeWidth + (offsetx / 2),
                        bodypart.getY() * fieldSizeHeight + (offsety / 2),
                        fieldSizeWidth - 5,
                        fieldSizeHeight - 5
                );
            }
        }

        /* if there's a headimg, draw the img, otherwise draw a default head*/
        if (headImg != null){
            int degrees = 0;

            if (this.direction == Direction.up){
                degrees = 180;
            }
            else if (this.direction == Direction.left){
                degrees = 90;
            }
            else if (this.direction == Direction.right){
                degrees = -90;
            }

            double rotationRequired = Math.toRadians(degrees);
            double locationX = headImg.getWidth() / 2.0;
            double locationY = headImg.getHeight() / 2.0;
            AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            BufferedImage newImage = op.filter(headImg,null);

            g2d.drawImage(newImage.getScaledInstance(
                            fieldSizeWidth,
                            fieldSizeHeight, 1),
                    this.snakePos.get(0).getX() * fieldSizeWidth + (offsetx / 2),
                    this.snakePos.get(0).getY() * fieldSizeHeight + (offsety / 2),
                    null
            );
        }
        else{
            g2d.setColor(Color.BLACK);
            g2d.fillArc(this.snakePos.get(0).getX() * fieldSizeWidth + (offsetx / 2),
                    this.snakePos.get(0).getY() * fieldSizeHeight + (offsety / 2),
                    fieldSizeWidth,
                    fieldSizeHeight,
                    0,
                    360);
        }
    }

    /**
     * Get the headpos of the snake
     * @return the headpos of the snake
     */
    public Coord getSnakeHeadPos(){
        return snakePos.get(0);
    }

    /**
     * This function sets a new direction, opposite direction on the old direction we did go arent allowed
     * @param direction the direction we want to go
     */
    public void setDirection(Direction direction){
        switch (direction) {
            case up:
                if (oldDirection != Direction.down) {
                    this.direction = direction;
                }
                break;
            case down:
                if (oldDirection != Direction.up) {
                    this.direction = direction;
                }
                break;
            case left:
                if (oldDirection != Direction.right) {
                    this.direction = direction;
                }
                break;
            case right:
                if (oldDirection != Direction.left) {
                    this.direction = direction;
                }
                break;
        }
    }

    /**
     * Gets the current direction
     * @return the direction we have
     */
    public Direction getDirection(){
        return direction;
    }

    /**
     * Moves the snake to a position we calculated in the desert, also lets the snake grow or not
     * @param nextPos the position the snake goes
     */
    public void move (Coord nextPos){
        snakePos.add(0, nextPos);
        oldDirection = direction;
        if(!eat){
            snakePos.remove(snakePos.size() - 1);
        }

        this.eat = false;
    }

    /**
     * Lets the snake by the next move grow
     */
    public void eat (){
        this.eat = true;
    }

    /**
     * Checks if the snake colided with itself
     * @return if it colided or not
     */
    public boolean selfColission () {
        for (int i = 1; i < snakePos.size() - 1; i++) {
            if (snakePos.get(0).equals(snakePos.get(i)))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the snake intersects with a given position
     * @param pos the position we want to check
     * @return if it will intersect or not
     */
    public boolean intersects(Coord pos){
        return snakePos.contains(pos);
    }

}
