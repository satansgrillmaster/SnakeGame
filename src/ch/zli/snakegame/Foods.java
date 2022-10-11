package ch.zli.snakegame;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ch.zli.snakegame.util.Coord;

public class Foods {
    private List<Food> foodList = new ArrayList<>();

    /**
     * Adds a food
     * @param food is the food we want to add
     */
    public void addFood(Food food){
        foodList.add(food);
    }

    /**
     * This function checks every food in it, if its intersects with a position we give
     * @param pos is the position we want to check
     * @return true if it intersects, otherwise false
     */
    public boolean intersectsWith(Coord pos) {
        for (Food food : foodList) {
            if(food.getFoodPos().equals(pos)) return true;
        }
        return false;
    }

    /**
     * Draws every single food
     * @param g is the graphics2D to paint
     * @param fieldSizeWidth is the width of a fieldSizeWidth in the panel
     * @param offsetx is the amount of pixel we have left after all squares
     */
    public void draw(Graphics2D g, int fieldSizeWidth,int fieldSizeHeight, int offsetx, int offsety) {
        for (Food food : foodList) {
            food.draw(g, fieldSizeWidth, fieldSizeHeight, offsetx, offsety);
        }
    }

    /**
     * Removes a food of the list if the position we give
     * @param pos the position we want to remove the food
     */
    public void removeFood(Coord pos){
        for (Food food : foodList) {
            if (food.getFoodPos().equals(pos)) {
                foodList.remove(food);
                return;
            }
        }
    }
}
