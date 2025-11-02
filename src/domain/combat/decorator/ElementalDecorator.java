package domain.combat.decorator;

import domain.combat.DamageModel;
import domain.hero.Element;
import domain.hero.Hero;

public class ElementalDecorator implements DamageModel {
    private final DamageModel inner;
    private final Element atk;
    private final Element def;

    public ElementalDecorator(DamageModel inner, Element atk, Element def) {
        this.inner = inner;
        this.atk = atk;
        this.def = def;
    }

    public int compute(Hero a, Hero d) {
        int v = inner.compute(a, d);
        double m = atk.mult(def);
        return (int)Math.round(v * m);
    }
}
