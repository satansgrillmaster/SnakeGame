package ch.zli.game.snakegame;

import ch.zli.game.Game;
import ch.zli.snakegame.SnakeGameLogic;
import ch.zli.snakegame.util.Direction;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends Game {
    private boolean gamePaused;
    private boolean gameOver = false;
    private SnakeGameLogic snakeGameLogic;

    /**
     * Constructor for the Snakegame
     */
    public SnakeGame() {
        gamePaused = true;
        snakeGameLogic = new SnakeGameLogic(this);
        snakeGameLogic.initAfterLevelChanged();
    }

    /**
     * Interrupts the old game and restarts the logic of it
     */
    @Override
    public void newGame() {
        System.out.println("newGame");
        if (snakeGameLogic != null){
            snakeGameLogic.cancelTimer();
            this.snakeGameLogic = new SnakeGameLogic(this);
            snakeGameLogic.initAfterLevelChanged();
            if (!gamePaused){
                gamePaused = !gamePaused;
            }
            if (gameOver){
                gameOver = !gameOver;
            }
            gameNeedsRedraw();
        }
    }

    /**
     * Quits everything
     */
    @Override
    public void quitGame() {
        System.out.println("quitGame");
        System.exit(0);
    }

    /**
     * Pauses the game
     */
    @Override
    public void pauseGame() {
        System.out.println("pauseGame");
        gamePaused = !gamePaused;
        gameNeedsRedraw();
    }

    /**
     * Sets the variable, so we know that the player lost and the game is over
     */
    public void setGameOver(){
        gameOver = true;
    }

    /**
     * Handle for the direction change
     */
    @Override
    public void goUp() {
        System.out.println("goUp");
        snakeGameLogic.changeDir(Direction.up);
    }

    /**
     * Handle for the direction change
     */
    @Override
    public void goDown() {
        System.out.println("goDown");
        snakeGameLogic.changeDir(Direction.down);
    }

    /**
     * Handle for the direction change
     */
    @Override
    public void goLeft() {
        System.out.println("goLeft");
        snakeGameLogic.changeDir(Direction.left);
    }

    /**
     * Handle for the direction change
     */
    @Override
    public void goRight() {
        System.out.println("goRight");
        snakeGameLogic.changeDir(Direction.right);
    }

    /**
     * Draws the state of the game
     */
    @Override
    public void drawStatus(JPanel panel, Graphics2D g) {

        int fontSize = panel.getHeight() / 16;

        g.setFont(new Font("myFont", 1, fontSize));

        g.drawString(
            gamePaused ? "Game Paused" : "Game Running", 20, 20 + fontSize);
        if (gameOver) {
            g.drawString("Game Over", 20, 40 + 3 * fontSize);
        }

        g.drawString("Level: " + snakeGameLogic.getLevelHeight(), 20, 60 + 5 * fontSize);

        g.drawString("Your score: " + snakeGameLogic.getPoints(), 20, 80 + 7 * fontSize);
    }

    /**
     * Draws the Game
     */
    @Override
    public void drawScene(JPanel panel, Graphics2D g) {
        snakeGameLogic.draw(panel, g);
    }

    /**
     * 
     * @return if the game is over or not
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * 
     * @return if the game is paused or not
     */
    public boolean isPaused(){
        return gamePaused;
    }
    
}
