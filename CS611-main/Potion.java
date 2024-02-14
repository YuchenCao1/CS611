public class Potion extends RPGItem implements Consumable {
    private PotionEffect potionEffect;
    private final double agilityIncreaseFactor = 1.02;
    private final double defenseIncreaseFactor = 1.03;
    private final double dexterityIncreaseFactor = 1.03;
    private final double healthIncreaseFactor = 1.05;
    private final double manaIncreaseFactor = 1.09;
    private final double strengthIncreaseFactor = 1.04;
    private final double marketPriceIncreaseFactor = 1.08;
    private int numberOfUsesLeft = 1;

    public Potion(final String name, final int initialLevel, final double initialCost, final PotionEffect potionEffect){
        super(name, initialLevel, initialCost);
        this.potionEffect = potionEffect;
    }

    public PotionEffect getPotionEffect(){ return potionEffect; }

    public int getNumberOfUsesLeft(){
        return numberOfUsesLeft;
    }

    @Override
    public void consumeItem(){
        numberOfUsesLeft--;
    }

    @Override
    public String getItemContent(){
        StringBuilder result = new StringBuilder();
        result.append(" - level ").append(currentLevel).append("\n");
        result.append(" - agility   (+) ").append(potionEffect.getAgilityIncrease()).append("\n");
        result.append(" - defense   (+) ").append(potionEffect.getDefenseIncrease()).append("\n");
        result.append(" - dexterity (+) ").append(potionEffect.getDexterityIncrease()).append("\n");
        result.append(" - health    (+) ").append(potionEffect.getHealthIncrease()).append("\n");
        result.append(" - mana      (+) ").append(potionEffect.getManaIncrease()).append("\n");
        result.append(" - strength  (+) ").append(potionEffect.getStrengthIncrease());

        return result.toString();
    }

    @Override
    public void setLevel(final int newLevel) {
        final int levelDifference = newLevel - currentLevel;
        marketPrice = (int) (marketPrice * Math.pow(marketPriceIncreaseFactor, levelDifference));

        potionEffect.setAgilityIncrease((int) (potionEffect.getAgilityIncrease() * Math.pow((agilityIncreaseFactor), levelDifference)));
        potionEffect.setDefenseIncrease((int) (potionEffect.getDefenseIncrease()* Math.pow(defenseIncreaseFactor, levelDifference)));
        potionEffect.setDexterityIncrease((int) (potionEffect.getDexterityIncrease()* Math.pow(dexterityIncreaseFactor, levelDifference)));
        potionEffect.setHealthIncrease((int) (potionEffect.getHealthIncrease() * Math.pow(healthIncreaseFactor, levelDifference)));
        potionEffect.setManaIncrease((int) (potionEffect.getManaIncrease() * Math.pow(manaIncreaseFactor, levelDifference)));
        potionEffect.setStrengthIncrease((int) (potionEffect.getStrengthIncrease() * Math.pow(strengthIncreaseFactor, levelDifference)));

        currentLevel = newLevel;
    }

    @Override
    public String getItemName(){
        return toString();
    }

    @Override
    public String toString(){
        return super.toString() +" ("+getNumberOfUsesLeft()+" uses left)";
    }
}
