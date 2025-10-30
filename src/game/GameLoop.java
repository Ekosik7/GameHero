package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLoop {
    private final Scanner in;
    private final GameMap map = new GameMap(5);
    private final Random rnd = new Random();
    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final BattleSystem battle;
    private final List<GameObserver> observers = new ArrayList<>();

    public GameLoop(Scanner in) { this.in = in; this.battle = new BattleSystem(in); }

    public void registerObserver(GameObserver o) { if (o != null && !observers.contains(o)) observers.add(o); }

    public void start(Hero player) {
        emit(player, GameEventType.INFO, "Начало приключения. Карта 5x5. Старт: " + map.pos());
        System.out.println(map.miniMap());
        while (true) {
            System.out.println("\nКоманды: move n/s/e/w | status | strategy | map | quit");
            System.out.print("> ");
            String cmd = in.nextLine().trim().toLowerCase();
            if (cmd.startsWith("move") || cmd.startsWith("go")) {
                String[] parts = cmd.split("\\s+");
                if (parts.length < 2) { System.out.println("Куда? n/s/e/w"); continue; }
                boolean ok = map.move(parts[1]);
                if (!ok) { System.out.println("Дальше граница."); continue; }
                emit(player, GameEventType.MOVE, "Перемещение в " + map.pos());
                System.out.println(map.miniMap());
                if (rnd.nextDouble() < 0.30) {
                    Hero enemy = enemyFactory.randomEnemyNear(player.getLevel());
                    for (GameObserver o : observers) enemy.register(o);
                    emit(player, GameEventType.ENCOUNTER, "Вы встретили " + enemy.getName() + " (" + enemy.getArchetype() + "/" + enemy.getElement() + ")");
                    System.out.print("Сразиться? (y/n): ");
                    String ans = in.nextLine().trim().toLowerCase();
                    if (ans.startsWith("y")) {
                        boolean alive = battle.fight(player, enemy);
                        if (!alive) { emit(player, GameEventType.INFO, "Вы очнулись в центре локации."); break; }
                    } else emit(player, GameEventType.ESCAPE, "Вы проигнорировали встречу.");
                }
            } else if (cmd.equals("status")) {
                System.out.println(player);
            } else if (cmd.equals("strategy")) {
                System.out.println("1) Melee  2) Ranged  3) Magic  4) Ultimate*");
                String s = in.nextLine().trim();
                if (s.equals("1")) player.setStrategy(new MeleeAttack());
                else if (s.equals("2")) player.setStrategy(new RangedAttack());
                else if (s.equals("3")) player.setStrategy(new MagicAttack());
                else if (s.equals("4") && player.hasUltimate()) player.setStrategy(new UltimateAttack());
            } else if (cmd.equals("map")) {
                System.out.println(map.miniMap());
            } else if (cmd.equals("quit") || cmd.equals("exit")) {
                emit(player, GameEventType.INFO, "Вы вышли из игры."); break;
            } else System.out.println("Неизвестная команда.");
        }
    }

    private void emit(Hero subject, GameEventType type, String msg) {
        HeroEvent ev = new HeroEvent(type, subject, msg);
        for (GameObserver o : observers) o.onEvent(ev);
        if (subject != null) subject.emit(type, msg);
    }
}
