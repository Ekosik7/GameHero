package domain.hero;

public class Hero {
    private final String name;
    private final HeroClass clazz;
    private final Element element;
    private Stats stats;
    private int hp;
    private int level = 1;
    private int xp = 0;

    public Hero(String name, HeroClass clazz, Element element, Stats stats) {
        this.name = name;
        this.clazz = clazz;
        this.element = element;
        this.stats = stats;
        this.hp = stats.hp();
    }

    public String name() { return name; }
    public HeroClass clazz() { return clazz; }
    public Element element() { return element; }
    public Stats stats() { return stats; }
    public int hp() { return hp; }
    public int level() { return level; }
    public boolean alive() { return hp > 0; }

    public void healFull() { hp = stats.hp(); }

    public void take(int dmg) { hp = Math.max(0, hp - dmg); }

    public void gain(int amount) {
        xp += amount;
        while (xp >= need()) {
            xp -= need();
            level++;
            stats = switch (clazz) {
                case WARRIOR -> stats.add(10, 4, 3, 1);
                case MAGE -> stats.add(7, 5, 2, 2);
                case ARCHER -> stats.add(8, 4, 2, 3);
            };
            hp = Math.min(stats.hp(), hp + 10);
        }
    }

    private int need() { return 50 + level * 20; }

    public String shortView() {
        return name + " L" + level + " HP " + hp + "/" + stats.hp() + " ATK " + stats.atk() + " DEF " + stats.def();
    }

    public String detailView() {
        return name + " " + clazz + "/" + element + " L" + level +
                " HP " + hp + "/" + stats.hp() + " ATK " + stats.atk() + " DEF " + stats.def() + " SPD " + stats.spd();
    }
}
