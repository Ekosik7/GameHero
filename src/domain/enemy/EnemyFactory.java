package domain.enemy;

import config.Config;
import domain.hero.*;
import infra.Randoms;

public final class EnemyFactory {
    private EnemyFactory(){}

    public static Hero base(HeroClass c, Element e){
        return switch (c){
            case WARRIOR -> new Hero("Rogue Warrior", c, e, new Stats(90,16,10,8));
            case MAGE    -> new Hero("Wild Mage",    c, e, new Stats(75,18, 8,10));
            case ARCHER  -> new Hero("Forest Archer",c, e, new Stats(80,16, 8,12));
        };
    }

    public static Element rndElem(){ return Element.values()[Randoms.pick(Element.values().length)]; }

    public static void scaleTo(Hero h, int targetLevel){
        for(int i=1;i<targetLevel;i++) h.gain(50 + i*20);
    }

    public static int randomBonus(){ return Randoms.range(Config.BONUS_MIN, Config.BONUS_MAX); }
}
