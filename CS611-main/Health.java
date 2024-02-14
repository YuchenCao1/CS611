/**
 * This class is used as a health value storage.
 * Currently, it is used in Legends: Monsters
 * and Heroes.
 */
public class Health {
    private int maximumHealth;
    private int currentHealth;

    public Health(final int maximumHealth){
        currentHealth = maximumHealth;
        this.maximumHealth = maximumHealth;
    }

    public Health(){
        maximumHealth = 100;
        this.currentHealth = maximumHealth;
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

    public int getMaximumHealth(){ return maximumHealth; }

    public void setMaximumHealth(final int maximumHealth){
        this.maximumHealth = maximumHealth;
        //NOTE: this method is only used to upgrade one's health
        //so we will also heal the individual
        this.currentHealth = maximumHealth;
    }

    public void takeDamage(final int damageAmount){
        //Health cannot be negative
        currentHealth = Math.max(0, currentHealth - damageAmount);
    }

    public void healByAmount(final int amountToHeal){
        //Adding a cap since assignment has no limit
        currentHealth = Math.min(maximumHealth, currentHealth + amountToHeal);
    }

    public boolean isAlive(){
        return currentHealth > 0;
    }

    public String toString(){
        return currentHealth + " / " + maximumHealth;
    }
}
