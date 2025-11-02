package infra;

import domain.combat.DamageModel;
import domain.hero.Hero;

public class LegacyDamageAdapter implements DamageModel {
    private final OldService svc = new OldService();

    public int compute(Hero attacker, Hero defender) {
        return Math.max(1, svc.calc(attacker.stats().atk(), defender.stats().def()));
    }

    static class OldService {
        int calc(int atk, int def) { return (atk * 12 / 10) - (def / 3); }
    }
}
