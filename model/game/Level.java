package model.game;

import model.game.input.InputProvider;
import model.game.input.InputReader;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.callbacks.MessageCallback;

import java.util.List;

public class Level {
    private Board board;
    private MessageCallback messageCallback;
    private Player player;
    private List<Enemy> enemies;

    public Level(Board board, MessageCallback messageCallback) {
        this.messageCallback=messageCallback;
        this.board=board;
        this.player = board.getPlayer();
        enemies = board.getEnemies();
        startLevel();
    }

    private void startLevel() {
        boolean isPlayerTurn = true;
        messageCallback.send(board.toString());
        messageCallback.send(player.description());
        while (player.alive() && !enemies.isEmpty()) {
            // player's turn
            if (isPlayerTurn){
                InputProvider playerMove = player.onTick();
                board.moveTile(player, playerMove);
            }

                // enemies turn
            else {
                if (player.alive()) {
                    for (Enemy e : enemies)
                        board.moveTile(e,e.turn(player));
                }
            }
            isPlayerTurn = !isPlayerTurn;
        }
    }
}
