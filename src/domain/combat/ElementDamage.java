package domain.combat;

import domain.hero.Element;
import domain.hero.Hero;

public class ElementDamage implements Damage {
    private final Damage inner;
    private final Element atkElem, defElem;
    public ElementDamage(Damage inner, Element atkElem, Element defElem){
        this.inner=inner; this.atkElem=atkElem; this.defElem=defElem;
    }
    public int compute(Hero a, Hero d){
        int base = inner.compute(a,d);
        double m = atkElem.mult(defElem);
        return (int)Math.round(base * m);
    }
}
