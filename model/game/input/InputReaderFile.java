package model.game.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class InputReaderFile extends InputReader {
    private Stack<String> inputs;
    public InputReaderFile(String pathToTestFile) {
        inputs = new Stack<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(pathToTestFile));
            for(int i=lines.size()-1;i>=0;i--) {
                if (lines.get(i).length()>=1 && !lines.get(i).startsWith("/")) {
                    inputs.push(lines.get(i));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected char readInput(String regex) {

        while(!inputs.empty() && !inputs.peek().matches(regex)) {
            inputs.pop();
        }
        return !inputs.empty() ? inputs.pop().charAt(0): null;
    }
}
