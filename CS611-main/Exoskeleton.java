/**
 * Exoskeletons are monsters with an
 *  defense advantage.
 */
public class Exoskeleton extends Monster {

    public Exoskeleton(final MonsterBuilder monsterBuilder){
        super(monsterBuilder);
        defenseValueIncreaseRate = 1.12;
    }

    @Override
    public String getBody() {
                                                    return "E|∃";
    }

    @Override
    public String getLegs() {
        return "/ \\";
    }
}
