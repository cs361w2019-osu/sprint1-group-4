package cs361.battleships.models;

import org.hibernate.loader.custom.sql.SQLQueryReturnProcessor;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	final int BOARDX = 10,
			  BOARDY = BOARDX;
	private List<Ship> totalShips;
	private List<Result> totalAtacks;
	private boolean[][] boardLayout;	// for identifying occupied spaces

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board()
	{
		totalShips = new ArrayList();
		totalAtacks = new ArrayList();
		boardLayout = new boolean[BOARDX][BOARDY];
		// setting up the board as a blank slate
		for (int i = 0; i < BOARDX; i++) {
			for (int j = 0; j < BOARDY; j++) {
				boardLayout[i][j] = false;
			}
		}
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical)
	{
		// TODO Implement
		int xoff;
		char yoff;

		for (int i = 0; i < ship.getOccupiedSquares().size(); i++)
		{
			xoff = ship.getOccupiedSquares().get(i).getRow();
			yoff = ship.getOccupiedSquares().get(i).getColumn();

			if((xoff > 0 && xoff < 11) && (yoff >= 'A' && yoff <= 'J'))
			{
				totalShips.add(ship);
				return true;
			}
		}
		//return false;

		if (totalShips.size() >= 3) {
			return false;
		} else if (totalShips.stream().anyMatch(s -> s.isSameShip(ship.getKind())))  {
			return false;
		}

		final var placedShip = new Ship(ship.getKind());
		placedShip.setOccupiedSquares(y, (char) x, isVertical);

		if (totalShips.stream().anyMatch(s -> s.isOverlaping(placedShip))) {
			return false;
		}
		if (placedShip.getOccupiedSquares().stream().anyMatch(s -> s.isOut())) {
			return false;
		}
		totalShips.add(placedShip);
		return true;

		/*Square s;
		int xoff;
		char yoff;

		for(Ship sType: totalShips) {
			if(sType.getsName().equals(ship.getsName())) {
				return false;
			} else if ((x < 0 || x > 10) || (y < 'A' || y > 'J')) {
				return false;
			}
		}

		if(isVertical) {
			for (int i = 0; i < ship.getSize(); i++) {
				//s = new Square((x + i), y);
				if ((x + i) < 1 || (x + 1) > 10) {
					return false;
				} else if (getBoardLayout(x,y) == true) {
					return false;
				} else {
					ship.setOccupiedSquares((x + i), y,true);
					setBoardLayout(x,y,true);
				}
			}
		} else {
			for(int i = 0; i < ship.getSize(); i++)
			{
				if ((char)((int)y+i) > 'A' || (char)((int)y+i) < 'J') {
					return false;
				} else if (getBoardLayout(x,y) == true) {
					return false;
				}
				else {
					//s = new Square(x, (char)((int)y+i));
					char yChar = (char)((int)y+i);
					ship.setOccupiedSquares(x,yChar,false);
					setBoardLayout(x,y,true);

				}
			}
		}*/

	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y)
	{
		//TODO Implement
		Result r = new Result();
		Square s = new Square(x, y);
		r.setLocation(s);

		if((x > 0 && x < 11) &&(y >= 'A' && y <= 'J'))
		{
			for(Ship ship : totalShips)
			{
				for (Square square : ship.getOccupiedSquares())
				{
					if(square.getRow() == x && square.getColumn() == y)
					{
						r.setResult(AtackStatus.HIT);
						ship.setsHealth();
						if(ship.getsHealth() == 0)
						{
							r.setResult(AtackStatus.SUNK);
							r.setShip(new Ship(ship.getsName()));
							if(r.getShipCount() == 3)
							{
								r.setResult(AtackStatus.SURRENDER);
							}
						}
					}
					else
					{
						r.setResult(AtackStatus.MISS);
					}
				}
			}
		}
		else
		{
			r.setResult(AtackStatus.INVALID);
		}

		if(totalAtacks.isEmpty())
        {
            totalAtacks.add(r);
        }
		else {
            for (int i = 0; i < totalAtacks.size(); i++) {
                if (totalAtacks.get(i).getLocation() == r.getLocation()) {
                    r.setResult(AtackStatus.INVALID);
                }
            }
        }
		totalAtacks.add(r);

		return r;
	}

	public boolean getBoardLayout(int xCoo, int yCoo) {
		return boardLayout[xCoo][yCoo];
	}
	public void setBoardLayout(int xCoo, int yCoo, boolean state) {
		boardLayout[xCoo][yCoo] = state;
	}

	public List<Ship> getShips()
	{
		//TODO implement
		return this.totalShips;
	}

	public void setShips(List<Ship> ships)
	{
		//TODO implement
		this.totalShips = ships;
	}

	public List<Result> getAttacks()
	{
		//TODO implement
		return this.totalAtacks;
	}

	public void setAttacks(List<Result> attacks)
	{
		//TODO implement
		this.totalAtacks = attacks;
	}
}
