package battleship.gameStates;

import battleship.Board;
import battleship.Game;
import battleship.OccupiedException;
import battleship.ships.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Game state that lets the players place their ships wherever they want.
 */
public class GameStatePlacement extends GameState {
	@Override
	public GameState evaluate() {
		Board[] boards = Game.current().getBoards();
		String[] playerNames = Game.current().getPlayerNames();
		System.out.println(playerNames[1] + ", please look away from the screen. " + playerNames[0] + ", type 'ready'.");
		waitForEnter();
		setupBoard(boards[0], playerNames[0]);
		System.out.println(playerNames[0] + ", step away from the screen. " + playerNames[1] + ", type 'ready'.");
		waitForEnter();
		setupBoard(boards[1], playerNames[1]);
		return new GameStateBattle(false);
	}

	private void waitForEnter() {
		Scanner in = new Scanner(System.in);
		in.next();
	}

	private void setupBoard(Board board, String playerName) {
		System.out.println(board);
		Ship[] newShips = new Ship[] {
				new ShipCarrier(),
				new ShipBattleship(),
				new ShipDestroyer(),
				new ShipSubmarine(),
				new ShipPatrolBoat()
		};
		System.out.println(playerName + ", please enter the coordinates for your ships.");
		for(Ship s : newShips) {
			boolean success = false;
			System.out.print("Enter the coordinates for the " + s.getName() + ": ");
			while(!success) {
				s.setCoordinates(askCoordinates());
				System.out.print("Place horizontally or vertically (h or v)? ");
				if(askVertical())
					s.setToVertical();
				else
					s.setToHorizontal();
				try {
					board.placeShip(s);
					success = true;
				}
				catch(ArrayIndexOutOfBoundsException ignored) {
					System.out.print("Ship is outside the board, enter another location: ");
				}
				catch(OccupiedException e) {
					System.out.print(e.getMessage() + ", enter another location: ");
				}
			}
			System.out.println(board);
		}
		System.out.println("Type 'ready' when done viewing.");
		waitForEnter();
		clearScreen();
	}

	private boolean askVertical() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String input = scanner.nextLine();
			if(input.startsWith("v"))
				return true;
			if(input.startsWith("h"))
				return false;
			System.out.print("Please enter h or v: ");
		}
	}

	private void clearScreen() {
		try {
			final String os = System.getProperty("os.name");
			if(os.contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}
		catch(InterruptedException | IOException ignored) {
		}
	}
}
