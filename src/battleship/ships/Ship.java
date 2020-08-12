package battleship.ships;

import java.awt.*;

/**
 * Base class for all the set pieces in the game.
 */
public abstract class Ship {
	private Point coordinates = new Point(0, 0);
	private boolean vertical;
	private boolean isSunk;

	public abstract int getSpaceCount();

	public Point getCoordinates() {
		return new Point(coordinates);
	}

	public void setCoordinates(Point newCoordinates) {
		coordinates = new Point(newCoordinates);
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setToHorizontal() {
		vertical = false;
	}

	public void setToVertical() {
		vertical = true;
	}

	public void sink() {
		isSunk = true;
	}

	public boolean isSunk() {
		return isSunk;
	}

	public char toChar() {
		return Character.toLowerCase(getName().charAt(0));
	}

	public abstract String getName();
}
