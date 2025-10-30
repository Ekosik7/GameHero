package game;

public enum Element {
    FIRE, WATER, GRASS, ELECTRIC, ICE;
    public double multiplierAgainst(Element target) {
        if (this == FIRE) { if (target == GRASS) return 1.5; if (target == WATER) return 0.7; }
        else if (this == WATER) { if (target == FIRE) return 1.5; if (target == GRASS) return 0.7; }
        else if (this == GRASS) { if (target == WATER) return 1.5; if (target == FIRE) return 0.7; }
        else if (this == ELECTRIC) { if (target == WATER) return 1.5; if (target == GRASS) return 0.9; }
        else if (this == ICE) { if (target == GRASS) return 1.5; if (target == FIRE) return 0.7; }
        return 1.0;
    }
}
