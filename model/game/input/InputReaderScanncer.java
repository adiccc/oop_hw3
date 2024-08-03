package model.game.input;

import java.util.Scanner;

public class InputReaderScanncer extends InputReader {

    protected char readInput(String regex) {
        Scanner scanner = new Scanner(System.in);
        String input=scanner.nextLine();
        while(!input.matches(regex)) {
            input = scanner.nextLine();
        }
        return input.charAt(0);
    }
}
