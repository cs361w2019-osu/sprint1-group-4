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
    public void testIsAtLocation()
    {
        Square s = new Square(5, 'B');
        Ship dest = new Ship("DESTROYER");
        dest.place('A', 5, false);

        assertTrue(dest.isAtLocation(s));
    }

    @Test
    public void testGetKind()
    {
        Ship battle = new Ship("BATTLESHIP");
        assertEquals(battle.getKind(), "BATTLESHIP");
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

    @Test
    public void testHitAttack()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 3, false);
        Result r = mine.attack(3, 'A');
        assertEquals(AtackStatus.HIT, r.getResult());
    }

    @Test
    public void testMissAttack()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 3, false);
        Result r = mine.attack(7, 'A');
        assertEquals(AtackStatus.MISS, r.getResult());
    }

    @Test
    public void testIsSunk()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 3, false);
        Result r1 = mine.attack(3, 'A');
        Result r2 = mine.attack(3, 'B');
        assertEquals(AtackStatus.HIT, r1.getResult());
        assertEquals(AtackStatus.SUNK, r2.getResult());
    }

    @Test
    public void testMultipleSameAttack()
    {
        Ship mine = new Ship("MINESWEEPER");
        mine.place('A', 3, false);
        Result r1 = mine.attack(3, 'A');
        Result r2 = mine.attack(3, 'A');
        assertEquals(AtackStatus.HIT, r1.getResult());
        assertEquals(AtackStatus.INVALID, r2.getResult());
    }

    @Test
    public void testTrueEquals()
    {
        Ship mine1 = new Ship("MINESWEEPER");
        Ship mine2 = new Ship("MINESWEEPER");

        assertTrue(mine1.equals(mine2));
    }

    @Test
    public void testFalseEquals()
    {
        Ship mine = new Ship("MINESWEEPER");
        Ship dest = new Ship("DESTROYER");

        assertFalse(mine.equals(dest));
    }
}