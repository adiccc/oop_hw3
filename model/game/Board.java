package model.game;

import model.game.input.InputProvider;
import model.tiles.Tile;
import model.tiles.units.Unit;
import model.tiles.units.enemies.Enemy;
import model.tiles.units.players.Player;
import utils.Position;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Board {
    private Map<Position, Tile> board;
    private Player player;
    private List<Enemy> enemies;
    private int width;

    public Board(){
    }

    public Board build(List<Tile> tiles, Player p, List<Enemy> enemies, int width){
        this.player = p;
        this.enemies = enemies;
        this.width = width;
        this.board = new TreeMap<>();
        for(Tile t : tiles)
            board.put(t.getPosition(), t);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Position, Tile> entry : board.entrySet()){
            sb.append(entry.getValue().toString());
            if(entry.getKey().getX() == width-1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void moveTile(Unit u, InputProvider direction){
        int nx = u.getPosition().getX();
        int ny = u.getPosition().getY();
        boolean  specialAbility = false;
        switch(direction){
            case Right:
                nx++;
                break;
            case Left:
                nx--;
                break;
            case Up:
                ny--;
                break;
            case Down:
                ny++;
                break;
            case CastAbility:
                specialAbility = true;
                break;
            default:
                break;
        }
        if(!specialAbility){
            Position newPosition = new Position(nx, ny);
            interactTiles(u, newPosition);
        }
        if(specialAbility){
            player.specialAbility(enemies);
        }
        specialAbility = false;
    }

    private void interactTiles(Unit u,Position newPosition){
        if(isLegalPosition(newPosition)){
            board.get(newPosition).accept(u);
            if(u.getPosition().equals(newPosition))
                swapPosition(u,board.get(newPosition));
        }
    }

    private void swapPosition(Unit u, Tile t){
        board.replace(u.getPosition(), u);
        board.replace(t.getPosition(), t);
    }

    private boolean isLegalPosition(Position p){
        return board.containsKey(p);
    }

    public void removeEnemy(Enemy e, Tile tile){
        enemies.remove(e);
        board.replace(e.getPosition(), tile);
        tile.swapPosition(e);
    }

    public Map<Position, Tile> getBoard(){
        return board;
    }
    public Player getPlayer(){return this.player;}
    public List<Enemy> getEnemies(){return this.enemies;}

}
