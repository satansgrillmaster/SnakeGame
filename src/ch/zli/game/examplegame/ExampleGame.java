package ch.zli.game.examplegame;

import ch.zli.game.Game;

import javax.swing.*;
import java.awt.*;

public class ExampleGame extends Game {
    private boolean gamePaused = true;

    @Override
    public void newGame() {
        System.out.println("newGame");        
    }

    @Override
    public void quitGame() {
        System.out.println("quitGame");
    }

    @Override
    public void pauseGame() {
        System.out.println("pauseGame");
        gamePaused = !gamePaused;
        gameNeedsRedraw();
    }

    @Override
    public void goUp() {
        System.out.println("goUp");
    }

    @Override
    public void goDown() {
        System.out.println("goDown");
    }

    @Override
    public void goLeft() {
        System.out.println("goLeft");
    }

    @Override
    public void goRight() {
        System.out.println("goRight");
    }

    @Override
    public void drawStatus(JPanel panel, Graphics2D g) {
        g.drawString(
            gamePaused ? "Game Paused" : "Game Running", 20, 20);
    }

    @Override
    public void drawScene(JPanel panel, Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        g.setColor(Color.BLACK);
        g.drawString("Example Game Scene", 20, 20);
    }
    
}
