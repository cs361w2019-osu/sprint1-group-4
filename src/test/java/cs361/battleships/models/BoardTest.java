package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testValidPlacementMultiple()
    {
        Game g = new Game();
        Board b = new Board();
        assertTrue(g.placeShip(new Ship("MINESWEEPER"), 3, 'C', false));
        assertTrue(g.placeShip(new Ship("DESTROYER"), 2, 'D', false));
    }
}
