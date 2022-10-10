package ch.zli.test;

import ch.zli.snakegame.Desert;
import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesertTest {
    private Desert desert = new Desert();

    @Test
    public void nextPosTest() {
        assertEquals(new Coord(6, 5), desert.nextPos(new Coord(5, 5), Direction.right));
    }
}
