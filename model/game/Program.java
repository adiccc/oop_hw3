package model.game;

import control.initializers.LevelInitializer;
import control.initializers.TileFactory;
import model.game.input.InputReader;
import model.game.input.InputReaderFile;
import model.game.input.InputReaderScanncer;
import model.tiles.units.players.Player;
import utils.callbacks.MessageCallback;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program
{
    private MessageCallback messageCallback;
    private Board board;
    private InputReader inputReader;
    private TileFactory tileFactory;

    public Program(MessageCallback messageCallback,boolean onTest){
        this.messageCallback=messageCallback;
        if(onTest)
            inputReader=new InputReaderFile();
        else
            inputReader=new InputReaderScanncer();
        this.tileFactory=new TileFactory();
    }

    public void start(){
        instruction();
        int playerId=choosePlayer();
        LevelInitializer initializer=new LevelInitializer(playerId);
        Game game=new Game(initializer);
    }


    private int choosePlayer() {
        try{
            ArrayList<Player> pl=new ArrayList<>();
            for(int i=0;i<tileFactory.getNumPlayer();i++)
                pl.add(tileFactory.producePlayer(i));
            viewPlayersOption(pl);
            char charPlayerChoise=inputReader.readPlayerChoise(pl.size());
            return Integer.parseInt(String.valueOf(charPlayerChoise));
        } catch(Exception e){
            this.messageCallback.send("the player option view faild failed");
        }
        return -1;
    }


    private void viewPlayersOption(ArrayList<Player> pl){
        this.messageCallback.send("select player :");
        for(Player entry: pl){
            this.messageCallback.send((pl.indexOf(entry)+1)+". "+entry.description());
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