package cs361.battleships.models;

// Battleship Details
// All ships extending the Ship class which have the ships
// name, length, health, and an indicator on if it is dead.
public class ShipBattleship extends Ship {

    public ShipBattleship() {
        super();                // extends ship
        sName = "BATTLESHIP";   // Ship name
        sLength = 4;            // Ship Length
        sHealth = sLength;      // Ship health which starts out equal to the ships length. 0 = sunk
        sDead = false;          // Ships status: false = afloat, true = sunk
    }
}