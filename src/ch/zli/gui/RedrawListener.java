package ch.zli.gui;

/**
 * GUI notification interface
 */
public interface RedrawListener {

	/** Notify a GUI that it has to redraw itself. */
	public void gameNeedsRedraw();
}
