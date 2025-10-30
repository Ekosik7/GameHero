package game;

public class RangedAttack implements AttackStrategy {
    @Override public String name() { return "Ranged"; }
    @Override public int perform(Hero a, Hero d) {
        int base = (int)(a.getAttack() * 0.9) - d.getDefense() / 2; if (base < 1) base = 1;
        double mult = a.getElement().multiplierAgainst(d.getElement());
        int dmg = (int)Math.round(base * mult);
        d.takeDamage(dmg, a.getName() + " выстрел (" + name() + ") x" + mult);
        return dmg;
    }
}
