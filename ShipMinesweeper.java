package cs361.battleships.models;

// Minesweeper Details
// All ships extending the Ship class which have the ships
// name, length, health, and an indicator on if it is dead.
public class ShipMinesweeper extends Ship {

    public ShipMinesweeper() {
        super();                // extends ship
        sName = "MINESWEEPER";  // Ship name
        sLength = 2;            // Ship Length
        sHealth = sLength;      // Ship health which starts out equal to the ships length. 0 = sunk
        sDead = false;          // Ships status: false = afloat, true = sunk
    }
}