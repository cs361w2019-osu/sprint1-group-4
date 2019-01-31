package cs361.battleships.models;

public class Result {
	private Square s = new Square();
	private AtackStatus res;
	private Ship newship = new Ship();
	private int shipCount;

	public AtackStatus getResult() {
		return this.res;
	}

	public void setResult(AtackStatus result) {
		this.res = result;
	}

	public Ship getShip() {
		return this.newship;
	}

	public void setShip(Ship ship) {
		//TODO implement
		shipCount++;
		this.newship = ship;
	}

	public Square getLocation()
	{
		return this.s;
	}

	public void setLocation(Square square) {
		this.s = square;
	}

	public int getShipCount()
	{
		return shipCount;
	}
}