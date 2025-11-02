package domain.combat.impl;

import domain.combat.DamageModel;
import domain.hero.Hero;

public class BasicDamage implements DamageModel {
    private final double atkMul;
    private final double defDiv;

    public BasicDamage(double atkMul, double defDiv) {
        this.atkMul = atkMul;
        this.defDiv = defDiv;
    }

    public int compute(Hero a, Hero d) {
        int base = (int)Math.round(a.stats().atk() * atkMul) - (int)Math.round(d.stats().def() * defDiv);
        return Math.max(1, base);
    }
}
