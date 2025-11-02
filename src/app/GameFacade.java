package app;

import domain.combat.DamageModel;
import domain.combat.decorator.CritDecorator;
import domain.combat.decorator.ElementalDecorator;
import domain.combat.impl.BasicDamage;
import domain.enemies.AbstractEnemyFactory;
import domain.enemies.DungeonEnemyFactory;
import domain.enemies.EnemyUtil;
import domain.enemies.ForestEnemyFactory;
import domain.hero.Hero;
import domain.map.GameMap;
import domain.weapon.Bow;
import domain.weapon.Sword;
import domain.weapon.Weapon;
import infra.LegacyDamageAdapter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameFacade {
    private final GameMap map = new GameMap();
    private final AbstractEnemyFactory forest = new ForestEnemyFactory();
    private final AbstractEnemyFactory dungeon = new DungeonEnemyFactory();

    public boolean move(String dir) { return map.move(dir); }
    public String mapView() { return map.mini(); }

    public boolean shouldEncounter() { return ThreadLocalRandom.current().nextDouble() < 0.30; }

    public Hero spawnEnemyNear(Hero player) {
        var f = ThreadLocalRandom.current().nextBoolean() ? forest : dungeon;
        return f.create(player.level());
    }

    private DamageModel pipelineFor(Hero attacker, Hero defender, DamageModel base) {
        DamageModel d = base;
        d = new ElementalDecorator(d, attacker.element(), defender.element());
        d = new CritDecorator(d, 0.15, 1.5);
        return d;
    }

    private int strike(Hero atk, Hero def, Weapon w) {
        DamageModel decorated = pipelineFor(atk, def, w.damageModel());
        int dmg = decorated.compute(atk, def) + EnemyUtil.randomBonus(10, 30);
        def.take(dmg);
        return dmg;
    }

    public int strikeMelee(Hero atk, Hero def) {
        Weapon w = new Sword(new BasicDamage(1.0, 0.5));
        return strike(atk, def, w);
    }

    public int strikeRanged(Hero atk, Hero def) {
        Weapon w = new Bow(new BasicDamage(0.9, 0.5));
        return strike(atk, def, w);
    }

    public int strikeMagic(Hero atk, Hero def) {
        Weapon w = new Sword(new BasicDamage(1.1, 0.45));
        return strike(atk, def, w);
    }

    public int strikeLegacy(Hero atk, Hero def) {
        Weapon w = new Sword(new LegacyDamageAdapter());
        return strike(atk, def, w);
    }

    public void enemyStrike(Hero enemy, Hero player) {
        List<Runnable> opts = List.of(
                () -> strikeMelee(enemy, player),
                () -> strikeRanged(enemy, player),
                () -> strikeMagic(enemy, player)
        );
        opts.get(ThreadLocalRandom.current().nextInt(opts.size())).run();
    }

    public void reward(Hero player) { player.gain(120); }
}
