package ch.zli.test;

import ch.zli.snakegame.Desert;
import ch.zli.snakegame.Snake;
import ch.zli.snakegame.util.Coord;
import ch.zli.snakegame.util.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    
    private Snake snake = new Snake();

    @Test
    public void getSnakeHeadPosTest() {
        assertEquals(new Coord(5, 5), snake.getSnakeHeadPos());
    }

    @Test
    public void setImpossibleDirectionTest() { 
        snake.setDirection(Direction.up);
        assertNotEquals(Direction.down, snake.getDirection());
    }

    @Test
    public void setValidDirectionTest() {
        snake.setDirection(Direction.left);
        assertEquals(Direction.left, snake.getDirection());    
    }

    @Test
    public void moveTest() {
        snake.move(new Coord(20, 20));
        assertEquals(new Coord(20, 20), snake.getSnakeHeadPos());
    }

    @Test
    public void ultimateMoveTest(){
        Desert desert = new Desert();
        snake.setDirection(Direction.right);
        Coord nextPos = desert.nextPos(snake.getSnakeHeadPos(), snake.getDirection());
        snake.move(nextPos);
        assertEquals(new Coord(6, 5), snake.getSnakeHeadPos());
    }

    @Test
    public void selfColissionTest(){
        snake.move(new Coord(5, 4));
        assertTrue(snake.selfColission());
    }
}
