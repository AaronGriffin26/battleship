package battleship;

/**
 * Pairs a player's name with a board.
 */
public class Player {
	private String name;
	private Board board;

	public Player() {
		board = new Board();
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public Board getBoard() {
		return board;
	}
}
