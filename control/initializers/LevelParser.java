package control.initializers;

import model.tiles.Tile;
import model.tiles.units.Unit;
import utils.Position;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelParser {
    private List<Tile> tiles;

    public LevelParser(String Path) {
        Path folderPath = Paths.get("/Users/adicohen/Documents/oop_projects/hw3/Levels");
        List<String> gameLevels = new ArrayList<>();
        try {
            Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file)) {
                        String content = Files.readString(file);
                        gameLevels.add(content);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.err.println("Failed to visit file: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // build the game board
    public void LevelParser(File mapFile) {
//        try {
//            Scanner mapReader = new Scanner(mapFile);
//            int y = 0, x=0;
//            Position position = new Position(x, y);
//            Unit enemy;
//            String data;
//            while (mapReader.hasNextLine()){
//                data = mapReader.nextLine();
//
//                x = 0;
//                for (char ch: data.toCharArray()){
//                    if (ch == '@') {
//                        player.setPosition(position);
//                    }
//                    if (ch == '#') {
//                        walls.put(position, new Wall(position));
//                    }
//                    if (DatabaseUnits.enemyPool.containsKey(ch+"")){
//                        enemy = DatabaseUnits.enemyPool.get(ch+"").copy();
//                        enemy.setPosition(position);
//                        enemies.add((Enemy) enemy);
//                    }
//                    x++;
//                }
//                y++;
//            }
//            this.width = x;
//            this.height = y;
//            this.board = new String[y][x];
//        }
//        catch (Exception e){}
    }
}
