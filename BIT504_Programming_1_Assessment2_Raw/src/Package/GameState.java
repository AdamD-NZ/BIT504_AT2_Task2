/**
 * Course: BIT504
 * Student Name: Adam Donson
 * Student Number: 5114991
 * Assessment: BIT504 Programming 1 Assessment 2
 */

package Package;

/**
 * Enumeration for the current state of the game.
 * 
 * This enum represents the possible states of the game at any given time.
 * It is used to track the progress of the game and determine the game's outcome.
 * The enum defines the following states:
 * 
 * - Draw: The game ends in a draw, meaning no player has won and all cells are filled.
 * - Cross_won: The first player (Cross) wins the game.
 * - Nought_won: The second player (Nought) wins the game.
 * - Playing: The game is still ongoing, with players continuing to make moves.
 */

public enum GameState {
	/**
     * Represents a game that ends in a draw.
     * This state is set when all cells are filled and neither player has won.
     */
	
	Draw,
	
	/**
     * Represents the state when the first player (Cross) wins the game.
     * This state is set when player "Cross" has completed a row, column, or diagonal with their symbol.
     */
	Cross_won,
	
	/**
     * Represents the state when the second player (Nought) wins the game.
     * This state is set when player "Nought" has completed a row, column, or diagonal with their symbol.
     */
	Nought_won,
	
	/**
     * Represents the state when the game is still ongoing.
     * This state is set when players are still making moves and the game has not ended.
     */
	Playing
}