package domain.map;

import config.Config;

public class GameMap {
    private final int size = Config.MAP_SIZE;
    private int x = 2, y = 2;

    public boolean move(String dir) {
        int nx = x, ny = y;
        switch (dir.toLowerCase()) {
            case "n", "north" -> ny--;
            case "s", "south" -> ny++;
            case "w", "west" -> nx--;
            case "e", "east" -> nx++;
            default -> { return false; }
        }
        if (nx < 0 || ny < 0 || nx >= size || ny >= size) return false;
        x = nx; y = ny; return true;
    }

    public String mini() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                sb.append(i == x && j == y ? "@" : ".");
                if (i < size - 1) sb.append(" ");
            }
            if (j < size - 1) sb.append("\n");
        }
        return sb.toString();
    }
}
