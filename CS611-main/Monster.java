/**
 * This class establishes the base features of a Monster
 * in the game Legends: Monsters and Heroes
 */
public abstract class Monster implements Avatarable, Levelable {
    protected String name;
    protected int level;
    protected Health health;
    protected int baseDamage;
    protected int defenseValue;
    protected int dodgeAbility;
    protected final double healthIncreaseRate = 1.07;
    protected double baseDamageIncreaseRate;
    protected double defenseValueIncreaseRate;
    protected double dodgeAgilityIncreaseRate;

    //Default limit values
    private final int maxDodgeAbility = 85; //max dodge percentage to balance game

    public Monster(MonsterBuilder monsterBuilder){
        name = monsterBuilder.name;
        level = monsterBuilder.level;
        health = monsterBuilder.health;
        baseDamage = monsterBuilder.damage;
        defenseValue = monsterBuilder.defense;
        dodgeAbility = monsterBuilder.dodgeChance;

        // Setting default monster rates
        baseDamageIncreaseRate = 1.07;
        defenseValueIncreaseRate = 1.02;
        dodgeAgilityIncreaseRate = 1.05;
    }

    /**
     * Method to see whether hero will dodge an attack
     */
    public boolean doesDodgeAttack(){
        final double randomNumber = Math.random()*100;
        return dodgeAbility > randomNumber; //Max odds are 50%;
    }

    /**
     * Method to attack a given hero
     */
    public void attackHero(Hero heroToAttack){
        System.out.println(OutputColorer.withColor(getOutputColor(),
                "Monster "+this.name+" has attacked "+
                        heroToAttack)+OutputColorer.withColor(getOutputColor(), "!"));
        heroToAttack.takeDamage(baseDamage);
    }


    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void levelUp() {
        setLevel(level + 1);
    }

    public void setLevel(final int newLevel){
        final int levelDifference = newLevel - level;

        final int newHealth;
        if (levelDifference > 0) {
            baseDamage = (int) (baseDamage * Math.pow(baseDamageIncreaseRate, levelDifference));
            defenseValue = (int) (defenseValue * Math.pow(defenseValueIncreaseRate, levelDifference));
            dodgeAbility = Math.min(maxDodgeAbility, (int) (dodgeAbility * Math.pow(dodgeAgilityIncreaseRate, levelDifference)));

            newHealth = (int) (health.getCurrentHealth() + 1000 * Math.pow(healthIncreaseRate, levelDifference));
        }
        else if (levelDifference < 0){
            baseDamage = (int)(baseDamage / Math.pow(baseDamageIncreaseRate, -levelDifference)); //preventing overflow so repeating above
            defenseValue = (int)(defenseValue / Math.pow(defenseValueIncreaseRate, -levelDifference));
            dodgeAbility = (int)(dodgeAbility / Math.pow(dodgeAgilityIncreaseRate, -levelDifference));
            dodgeAbility = Math.min(maxDodgeAbility, dodgeAbility);

            newHealth = (int) (health.getCurrentHealth() - 1000 * Math.pow(healthIncreaseRate, -1*levelDifference));
        }
        else{
            newHealth = health.getCurrentHealth();
        }

        health.setMaximumHealth(newHealth);
        level = newLevel;
    }

    /**
     * Method for monster to take damage
     * @return whether damage was taken
     */
    public boolean takeDamage(final int damageToTake){
        //See if monster dodges first
        if (doesDodgeAttack()){
            System.out.println(this + OutputColorer.withColor(getOutputColor(), " has dodged the attack!"));
            return false;
        }
        //Monsters do also have a defense mechanism!
        final int damageWithDefense = damageToTake - (int)(damageToTake*((double)defenseValue/10000));
        System.out.println(this + OutputColorer.withColor(getOutputColor(), " has taken "+damageWithDefense+" damage!"));
        health.takeDamage(damageToTake);
        if (!health.isAlive()){
            System.out.println(OutputColorer.withColor(getOutputColor(), "Monster "+this.name+" has fainted!"));
        }
        return true;
    }

    //Simply use the first name initial as their symbol
    @Override
    public char getSymbol() {
        return name.charAt(0);
    }

    @Override
    //All monsters will be red
    public OutputColor getOutputColor() {
        return OutputColor.RED;
    }

    @Override
    public String toString(){
        return OutputColorer.withColor(getOutputColor(), name);
    }

    /**
     * Displays the statistics of a certain {@link Monster}
     * Increase factors are ignored as they are used in the game's
     * backend.
     * Stats are in bullet points format.
     */
    public String getStats(){
        StringBuilder stats = new StringBuilder();
        stats.append(" - level:     ").append(level).append("\n");
        stats.append(" - health:    ").append(health).append("\n");
        stats.append(" - damage:    ").append(baseDamage).append("\n");
        stats.append(" - defense:   ").append(defenseValue).append("\n");
        stats.append(" - dodging:   ").append(dodgeAbility);
        return stats.toString();
    }

    /**
     * Class used to generate monsters in a simple manner.
     */
    public final static class MonsterBuilder{
        private String name;
        private Health health;
        private int level;
        private int damage;
        private int defense;
        private int dodgeChance;
        private final double healthIncreaseRate = 1.07;

        //adjusts health based on level
        private void adjustHealth(){
            health = new Health(1000);
            final int newHealth = (int)(health.getCurrentHealth() + 1000*Math.pow(healthIncreaseRate, level));
            health.setMaximumHealth(newHealth);
        }

        public MonsterBuilder name(final String name){
            this.name = name;
            return this;
        }

        public MonsterBuilder level(final int level){
            this.level = level;
            adjustHealth();
            return this;
        }

        public MonsterBuilder damage(final int damage){
            this.damage = damage;
            return this;
        }

        public MonsterBuilder defense(final int defense){
            this.defense = defense;
            return this;
        }

        public MonsterBuilder dodgeChance(final int dodgeChance){
            this.dodgeChance = dodgeChance;
            return this;
        }
    }
}
