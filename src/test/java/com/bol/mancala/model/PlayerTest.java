package com.bol.mancala.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    private Player player1;
    private Player player2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player(true);
        player2 = new Player(false);
    }

    @Test
    public void initialBoardTest() {
        assertThat("Player should have 6 pits", player1.getPits().length, is(6));
        assertThat("All pits should have 6 stones", Arrays.stream(player1.getPits()).boxed().collect(Collectors.toList()), everyItem(equalTo(6)));
        assertThat("Big pit should be empty", player1.getBigPit(), is(0));
    }
}