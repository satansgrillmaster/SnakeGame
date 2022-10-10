package ch.zli.gui;

import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * A PaintListener will be notified by the GUI when the status and the scene
 * must be redrawn. Subclasses have to do the actual redrawing.
 */
public interface GamePainter {

	/** Redraw the status panel */
	public void drawStatus(JPanel panel, Graphics2D g);

	/** Redraw the game panel */
	public void drawScene(JPanel panel, Graphics2D g);
}
