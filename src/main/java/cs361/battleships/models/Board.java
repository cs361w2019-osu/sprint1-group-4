package cs361.battleships.models;

import org.hibernate.loader.custom.sql.SQLQueryReturnProcessor;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    private Square s;
    private List<Square> field;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */

	//Board constructor
	public Board()
	{
		// TODO Implement
		/*
		field = new ArrayList<>(100);

		//WARNING: PSEUDO CODE, WILL NOT WORK
		/*for(int i=1; i <= 10; i++)
		{
			for (int j='A'; j <= 'J'; j++)
			{
				s = new Square(i, (char)j);
				field.add(s);
			}
		}*/
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical)
	{
		// TODO Implement
        //check if placement is within limits of the boundaries?
        //if ship.getOccupiedSquares() is within limits of the field then return true
        //offset the occupied squares by x and y
		//if overlaping another ship return false
        //if isVertical is true then change offset
        //else return false
        return false;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y)
	{


		AtackStatus a1 = AtackStatus.MISS;
		System.out.println(a1);


		//TODO Implement
        //if occupied location is not same as attack
		/*Occupied - F    Attack - T, The attack position is not occupied
		MISS,



		//Occupied - T    Attack - T, The attack position is occupied
		HIT,
		Find the ship being attack by coordinate, identify it name
		Deduct the ship health by 1
		FUNCTION BEING USE:
		getsName
		shipDamage
		getsHealth
		set the square value



		//All of the hit match the occupation of one ship
		SUNK,
		Check the health of the 3 ship, if one of the health fall to 0 then it sunk
		FUNCTION BEING USE:
		getsName (of the sunk ship)
		shipDamage
		getsHealth



		//All of the place the ship occupied is fill with hit
		SURRENDER,
		Player or opponent overall health deduct to 0 then it sunk



		//Attack coordinate is out of array bound (input validation to enter again?)
		INVALID,
		Check if coordinate is out of bound, then player have to enter again
		Ex: 0 < x < sizeOfBoard and
		    0 < y < sizeOfBoard

		*/


		return null;
	}

	public List<Ship> getShips()
	{
		//TODO implement
        //returns coordinates?
		return null;
	}

	public void setShips(List<Ship> ships)
	{

		//Still not figure out what is this function do!
		//TODO implement
	}

	public List<Result> getAttacks()
	{

		//TODO implement
		//List of type of attack
		//return attack coordinate?

		//return attacks
		return null;
	}

	public void setAttacks(List<Result> attacks)
	{


		//setting the result of attacks: HIT, MISS, SURRENDER, INVALID, SUNK
		//this.attack = attacks
		//TODO implement
	}
}
