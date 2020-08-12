package battleship.ships;

public class ShipBattleship extends Ship {
	@Override
	public int getSpaceCount() {
		return 4;
	}

	@Override
	public String getName() {
		return "Battleship";
	}
}
