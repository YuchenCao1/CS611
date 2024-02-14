/**
 * This class defines a Sorcerer Hero type. As the assignment
 * states, these Heroes are favored on dexterity and agility.
 *
 */
public class Sorcerer extends Hero {
    public Sorcerer(HeroBuilder heroBuilder) {
        super(heroBuilder
                //Required details for all Warriors
                .manaIncreaseFactor(1.03)
                .strengthIncreaseFactor(1.001)
                .dexterityIncreaseFactor(1.13)
                .agilityIncreaseFactor(1.16)
        );
    }

    @Override
    public OutputColor getOutputColor(){
        return OutputColor.PURPLE;
    }

    @Override
    public String getBody() {
        return "-|-";
    }

    @Override
    public String getLegs(){
        return "|^|";
    }
}
