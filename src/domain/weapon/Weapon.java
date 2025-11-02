package domain.weapon;

import domain.combat.Damage;

public abstract class Weapon {
    protected final Damage model;
    protected Weapon(Damage m){ this.model=m; }
    public Damage model(){ return model; }
    public abstract String id();
}
