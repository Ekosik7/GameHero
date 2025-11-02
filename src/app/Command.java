package app;

public enum Command {
    MOVE, STATUS, MAP, QUIT, UNKNOWN;

    public static Command of(String token) {
        return switch (token) {
            case "move", "go" -> MOVE;
            case "status" -> STATUS;
            case "map" -> MAP;
            case "quit", "exit" -> QUIT;
            default -> UNKNOWN;
        };
    }
}
