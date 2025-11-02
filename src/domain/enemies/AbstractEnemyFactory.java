package domain.enemies;

import domain.hero.Hero;

public interface AbstractEnemyFactory {
    Hero create(int aroundLevel);
}
