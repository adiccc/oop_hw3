package model.game;

import model.game.input.InputReader;
import utils.callbacks.MessageCallback;

public class Level {

    private Board board;
    private MessageCallback messageCallback;

    public Level(Board board, MessageCallback messageCallback, InputReader inputReader) {
        this.messageCallback=messageCallback;
        this.board=board;
        startLevel();
    }

    private void startLevel() {
        messageCallback.send(board.toString());
        //implement the turn switching between the player and enemies
        //if the player died or all the enemies are removed end the level

    }
}
