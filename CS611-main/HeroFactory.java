/**
 * This factory is used to generate heroes
 */
public final class HeroFactory {
    public static Hero createHero(Class<? extends Hero> heroClass, Hero.HeroBuilder heroBuilder){
        switch (heroClass.getName()){
            case "Sorcerer":
                return new Sorcerer(heroBuilder);
            case "Paladin":
                return new Paladin(heroBuilder);
            case "Warrior":
                return new Warrior(heroBuilder);
            default:
                throw new IllegalArgumentException("Unknown hero type found when trying to be created: "+heroClass.getTypeName());
        }
    }
}
