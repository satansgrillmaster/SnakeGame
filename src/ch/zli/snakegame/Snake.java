package ch.zli.snakegame;

import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;


public class Snake {
    private List<Coord> snakePos;
    private Direction direction;
    private Direction oldDirection;
    private boolean eat;

    /**
     * The constructor of the snake with default direction down at the beginning
     */
    public Snake (){
        this.snakePos = new ArrayList<>();
        this.direction = Direction.down;
        this.oldDirection = Direction.down;
        this.eat = false;

        for(int $i = 0; $i < 4; $i++){
            this.snakePos.add(new Coord(5, 5 - $i));
        }
    }

    /**
     * Draws the snake with a blue head and green body
     * @param g is the graphics2D to paint
     * @param square is the width of a square in the panel
     * @param offset is the amount of pixel we have left after all squares
     */
    public void draw(Graphics2D g, int square, int offset){
        g.setColor(Color.GREEN);

        for (Coord bodypart : snakePos) {
            if (bodypart != this.snakePos.get(0)) {
                g.fillArc(bodypart.getX() * square + (offset / 2), bodypart.getY() * square + (offset / 2), square, square, square, 360);
            }
        }

        g.setColor(Color.BLUE);
        g.fillArc(this.snakePos.get(0).getX() * square + (offset / 2), this.snakePos.get(0).getY() * square + (offset / 2), square, square, 0, 270);
    }

    /**
     * To get the headposition of the snake
     * @return the headposition of the snake
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
