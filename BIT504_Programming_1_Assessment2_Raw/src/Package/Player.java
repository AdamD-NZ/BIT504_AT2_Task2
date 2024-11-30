/**
 * Course: BIT504
 * Student Name: Adam Donson
 * Student Number: 5114991
 * Assessment: BIT504 Programming 1 Assessment 2
 */

package Package;

/**
 * Enumeration for the players' move.
 * 
 * This enum is used to represent the possible states of a cell in the game board.
 * It defines three possible values for a cell:
 * - Empty: Represents an empty cell that has not been filled yet.
 * - Cross: Represents a cell that has been filled with a "X" symbol.
 * - Nought: Represents a cell that has been filled with a "O" symbol.
 * 
 * The enum helps to track the state of each cell and determine the game progress (whether a player has won, or if the game is a draw).
 */

public enum Player {
	/**
     * Represents an empty cell on the board.
     * Initially, all cells are empty before any player makes a move.
     */
	Empty,
	
	/**
     * Represents a move made by the first player (usually 'X').
     * This value is used to indicate that a cell has been filled with a "X" symbol.
     */
	
	Cross,
	
	/**
     * Represents a move made by the second player (usually 'O').
     * This value is used to indicate that a cell has been filled with an "O" symbol.
     */
	Nought
}