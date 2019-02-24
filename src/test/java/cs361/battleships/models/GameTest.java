package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest
{
    @Test
    public void testPlaceShip()
    {
        Game g = new Game();
        Ship ship = new Ship("MINESWEEPER");
        assertTrue(g.placeShip(ship, 7, 'A', false));
    }

    @Test
    public void testValidAttack()
    {
        Game g = new Game();
        assertTrue(g.attack(5, 'I'));
    }

    @Test
    public void testSonar()
    {
        Game g = new Game();
        assertTrue(g.sonar(10, 'A'));
    }
}
