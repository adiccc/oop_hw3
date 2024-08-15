package model.game.input;

public abstract class InputReader {

    public char readPlayerChoise(int numPlayersOptions){
        String playersOptions="[1-" + numPlayersOptions + "]";
        return readInput(playersOptions);
    }

    public char readPlayerMove(){
        String playersMove ="[" + InputProvider.getRegex() + "]";
        return readInput(playersMove);
    }

    protected abstract char readInput(String regex);

}
