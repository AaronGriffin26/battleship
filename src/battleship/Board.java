package battleship;

import battleship.ships.Ship;

import java.awt.*;
import java.util.ArrayList;

/**
 * Contains an arrangement of ships and hit markers.
 */
public class Board {
	//* (remove first / if cannot print color)
	public static final String HIT_COLOR = "\033[1;91m";
	public static final String MISS_COLOR = "\033[0;96m";
	public static final String NORMAL_COLOR = "\033[0m";
	public static final String SHIP_COLOR = "\033[1;93m";
	public static final String WATER_COLOR = "\033[0;34m";
	/*/
	public static final String HIT_COLOR = "";
	public static final String MISS_COLOR = "";
	public static final String NORMAL_COLOR = "";
	public static final String SHIP_COLOR = "";
	public static final String WATER_COLOR = "";
	//*/

	private ArrayList<Ship> ships = new ArrayList<>();
	private boolean[][] strikepoints = new boolean[10][10];
	private boolean revealShips = true;
	private boolean finished = false;

	public void placeShip(Ship ship) throws OccupiedException {
		Point point = new Point(ship.getCoordinates());
		for(int i = 0; i < ship.getSpaceCount(); i++) {
			Ship blockingShip = getShipAt(point);
			if(blockingShip != null)
				throw new OccupiedException(blockingShip.getName() + " is already located there");
			if(ship.isVertical())
				point.y++;
			else
				point.x++;
		}
		ships.add(ship);
	}

	private Ship getShipAt(Point point) {
		if(point.x < 1 || point.x > strikepoints.length || point.y < 1 || point.y > strikepoints[0].length)
			throw new ArrayIndexOutOfBoundsException("Point is outside the board");
		for(Ship s : ships) {
			Point coords = s.getCoordinates();
			int range = s.getSpaceCount() - 1;
			if(s.isVertical()) {
				if(coords.x == point.x && coords.y <= point.y && coords.y + range >= point.y)
					return s;
			}
			else if(coords.y == point.y && coords.x <= point.x && coords.x + range >= point.x)
				return s;
		}
		return null;
	}

	public boolean pointWasStruck(Point point) {
		return strikepoints[point.x - 1][point.y - 1];
	}

	public void strike(Point point) {
		strikepoints[point.x - 1][point.y - 1] = true;
		if(shipPresentAt(point)) {
			System.out.println("It's a hit!");
			refreshShipStates();
		}
		else
			System.out.println("You missed!");
	}

	public boolean shipPresentAt(Point point) {
		return (getShipAt(point) != null);
	}

	public void refreshShipStates() {
		finished = true;
		for(Ship s : ships) {
			if(!s.isSunk()) {
				refreshShipState(s);
				finished &= s.isSunk();
			}
		}
	}

	private void refreshShipState(Ship ship) {
		Point point = ship.getCoordinates();
		boolean willSink = true;
		for(int i = 0; i < ship.getSpaceCount(); i++) {
			if(!strikepoints[point.x - 1][point.y - 1]) {
				willSink = false;
				break;
			}
			if(ship.isVertical())
				point.y++;
			else
				point.x++;
		}
		if(willSink) {
			ship.sink();
			System.out.println("The " + ship.getName() + " was sunk!");
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public void revealShips() {
		revealShips = true;
	}

	public void hideShips() {
		revealShips = false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("   ");
		for(int i = 1; i <= 10; i++) {
			builder.append(i).append(' ');
		}
		for(int y = 1; y <= 10; y++) {
			builder.append(String.format("\n%1$2s ", y));
			for(int x = 1; x <= 10; x++) {
				Ship ship = getShipAt(new Point(x, y));
				if(strikepoints[x - 1][y - 1])
					builder.append(ship != null ? HIT_COLOR + "x" : MISS_COLOR + "m").append(NORMAL_COLOR);
				else if(revealShips && ship != null)
					builder.append(SHIP_COLOR).append(ship.toChar()).append(NORMAL_COLOR);
				else
					builder.append(WATER_COLOR + "~" + NORMAL_COLOR);
				builder.append(' ');
			}
		}
		return builder.toString();
	}
}
