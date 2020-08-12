package battleship;

/**
 * Exception thrown when attempting to place a ship where another one is location.
 */
public class OccupiedException extends Exception {
	public OccupiedException(String message) {
		super(message);
	}
}
