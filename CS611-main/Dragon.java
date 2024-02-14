/**
 * Exoskeletons are monsters with an
 *  base damage advantage.
 */
public class Dragon extends Monster {

    public Dragon(final MonsterBuilder monsterBuilder){
        super(monsterBuilder);
        baseDamageIncreaseRate = 1.13;
    }

    @Override
    public String getBody() {
        return "<_/";
    }

    @Override
    public String getLegs() {
        return "   "; //no legs
    }
}
