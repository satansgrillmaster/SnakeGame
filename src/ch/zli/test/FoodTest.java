package ch.zli.test;

import ch.zli.snakegame.Food;
import ch.zli.snakegame.util.Coord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodTest {
    private Food food = new Food(new Coord(9, 9));

    @Test
    public void intersectsWithTest() {
        assertTrue(food.intersectsWith(new Coord(9, 9)));
    }

    @Test
    public void getFoodPosTest(){
        assertEquals(new Coord(9, 9), food.getFoodPos());
    }
}
