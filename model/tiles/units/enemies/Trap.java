package model.tiles.units.enemies;

public class Trap extends Enemy {
    private int visibiltyTime;
    private int invisibiltyTime;

    public Trap(char tile, String name, int hitPoints, int attack, int defense, int experinceValue, int visibiltyTime, int invisibiltyTime) {
        super(tile,name,hitPoints,attack,defense,experinceValue);
        this.visibiltyTime = visibiltyTime;
        this.invisibiltyTime = invisibiltyTime;
    }
}
