public class Armor extends RPGItem {
    private int damageReduction;
    private final double marketPriceIncreaseFactor = 1.15;
    private final double damageReductionFactor = 1.12;

    public Armor(final String name, final int initialLevel, final double initialCost, final int damageReduction){
        super(name, initialLevel, initialCost);
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction(){ return damageReduction; }

    @Override
    public String getItemContent(){
        StringBuilder result = new StringBuilder();
        result.append(" - level ").append(currentLevel).append("\n");
        result.append(" - damage reduction  ").append(damageReduction);

        return result.toString();
    }
    @Override
    public void setLevel(final int newLevel) {
        final int levelDifference = newLevel - currentLevel;
        damageReduction = (int) (damageReduction * Math.pow(damageReductionFactor, levelDifference));
        marketPrice = (int)(marketPrice * Math.pow(marketPriceIncreaseFactor, levelDifference));
        currentLevel = newLevel;
    }
}
