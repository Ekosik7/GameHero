package game;

import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    private final Scanner in;
    private final Random rnd = new Random();

    public BattleSystem(Scanner in) { this.in = in; }

    public boolean fight(Hero player, Hero enemy) {
        player.emit(GameEventType.ENCOUNTER, "Бой! Противник: " + enemy);
        while (player.isAlive() && enemy.isAlive()) {
            if (!playerTurn(player, enemy)) return true;
            if (!enemy.isAlive() || !player.isAlive()) break;
            enemyTurn(enemy, player);
        }
        if (player.isAlive() && !enemy.isAlive()) {
            player.emit(GameEventType.VICTORY, "Победа над " + enemy.getName());
            player.gainXp(120);
            return true;
        }
        if (!player.isAlive()) player.emit(GameEventType.INFO, "Вы очнулись позже в центре локации.");
        return player.isAlive();
    }

    private boolean playerTurn(Hero player, Hero enemy) {
        while (true) {
            System.out.println("\nТвой ход: attack | strategy | run");
            String cmd = in.nextLine().trim().toLowerCase();
            if (cmd.equals("attack") || cmd.equals("a")) { doAttack(player, enemy); return true; }
            else if (cmd.equals("strategy") || cmd.equals("s")) {
                chooseStrategy(player);
                player.emit(GameEventType.STRATEGY_CHANGED, "Игрок сменил стратегию.");
            } else if (cmd.equals("run") || cmd.equals("r")) {
                if (tryRun()) { player.emit(GameEventType.ESCAPE, "Вы успешно убежали."); return false; }
                else { player.emit(GameEventType.INFO, "Не удалось убежать!"); return true; }
            } else System.out.println("Не понял команду.");
        }
    }

    private void enemyTurn(Hero enemy, Hero player) {
        if (player.getDefense() > enemy.getAttack() && enemy.hasUltimate()) enemy.setStrategy(new UltimateAttack());
        else if (player.getDefense() > enemy.getAttack()) enemy.setStrategy(new MagicAttack());
        else enemy.setStrategy(new MeleeAttack());
        doAttack(enemy, player);
    }

    private void chooseStrategy(Hero h) {
        System.out.println("1) Melee  2) Ranged  3) Magic  4) Ultimate*");
        String s = in.nextLine().trim();
        if (s.equals("1")) h.setStrategy(new MeleeAttack());
        else if (s.equals("2")) h.setStrategy(new RangedAttack());
        else if (s.equals("3")) h.setStrategy(new MagicAttack());
        else if (s.equals("4") && h.hasUltimate()) h.setStrategy(new UltimateAttack());
    }

    private void doAttack(Hero atk, Hero def) {
        int before = def.getHp();
        atk.attack(def);
        int randomBonus = 10 + rnd.nextInt(21);
        def.takeExtraDamage(randomBonus, "случайный урон " + randomBonus);
        int after = def.getHp();
        atk.emit(GameEventType.ATTACK, atk.getName() + " завершает удар: HP цели " + before + " → " + after);
        if (!def.isAlive()) def.emit(GameEventType.DEATH, def.getName() + " пал в бою.");
    }

    private boolean tryRun() { return rnd.nextDouble() < 0.5; }
}
