package model.tiles.units;

import java.util.List;

public interface HeroicUnit <T extends Unit>{
    public void casAbility(List<T> units);
}
