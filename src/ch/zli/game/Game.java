package ch.zli.game;

import ch.zli.gui.GameCmdListener;
import ch.zli.gui.GamePainter;
import ch.zli.gui.RedrawListener;

public abstract class Game implements GameCmdListener, GamePainter {
    private RedrawListener redrawListener;

    /**
     * Function setts a redrawListener.
     * @param redrawListener is the redrawlistener we need
     */
    public void setRedrawListener(RedrawListener redrawListener) {
        this.redrawListener = redrawListener;
    }

    /**
     * This function makes the game redraw
     */
    public void gameNeedsRedraw() {
        if (redrawListener != null) {
            redrawListener.gameNeedsRedraw();
        }
    }
}
