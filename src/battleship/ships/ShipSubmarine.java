package battleship.ships;

public class ShipSubmarine extends Ship {
	@Override
	public int getSpaceCount() {
		return 3;
	}

	@Override
	public String getName() {
		return "Submarine";
	}
}
