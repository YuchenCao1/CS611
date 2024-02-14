/**
 * This class defines a Paladin Hero type. As the assignment
 * states, these Heroes are favored on strength and dexterity.
 *
 */
public class Paladin extends Hero {
    public Paladin(HeroBuilder heroBuilder) {
        super(heroBuilder // builder already contains hero-specific details

                //Rest are required details for all Warriors
                .manaIncreaseFactor(1.02)
                .strengthIncreaseFactor(1.15)
                .dexterityIncreaseFactor(1.12)
                .agilityIncreaseFactor(1.02)
        );
    }

    @Override
    public OutputColor getOutputColor(){
        return OutputColor.CYAN;
    }

    @Override
    public String getBody() {
        return "-#-";
    }

    @Override
    public String getLegs(){
        return "# #";
    }

}