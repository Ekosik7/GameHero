package game;

public class MeleeAttack implements AttackStrategy {
    @Override public String name() { return "Melee"; }
    @Override public int perform(Hero a, Hero d) {
        int base = a.getAttack() - d.getDefense() / 2; if (base < 1) base = 1;
        double mult = a.getElement().multiplierAgainst(d.getElement());
        int dmg = (int)Math.round(base * mult);
        d.takeDamage(dmg, a.getName() + " удар (" + name() + ") x" + mult);
        return dmg;
    }
}
