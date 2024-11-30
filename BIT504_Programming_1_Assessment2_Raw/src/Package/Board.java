/**
 * Course: BIT504
 * Student Name: Adam Donson
 * Student Number: 5114991
 * Assessment: BIT504 Programming 1 Assessment 2
 */

package Package;

import java.awt.*;

public class Board {
	// 2D array to represent the game board. Each element is an instance of the Cell class.
    // The size of the array is determined by ROWS and COLS constants defined in the GameMain class.
	Cell [][] cells;

	/** Constructor to create the game board */
	public Board() {
		// Initialize the 2D array with the specified number of rows and columns.
		cells = new Cell[GameMain.ROWS][GameMain.COLS];
		
		// Initialize each cell in the board with a new Cell instance
        // Each cell is initialized with its row and column index to keep track of its position
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}
	
	/** Return true if it is a draw (i.e., no more EMPTY cells) */
	public boolean isDraw() {
		// Loop through each cell in the board to check if any cell is empty.
        // If an empty cell is found, return false because the game is not over.
	    for (int row = 0; row < GameMain.ROWS; row++) {
	        for (int col = 0; col < GameMain.COLS; col++) {
	            // If any cell is empty, it is not a draw
	            if (cells[row][col].content == Player.Empty) {
	                return false; // // Found an empty cell, so it's not a draw.
	            }
	        }
	    }
	    // If no empty cells are found, the board is full, so it's a draw.
	    return true;
	}
		   
	/** Return true if the current player "thePlayer" has won after making their move */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		// Check if the player has 3 of their pieces in the same row.
		if(cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer )
			return true; // Player wins by completing a row.
		
		// Check if the player has 3 of their pieces in the same column. 
	    if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer && cells[2][playerCol].content == thePlayer) {
	        return true;
	    }
		
	    // Check if the player has 3 of their pieces in the main diagonal (top-left to bottom-right).
		if( cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer)
			return true; // // Player wins by completing the main diagonal.
		 
		// Check if the player has 3 of their pieces in the anti-diagonal (top-right to bottom-left).
		if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer)
			return true; // // Player wins by completing the anti-diagonal.

		// No winner found, game continues.
		return false;
	}
	
	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		// Set color for drawing the grid lines
	    g.setColor(Color.GRAY);

	    // Draw horizontal grid lines
	    for (int row = 1; row < GameMain.ROWS; ++row) {
	    	// Calculate the vertical position of each horizontal line.
	        int y = GameMain.CELL_SIZE * row - GameMain.GRID_WIDTH_HALF; // Center line vertically
	        g.fillRoundRect(0, y, GameMain.CANVAS_WIDTH, GameMain.GRID_WIDTH, GameMain.GRID_WIDTH, GameMain.GRID_WIDTH);
	    }

	    // Draw vertical grid lines
	    for (int col = 1; col < GameMain.COLS; ++col) {
	    	// Calculate the horizontal position of each vertical line.
	        int x = GameMain.CELL_SIZE * col - GameMain.GRID_WIDTH_HALF; // Center line horizontally
	        g.fillRoundRect(x, 0, GameMain.GRID_WIDTH, GameMain.CANVAS_HEIGHT, GameMain.GRID_WIDTH, GameMain.GRID_WIDTH);
	    }

	    // Draw the individual cells in the grid
	    for (int row = 0; row < GameMain.ROWS; ++row) {
	        for (int col = 0; col < GameMain.COLS; ++col) {
	        	// Each cell is responsible for drawing itself
	            cells[row][col].paint(g);
	        }
	    }
	}
}