package cs361.battleships.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest
{
    @Test
    public void testSonar()
    {
        Game g = new Game();
        assertTrue(g.sonar(10, 'A'));
    }
}
