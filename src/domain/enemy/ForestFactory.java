package domain.enemy;

import domain.hero.*;
import infra.Randoms;

public class ForestFactory implements AbstractEnemyFactory {
    public Hero create(int aroundLevel){
        HeroClass c = switch (Randoms.pick(3)){
            case 0 -> HeroClass.ARCHER;
            case 1 -> HeroClass.WARRIOR;
            default -> HeroClass.MAGE;
        };
        Element e = EnemyFactory.rndElem();
        Hero h = EnemyFactory.base(c,e);
        int delta = Randoms.range(-1,1);
        EnemyFactory.scaleTo(h, Math.max(1, aroundLevel + delta));
        return h;
    }
}
