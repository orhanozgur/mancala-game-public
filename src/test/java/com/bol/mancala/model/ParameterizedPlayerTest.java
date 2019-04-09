package com.bol.mancala.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParameterizedPlayerTest {

    @Parameterized.Parameter(0)
    public Player actP1;
    @Parameterized.Parameter(1)
    public Player actP2;
    @Parameterized.Parameter(2)
    public Player expP1;
    @Parameterized.Parameter(3)
    public Player expP2;

    // Game rules are being tested by the following input cases.

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {
                        new Player(new int[]{6, 2, 5, 9, 1, 0}, 6, 2, true, ""),
                        new Player(new int[]{6, 7, 5, 4, 6, 4}, 11, -1, false, ""),
                        new Player(new int[]{6, 2, 1, 10, 2, 1}, 7, -1, true, ""),
                        new Player(new int[]{6, 7, 5, 4, 6, 4}, 11, -1, false, "")
                },
                {
                        new Player(new int[]{10, 3, 4, 0, 2, 5}, 11, 0, true, ""),
                        new Player(new int[]{1, 4, 0, 3, 9, 8}, 12, -1, false, ""),
                        new Player(new int[]{1, 4, 5, 1, 3, 6}, 12, -1, false, ""),
                        new Player(new int[]{2, 5, 1, 3, 9, 8}, 12, -1, true, "")
                },
                {
                        new Player(new int[]{0, 0, 2, 3, 1, 5}, 20, 4, true, ""),
                        new Player(new int[]{6, 2, 5, 0, 1, 3}, 24, -1, false, ""),
                        new Player(new int[]{0, 0, 2, 3, 0, 6}, 20, -1, false, ""),
                        new Player(new int[]{6, 2, 5, 0, 1, 3}, 24, -1, true, "")

                },
                {
                        new Player(new int[]{8, 2, 5, 2, 13, 5}, 6, 4, true, ""),
                        new Player(new int[]{5, 1, 5, 7, 4, 4}, 5, -1, false, ""),
                        new Player(new int[]{9, 3, 6, 3, 1, 6}, 7, -1, false, ""),
                        new Player(new int[]{6, 2, 6, 8, 5, 5}, 5, -1, true, "")
                },
                {
                        new Player(new int[]{2, 6, 1, 0, 4, 6}, 9, 1, true, ""),
                        new Player(new int[]{6, 7, 1, 9, 10, 5}, 6, -1, false, ""),
                        new Player(new int[]{2, 1, 2, 1, 5, 7}, 10, -1, true, ""),
                        new Player(new int[]{6, 7, 1, 9, 10, 5}, 6, -1, false, "")
                },
                {
                        new Player(new int[]{5, 7, 2, 0, 4, 8}, 10, 2, true, ""),
                        new Player(new int[]{4, 4, 7, 5, 4, 4}, 8, -1, false, ""),
                        new Player(new int[]{5, 7, 1, 0, 4, 8}, 18, -1, false, ""),
                        new Player(new int[]{4, 4, 0, 5, 4, 4}, 8, -1, true, "")
                },
                {
                        new Player(new int[]{0, 0, 0, 1, 0, 0}, 30, 3, true, ""),
                        new Player(new int[]{4, 1, 1, 0, 3, 4}, 28, -1, false, ""),
                        new Player(new int[]{0, 0, 0, 0, 0, 0}, 44, -1, false, ""),
                        new Player(new int[]{0, 0, 0, 0, 0, 0}, 28, -1, true, "")
                }
        };
        return Arrays.asList(data);
    }

    @Test
    public void move() {
        assertEquals("Before move total stones should be 72", 72, actP1.getAllTotal() + actP2.getAllTotal());

        actP1.move(actP2);

        assertEquals("After move total stones should be 72", 72, actP1.getAllTotal() + actP2.getAllTotal());
        assertArrayEquals("Checking pits of player1", expP1.getPits(), actP1.getPits());
        assertArrayEquals("Checking pits of player2", expP2.getPits(), actP2.getPits());
        assertEquals("Checking big pit of player1", expP1.getBigPit(), actP1.getBigPit());
        assertEquals("Checking big pit of player2", expP2.getBigPit(), actP2.getBigPit());
        assertEquals("Checking pit index of player1", expP1.getPitIndex(), actP1.getPitIndex());
        assertEquals("Checking pit index of player2", expP2.getPitIndex(), actP2.getPitIndex());
        assertEquals("Checking turn of player1", expP1.isMyTurn(), actP1.isMyTurn());
        assertEquals("Checking turn of player2", expP2.isMyTurn(), actP2.isMyTurn());
    }
}