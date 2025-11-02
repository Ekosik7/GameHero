package app.ui;

import domain.hero.Hero;

public final class Printer {
    private Printer() {}

    public static void banner(String text) { System.out.println("\n== " + text + " =="); }
    public static void say(String text) { System.out.println(text); }
    public static void info(String text) { System.out.println("[info] " + text); }
    public static void ok(String text) { System.out.println("[ok] " + text); }
    public static void warn(String text) { System.out.println("[hmm] " + text); }
    public static void rip(String text) { System.out.println("[rip] " + text); }
    public static void hype(String text) { System.out.println("[gg] " + text); }
    public static void hpBar(Hero h) { System.out.println("hp " + h.name() + ": " + h.hp() + "/" + h.stats().hp()); }
    public static void encounter(String line) { System.out.println("встреча: " + line); }
    public static void atk(String line) { System.out.println("удар: " + line); }
}
