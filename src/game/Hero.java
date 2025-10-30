package game;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private final String name;
    private final Archetype archetype;
    private final Element element;

    private int maxHp, hp, attack, defense, speed;
    private int level = 1, xp = 0;
    private final int evolveAt = 6;
    private boolean evolved = false;

    private AttackStrategy strategy;
    private final List<GameObserver> observers = new ArrayList<>();

    public Hero(String name, Archetype archetype, Element element, int hp, int attack, int defense, int speed) {
        this.name = name; this.archetype = archetype; this.element = element;
        this.maxHp = hp; this.hp = hp; this.attack = attack; this.defense = defense; this.speed = speed;
    }

    public void register(GameObserver o) { if (o != null && !observers.contains(o)) observers.add(o); }
    public void unregister(GameObserver o) { observers.remove(o); }
    private void notifyAllObservers(GameEventType type, String msg) {
        HeroEvent ev = new HeroEvent(type, this, msg);
        for (GameObserver o : observers) o.onEvent(ev);
    }
    public void emit(GameEventType type, String msg) { notifyAllObservers(type, msg); }

    public void setStrategy(AttackStrategy s) {
        this.strategy = s;
        notifyAllObservers(GameEventType.STRATEGY_CHANGED, name + " выбрал " + s.name());
    }

    public void attack(Hero target) {
        if (strategy == null) setStrategy(new MeleeAttack());
        notifyAllObservers(GameEventType.ATTACK, name + " атакует " + target.name);
        int dealt = strategy.perform(this, target);
        notifyAllObservers(GameEventType.ATTACK, name + " нанёс " + dealt + " урона");
    }

    public void takeDamage(int dmg, String meta) {
        hp -= dmg; if (hp < 0) hp = 0;
        notifyAllObservers(GameEventType.DAMAGE_TAKEN, name + " получил " + dmg + " урона. " + meta);
        notifyAllObservers(GameEventType.HEALTH_CHANGED, "HP " + name + ": " + hp + "/" + maxHp);
        if (hp == 0) notifyAllObservers(GameEventType.DEATH, name + " пал в бою.");
    }

    public void takeExtraDamage(int extra, String why) {
        hp -= extra; if (hp < 0) hp = 0;
        notifyAllObservers(GameEventType.DAMAGE_TAKEN, name + " доп. урон " + extra + " (" + why + ")");
        notifyAllObservers(GameEventType.HEALTH_CHANGED, "HP " + name + ": " + hp + "/" + maxHp);
        if (hp == 0) notifyAllObservers(GameEventType.DEATH, name + " пал в бою.");
    }

    public void gainXp(int amount) {
        xp += amount;
        notifyAllObservers(GameEventType.INFO, name + " получил XP +" + amount + " (всего " + xp + ")");
        while (xp >= xpToNextLevel()) {
            xp -= xpToNextLevel(); level++;
            if (archetype == Archetype.WARRIOR) { maxHp += 10; attack += 4; defense += 3; speed += 1; }
            else if (archetype == Archetype.MAGE) { maxHp += 7; attack += 5; defense += 2; speed += 2; }
            else { maxHp += 8; attack += 4; defense += 2; speed += 3; }
            hp = Math.min(maxHp, hp + 10);
            notifyAllObservers(GameEventType.LEVEL_UP, name + " поднял уровень до " + level);
            if (!evolved && level >= evolveAt) {
                evolved = true;
                notifyAllObservers(GameEventType.EVOLVE, name + " эволюционировал. Доступна Ultimate.");
            }
        }
    }

    private int xpToNextLevel() { return 50 + (level * 20); }
    public void healFull() { hp = maxHp; }
    public boolean hasUltimate() { return evolved; }
    public boolean isAlive() { return hp > 0; }

    public String getName() { return name; }
    public Archetype getArchetype() { return archetype; }
    public Element getElement() { return element; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public int getHp() { return hp; }
    public int getLevel() { return level; }

    public String toString() {
        return name + " (" + archetype + "/" + element + ") Lvl " + level +
                " HP " + hp + "/" + maxHp + " ATK " + attack + " DEF " + defense + " SPD " + speed +
                (evolved ? " [ULT]" : "");
    }
}
