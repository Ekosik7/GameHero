package game;

public class HeroEvent {
    private final GameEventType type;
    private final Hero subject;
    private final String message;

    public HeroEvent(GameEventType type, Hero subject, String message) {
        this.type = type;
        this.subject = subject;
        this.message = message;
    }

    public GameEventType getType() { return type; }
    public Hero getSubject() { return subject; }
    public String getMessage() { return message; }
}
