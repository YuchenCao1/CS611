/**
 * This class is to declare attributes of what a potion
 * can affect a person by in the given RPG game item.
 */
public class PotionEffect {
    private int healthIncrease;
    private int manaIncrease;
    private int strengthIncrease;
    private int dexterityIncrease;
    private int defenseIncrease;
    private int agilityIncrease;

    public PotionEffect(
            final int healthIncrease,
            final int manaIncrease,
            final int strengthIncrease,
            final int dexterityIncrease,
            final int defenseIncrease,
            final int agilityIncrease
            ){
        this.healthIncrease = healthIncrease;
        this.manaIncrease = manaIncrease;
        this.strengthIncrease = strengthIncrease;
        this.dexterityIncrease = dexterityIncrease;
        this.defenseIncrease = defenseIncrease;
        this.agilityIncrease = agilityIncrease;
    }

    public PotionEffect(){
        healthIncrease = 0;
        manaIncrease = 0;
        strengthIncrease = 0;
        dexterityIncrease = 0;
        defenseIncrease = 0;
        agilityIncrease = 0;
    }

    public int getHealthIncrease(){ return healthIncrease; }
    public int getManaIncrease(){ return manaIncrease; }
    public int getStrengthIncrease(){ return strengthIncrease; }
    public int getDexterityIncrease(){ return dexterityIncrease; }
    public int getDefenseIncrease(){ return defenseIncrease; }
    public int getAgilityIncrease(){ return agilityIncrease; }

    public void setHealthIncrease(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }

    public void setManaIncrease(int manaIncrease) {
        this.manaIncrease = manaIncrease;
    }

    public void setStrengthIncrease(int strengthIncrease) {
        this.strengthIncrease = strengthIncrease;
    }

    public void setDexterityIncrease(int dexterityIncrease) {
        this.dexterityIncrease = dexterityIncrease;
    }

    public void setDefenseIncrease(int defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }

    public void setAgilityIncrease(int agilityIncrease) {
        this.agilityIncrease = agilityIncrease;
    }
}
