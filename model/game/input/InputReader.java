package model.game.input;

import java.util.Scanner;

public abstract class InputReader {

    public char readPlayerChoise(int numPlayersOptions){
        String playersOptions="[1-" + numPlayersOptions + "]";
        return readInput(playersOptions);
    }

    protected abstract char readInput(String regex);

}
