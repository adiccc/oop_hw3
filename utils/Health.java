package utils;

public class Health {
    private int capacity;
    private int current;

    public Health(int capacity) {
        this.capacity = capacity;
        this.current = capacity;
    }

    public int takeDamage(int damageTaken) {

        damageTaken = Math.max(0, damageTaken);
        damageTaken = Math.min(current, damageTaken);
        current -= damageTaken;
        return damageTaken;
    }

    public int getCurrent() {
        return current;
    }
    public int updateCurrentHealthOnCast(int defense) {
        current = Math.min(current  + (10*defense), capacity);
        return current;
    }

    public void increaseMax(int healthGain) {
        capacity += healthGain;
    }

    public void heal() {
        current = capacity;
    }

    public String toString() {
        return current+"/"+capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
