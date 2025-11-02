package domain.combat;

import domain.hero.Hero;

public class BasicDamage implements Damage {
    private final double atkMul;
    private final double defDiv;
    public BasicDamage(double atkMul, double defDiv){ this.atkMul=atkMul; this.defDiv=defDiv; }
    public int compute(Hero a, Hero d){
        int v = (int)Math.round(a.stats().atk()*atkMul) - (int)Math.round(d.stats().def()*defDiv);
        return Math.max(1, v);
    }
}
