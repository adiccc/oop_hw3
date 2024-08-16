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
        this.playerId=choosePlayer();
    }

    public int getPlayerId(){
        return playerId;
    }

    private int choosePlayer() {
        try{
            viewPlayersOption();
            char charPlayerChoise=inputReader.readPlayerChoise(TileFactory.playerTypes.size());
            return Integer.parseInt(String.valueOf(charPlayerChoise));
        } catch(Exception e){
            this.messageCallback.send("the player option view faild failed");
        }
        return -1;
    }

    private void viewPlayersOption(){
        this.messageCallback.send("select player :");
        for(int i=0;i<TileFactory.playerTypes.size();i++){
            this.messageCallback.send((TileFactory.playerTypes.indexOf(i)+1)+". "+TileFactory.playerTypes.get(i).get().description());
        }
    }

    // print the instructions and game control
    public void instruction(){
        messageCallback.send("*!*!*!*!*!*!*!*!*! D&D-Roguelike !*!*!*!*!*!*!*!*!*");
        messageCallback.send("*** Game instructions:\n");
        messageCallback.send("* Game Controls:\n");
        messageCallback.send(
                "-Move up:\tW\n" +
                        "-Move down:\tS\n" +
                        "-Move right:\tD\n" +
                        "-Move left:\tA\n" +
                        "-Wait:\tQ\n" +
                        "-Attack: Steping on an enemy\n" +
                        "-Cast special Attack:\tE\n");
        messageCallback.send("* Map description:\n");
        messageCallback.send("-(.):\t Free space\n" +
                "-(#):\t Wall\n" +
                "-(@):\t Your player\n");
        messageCallback.send("* Enemies list:\n");
        messageCallback.send("-(s):\t Lannister Solider\n" +
                "-(k):\t Lannister Knight\n" +
                "-(q):\t Queen’s Guard\n" +
                "-(z):\t Wright\n" +
                "-(b):\t Bear-Wright\n" +
                "-(g):\t Giant-Wright\n" +
                "-(w):\t White Walker\n" +
                "^ Traps:\n" +
                "-(B):\t Bonus Trap\n" +
                "-(Q):\t Queen’s Trap\n" +
                "-(D):\t Death Trap\n");
    }
}
