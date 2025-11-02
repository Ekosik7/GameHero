package domain.combat;
import domain.hero.Hero;
public interface Damage { int compute(Hero attacker, Hero defender); }
