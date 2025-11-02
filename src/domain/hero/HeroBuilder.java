package domain.hero;

public class HeroBuilder {
    private String name = "Hero";
    private HeroClass clazz = HeroClass.WARRIOR;
    private Element element = Element.FIRE;
    private Stats stats = new Stats(90,16,10,8);

    public HeroBuilder name(String v){ name=v; return this; }
    public HeroBuilder clazz(HeroClass v){ clazz=v; return this; }
    public HeroBuilder element(Element v){ element=v; return this; }
    public HeroBuilder stats(Stats v){ stats=v; return this; }

    public Hero build(){ return new Hero(name,clazz,element,stats); }
}
