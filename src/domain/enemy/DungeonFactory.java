package domain.enemy;

import domain.hero.*;
import infra.Randoms;

public class DungeonFactory implements AbstractEnemyFactory {
    public Hero create(int aroundLevel){
        HeroClass c = switch (Randoms.pick(3)){
            case 0 -> HeroClass.WARRIOR;
            case 1 -> HeroClass.MAGE;
            default -> HeroClass.ARCHER;
        };
        Element e = EnemyFactory.rndElem();
        Hero h = EnemyFactory.base(c,e);
        int delta = Randoms.range(0,1);
        EnemyFactory.scaleTo(h, Math.max(1, aroundLevel + delta));
        return h;
    }
}
