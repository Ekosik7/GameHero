package game;

public class GameMap {
    private final int size;
    private int x = 2, y = 2;

    public GameMap(int size) { this.size = size; }

    public String pos() { return "(" + x + "," + y + ")"; }

    public boolean move(String dir) {
        int nx = x, ny = y;
        String d = dir.toLowerCase();
        if (d.equals("north") || d.equals("n")) ny--;
        else if (d.equals("south") || d.equals("s")) ny++;
        else if (d.equals("west") || d.equals("w")) nx--;
        else if (d.equals("east") || d.equals("e")) nx++;
        else return false;
        if (nx < 0 || ny < 0 || nx >= size || ny >= size) return false;
        x = nx; y = ny; return true;
    }

    public String miniMap() {
        StringBuilder sb = new StringBuilder();
        for (int j=0;j<size;j++) {
            for (int i=0;i<size;i++) {
                sb.append(i==x && j==y ? "🧭" : "·");
                if (i<size-1) sb.append(" ");
            }
            if (j<size-1) sb.append("\n");
        }
        return sb.toString();
    }
}
