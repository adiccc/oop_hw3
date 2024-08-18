import model.game.Program;
import model.tiles.units.Unit;
import model.tiles.units.players.Player;
import utils.Position;
import utils.generators.FixedGenerator;
import view.CLI;
import view.View;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        View view = new CLI();
        Program program = new Program(view.getCallback());
        program.start();
    }
}
