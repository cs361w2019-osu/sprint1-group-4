package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {



    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testValidPlacementMultiple()
    {
        Board b = new Board();
        assertTrue(b.placeShip(new Ship("MINESWEEPER"), 3, 'C', false));
        assertTrue(b.placeShip(new Ship("DESTROYER"), 2, 'D', false));
    }

    @Test
    public void testInvalidPlacementMultiple()
    {
        Board b = new Board();
        assertTrue(b.placeShip(new Ship("MINESWEEPER"), 6, 'F', false));
        assertFalse(b.placeShip(new Ship("DESTROYER"), 6, 'F', false));
    }

    @Test
    public void testInvalidPlacementOfSameType()
    {
        Board b = new Board();
        assertTrue(b.placeShip(new Ship("MINESWEEPER"), 6, 'F', false));
        assertFalse(b.placeShip(new Ship("MINESWEEPER"), 7, 'A', false));
    }

    @Test
    public void testAttackOnShip()
    {
        Board b = new Board();
        Result r;
        b.placeShip(new Ship("DESTROYER"), 3, 'E', false);
        r = b.attack(3, 'E');
        assertEquals(AtackStatus.HIT, r.getResult());
    }

    @Test
    public void testAttackOnEmptySquare()
    {
        Board b = new Board();
        Result r;
        r = b.attack(3, 'E');
        assertEquals(AtackStatus.MISS, r.getResult());
    }

    @Test
    public void testAttackOnSameSpace()
    {
        Board b = new Board();
        Result r1, r2;
        r1 = b.attack(7, 'E');
        r2 = b.attack(7, 'E');
        assertEquals(AtackStatus.MISS, r1.getResult());
        assertEquals(AtackStatus.INVALID, r2.getResult());
    }

    @Test
    public void testHitSonar()
    {
        Board b = new Board();
        b.placeShip(new Ship("MINESWEEPER"), 4, 'B', false);
        Result r = new Result();
        r = b.sonar(4, 'B');
        assertEquals(AtackStatus.HIT, r.getResult());
    }

    @Test
    public void testMissSonar()
    {
        Board b = new Board();
        b.placeShip(new Ship("MINESWEEPER"), 4, 'B', false);
        Result r = new Result();
        r = b.sonar(5, 'B');
        assertEquals(AtackStatus.MISS, r.getResult());
    }

    @Test
    public void testHitLaser()
    {
        Board b = new Board();
        b.placeShip(new Ship("MINESWEEPER"), 4, 'B', false);
        Result r = new Result();
        r = b.laser(4, 'B');
        assertEquals(AtackStatus.HIT, r.getResult());
    }

    @Test
    public void testMissLaser()
    {
        Board b = new Board();
        b.placeShip(new Ship("MINESWEEPER"), 4, 'B', false);
        Result r = new Result();
        r = b.laser(5, 'B');
        assertEquals(AtackStatus.MISS, r.getResult());
    }
}
