package domain.weapon;

import domain.combat.DamageModel;

public abstract class Weapon {
    protected final DamageModel model;
    protected Weapon(DamageModel m) { this.model = m; }
    public DamageModel damageModel() { return model; }
}
