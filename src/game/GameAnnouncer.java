package game;

public class GameAnnouncer implements GameObserver {
    @Override
    public void onEvent(HeroEvent event) {
        switch (event.getType()) {
            case MOVE:            System.out.println("🧭 " + event.getMessage()); break;
            case ENCOUNTER:       System.out.println("👀 " + event.getMessage()); break;
            case ATTACK:          System.out.println("⚔️  " + event.getMessage()); break;
            case DAMAGE_TAKEN:    System.out.println("💢 " + event.getMessage()); break;
            case HEALTH_CHANGED:  System.out.println("❤️ " + event.getMessage()); break;
            case LEVEL_UP:        System.out.println("⭐ " + event.getMessage()); break;
            case EVOLVE:          System.out.println("🌟 " + event.getMessage()); break;
            case DEATH:           System.out.println("☠️  " + event.getMessage()); break;
            case VICTORY:         System.out.println("🎉 " + event.getMessage()); break;
            case STRATEGY_CHANGED:System.out.println("🔄 " + event.getMessage()); break;
            case ESCAPE:          System.out.println("💨 " + event.getMessage()); break;
            default:              System.out.println("📢 " + event.getMessage());
        }
    }
}
