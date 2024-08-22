package model.game;

import control.initializers.TileFactory;
import model.game.input.InputReader;
import model.tiles.units.players.Player;
import utils.callbacks.MessageCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PreGame {
    private int playerId;
    private MessageCallback messageCallback;
    private InputReader  inputReader;

    public PreGame(MessageCallback messageCallback, InputReader inputReader){
        this.messageCallback = messageCallback;
        this.inputReader = inputReader;
        instruction();
        viewPlayersOption();
        this.playerId=choosePlayer()-1;
        messageCallback.send(String.format("you have selected : %s",TileFactory.playerTypes.get(playerId).get().getName()));
    }

    public int getPlayerId(){
        return playerId;
    }

    private int choosePlayer() {
        try{
            char charPlayerChoise=inputReader.readPlayerChoise(TileFactory.playerTypes.size());
            return Integer.parseInt(String.valueOf(charPlayerChoise));
        } catch(Exception e){
            this.messageCallback.send("the player option view faild failed");
        }
        return -1;
    }

    private void viewPlayersOption(){
        StringBuilder sb=new StringBuilder();
        sb.append("select player :");
        for(int i=0;i<TileFactory.playerTypes.size();i++){
            sb.append(String.format("\n %d. %s",i+1,TileFactory.playerTypes.get(i).get().description()));
        }
        messageCallback.send(sb.toString());
    }

    // print the instructions and game control
    private void instruction(){
        StringBuilder sb = new StringBuilder();
        sb.append("*** Game instructions:\n");
        sb.append("* Game Controls:\n");
        sb.append("-Move up:\tW\n");
        sb.append("-Move down:\tS\n");
        sb.append("-Move right:\tD\n");
        sb.append("-Move left:\tA\n");
        sb.append("-Wait:\tQ\n");
        sb.append("-Attack: Stepping on an enemy\n");
        sb.append("-Cast special Attack:\tE\n");
        sb.append("* Map description:\n");
        sb.append("-(.):\t Free space\n");
        sb.append("-(#):\t Wall\n");
        sb.append("-(@):\t Your player\n");
        messageCallback.send(sb.toString());
    }
}
