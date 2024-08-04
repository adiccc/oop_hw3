package model.game;

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
    private InputReader inputReader;

    public Level(Board board, MessageCallback messageCallback, InputReader inputReader  ) {
        this.messageCallback=messageCallback;
        this.board=board;
        this.player = board.getPlayer();
        enemies = board.getEnemies();
        this.inputReader = inputReader;
        startLevel();


    }

    private void startLevel() {
        int turn =0;
        messageCallback.send(board.toString());
        while(player.alive()&& !enemies.isEmpty()) {
            if(turn ==0 ){ // player's turn
                player.onTick(inputReader.readPlayerMove());
                turn++;
            }
            else if(turn ==1){ // enemies turn
                for(Unit unit : enemies){
                    unit.onTick();
                }
                turn--;
            }
        }
        //implement the turn switching between the player and enemies
        //if the player died or all the enemies are removed end the level

    }
}
