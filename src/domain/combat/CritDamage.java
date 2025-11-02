package domain.combat;

import config.Config;
import domain.hero.Hero;
import infra.Randoms;

public class CritDamage implements Damage {
    private final Damage inner;
    public CritDamage(Damage inner){ this.inner=inner; }
    public int compute(Hero a, Hero d){
        int base = inner.compute(a,d);
        boolean crit = Randoms.range(1, 100) <= (int)(Config.CRIT_CHANCE * 100);
        return crit ? (int)Math.round(base * Config.CRIT_MULT) : base;
    }
}
