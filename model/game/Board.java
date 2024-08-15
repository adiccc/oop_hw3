package model.game;

import control.initializers.TileFactory;
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
    private final int width;

    public Board(List<Tile> tiles, Player p, List<Enemy> enemies, int width){
        this.player = p;
        this.enemies = enemies;
        this.width = width;
        this.board = new TreeMap<>();
        for(Tile t : tiles)
            board.put(t.getPosition(), t);
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

//    //directions : 0 - right, 1- left, 2 - up, 3 - down, 4- random
//    public void moveTile(Unit u, int direction){
//        int nx = u.getPosition().getX();
//        int ny = u.getPosition().getY();
//        switch(direction){
//            case 0:
//                nx++;
//                break;
//            case 1:
//                nx--;
//                break;
//            case 2:
//                ny--;
//                break;
//            case 3:
//                ny++;
//                break;
//            default:
//                break;
//        }
//        Position newPosition = new Position(nx, ny);
//        if(board.containsKey(newPosition))
//            u.swapPosition(board.get(newPosition));
//    }

    public Tile getTile(Position pos){
        return board.get(pos);
    }


    public void removeEnemy(Enemy e){
        enemies.remove(e);
        board.remove(e.getPosition());
        // TODO: need to fill the position of e with emptyTile
    }



    public Map<Position, Tile> getBoard(){
        return board;
    }
    public Player getPlayer(){return this.player;}
    public List<Enemy> getEnemies(){return this.enemies;}
}
