package com.bol.mancala.model;

import java.util.Arrays;
import java.util.Objects;

public class Player {

    private int[] pits;

    private int bigPit;

    private int pitIndex;

    private boolean isMyTurn;

    private String message;

    //Board Setup
    //Each of the two players has his six pits in front of him. To the right of the six pits,
    //each player has a larger pit. At the start of the game, there are six stones in each
    //of the six round pits.
    public Player(boolean isMyTurn) {
        this.pits = new int[6];
        Arrays.fill(pits, 6);
        this.bigPit = 0;
        this.pitIndex = -1;
        setMyTurn(isMyTurn);
    }

    public Player(int[] pits, int bigPit, int pitIndex, boolean isMyTurn, String message) {
        this.pits = pits;
        this.bigPit = bigPit;
        this.pitIndex = pitIndex;
        this.isMyTurn = isMyTurn;
        this.message = message;
    }

    public void move(Player opponent) {
        int ind = getPitIndex();
        int pitCount = getPits()[ind];
        boolean isPlayerSide = true;
        int k = ind;

        //Game is over
        if (isEmpty() || opponent.isEmpty()) {
            return;
        }

        //Game Play
        if (pitCount == 0) {
            return;
        } else if (pitCount == 1) {
            getPits()[ind] = 0;
        } else {
            getPits()[ind] = 1;
            pitCount--;
        }

        while (pitCount > 0) {
            ind++;
            isPlayerSide = (ind / 7) % 2 == 0;
            k = ind % 7;

            if (isPlayerSide) {     // Player's side
                if (k == 6) {       // Big pit
                    addToBigPit(1);
                } else {
                    getPits()[k]++;
                }
            } else {                // Opponent's side
                if (k == 6) {       // No stones are put in the opponents' big pit.
                    continue;
                } else {
                    opponent.getPits()[k]++;
                }
            }
            pitCount--;
        }

        //Clear messages
        setMessage("");
        opponent.setMessage("");

        //If the player's last stone lands in his own big pit, he gets another turn.
        if (isPlayerSide && k == 6) {
            setMessage("Again your turn!");
        } else {
            setMyTurn(false);
            opponent.setMyTurn(true);
        }

        //Capturing Stones
        //During the game the pits are emptied on both sides. Always when the last stone
        //lands in an own empty pit, the player captures his own stone and all stones in the
        //opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.
        if (isPlayerSide && k != 6 && getPits()[k] == 1) {
            addToBigPit(1 + opponent.getPits()[5 - k]);
            getPits()[k] = 0;
            opponent.getPits()[5 - k] = 0;
            setMessage("Captured the stones!");
        }

        //The Game Ends
        //The game is over as soon as one of the sides runs out of stones. The player who
        //still has stones in his pits keeps them and puts them in his big pit. The winner of
        //the game is the player who has the most stones in his big pit.
        if (isEmpty()) {
            addToBigPit(opponent.getPitsTotal());
            opponent.emptyPits();
        } else if (opponent.isEmpty()) {
            opponent.addToBigPit(getPitsTotal());
            emptyPits();
        }

        if (isEmpty() || opponent.isEmpty()) {
            switch (Integer.compare(getBigPit(), opponent.getBigPit())) {
                case 1:
                    setMessage("You won the game!");
                    opponent.setMessage("");
                    break;
                case 0:
                    setMessage("Drawn!");
                    opponent.setMessage("Drawn!");
                    break;
                case -1:
                    opponent.setMessage("You won the game!");
                    setMessage("");
                    break;
            }
        }

        setPitIndex(-1);
        opponent.setPitIndex(-1);
    }

    private void addToBigPit(int stone) {
        bigPit += stone;
    }

    private void emptyPits() {
        Arrays.fill(pits, 0);
    }

    public boolean isEmpty() {
        return Arrays.stream(pits).allMatch(value -> value == 0);
    }

    public int getPitsTotal() {
        return Arrays.stream(pits).sum();
    }

    public int getAllTotal() {
        return getPitsTotal() + getBigPit();
    }

    public int[] getPits() {
        return pits;
    }

    public void setPits(int[] pits) {
        this.pits = pits;
    }

    public int getBigPit() {
        return bigPit;
    }

    public void setBigPit(int bigPit) {
        this.bigPit = bigPit;
    }

    public int getPitIndex() {
        return pitIndex;
    }

    public void setPitIndex(int pitIndex) {
        this.pitIndex = pitIndex;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
        message = myTurn ? "Your turn!" : "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return bigPit == player.bigPit &&
                pitIndex == player.pitIndex &&
                isMyTurn == player.isMyTurn &&
                Arrays.equals(pits, player.pits) &&
                Objects.equals(message, player.message);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(bigPit, pitIndex, isMyTurn, message);
        result = 31 * result + Arrays.hashCode(pits);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pits=" + Arrays.toString(pits) +
                ", bigPit=" + bigPit +
                ", pitIndex=" + pitIndex +
                ", isMyTurn=" + isMyTurn +
                ", message='" + message + '\'' +
                '}';
    }
}
