package battleship.gameStates;

import battleship.Board;
import battleship.Game;
import battleship.OccupiedException;
import battleship.ships.*;

import java.awt.*;

/**
 * Debug class that randomizes ship positions.
 */
public class GameStateDefaultPlacement extends GameState {
	@Override
	public GameState evaluate() {
		Game.current().setNames("A", "B");
		Board[] boards = Game.current().getBoards();
		setupBoard(boards[0]);
		setupBoard(boards[1]);
		return new GameStateBattle(false);
	}

	private void setupBoard(Board board) {
		Ship[] newShips = new Ship[] {
				new ShipCarrier(),
				new ShipBattleship(),
				new ShipDestroyer(),
				new ShipSubmarine(),
				new ShipPatrolBoat()
		};
		for(Ship s : newShips) {
			boolean success = false;
			while(!success) {
				Point point = new Point((int)(Math.random() * 10 + 1), (int)(Math.random() * 10 + 1));
				s.setCoordinates(point);
				if(Math.random() < 0.5)
					s.setToVertical();
				else
					s.setToHorizontal();
				try {
					board.placeShip(s);
					success = true;
				}
				catch(ArrayIndexOutOfBoundsException | OccupiedException ignored) {
				}
			}
		}
		System.out.println(board);
	}
}
