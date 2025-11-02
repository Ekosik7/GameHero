package infra;

import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {
    private Randoms() {}

    public static boolean chance(double probability) {
        return ThreadLocalRandom.current().nextDouble() < probability;
    }

    public static int range(int minInclusive, int maxInclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }

    public static int pick(int size) {
        return ThreadLocalRandom.current().nextInt(size);
    }
}
