public class CharacterLevel {
    private int currentLevel;
    private int currentExperience;
    private int maximumExperience;
    private final double experienceGrowthByLevel;

    public CharacterLevel(final int initialLevel, final int initialCurrentExperience,
                          final int initialMaxExperience,
                          final double experienceGrowthByLevel
    ){
        currentLevel = initialLevel;
        currentExperience = initialCurrentExperience;
        maximumExperience = initialMaxExperience;
        this.experienceGrowthByLevel = experienceGrowthByLevel;
    }

    /**
     * Method add experiences to the character's level. If there was a level up,
     * then the method returns true.
     */
    public boolean addExperience(final int experienceAmount){
        int experienceLeftToAdd = experienceAmount;
        boolean didLevelUp = false;

        while (experienceLeftToAdd > 0){
            final int experienceToLevelUp = maximumExperience - currentExperience;
            if (experienceLeftToAdd >= experienceToLevelUp){
                didLevelUp = true;
                experienceLeftToAdd -= experienceToLevelUp;
                levelUp();
            }
            else{
                currentExperience += experienceLeftToAdd;
                break;
            }
        }

        return didLevelUp;
    }

    public void levelUp(){
        currentLevel += 1;
        currentExperience = 0;
        setNewMaxExperience();
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    private void setNewMaxExperience(){
        maximumExperience = (int) (maximumExperience * experienceGrowthByLevel);
    }

    public String toString(){
        return "Level "+currentLevel+" ("+currentExperience+" / "+maximumExperience+" exp)";
    }
}
