package ch.zli.gui;

import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import ch.zli.gui.impl.SquareLayout;
/**
 * A generic Swing game window consisting of two parts, a square part for the
 * game and a rectangular part for the game status.
 */
// @SuppressWarnings("serial")
public class SwingGameGui extends JFrame implements RedrawListener {

	private static final String CMD_QUIT = "quit";
	private static final String CMD_PAUSE = "pause";
	private static final String CMD_NEW = "new";

	private List<GameCmdListener> gameCmdListeners;
	private GamePainter gamePainter;

	/**
	 * ctor
	 * 
	 * @see SwingGameGui#SwingGameGui(String, ImageIcon)
	 */
	public SwingGameGui(String title) {
		this(title, null);
	}

	/**
	 * ctor
	 * 
	 * @param title
	 *            the game title
	 * @param icon
	 *            an icon representing the game
	 */
	public SwingGameGui(String title, ImageIcon icon) {
		gameCmdListeners = new ArrayList<GameCmdListener>();
		gamePainter = null;

		createGui(title, icon);
		createMenues();
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Add a command listener
	 * 
	 * @param gameCmdListener
	 *            a command listener
	 * 
	 * @see GameCmdListener
	 */
	public void addGameCommandListener(GameCmdListener gameCmdListener) {
		gameCmdListeners.add(gameCmdListener);
	}

	/**
	 * Set our redraw command receiver.
	 * 
	 * @param gamePainter
	 *            the PaintListener to be notified when the GUI needs to be
	 *            redrawn.
	 * 
	 * @see SwingGamePainter
	 */
	public void setGamePainter(GamePainter gamePainter) {
		this.gamePainter = gamePainter;
	}

	public void gameNeedsRedraw() {
		this.repaint();
	}

	private void createGui(String title, ImageIcon icon) {
		if (title != null) {
			setTitle(title);
		}

		if (icon != null) {
			setIconImage(icon.getImage());
		}

		setLayout(new SquareLayout(0));

		JPanel statusPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if (gamePainter != null) {
					gamePainter.drawStatus(this, (Graphics2D) g);
				}
			}
		};
		JPanel sceenePanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				if (gamePainter != null) {
					gamePainter.drawScene(this, (Graphics2D) g);
				}
			}
		};

		sceenePanel.setBackground(Color.WHITE);
		statusPanel.setBackground(Color.WHITE);

		sceenePanel.setBorder(BorderFactory.createEtchedBorder());
		statusPanel.setBorder(BorderFactory.createEtchedBorder());

		sceenePanel.setPreferredSize(new Dimension(256, 256));
		statusPanel.setPreferredSize(new Dimension(150, 0));

		add(statusPanel, SquareLayout.LEFTOVER);
		add(sceenePanel, SquareLayout.SQUARE);

		// I have to intercept any Key-Event because the solution
		// with a GlassPane doesn't work well enough.
		DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new SnakeKeyEventDispatcher());

	}

	private void createMenues() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem pauseGame = new JMenuItem("Pause Game");
		JMenuItem quitGame = new JMenuItem("Quit");

		fileMenu.add(newGame);
		fileMenu.add(pauseGame);
		fileMenu.add(new JSeparator());
		fileMenu.add(quitGame);

		newGame.setActionCommand(CMD_NEW);
		pauseGame.setActionCommand(CMD_PAUSE);
		quitGame.setActionCommand(CMD_QUIT);
		SnakeActionHandler snakeActionHandler = new SnakeActionHandler();
		newGame.addActionListener(snakeActionHandler);
		pauseGame.addActionListener(snakeActionHandler);
		quitGame.addActionListener(snakeActionHandler);
	}

	private class SnakeActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			for (GameCmdListener listener : gameCmdListeners) {
				switch (evt.getActionCommand()) {
				case CMD_NEW:
					listener.newGame();
					break;
				case CMD_PAUSE:
					listener.pauseGame();
					break;
				case CMD_QUIT:
					listener.quitGame();
					break;
				}
			}
		}
	}

	private class SnakeKeyEventDispatcher implements KeyEventDispatcher {

		@Override
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() != KeyEvent.KEY_PRESSED) {
				return false;
			}

			for (GameCmdListener listener : gameCmdListeners) {
				switch (e.getKeyChar()) {
				case 'i':
				case 'I':
					listener.goUp(); // handle up
					break;
				case 'm':
				case 'M':
					listener.goDown(); // handle down
					break;
				case 'j':
				case 'J':
					listener.goLeft(); // handle left
					break;
				case 'k':
				case 'K':
					listener.goRight(); // handle right
					break;
				case 'p':
				case 'P':
					listener.pauseGame(); // handle pause
					break;
				case 'n':
				case 'N':
					listener.newGame(); // handle new
					break;
				case 'q':
				case 'Q':
					listener.quitGame(); // handle quit
					break;
				default:
					break; // do nothing
				}

				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					listener.goUp(); // handle up
					break;
				case KeyEvent.VK_DOWN:
					listener.goDown(); // handle down
					break;
				case KeyEvent.VK_LEFT:
					listener.goLeft(); // handle left
					break;
				case KeyEvent.VK_RIGHT:
					listener.goRight(); // handle right
					break;
				default:
					break; // do nothing
				}
			}

			return false;
		}
	}

}
