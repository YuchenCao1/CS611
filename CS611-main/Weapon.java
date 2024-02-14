public class Weapon extends RPGItem {
    protected int damage;
    protected int numberOfHandsToUse;
    private final double damageIncreaseFactor = 1.08;
    private final double marketPriceIncreaseFactor = 1.09;
    public Weapon(final String name, final int level,
                  final double cost, final int damage, final int numberOfHandsToUse){
        super(name, level, cost);
        this.damage = damage;
        this.numberOfHandsToUse = numberOfHandsToUse;
    }

    public int getDamage(){ return damage; }
    public int getNumberOfHandsToUse(){ return numberOfHandsToUse; }

    @Override
    public String getItemContent(){
        StringBuilder result = new StringBuilder();
        result.append(" - level ").append(currentLevel).append("\n");
        result.append(" - damage ").append(damage).append("\n");
        result.append(" - # of hands ").append(numberOfHandsToUse);

        return result.toString();
    }
    @Override
    public void setLevel(final int newLevel) {
        final int levelDifference = newLevel - currentLevel;
        damage = (int) (damage * Math.pow(damageIncreaseFactor, levelDifference));
        marketPrice = (int)(marketPrice * Math.pow(marketPriceIncreaseFactor, levelDifference));
        currentLevel = newLevel;
    }
}
