package app;

import config.Config;
import domain.combat.BasicDamage;
import domain.combat.CritDamage;
import domain.combat.Damage;
import domain.combat.ElementDamage;
import domain.enemy.AbstractEnemyFactory;
import domain.enemy.DungeonFactory;
import domain.enemy.EnemyFactory;
import domain.enemy.ForestFactory;
import domain.hero.Hero;
import domain.weapon.Bow;
import domain.weapon.Sword;
import domain.weapon.Weapon;
import infra.LegacyDamageAdapter;
import infra.Randoms;

import java.util.Locale;

public class Game {
    private final AbstractEnemyFactory forest = new ForestFactory();
    private final AbstractEnemyFactory dungeon = new DungeonFactory();
    private String currentWeapon = "melee";

    public void selectWeapon(String name) {
        String w = name.toLowerCase(Locale.ROOT);
        if (w.equals("melee") || w.equals("ranged") || w.equals("magic") || w.equals("legacy")) {
            currentWeapon = w;
            System.out.println("Оружие выбрано: " + currentWeapon);
        } else {
            System.out.println("Не знаю такое оружие. Доступно: melee, ranged, magic, legacy");
        }
    }

    public void fightOnce(Hero player) {
        Hero enemy = spawnEnemyNear(player);
        System.out.println("Враг: " + enemy.detail());

        while (player.alive() && enemy.alive()) {
            int dealt = playerHit(player, enemy);
            System.out.println(player.name() + " наносит " + dealt + ". " + enemy.name() + " HP: " + enemy.hp() + "/" + enemy.stats().hp());
            if (!enemy.alive()) break;

            enemyHit(enemy, player);
            System.out.println(enemy.name() + " бьёт в ответ. " + player.name() + " HP: " + player.hp() + "/" + player.stats().hp());
        }

        if (player.alive() && !enemy.alive()) {
            System.out.println("Победа! +" + Config.XP_WIN + " XP");
            player.gain(Config.XP_WIN);
            System.out.println("Теперь: " + player.detail());
        }
    }

    private Hero spawnEnemyNear(Hero player) {
        AbstractEnemyFactory f = Randoms.pick(2) == 0 ? forest : dungeon;
        return f.create(player.level());
    }

    private int playerHit(Hero atk, Hero def) {
        Weapon w = makeWeapon(currentWeapon);
        return hit(atk, def, w);
    }

    private void enemyHit(Hero enemy, Hero player) {
        String pick = switch (Randoms.pick(3)) {
            case 0 -> "melee";
            case 1 -> "ranged";
            default -> "magic";
        };
        Weapon w = makeWeapon(pick);
        hit(enemy, player, w);
    }

    private Weapon makeWeapon(String kind) {
        return switch (kind) {
            case "ranged" -> new Bow(new BasicDamage(Config.RANGED_ATK, Config.RANGED_DEF));
            case "magic"  -> new Sword(new BasicDamage(Config.MAGIC_ATK, Config.MAGIC_DEF));
            case "legacy" -> new Sword(new LegacyDamageAdapter());
            default       -> new Sword(new BasicDamage(Config.MELEE_ATK, Config.MELEE_DEF));
        };
    }

    private int hit(Hero atk, Hero def, Weapon w) {
        Damage pipeline = new CritDamage(new ElementDamage(w.model(), atk.element(), def.element()));
        int dmg = pipeline.compute(atk, def) + EnemyFactory.randomBonus();
        def.take(dmg);
        return dmg;
    }
}
