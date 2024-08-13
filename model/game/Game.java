package model.game;

import control.initializers.LevelInitializer;
import model.game.input.InputReader;
import utils.callbacks.MessageCallback;
import utils.generators.Generator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class Game {
    private MessageCallback messageCallback;
    private InputReader inputReader;
    public Game(LevelInitializer initializer, MessageCallback messageCallback, InputReader inputReader){
        this.messageCallback = messageCallback;
        initAllLevels("/Users/adicohen/Documents/oop_projects/hw3/Levels",initializer);
        this.inputReader = inputReader;
    }

    private void initAllLevels(String path, LevelInitializer initializer){
        File f = new File(path);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return  name.endsWith("txt");
            }
        });
        List<File> levelsFiles= Arrays.asList(matchingFiles);
        levelsFiles.sort((File f1,File f2)->f1.getName().compareTo(f2.getName()));
        for (File level : levelsFiles){
            initializer.initLevel(path+"/"+level.getName());
            Level l=new Level(initializer.buildBord(),messageCallback,inputReader);
        }
    }
}
