package game;

public class UltimateAttack implements AttackStrategy {
    @Override public String name() { return "Ultimate"; }
    @Override public int perform(Hero a, Hero d) {
        int base = (int)(a.getAttack() * 1.5) - (int)(d.getDefense() * 0.4); if (base < 1) base = 1;
        double mult = a.getElement().multiplierAgainst(d.getElement());
        int dmg = (int)Math.round(base * mult);
        d.takeDamage(dmg, a.getName() + " УЛЬТ (" + name() + ") x" + mult);
        return dmg;
    }
}
