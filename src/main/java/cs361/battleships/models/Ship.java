package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    String sName;
    int sHealth, sLength;

    @JsonProperty
    private List<Square> occupiedSquares;

    public Ship() {
        this.occupiedSquares = new ArrayList<>();
        this.sLength = 0;
    }

    public Ship(String kind) {
        this.sLength = 0;

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
        }
        this.occupiedSquares = new ArrayList<>();
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
}