package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import com.mchange.v1.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Ship {

    @JsonProperty private String kind;
    @JsonProperty private List<Square> occupiedSquares;
    @JsonProperty private List<Square> occupiedSubmergedSquares;
    @JsonProperty private int size;
    @JsonProperty private boolean is_sub = false; //Checks if sub.

    public Ship() {
        occupiedSquares = new ArrayList<>();
    }

    public Ship(String kind) {
        this();
        this.kind = kind;
        switch(kind) {
            case "MINESWEEPER":
                size = 2;
                break;
            case "DESTROYER":
                size = 3;
                break;
            case "BATTLESHIP":
                size = 4;
                break;
		    case "SUBMARINE":
                size = 4;
				is_sub = true;
                break;
        }
    }

    public List<Square> getOccupiedSquares() {
        return occupiedSquares;
    }

    public void place(char col, int row, boolean isVertical) {

        for (int i=0; i<size; i++) {
            if (isVertical) {
                occupiedSquares.add(new Square(row+i, col));
            } else {
                occupiedSquares.add(new Square(row, (char) (col + i)));
            }
        }
		if(is_sub){
			if (isVertical) {
                occupiedSquares.add(new Square(row + 1, (char) (col + 1)));
            } else {
                occupiedSquares.add(new Square(row - 1, (char) (col + 1)));
            }
		}//end of is sub
    }

    public boolean overlaps(Ship other) {
        Set<Square> thisSquares = Set.copyOf(getOccupiedSquares());
        Set<Square> otherSquares = Set.copyOf(other.getOccupiedSquares());
        Sets.SetView<Square> intersection = Sets.intersection(thisSquares, otherSquares);
        return intersection.size() != 0;
    }

    public boolean isAtLocation(Square location) {
        return getOccupiedSquares().stream().anyMatch(s -> s.equals(location));
    }

    public String getKind() {
        return kind;
    }

    //uses a lot of the same code for the attack function and for good reason
    //they are very similar
    public Result sonarHit(int x, char y)
    {
        var attackedLocation = new Square(x, y);
        //gets the square the sonar hit
        var square = getOccupiedSquares().stream().filter(s -> s.equals(attackedLocation)).findFirst();
        if(!square.isPresent()) //if it isn't in the list just return a miss
        {
            return new Result(attackedLocation);
        }
        //otherwise return a hit
        var attackedSquare = square.get();
        Result result = new Result(attackedSquare);
        result.setResult(AtackStatus.HIT);
        return result;
    }

    public Result attack(int x, char y) {
        var attackedLocation = new Square(x, y);
        var square = getOccupiedSquares().stream().filter(s -> s.equals(attackedLocation)).findFirst();
        if (!square.isPresent()) {
            return new Result(attackedLocation);
        }
        var attackedSquare = square.get();
        if (attackedSquare.isHit()) {
            var result = new Result(attackedLocation);
            result.setResult(AtackStatus.INVALID);
            return result;
        }
        attackedSquare.hit();
        var result = new Result(attackedLocation);
        result.setShip(this);
        if (isSunk()) {
            result.setResult(AtackStatus.SUNK);
        } else {
            result.setResult(AtackStatus.HIT);
        }
        return result;
    }

    @JsonIgnore
    public boolean isSunk() {
        return getOccupiedSquares().stream().allMatch(s -> s.isHit());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Ship)) {
            return false;
        }
        var otherShip = (Ship) other;

        return this.kind.equals(otherShip.kind)
                && this.size == otherShip.size
                && this.occupiedSquares.equals(otherShip.occupiedSquares);
    }

    @Override
    public int hashCode() {
        return 33 * kind.hashCode() + 23 * size + 17 * occupiedSquares.hashCode();
    }

    @Override
    public String toString() {
        return kind + occupiedSquares.toString();
    }
}