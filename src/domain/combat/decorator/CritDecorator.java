package domain.combat.decorator;

import domain.combat.DamageModel;
import domain.hero.Hero;

import java.util.concurrent.ThreadLocalRandom;

public class CritDecorator implements DamageModel {
    private final DamageModel inner;
    private final double chance;
    private final double mult;

    public CritDecorator(DamageModel inner, double chance, double mult) {
        this.inner = inner;
        this.chance = chance;
        this.mult = mult;
    }

    public int compute(Hero a, Hero d) {
        int v = inner.compute(a, d);
        if (ThreadLocalRandom.current().nextDouble() < chance) return (int)Math.round(v * mult);
        return v;
    }
}
