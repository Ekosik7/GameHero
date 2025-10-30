package game;

public class MagicAttack implements AttackStrategy {
    @Override public String name() { return "Magic"; }
    @Override public int perform(Hero a, Hero d) {
        int base = (int)(a.getAttack() * 1.1) - (int)(d.getDefense() * 0.45); if (base < 1) base = 1;
        double mult = a.getElement().multiplierAgainst(d.getElement());
        int dmg = (int)Math.round(base * mult);
        d.takeDamage(dmg, a.getName() + " заклинание (" + name() + ") x" + mult);
        return dmg;
    }
}
