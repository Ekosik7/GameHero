package app;

import app.ui.Printer;
import config.Config;
import domain.hero.Element;
import domain.hero.Hero;
import domain.hero.HeroBuilder;
import domain.hero.HeroClass;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameFacade game = new GameFacade();

        Printer.banner("Hero GO: консольный забег");
        System.out.print("Как звать? Кто ты, воин? ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Hero";

        Hero player = new HeroBuilder()
                .name(name)
                .clazz(HeroClass.WARRIOR)
                .element(Element.FIRE)
                .build();

        Printer.ok("Герой создан: " + player.detailView());
        System.out.println(game.mapView());

        loop:
        while (true) {
            System.out.print("\n> ");
            CommandParser parser = new CommandParser(in.nextLine());
            Command cmd = parser.command();

            switch (cmd) {
                case MOVE -> {
                    String dir = parser.arg();
                    if (dir.isEmpty()) { Printer.warn("куда путь держим? n/s/e/w"); continue; }
                    if (!game.move(dir)) { Printer.warn("граница. дальше — пустота и баги."); continue; }
                    Printer.info("шаг сделан. вот карта:");
                    System.out.println(game.mapView());

                    if (game.shouldEncounter()) {
                        Hero enemy = game.spawnEnemyNear(player);
                        Printer.encounter("намечается движ: " + enemy.detailView());
                        System.out.print("в бой? (y/n): ");
                        String ans = in.nextLine().trim().toLowerCase();
                        if (ans.startsWith("y")) {
                            fight(in, game, player, enemy);
                            if (!player.alive()) {
                                Printer.rip("минус " + player.name() + ". было мощно.");
                                break loop;
                            }
                        } else {
                            Printer.say("пропустили. скорость — лучший доспех.");
                        }
                    }
                }
                case STATUS -> Printer.say(player.detailView());
                case MAP -> System.out.println(game.mapView());
                case QUIT -> { Printer.say("выйти — тоже стратегия. до связи."); break loop; }
                case UNKNOWN -> Printer.say("команды: move n/s/e/w | status | map | quit");
            }
        }

        Printer.banner("Сеанс завершён. сохранения в облаках воображения.");
    }

    private static void fight(Scanner in, GameFacade game, Hero player, Hero enemy) {
        Printer.banner("арена загружена: без предметов, только скилл");
        Printer.hpBar(player);
        Printer.hpBar(enemy);

        while (player.alive() && enemy.alive()) {
            Printer.say("чем бьём? melee | ranged | magic | legacy");
            String a = in.nextLine().trim().toLowerCase();

            int dealt;
            switch (a) {
                case "ranged" -> dealt = game.strikeRanged(player, enemy);
                case "magic"  -> dealt = game.strikeMagic(player, enemy);
                case "legacy" -> dealt = game.strikeLegacy(player, enemy);
                default       -> dealt = game.strikeMelee(player, enemy);
            }
            Printer.atk(player.name() + " вносит " + dealt + ". красиво.");
            Printer.hpBar(enemy);
            if (!enemy.alive()) break;

            game.enemyStrike(enemy, player);
            Printer.atk(enemy.name() + " ответил. не расслабляемся.");
            Printer.hpBar(player);
        }

        if (player.alive() && !enemy.alive()) {
            Printer.hype("победа. +" + Config.XP_WIN + " xp");
            game.reward(player);
            Printer.ok(player.detailView());
        } else if (!player.alive()) {
            Printer.rip("проигрыш. перезапуск спасёт честь.");
        }
    }
}
