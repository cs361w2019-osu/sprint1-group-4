package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest
{
    @Test
    public void testGetColumn()
    {
        Square s = new Square(5, 'A');
        assertEquals(s.getColumn(), 'A');
    }

    @Test
    public void testGetRow()
    {
        Square s = new Square(5, 'A');
        assertEquals(s.getRow(), 5);
    }

    @Test
    public void testEquals()
    {
        Square sa = new Square(5, 'A');
        Square sb = new Square(5, 'A');

        assertTrue(sa.equals(sb));
    }

    @Test
    public void testOutOfBounds()
    {
        Square s = new Square(11, 'B');
        assertTrue(s.isOutOfBounds());
    }

    @Test
    public void testHit()
    {
        Square s = new Square(10, 'E');
        s.hit();
        assertTrue(s.isHit());
    }

    @Test
    public void testToString()
    {
        Square s = new Square(3, 'G');
        var string = s.toString();
        assertEquals(string, "(3, G)");
    }
}
