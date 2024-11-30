/**
 * Course: BIT504
 * Student Name: Adam Donson
 * Student Number: 5114991
 * Assessment: BIT504 Programming 1 Assessment 2
 */

package Package;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Cell {
	// Properties to represent the position of the cell on the board.
    int row, col;
    // The content of the cell, which can be Empty, Cross, or Nought.
    Player content;

    /** Constructor to initialize a cell at a specific position with an initial content */
    public Cell(int row, int col) {
        this.row = row; // Assign the row index to this cell.
        this.col = col; // Assign the column index to this cell.
        this.content = Player.Empty; // Initially, the cell is empty.
    }	

    /** Paints the content of the cell on the graphics canvas */ 
	public void paint(Graphics g) {
		// Cast the Graphics object to Graphics2D to allow setting the stroke (pen thickness).
		Graphics2D graphic2D = (Graphics2D) g;
		
		// Set the stroke width for drawing symbols (e.g., X or O).
		graphic2D.setStroke(new BasicStroke(GameMain.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		// Calculate the x and y positions where the symbol should be drawn inside the cell.
		int x1 = col * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
		int y1 = row * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
		
		// If the content is a Cross, draw an X symbol in the cell.
		if (content == Player.Cross) {
			graphic2D.setColor(Color.RED); // Set the color for the cross (red).
			int x2 = (col + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING; // Calculate the end x position for the cross.
			int y2 = (row + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING; // Calculate the end y position for the cross.
			
			// Draw the two diagonal lines to form the cross.
			graphic2D.drawLine(x1, y1, x2, y2);
			graphic2D.drawLine(x2, y1, x1, y2);
			
		// If the content is a Nought, draw an O symbol in the cell.	
		} else if (content == Player.Nought) {
			graphic2D.setColor(Color.BLUE); // Set the color for the nought (blue).
			// Draw the oval (circle) for the nought symbol at the calculated position.
			graphic2D.drawOval(x1, y1, GameMain.SYMBOL_SIZE, GameMain.SYMBOL_SIZE);
		}
	}
	
	/** Clears the content of this cell and sets it back to EMPTY */
	public void clear() {
		// Set the content of the cell to Empty, indicating the cell is cleared.
		this.content = Player.Empty;	
	}		
}