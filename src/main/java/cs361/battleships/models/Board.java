package cs361.battleships.models;

import org.hibernate.loader.custom.sql.SQLQueryReturnProcessor;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	private List<Ship> totalShips;
	private List<Result> totalAtacks;
	private int surr;

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */

	//Board constructor
	public Board()
	{
		this.totalShips = new ArrayList<>();
		this.totalAtacks = new ArrayList<>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	//this function almost works, only able to place one ship in the game though
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical)
	{
		int xoff; //integer to check offset from clickpoint, x-axis
		char yoff; //char to check offset from clickpoint, y-axis

		//if the ship is vertical do this
		if(isVertical)
        {
            xoff = x + ship.getSize();
            yoff = y;
        }
		//if it isn't, do this
		else
        {
            xoff = x;
            yoff = (char)((int)y + ship.getSize());
        }

		//if the ship is within the bounds of the grid add it
		if ((xoff > 0 && xoff < 12) && (yoff >= 'A' && yoff <='K'))
		{
			ship.setOccupiedSquares(x, y, isVertical);
			this.totalShips.add(ship);
			return true;
		}
		//otherwise return false
		else
        {
            return false;
        }
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y)
	{
		//new result object to set the state of a square
		Result r = new Result();
		//setup attack square to compare things against
		Square s = new Square(x, y);
		r.setLocation(s);

		//if the mouse click is within the borders do this
		if((x > 0 && x < 11) &&(y >= 'A' && y <= 'J'))
		{
			//check all the ships
			for(Ship ship : totalShips)
			{
				//check all the squares within all the ships occupied squares
				for (Square square : ship.getOccupiedSquares())
				{
					//if the mouse click matches with a square then do this
					if(square.getRow() == x && square.getColumn() == y)
					{
						//set attack status to hit
						r.setResult(AtackStatus.HIT);
						ship.setsHealth();
						if(ship.getsHealth() == 0)
						{
							r.setResult(AtackStatus.SUNK);
							//temporary implementation of surrender mechanic
							surr++;
							if(surr == 3)
							{
								r.setResult(AtackStatus.SURRENDER);
							}
						}
					}
					//if we cant find a matching set of squares it will be a miss
					else
					{
						r.setResult(AtackStatus.MISS);
					}
				}
			}
		}
		//if the mouse is not within the borders do this
		else
		{
			r.setResult(AtackStatus.INVALID);
		}

		//this block is to check to see if a square has already been clicked by the user
		if(totalAtacks.isEmpty())
		{
			totalAtacks.add(r);
		}
		else {
			//check if the user has already clicked that square
			for (int i = 0; i < totalAtacks.size(); i++) {
				if (totalAtacks.get(i).getLocation() == r.getLocation()) {
					r.setResult(AtackStatus.INVALID);
				}
			}
		}
		totalAtacks.add(r);

		return r;
	}

	public List<Ship> getShips()
	{
		return this.totalShips;
	}

	public void setShips(List<Ship> ships)
	{
		this.totalShips = ships;
	}

	public List<Result> getAttacks()
	{
		return this.totalAtacks;
	}

	public void setAttacks(List<Result> attacks)
	{
		this.totalAtacks = attacks;
	}
}