package infra;

import domain.combat.Damage;
import domain.hero.Hero;

public class LegacyDamageAdapter implements Damage {
    private final OldService s = new OldService();
    public int compute(Hero a, Hero d) { return Math.max(1, s.calc(a.stats().atk(), d.stats().def())); }
    static class OldService { int calc(int atk, int def) { return (atk * 12 / 10) - (def / 3); } }
}
