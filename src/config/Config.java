package config;

public final class Config {
    private Config() {}

    public static final int MAP_SIZE = 5;
    public static final double ENCOUNTER_CHANCE = 0.30;

    public static final int BONUS_MIN = 10;
    public static final int BONUS_MAX = 30;

    public static final double CRIT_CHANCE = 0.15;
    public static final double CRIT_MULT = 1.50;

    public static final int XP_WIN = 120;

    public static final double MELEE_ATK = 1.00;
    public static final double MELEE_DEF = 0.50;
    public static final double RANGED_ATK = 0.90;
    public static final double RANGED_DEF = 0.50;
    public static final double MAGIC_ATK = 1.10;
    public static final double MAGIC_DEF = 0.45;
}
