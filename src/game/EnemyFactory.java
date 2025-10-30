package game;

import java.util.Random;

public class EnemyFactory {
    private final Random rnd = new Random();

    public Hero randomEnemyNear(int aroundLevel) {
        Archetype type;
        int t = rnd.nextInt(3);
        if (t == 0) type = Archetype.WARRIOR;
        else if (t == 1) type = Archetype.MAGE;
        else type = Archetype.ARCHER;

        Element el;
        int e = rnd.nextInt(5);
        if (e == 1) el = Element.WATER;
        else if (e == 2) el = Element.GRASS;
        else if (e == 3) el = Element.ELECTRIC;
        else if (e == 4) el = Element.ICE;
        else el = Element.FIRE;

        String name;
        if (type == Archetype.WARRIOR) name = "Rogue Warrior";
        else if (type == Archetype.MAGE) name = "Wild Mage";
        else name = "Forest Archer";

        Hero h = base(type, name, el);
        int delta = rnd.nextInt(3) - 1;
        int targetLvl = Math.max(1, aroundLevel + delta);
        for (int i = 1; i < targetLvl; i++) h.gainXp(50 + i * 20);
        h.healFull();
        return h;
    }

    private Hero base(Archetype type, String name, Element el) {
        if (type == Archetype.WARRIOR) return new Hero(name, type, el, 90, 16, 10, 8);
        if (type == Archetype.MAGE)    return new Hero(name, type, el, 75, 18, 8, 10);
        return new Hero(name, type, el, 80, 16, 8, 12);
    }
}
