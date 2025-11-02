package domain.combat;

import domain.hero.Hero;

public interface DamageModel {
    int compute(Hero attacker, Hero defender);
}
