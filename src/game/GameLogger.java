package game;

public class GameLogger implements GameObserver {
    @Override
    public void onEvent(HeroEvent event) {
        System.out.println("[LOG] " + event.getType() + " :: " + event.getMessage());
    }
}
