package ch.zli.snakegame;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;

import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;

public class Desert {
    public final int width;
    public final int height;
    /**
     * Constructor for the desert
     */
    public Desert(){
        width = 15;
        height = 15;
    }

    /**
     * Draws the desert with a yellow background (typical for a desert)
     * @param panel is the panel to paint on
     * @param g is the graphics2D
     */
    public void draw(JPanel panel, Graphics2D g){
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        g.setColor(Color.BLACK);
    }

    /**
     * This function calculates the next position
     * @param position is the current position, which we want to move
     * @param direction is the direction in which we want to move
     * @return an object from the helpclass Coord
     */
    public Coord nextPos(Coord position, Direction direction){
        int x = position.getX();
        int y = position.getY();

        switch (direction) {
            case up:
                y--;
                break;
            case down:
                y++;
                break;
            case left:
                x--;
                break;
            default:
                x++;
                break;
            }
        if (x < 0) {
            x = width - 1;
        }
        if (y < 0){
            y = height - 1;
        }
        if (x == width){
            x = 0;
        }
        if (y == height){
            y = 0;
        }

        return new Coord(x, y);
    }
}
