package cs361.battleships.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShipTest
{
    @Test
    public void testValidPlacement()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 6, false);
        
        List<Square> shipSquares = mine.getOccupiedSquares();
        List<Square> actualSquares = new ArrayList<>();

        actualSquares.add(new Square(6, 'A'));
        actualSquares.add(new Square(6, 'B'));
        assertEquals(shipSquares, actualSquares);
    }


    @Test
    public void testOverlap()
    {
        Ship mine = new Ship("MINESWEEPER");
        Ship dest = new Ship("DESTROYER");

        mine.place('A', 4, false);
        dest.place('A', 4, false);

        assertTrue(mine.overlaps(dest));
    }

    @Test
    public void testHitSonarHit()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 4, false);
        Result r = mine.sonarHit(4, 'A');

        assertEquals(AtackStatus.HIT, r.getResult());
    }

    @Test
    public void testMissSonarHit()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 4, false);
        Result r = mine.sonarHit(5, 'A');

        assertEquals(AtackStatus.MISS, r.getResult());
    }
}