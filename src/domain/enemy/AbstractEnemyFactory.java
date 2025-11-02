package domain.enemy;
import domain.hero.Hero;
public interface AbstractEnemyFactory { Hero create(int aroundLevel); }
