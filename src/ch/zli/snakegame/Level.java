package ch.zli.snakegame;

import java.util.Random;

import javax.swing.JPanel;
import java.awt.Graphics2D;

import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;

public class Level {

    private Desert desert = new Desert();
    private Snake snake = new Snake();
    private Foods foods = new Foods();
    private Obstacles obstacles;
    private static final Random rn = new Random();

    /**
     * Constructor for the Level
     */
    public Level(){
        this(0, 3);
    }

    /**
     * Constructor for the level with params
     * @param numberOfObstacles number of obstacles we want to have in this level
     * @param numberOfFood number of food we want in this level
     */
    public Level(int numberOfObstacles, int numberOfFood){

        obstacles = new Obstacles(numberOfObstacles, snake, desert.width, desert.height);

        for (int i = 0; i < numberOfFood; i++) {
            foods.addFood(createFood());
        }
    }


    /**
     * Draws the the level and everything in it
     * @param panel is the panel to paint on
     * @param g2d is the graphics2D
     */
    public void draw(JPanel panel, Graphics2D g2d){
        int squareWidth = panel.getWidth() / this.desert.width;
        int squareHeight = panel.getHeight() / this.desert.height;
        int offsetx = panel.getWidth() % squareWidth;
        int offsety = panel.getHeight() % squareHeight;
        desert.draw(panel, g2d);
        snake.draw(g2d, squareWidth, squareHeight, offsetx, offsety);
        obstacles.draw(g2d, squareWidth, squareHeight, offsetx, offsety);
        foods.draw(g2d, squareWidth, squareHeight, offsetx, offsety);
    }

    /**
     * Creates a random positioned food
     * @return the new position of the food
     */
    private Food createFood() {
        Coord pos;

        do {
            pos =  new Coord(rn.nextInt(desert.height), rn.nextInt(desert.width));
        } while(foods.intersectsWith(pos) || obstacles.intersectsWith(pos) || snake.intersects(pos));
        return new Food(pos);
    }

    /**
     * Tells the snake to change the direction (just if its allowed)
     * @param dir the direction in which we want to change
     */
    public void changeDir(Direction dir){
        snake.setDirection(dir);
    }

    /**
     * Moves the snake and let it eat if theres a food
     * @return returns if the snake has ate or not
     */
    public boolean SnakemoveAndEat(){
        Direction dir = snake.getDirection();
        Coord headPos = snake.getSnakeHeadPos();
        Coord nextPos = desert.nextPos(headPos, dir);
        snake.move(nextPos);

        if(foods.intersectsWith(nextPos)){
            snake.eat();
            foods.removeFood(nextPos);
            foods.addFood(createFood());
            return true;
        }
        return false;
    }

    /**
     * Checks if the snake collideds with something, so she would die
     * @return if the snake will die or not
     */
    public boolean checkDyingCollisions(){
        Coord headPos = snake.getSnakeHeadPos();

        if (snake.selfColission()) {
            return true;
        }else if (obstacles.intersectsWith(headPos)){
            return true;
        }else{
            return false;
        }
    }
}
