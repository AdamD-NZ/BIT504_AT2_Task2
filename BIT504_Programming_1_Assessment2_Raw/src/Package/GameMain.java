/**
 * Course: BIT504
 * Student Name: Adam Donson
 * Student Number: 5114991
 * Assessment: BIT504 Programming 1 Assessment 2
 */

package Package;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Dimension;


/**
 * GameMain is the main class for the Tic Tac Toe game.
 * It extends JPanel to serve as the canvas for drawing the game board 
 * and implements MouseListener to handle user input through mouse clicks.
 */
public class GameMain extends JPanel implements MouseListener{
	// Constants for window and game settings
	private static final String WINDOW_TITLE = "Tic Tac Toe";
	private static final int WINDOW_WIDTH = 301;
	private static final int WINDOW_HEIGHT = 351;
		
	// Game board dimensions and UI constants
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final int CELL_SIZE = 100;
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
    public static final int GRID_WIDTH = 8;
    public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
    public static final int CELL_PADDING = CELL_SIZE / 6;
    public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
    public static final int SYMBOL_STROKE_WIDTH = 5;
	
	
    // Game objects
    private Board board; // Game board object
    private GameState currentState; // Current game state (Playing, Win, Draw, etc.)
    private Player currentPlayer; // Current player (Cross or Nought)
    private JLabel statusBar; // Status bar to display game messages
	
		
    /** Constructor to set up the UI and initialize game components. */
    public GameMain() {
        // Add mouse listener for handling click events
        addMouseListener(this);
	    
        // Initialize and style the status bar for displaying messages    
		statusBar = new JLabel("         ");       
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));       
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));       
		statusBar.setOpaque(true);       
		statusBar.setBackground(Color.LIGHT_GRAY);  
				
		// Set layout and add status bar to the panel
		setLayout(new BorderLayout());       
		add(statusBar, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30)); // account for statusBar height in overall height
		
		// Initialize the game board and start the game
        board = new Board();
        initGame();
        }
	
    /** Main method to run the game application. */
	public static void main(String[] args) {
	    
		
		// Run GUI code in the Event Dispatch thread for thread safety
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    	public void run() {
	            // Create the main window (JFrame)
	            JFrame frame = new JFrame(WINDOW_TITLE); // Use WINDOW_TITLE
	            GameMain gamePanel = new GameMain();     // Create the game panel
	            frame.add(gamePanel);                    // Add the panel to the frame
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing the JFrame and exit the application

	         // Set window size and center it on the screen
	            frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // Sets the size of the JFrame
	            frame.setLocationRelativeTo(null); // Center the window on the screen
	            frame.setVisible(true); // Make the window visible
	        }
	    });
	}
	
	/**
     * Custom painting method to draw the game board and grid, and update the status bar.
     * @param g Graphics object for drawing.
     */
	public void paintComponent(Graphics g) {
		//fill background and set colour to white
		super.paintComponent(g);
		setBackground(Color.WHITE);
		
		// Draw the game board
	    g.setColor(Color.GRAY); // Draw the grid lines
		board.paint(g); //ask the game board to paint itself
		
		// Update status bar messages based on game state
		if (currentState == GameState.Playing) {          
			statusBar.setForeground(Color.BLACK);          
			if (currentPlayer == Player.Cross) {
				statusBar.setForeground(Color.RED);          
				statusBar.setText("X's Turn"); 	
			} else {
				statusBar.setForeground(Color.RED);          
				statusBar.setText("O's Turn"); 				
			}       
			} else if (currentState == GameState.Draw) {          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("It's a Draw! Click to play again.");       
			} else if (currentState == GameState.Cross_won) {          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("'X' Won! Click to play again.");       
			} else if (currentState == GameState.Nought_won) {          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("'O' Won! Click to play again.");       
			}
		}
			
	    /** Initialize the game by resetting the board and setting initial states. */
		public void initGame() {
			// Clear the board
			for (int row = 0; row < ROWS; ++row) {          
				for (int col = 0; col < COLS; ++col) {  
					// all cells empty
					board.cells[row][col].content = Player.Empty;           
				}
			}
			 currentState = GameState.Playing;
			 currentPlayer = Player.Cross;
		}
		
		/**
		 * Checks the game state after each turn to determine if the current player has won or if the game ends in a draw.
		 * If the current player wins, the GameState is updated to reflect the winner.
		 * If no winner is found, the isDraw method checks for a draw. Otherwise, the GameState remains as PLAYING.
		 * 
		 * @param thePlayer The player making the move.
		 * @param row The row where the move was made.
		 * @param col The column where the move was made.
		 */	     
		
		public void updateGame(Player thePlayer, int row, int col) {
		    // Check for win after the player's move
		    if (board.hasWon(thePlayer, row, col)) {
		        // Update game state based on the winning player
		        if (thePlayer == Player.Cross) {
		            currentState = GameState.Cross_won;
		        } else {
		            currentState = GameState.Nought_won;
		        }
		    } else if (board.isDraw()) {
		        // Update game state to draw
		        currentState = GameState.Draw;
		    }
		}

		/**
		 * Handles mouse click events on the game board.
		 * If the selected cell is valid and empty, the current player's symbol is added to the cell's content.
		 * The game state is then updated to check for a win or a draw.
		 * If the game ends (win or draw), the board is reset for a new game.
		 * Finally, the canvas is refreshed to display the updated board.
		 *
		 * @param e The MouseEvent triggered by a click.
		 */
	
	public void mouseClicked(MouseEvent e) {          
		int mouseX = e.getX(); // get the X coordinate of where the click event happened         
		int mouseY = e.getY(); // get the Y coordinate of where the click event happened                       
		int rowSelected = mouseY / CELL_SIZE; // Get the row clicked        
		int colSelected = mouseX / CELL_SIZE; // Get the column clicked          			
		
		if (currentState == GameState.Playing) { // Check if the game is still in progress          
			if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS &&
				board.cells[rowSelected][colSelected].content == Player.Empty) { // Validate the clicked cell 
				board.cells[rowSelected][colSelected].content = currentPlayer; // Place the player's symbol               
				updateGame(currentPlayer, rowSelected, colSelected); // Update the game state
				// Switch the current player
				if (currentPlayer == Player.Cross) { 
					currentPlayer =  Player.Nought;
				}
				else {
					currentPlayer = Player.Cross;
				}
			}             
		} else {        
			// game over and restart              
			initGame(); // Handle game over       
		}
		// Redraw the graphics on the UI
		repaint();
        }
		
	// Unused mouse listener methods retained for potential future use
	@Override
	public void mousePressed(MouseEvent e) { }
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }	
	@Override
	public void mouseExited(MouseEvent e) { }
}