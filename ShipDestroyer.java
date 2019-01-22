
package cs361.battleships.models;

// Destroyer Details
// All ships extending the Ship class which have the ships
// name, length, health, and an indicator on if it is dead.
public class ShipDestroyer extends Ship {

    public ShipDestroyer() {
        super();                // extends ship
        sName = "DESTROYER";    // Ship name
        sLength = 3;            // Ship Length
        sHealth = sLength;      // Ship health which starts out equal to the ships length. 0 = sunk
        sDead = false;          // Ships status: false = afloat, true = sunk
    }
}