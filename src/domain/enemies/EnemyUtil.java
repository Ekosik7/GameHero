package domain.enemies;

import domain.hero.*;
import java.util.concurrent.ThreadLocalRandom;

public final class EnemyUtil {
    private EnemyUtil() {}

    public static Hero base(HeroClass c, Element e) {
        return switch (c) {
            case WARRIOR -> new Hero("Rogue Warrior", c, e, new Stats(90, 16, 10, 8));
            case MAGE -> new Hero("Wild Mage", c, e, new Stats(75, 18, 8, 10));
            case ARCHER -> new Hero("Forest Archer", c, e, new Stats(80, 16, 8, 12));
        };
    }

    public static Element rndElem() {
        return Element.values()[ThreadLocalRandom.current().nextInt(Element.values().length)];
    }

    public static int randomBonus(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void scaleToLevel(Hero h, int target) {
        for (int i = 1; i < target; i++) h.gain(50 + i * 20);
        h.healFull();
    }
}
