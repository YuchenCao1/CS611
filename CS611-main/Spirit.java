/**
 * Exoskeletons are monsters with an
 *  dodge advantage.
 */
public class Spirit extends Monster {

    public Spirit(final MonsterBuilder monsterBuilder){
        super(monsterBuilder);
        dodgeAgilityIncreaseRate = 1.08;
    }

    @Override
    public String getBody() {
        return "< >";
    }

    @Override
    public String getLegs() {
        return " w ";
    }
}
