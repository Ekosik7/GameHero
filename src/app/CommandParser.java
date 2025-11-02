package app;

public final class CommandParser {
    private final String raw;

    public CommandParser(String line) {
        this.raw = line == null ? "" : line.trim().toLowerCase();
    }

    public Command command() {
        String t = firstToken();
        return Command.of(t);
    }

    public String arg() {
        String[] p = raw.split("\\s+");
        return p.length > 1 ? p[1] : "";
    }

    private String firstToken() {
        String[] p = raw.split("\\s+");
        return p.length == 0 ? "" : p[0];
    }
}
