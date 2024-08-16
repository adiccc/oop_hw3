package model.game;

import control.initializers.LevelInitializer;
import control.initializers.TileFactory;
import model.game.input.InputReader;
import model.game.input.InputReaderFile;
import model.game.input.InputReaderScanncer;
import model.tiles.units.players.Player;
import utils.callbacks.MessageCallback;
import utils.generators.FixedGenerator;
import utils.generators.Generator;
import utils.generators.RandomGenerator;

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
    private Generator generator;

    private Program(MessageCallback messageCallback,InputReader inputReader, Generator generator){
        this.messageCallback = messageCallback;
        this.inputReader = inputReader;
        this.generator = generator;

    }
    public Program(MessageCallback messageCallback,String pathToTestFile){
        this(messageCallback,new InputReaderFile(pathToTestFile), new FixedGenerator());
    }
    public Program(MessageCallback messageCallback){
        this(messageCallback,new InputReaderScanncer(), new RandomGenerator());
    }

    public void start(){
        PreGame  preGame=new PreGame(messageCallback,inputReader);
        LevelInitializer initializer=new LevelInitializer(preGame.getPlayerId(), generator, inputReader,messageCallback);
        Game game=new Game(initializer,messageCallback);
    }






}
