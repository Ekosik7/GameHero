package infra;

import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {
    private Randoms() {}
    public static int pick(int size) { return ThreadLocalRandom.current().nextInt(size); }
    public static int range(int a, int b) { return ThreadLocalRandom.current().nextInt(a, b + 1); }
}
