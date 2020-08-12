package battleship.ships;

public class ShipDestroyer extends Ship {
	@Override
	public int getSpaceCount() {
		return 3;
	}

	@Override
	public String getName() {
		return "Destroyer";
	}
}
