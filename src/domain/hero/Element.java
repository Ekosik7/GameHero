package domain.hero;

public enum Element {
    FIRE, WATER, GRASS, ELECTRIC, ICE;
    public double mult(Element t) {
        return switch (this) {
            case FIRE -> t==GRASS?1.5: t==WATER?0.7:1.0;
            case WATER -> t==FIRE?1.5: t==GRASS?0.7:1.0;
            case GRASS -> t==WATER?1.5: t==FIRE?0.7:1.0;
            case ELECTRIC -> t==WATER?1.5: t==GRASS?0.9:1.0;
            case ICE -> t==GRASS?1.5: t==FIRE?0.7:1.0;
        };
    }
}
