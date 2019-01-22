package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship {

	// ------------------------------------------------------
	// GLOBAL VARIABLES -------------------------------------
	// Used to define and change the status of a Ship
	List<Square> sDamage;   // Used to set the coordinates of a ships damage
	String sName;           // Used to store the name of a ship
	Boolean sDead;          // Used to set a ships life status
	int sHealth, sLength;   // Used to set a ships length and health

	// Space that ship occupies
	@JsonProperty private List<Square> occupiedSquares;

	public Ship() {
		occupiedSquares = new ArrayList<Square>();
	}

	// Setting up Ship class
	public Ship(String kind) {
		sName = kind;                           // Setting the ship name variable
		sDamage = new ArrayList<Square>();      // Spaces that the ship has taken damage
		sLength = 0;                            // Int used to indicate the ships length
		sHealth = 0;                            // Int used to indicate the ships health
		sDead = false;                          // Bool used to indicate the ships status
	}

	// ------------------------------------------------------
	// SETTERS AND GETTERS FOR SHIPS ------------------------
	// Setter/Getter for occupied squares variable ----------
	public List<Square> getOccupiedSquares() {
		return occupiedSquares;
	}
	public void setOccupiedSquares(List<Square> sCoordinates) {
		occupiedSquares = sCoordinates;
	}

	// Setter/Getter for sDamage variable -------------------
	// for damage coordinates on a ship and other functions
	public List<Square> getsDamage() {
		return sDamage;
	}
	public void setsDamage(List<Square> damagedSpace) {
		sDamage = damagedSpace;
	}
	public void addsDamage(Square damage) {
		if(sDamage == null) {
			sDamage = new ArrayList<Square>();
		}
		sDamage.add(damage);
	}
	public boolean alreadysDamage(Square coord1) {
		if (sDamage != null) {
			for (Square coord2 : sDamage) {
				if (coord2.getRow() == coord1.getRow() && coord2.getColumn() == coord1.getColumn()) {
					return true;
				}
			}
		}
		return false;
	}

	// Getter for ships length ----------
	public int getsLength() {
		return sHealth;
	}
	// Setter for ships health ----------
	public void setsHealth(int health) {
		sHealth = health;
	}
	// Decreases ships health ----------
	public void shipDamage() {
		sHealth = sHealth - 1;
	}

	// Setter/Getter for ships name ----------
	public String getsName() {
		return sName;
	}
	public void setsName(String name) {
		sName = name;
	}

	// Setter/Getter for ships status ----------
	public Boolean getsDead() {
		return sDead;
	}
	public void setsDead(Boolean death) {
		sDead = death;
	}

	// Setter/Getter for ships status ----------
	public void attackSpace(int x, int y) {
		findCoor(x, y, occupiedSquares.size());
		if(sDamage != null) {
			findCoor(x, y, sDamage.size());
		}
	}
	public void findCoor(int xCoor, int yCoor, int fLoop) {
		for(int i = 0; i < fLoop; i++) {
			Square sq = occupiedSquares.get(i);
			sq.setColumn((char) ((int) sq.getColumn() + xCoor));
			sq.setRow(sq.getRow() + yCoor);
		}
	}

	public boolean occupiedCoordinate(int x, char y) {
		for (Square i: occupiedSquares) {
			if(i.getRow() == x && i.getRow() == y) {
				return true;
			}
		}
		return false;
	}

	public static boolean duplicateShip(Ship ship, List<Ship> ships) {
		for (Ship i : ships) {
			if (i.getsName().equals(ship.getsName())) {
				return true;
			}
		}
		return false;
	}

}
