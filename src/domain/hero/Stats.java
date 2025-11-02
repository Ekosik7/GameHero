package domain.hero;

public record Stats(int hp, int atk, int def, int spd) {
    public Stats add(int dhp, int datk, int ddef, int dspd) { return new Stats(hp+dhp, atk+datk, def+ddef, spd+dspd); }
}
