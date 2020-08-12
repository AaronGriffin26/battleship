package battleship.gameStates;

import battleship.Game;

import java.util.Scanner;

/**
 * Game state that asks the players for their names.
 */
public class GameStateNames extends GameState {
	@Override
	public GameState evaluate() {
		var scanner = new Scanner(System.in);
		System.out.print("Enter Player 1 name: ");
		String name1 = scanner.nextLine();
		System.out.print("Enter Player 2 name: ");
		String name2 = scanner.nextLine();
		Game.current().setNames(name1, name2);
		return new GameStatePlacement();
	}
}
