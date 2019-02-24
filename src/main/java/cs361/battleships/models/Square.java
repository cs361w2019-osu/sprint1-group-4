package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class Square {

	@JsonProperty private int row;
	@JsonProperty private char column;
	@JsonProperty private boolean hit = false;
	@JsonProperty private boolean amored = false;
	@JsonProperty private boolean captain = false;
	public int amorStatus;

	public Square() {
	}

	public Square(int row, char column, boolean captain, int amorStatus, boolean amored) {
		this.row = row;
		this.column = column;
		this.captain = captain;
		this.amorStatus = amorStatus;
		this.amored = amored;
	}

	/*
    public setAmored(){

        if kind = MINESWEEP && isCaptain()
        		amorStatus == 1
        elif kind = BATTLESHIP && isCaptain()
        		amorStatus == 2
        elif kind = DESTROYER && isCaptain()
				armorSatus == 2


	//Since we just need to identify the square of captain, we only need to set the armor of that square

    }
    */

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public int deductAmored(int currentAmored){
		if (currentAmored > 0) {
			currentAmored--;
		}
		return currentAmored;
	}

	public int getAmor(){
		return amorStatus;
	}


	@Override
	public boolean equals(Object other) {
		if (other instanceof Square) {
			return ((Square) other).row == this.row && ((Square) other).column == this.column;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * row + column;
	}

	@JsonIgnore
	public boolean isOutOfBounds() {
		return row > 10 || row < 1 || column > 'J' || column < 'A';
	}

	public boolean isHit() {
		return hit;
	}

	public void hit() {
		hit = true;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ')';
	}
}