package battleship.gameStates;

import battleship.Board;
import battleship.Game;

/**
 * Reveals the winner's board.
 */
public class GameStateFinale extends GameState {
	private boolean winnerIsPlayer2;

	public GameStateFinale(boolean winnerIsPlayer2) {
		this.winnerIsPlayer2 = winnerIsPlayer2;
	}

	@Override
	public GameState evaluate() {
		String playerName = Game.current().getPlayerNames()[winnerIsPlayer2 ? 1 : 0];
		System.out.println(playerName + " won the battle!\n" + playerName + "'s board:");
		Board otherBoard = Game.current().getBoards()[winnerIsPlayer2 ? 1 : 0];
		otherBoard.revealShips();
		System.out.println(otherBoard);
		return null;
	}
}
