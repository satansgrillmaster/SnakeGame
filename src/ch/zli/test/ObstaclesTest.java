package ch.zli.test;

import ch.zli.snakegame.Obstacles;
import ch.zli.snakegame.util.Coord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObstaclesTest {
    private Obstacles obstacles = new Obstacles();

    @Test
    public void intersectsWithTest() {
        assertTrue(obstacles.intersectsWith(new Coord(0, 0)));
    }
}
