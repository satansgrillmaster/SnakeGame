package ch.zli.gui;

/**
 * Game command interface. To be implemented by the game instance
 */
public interface GameCmdListener {

	/** Start a new Game */
	public void newGame();

	/** Quit the game */
	public void quitGame();

	/** Pause the game */
	public void pauseGame();

	/** User generated an up command */
	public void goUp();

	/** User generated an down command */
	public void goDown();

	/** User generated an left command */
	public void goLeft();

	/** User generated an right command */
	public void goRight();
}
