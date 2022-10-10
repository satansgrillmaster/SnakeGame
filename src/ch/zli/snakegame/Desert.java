package ch.zli.snakegame;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;

public class Desert {
    private List<Coord> desertField;

    /**
     * Constructor for the desert
     */
    public Desert(){
        this.desertField = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.desertField.add(new Coord(j, i));
            }
        }
    }

    /**
     * Draws the desert with a yellow background (typical for a desert)
     * @param panel is the panel to paint on
     * @param g is the graphics2D
     */
    public void draw(JPanel panel, Graphics2D g){
        g.setColor(Color.YELLOW);
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

        switch (direction) {
            case up:
                return new Coord(position.getX(), position.getY() - 1);
            case down:
                return new Coord(position.getX(), position.getY() + 1);
            case left:
                return new Coord(position.getX() - 1, position.getY());
            default:
                return new Coord(position.getX() + 1, position.getY());
        }
    }

}
