package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

	@JsonProperty private List<Ship> ships;
	@JsonProperty private List<Result> attacks;
	@JsonProperty private  List<Result> sonars;
	/*Counter RoundCount = new Counter("Round", 100);
	Counter BoatCount = new Counter("Boat", 3);

	public void IncCounter() {

		System.out.printf("Current round: " + RoundCount.value() + "\n");
		int NewRound = RoundCount.value();
		NewRound++;
		RoundCount.setCount(NewRound);


		System.out.printf("Boat round: " + BoatCount.value() + "\n");
		BoatCount.increment();

	}*/
	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
		ships = new ArrayList<>();
		attacks = new ArrayList<>();
		sonars = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
		if (ships.size() >= 3) {
			return false;
		}
		if (ships.stream().anyMatch(s -> s.getKind().equals(ship.getKind()))) {
			return false;
		}
		final var placedShip = new Ship(ship.getKind());
		placedShip.place(y, x, isVertical);
		if (ships.stream().anyMatch(s -> s.overlaps(placedShip))) {
			return false;
		}
		if (placedShip.getOccupiedSquares().stream().anyMatch(s -> s.isOutOfBounds())) {
			return false;
		}
		ships.add(placedShip);
		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
		int amor = 1;
		boolean captain = false;
		boolean amored = false;
		Result attackResult = attack(new Square(x, y,captain,amor, amored));
		attacks.add(attackResult);
		return attackResult;
	}

	private Result attack(Square s) {
		//IncCounter();
		if (attacks.stream().anyMatch(r -> r.getLocation().equals(s))) {
			var attackResult = new Result(s);
			attackResult.setResult(AtackStatus.INVALID);
			return attackResult;
		}
    /*
		elseif (attackResult == captain quarter)
		{
		if attackResult == captain quarter && kind == MINESWEEP 
		attackResult.setResult(AttackStatus.SUNK);

		deductAmored();
		}

		 */
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(s)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) {
			var attackResult = new Result(s);
			return attackResult;
		}
		var hitShip = shipsAtLocation.get(0);
		var attackResult = hitShip.attack(s.getRow(), s.getColumn());
		if (attackResult.getResult() == AtackStatus.SUNK) {
			if (ships.stream().allMatch(ship -> ship.isSunk())) {
				attackResult.setResult(AtackStatus.SURRENDER);
			}
		}
		return attackResult;
	}

	//this will be called in the Game.java file when a user clicks to attack
	public Result sonar(int x, char y)
	{
		Square s = new Square(x, y);
		Result sonarResult = sonar(s);

		if(!s.isOutOfBounds()) //if the square being messed with is within the boundaries, do this
		{
			sonars.add(sonarResult);
		}
		return sonarResult;
	}

	//this is the actual logic of the sonar attack
	private Result sonar(Square s)
	{
		//find the ships at the squares location
		var shipsAtLocation = ships.stream().filter(ship -> ship.isAtLocation(s)).collect(Collectors.toList());
		if (shipsAtLocation.size() == 0) //if you don't find a ship
		{
			var sonarResult = new Result(s);
			return sonarResult;
		}
		//otherwise do this
		var hitShip = shipsAtLocation.get(0);
		var sonarResult = hitShip.sonarHit(s.getRow(), s.getColumn()); //call the sonarHit function on the ship
		return sonarResult;
	}

	List<Ship> getShips() {
		return ships;
	}
}