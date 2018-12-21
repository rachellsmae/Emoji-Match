import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * Board class holds the primary game logic for how different elements interact with one another. 
 */

@SuppressWarnings("serial")
public class Board extends JPanel {

	private JLabel status;
	public static boolean playing = false;
	
	private JLabel score;
	private String name;
	private String title;
	
	private static int[][] tiles = new int[4][4];
	private LinkedList<int[][]> moves = new LinkedList<int[][]>();
	private int playerScore = 0;
	
	private static final int width = 4;
	private static final int height = 4;
	
	// Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
	
	public Board(JLabel status, String name, JLabel score) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setFocusable(true);
		this.title = "Emoji Match";
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					tiles = shiftLeft(tiles);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					tiles = rotate(rotate(shiftLeft(rotate(rotate(tiles)))));
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					tiles = rotate(rotate(rotate(shiftLeft(rotate(tiles)))));
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					tiles = rotate(shiftLeft(rotate(rotate(rotate(tiles)))));
				} else {
					return;
				}
	             
	            if (hasWon(tiles)) {
	                status.setText("YOU WON!!!");
	                HighScores.addScore(Integer.toString(playerScore), name);
	                playing = false;
	            } else if (hasLost(tiles)) {
	                status.setText("You lost...");
	                HighScores.addScore(Integer.toString(playerScore), name);
	                restart();
	                return;
	            } else {
	                tiles = create(tiles);
	                moves.add(tiles);
	            }
	            playerScore = score(tiles);
	            score.setText(Integer.toString(playerScore));
	            repaint();
	          }
	        });

	    this.status = status;
	    this.name = name;
	    this.score = score;
	  }
	
	
	// mash numbers together if they are of the same value
	public static int[][] shiftLeft(int[][] tiles) {
		int[][] temp = new int[4][4];
		if (playing) {
			// multiply original numbers by 2 if they are mashed together
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (tiles[i][j] != 0) {
						int result = tiles[i][j];
						for (int k = i + 1; k < width; k++) {
							if (tiles[k][j] != 0) {
								if (tiles[k][j] == result) {
									tiles[i][j] = result * 2;
									tiles[k][j] = 0;

								}

							}
						}
					}
				}
			}

			// remove all 0's in between tiles when shifting to the left
			for (int j = 0; j < height; j++) {
				int start = 0;
				for (int i = 0; i < width; i++) {
					if (tiles[i][j] != 0) {
						temp[start][j] = tiles[i][j];
						start++;
					}
				}
			}
		}
		return temp;
	}

	// rotate board 90 degrees clockwise
	public static int[][] rotate(int[][] tiles) {
		int[][] temp = new int[4][4];
		if (playing) {
			for (int j = 0; j < height; j++) {
				int position = 3 - j;
				for (int i = 0; i < width; i++) {
					temp[position][i] = tiles[i][j];
				}
			}
		}
		return temp;
	}

	
	// randomly create tile
	public static int[][] create(int[][] tiles) {
		if (playing) {
			boolean stop = false;
			while (!stop) {
				int x = (int) Math.round(Math.random() * 3);
				int y = (int) Math.round(Math.random() * 3);
				if (tiles[x][y] == 0) {
					tiles[x][y] = 2;
					stop = true;
				}
			}
		}
		return tiles;
	}

	// reset game
	public void restart() {
		moves.clear();
		playing = true;
		tiles = new int[4][4];
		for (int i = 0; i < 2; i++) {
			int x = (int) Math.round(Math.random() * 3);
			int y = (int) Math.round(Math.random() * 3);
			tiles[x][y] = 2;
		}
		status.setText("Running");
		repaint();
		requestFocusInWindow();
	}

	// undo game
	public void undo() {
		if (moves.size() != 0) {
			moves.removeLast();
			tiles = moves.getLast();
			playerScore = score(tiles);
			score.setText(Integer.toString(playerScore));
		}
		repaint();
		requestFocusInWindow();
	}

	// allocate scores
	public static int score(int[][] tiles) {
		int score = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				score += tiles[i][j];
			}
		} 
		return score;
	}
	
	// check if player has won
	public static boolean hasWon(int[][] tiles) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == 512) {
					return true;
				}
			}
		} 
		return false;
	}
	
	// check if player has lost
	public static boolean hasLost(int[][] tiles) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == 0) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < width-1; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == tiles[i + 1][j]) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height - 1; j++) {
				if (tiles[i][j] == tiles[i][j + 1]) {
					return false;
				}
			}
		}
		return true;
	}
	

	  
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Tile temp = new Tile(i * 75, j * 75, tiles[i][j]);
					temp.paint(g);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}


}
	
