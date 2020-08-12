package battleship.ships;

public class ShipCarrier extends Ship {
	@Override
	public int getSpaceCount() {
		return 5;
	}

	@Override
	public String getName() {
		return "Carrier";
	}
}
