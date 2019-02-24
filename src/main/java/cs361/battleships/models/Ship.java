package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Ship {

    String sName;
    int sHealth;

    @JsonProperty private List<Square> occupiedSquares;
    @JsonProperty private String kind;
    @JsonProperty private int sLength;

    public Ship() {
        this.occupiedSquares = new ArrayList<>();
    }

    public Ship(String kind) {
        this();
        //this.sLength = sLength;
        this.kind = kind;
        switch(kind) {
            case "MINESWEEPER":
                sLength = 2;
                sHealth = 2;
                break;
            case "DESTROYER":
                sLength = 3;
                sHealth = 3;
                break;
            case "BATTLESHIP":
                sLength = 4;
                sHealth = 4;
                break;
        }
        /*
        if (kind.equals("MINESWEEPER")) {
            sName = "MINESWEEPER";
            sLength = 2;
            sHealth = 2;
        } else if (kind.equals("BATTLESHIP")) {
            sName = "BATTLESHIP";
            sLength = 4;
            sHealth = 4;
        } else if (kind.equals("DESTROYER")) {
            sName = "DESTROYER";
            sLength = 3;
            sHealth = 3;
        }*/
        //this.occupiedSquares = new ArrayList<>();
    }


    public List<Square> getOccupiedSquares() {
        return this.occupiedSquares;
    }

    //old method for getting occupied squares
    /*public void setOccupiedSquares(Square nums) {
        this.occupiedSquares.add(nums);
    }*/

    public void setsHealth() {
        sHealth--;
    }

    public int getsHealth() {
        return sHealth;
    }

    public String getKind() {
        return kind;
    }

    public int getSize() {
        return sLength;
    }

    public String getsName() {
        return sName;
    }

    //new attempt to set up occupied spaces
    public void setOccupiedSquares(int x, char y, boolean isVertical) {
        for(int i = 0; i < this.sLength; i++)
        {
            if(isVertical)
            {
                this.occupiedSquares.add(new Square(x+i, y));
            }
            else
            {
                this.occupiedSquares.add(new Square(x, (char)((int)y+i)));
            }
        }
    }

    //@Override
    public boolean isSameShip(Object otherShip) {
        var thisShip = (Ship) otherShip;
        if (!(otherShip instanceof Ship)) {
            return false;
        } else if ((this.kind.equals(thisShip.kind)) && (this.sLength == thisShip.sLength) && (this.occupiedSquares.equals(thisShip.occupiedSquares))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOverlaping(Ship otherShip) {
        Set<Square> thisSquares = Set.copyOf(getOccupiedSquares());
        Set<Square> otherSquares = Set.copyOf(otherShip.getOccupiedSquares());
        Sets.SetView<Square> intersection = Sets.intersection(thisSquares, otherSquares);
        return intersection.size() != 0;
    }

}
