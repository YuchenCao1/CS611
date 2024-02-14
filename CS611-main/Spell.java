public class Spell extends RPGItem implements Consumable {
    private int damage;
    private int manaRequired;
    private final SpellType spellType;
    private final double damageIncreaseFactor = 1.07;
    private final double manaIncreaseFactor = 1.08;
    private final double marketPriceIncreaseFactor = 1.05;
    private int numberOfUsesLeft = 5; //Doing the more complex route!

    public Spell(final String name, final int initialLevel,
                 final double initialCost, final int damage, final int manaRequired,
                final SpellType spellType
    ){
        super(name, initialLevel, initialCost);
        this.damage = damage;
        this.manaRequired = manaRequired;
        this.spellType = spellType;
    }

    public SpellType getSpellType(){ return spellType; }

    @Override
    public int getNumberOfUsesLeft(){
        return numberOfUsesLeft;
    }

    @Override
    public void consumeItem(){
        numberOfUsesLeft--;
    }

    public int getDamage(){ return damage; }

    public int getManaRequired(){ return manaRequired; }

    @Override
    public String getItemName(){
        return toString();
    }

    @Override
    public String getItemContent(){
        StringBuilder result = new StringBuilder();
        result.append(" - level ").append(currentLevel).append("\n");
        result.append(" - damage ").append(damage).append("\n");
        result.append(" - mana required ").append(manaRequired);

        return result.toString();
    }

    @Override
    public void setLevel(final int newLevel) {
        final int levelDifference = newLevel - currentLevel;
        damage = (int) (damage * Math.pow(damageIncreaseFactor, levelDifference));
        marketPrice = (int) (marketPrice * Math.pow(marketPriceIncreaseFactor, levelDifference));
        manaRequired = (int) (manaRequired * Math.pow(manaIncreaseFactor, levelDifference));
        currentLevel = newLevel;
    }

    @Override
    public String toString(){
        return super.toString() +" ("+getNumberOfUsesLeft()+" use(s) left)";
    }
}
