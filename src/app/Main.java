package app;

import domain.hero.Element;
import domain.hero.Hero;
import domain.hero.HeroBuilder;
import domain.hero.HeroClass;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game();

        System.out.println("=== Hero GO (lite) ===");
        System.out.print("Как звать? ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Hero";

        Hero player = new HeroBuilder()
                .name(name)
                .clazz(HeroClass.WARRIOR)
                .element(Element.FIRE)
                .build();

        System.out.println("Герой создан: " + player.detail());
        System.out.println("Команды: fight | status | weapon melee|ranged|magic|legacy | quit");

        while (true) {
            System.out.print("> ");
            String line = in.nextLine().trim().toLowerCase();
            if (line.isEmpty()) continue;

            if (line.equals("fight")) {
                game.fightOnce(player);
                if (!player.alive()) { System.out.println("Пал герой " + player.name() + ". Игра окончена."); break; }
            } else if (line.equals("status")) {
                System.out.println(player.detail());
            } else if (line.startsWith("weapon ")) {
                String w = line.substring("weapon ".length()).trim();
                game.selectWeapon(w);
            } else if (line.equals("quit") || line.equals("exit")) {
                System.out.println("Выход. Увидимся позже.");
                break;
            } else {
                System.out.println("Команды: fight | status | weapon melee|ranged|magic|legacy | quit");
            }
        }
    }
}
