package model.game.input;

public enum InputProvider {
    Up("w"),
    Down("s"),
    Left("a"),
    Right("d"),
    CastAbility("e"),
    Wait("q");

    private String key;

     InputProvider(String key) {
        this.key = key;
    }

    // getter method
    public String GetKey() {
        return this.key;
    }
    public static String getRegex() {
        StringBuilder regex = new StringBuilder();
        for (InputProvider inp : InputProvider.values()) {
            regex.append(inp.GetKey());
        }
        return regex.toString();
    }

    public static InputProvider FindByKey(String key) {
        for (InputProvider v : values()) {
            if (v.key.equals(key)) {
                return v;
            }
        }
        return null;
    }
}
