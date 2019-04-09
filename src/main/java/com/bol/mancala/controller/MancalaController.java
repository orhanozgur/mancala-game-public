package com.bol.mancala.controller;


import com.bol.mancala.model.Player;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequestMapping("/")
@RestController
public class MancalaController {

    @RequestMapping("/restart")
    public List<Player> restart() {
        List<Player> players = new ArrayList<>();

        Player player1 = new Player(true);
        Player player2 = new Player(false);

        players.add(player1);
        players.add(player2);

        return players;
    }

    @RequestMapping("/move")
    public List<Player> move(@RequestBody Object obj) {

        List<LinkedHashMap> map = (List<LinkedHashMap>) obj;
        List<Player> players = new ArrayList<>();
        map.forEach(m -> {
            List<Integer> pits = (List<Integer>) m.get("pits");
            Player player = new Player(false);
            player.setPits(pits.stream().mapToInt(i -> i).toArray());
            player.setBigPit(Integer.parseInt(m.get("bigPit").toString()));
            player.setPitIndex(Integer.parseInt(m.get("pitIndex").toString()));
            player.setMyTurn(Boolean.parseBoolean(m.get("myTurn").toString()));
            player.setMessage(m.get("message").toString());
            players.add(player);
        });

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        if (player1.isMyTurn()) {
            player1.move(player2);
        } else {
            player2.move(player1);
        }

        System.out.println("Player1: " + player1.toString());
        System.out.println("Player2: " + player2.toString());

        return players;
    }
}
