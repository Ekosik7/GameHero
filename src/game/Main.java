package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        GameLogger logger = new GameLogger();
        GameAnnouncer announcer = new GameAnnouncer();

        System.out.print("Введите имя героя: ");
        String name = in.nextLine().trim();
        if (name.isEmpty()) name = "Hero";

        Hero player = new Hero(name, Archetype.WARRIOR, Element.FIRE, 90, 16, 10, 8);
        player.register(logger);
        player.register(announcer);
        player.setStrategy(new MeleeAttack());
        player.healFull();

        GameLoop loop = new GameLoop(in);
        loop.registerObserver(logger);
        loop.registerObserver(announcer);
        loop.start(player);
    }
}
