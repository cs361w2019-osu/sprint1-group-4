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
		for(i=1; i <= 10; i++)
		{
			for (j=65; j <= 74; j++)
			{
				s = new Square(i, (char)j);
				field.add(s);
			}
		}
		*/
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
        //if getloctaion is the same as x and y then we hit?
		/*Occupied - F    Attack - T, The attack position is not occupied
		MISS,



		//Occupied - T    Attack - T, The attack position is occupied
		HIT,

		//All of the hit match the occupation of one ship
		SUNK,

		//All of the place the ship occupied is fill with hit
		SURRENDER,

		//Attack coordinate is out of array bound (input validation to enter again?)
		INVALID,

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
		//TODO implement
	}

	public List<Result> getAttacks()
	{

		//TODO implement
		//List of type of attack
		//return attack coordinate?
		return null;
	}

	public void setAttacks(List<Result> attacks)
	{
		//setting the result of attacks
		//this.attack = attacks
		//TODO implement
	}
}
