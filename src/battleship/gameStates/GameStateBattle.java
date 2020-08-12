package battleship.gameStates;

import battleship.Board;
import battleship.Game;

import java.awt.*;

/**
 * Game state used during gameplay.
 */
public class GameStateBattle extends GameState {
	private boolean player2IsGoing;

	public GameStateBattle(boolean player2IsGoing) {
		this.player2IsGoing = player2IsGoing;
	}

	@Override
	public GameState evaluate() {
		System.out.println("-".repeat(40));
		Board board = Game.current().getBoards()[player2IsGoing ? 0 : 1];
		board.hideShips();
		System.out.println(board);
		String playerName = Game.current().getPlayerNames()[player2IsGoing ? 1 : 0];
		System.out.print(playerName + " enter the coordinates for an attack: ");
		boolean wasStruck;
		Point point;
		do {
			point = askCoordinates();
			wasStruck = board.pointWasStruck(point);
			if(wasStruck)
				System.out.print("Already struck there, try new coordinates: ");
		} while(wasStruck);
		board.strike(point);
		System.out.println(board);
		if(board.isFinished())
			return new GameStateFinale(player2IsGoing);
		else
			return new GameStateBattle(!player2IsGoing);
	}
}
