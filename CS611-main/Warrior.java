import java.util.ArrayList;

/**
 * This class defines a Warrior Hero type. As the assignment
 * states, these Heroes are favored on strength and agility.
 *
 */
public class Warrior extends Hero {
    public Warrior(HeroBuilder heroBuilder) {
        super(heroBuilder
                //Requiredthe  details for all Warriors
                .manaIncreaseFactor(1.02)
                .strengthIncreaseFactor(1.16)
                .dexterityIncreaseFactor(1.02)
                .agilityIncreaseFactor(1.11)
        );
    }

    @Override
    public OutputColor getOutputColor(){
        return OutputColor.GREEN;
    }

    @Override
    public String getBody(){
        return "/#\\";
    }

    @Override
    public String getLegs(){
        return "/ \\";
    }
}
