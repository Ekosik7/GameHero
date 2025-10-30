package game;

public interface AttackStrategy {
    String name();
    int perform(Hero attacker, Hero defender);
}
