package cs361.battleships.models;

public enum AtackStatus {

	/**
	 * The result if an attack results in a miss.
	 */

	//Occupied - F    Attack - T, The attack position is not occupied
	MISS,

	/**
	 * The result if an attack results in a hit on an enemy ship.
	 */

	//Occupied - T    Attack - T, The attack position is occupied
	HIT,

	/**
	 * THe result if an attack sinks the enemy ship
	 */

	//All of the hit match the occupied of one ship
	SUNK,

	/**
	 * The results if an attack results in the defeat of the opponent (a
	 * surrender).
	 */

	//All of the place the ship occupied is fill with hit
	SURRENDER,
	
	/**
	 * The result if the coordinates given are invalid.
	 */

	//Display error and continue
	INVALID,

}
