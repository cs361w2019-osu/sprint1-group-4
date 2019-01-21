package cs361.battleships.models;

import org.hibernate.loader.custom.sql.SQLQueryReturnProcessor;

import java.util.ArrayList;
import java.util.List;

public class Board
{
    private List<Square> field;
	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board()
	{
		// TODO Implement
		field = new ArrayList<>(100);

		//WARNING: PSEUDO CODE, WILL NOT WORK
		/*zfor(int i=1; i < 10; i++)
		{
			for (int j='A'; j < 'J'; i++)
			{
				field.add(square(i, j));
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
		//TODO Implement
        //if getloctaion is the same as x and y then we hit?
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
		return null;
	}

	public void setAttacks(List<Result> attacks)
	{
		//TODO implement
	}
}
