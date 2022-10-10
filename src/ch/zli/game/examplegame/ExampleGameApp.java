package ch.zli.game.examplegame;

import ch.zli.gui.SwingGameGui;

import javax.swing.*;

public class ExampleGameApp {
    public static void main(String[] args) {
        // Do not ask, always do it this way
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // We need a game and a gui
                ExampleGame game = new ExampleGame();
                SwingGameGui gui = new SwingGameGui("Example App");

                // Add the game to the gui ...
                gui.addGameCommandListener(game);
                gui.setGamePainter(game);

                // and the gui to the game
                game.setRedrawListener(gui);

                // Show the gui
                gui.setVisible(true);
            }
        });
    }
}
