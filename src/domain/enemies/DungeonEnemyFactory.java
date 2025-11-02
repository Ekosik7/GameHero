package domain.enemies;

import domain.hero.*;

import java.util.concurrent.ThreadLocalRandom;

public class DungeonEnemyFactory implements AbstractEnemyFactory {
    public Hero create(int aroundLevel) {
        HeroClass c = switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0 -> HeroClass.WARRIOR;
            case 1 -> HeroClass.MAGE;
            default -> HeroClass.ARCHER;
        };
        Element e = EnemyUtil.rndElem();
        Hero h = EnemyUtil.base(c, e);
        int delta = ThreadLocalRandom.current().nextInt(0, 2);
        EnemyUtil.scaleToLevel(h, Math.max(1, aroundLevel + delta));
        return h;
    }
}
