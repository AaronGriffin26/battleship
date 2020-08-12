package battleship;

import battleship.gameStates.GameState;
import battleship.gameStates.GameStateNames;

/**
 * Contains and manages the two players that are playing the game.
 */
public class Game {
	private static Game main;
	private final Player player1;
	private final Player player2;

	public Game() {
		player1 = new Player();
		player2 = new Player();
	}

	public static Game current() {
		return main;
	}

	public static void main(String[] args) {
		System.out.println("Battleship Multiplayer");
		main = new Game();
		main.run();
	}

	private void run() {
		GameState state = new GameStateNames();
		while(state != null) {
			state = state.evaluate();
		}
	}

	public void setNames(String name1, String name2) {
		player1.setName(name1);
		player2.setName(name2);
	}

	public String[] getPlayerNames() {
		return new String[] { player1.getName(), player2.getName() };
	}

	public Board[] getBoards() {
		return new Board[] { player1.getBoard(), player2.getBoard() };
	}
}