/**
 * This class establishes the base features of a Hero
 * in the game Legends: Monsters and Heroes
 */
public abstract class Hero implements Packable, Avatarable, Levelable, InventoryHolder, MoneyHolder {
    protected final String name;
    protected CharacterLevel level;
    protected Health health;
    protected int mana;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected final double manaIncreaseFactor;
    protected final double strengthIncreaseFactor;
    protected final double dexterityIncreaseFactor;
    protected final double agilityIncreaseFactor;
    protected final double healthIncreaseFactor = 1.1;
    protected int gold;
    protected Inventory inventory;

    /*
     * The items the hero is currently holding on them.
     * NOTE: the hero's inventory and equipped inventory
     * are MUTUALLY EXCLUSIVE but this inventory
     * is only used for battles.
     */
    protected Weapon equippedWeapon;
    protected Armor equippedArmor;
    protected PotionEffect potionEffect;

    //Default limits
    private final int maxAgility = 5000;
    private final double oneHandStrengthIncreaseFactor = 1.1; //As instructed

    public Hero(HeroBuilder builder){
        name = builder.name;
        level = builder.level;
        health = builder.health;
        mana = builder.mana;
        strength = builder.strength;
        dexterity = builder.dexterity;
        agility = Math.min(maxAgility, builder.agility);
        manaIncreaseFactor = builder.manaIncreaseFactor;
        strengthIncreaseFactor = builder.strengthIncreaseFactor;
        dexterityIncreaseFactor = builder.dexterityIncreaseFactor;
        agilityIncreaseFactor = builder.agilityIncreaseFactor;
        gold = builder.gold;
        inventory = builder.inventory;
        potionEffect = new PotionEffect();
    }

    public String getName(){
        return name;
    }

    public Health getHealth(){
        return health;
    }

    @Override
    public int getLevel(){
        return level.getCurrentLevel();
    }

    @Override
    public String getItemContent(){
        //Will also include the equipped inventory:
        final StringBuilder equippedStr = new StringBuilder();
        equippedStr.append("Equipped:\n");
        equippedStr.append(" Weapon: ").append(equippedWeapon).append("\n");
                if (equippedWeapon != null)
                    equippedStr.append(equippedWeapon.getItemContent()).append("\n");
        equippedStr.append(" Armor: ").append(equippedArmor).append("\n");
                if (equippedArmor != null)
                    equippedStr.append(equippedArmor.getItemContent()).append("\n");

        return equippedStr + "\nStats:\n" + getStats();
    }

    /**
     * Method to take damage from an attack or incident
     */
    public void takeDamage(final int damageAmount){
        if (doesDodgeAttack()){
            System.out.println(this +" dodged the attack!");
            return;
        }

        int totalDamage = damageAmount;
        String armorMessage = "";
        if (equippedArmor != null) {
            totalDamage = Math.max(0, totalDamage - equippedArmor.getDamageReduction());
            if (equippedArmor.getDamageReduction() > 0) {
                armorMessage += " (" + equippedArmor.getDamageReduction() + " was reduced from armor)";
            }
        }

        //Potion effect: if there is health in there, take that health first
        final int healthFromPotion = potionEffect.getHealthIncrease();
        totalDamage = Math.max(0, totalDamage - healthFromPotion);
        String potionMessage = "";
        if (healthFromPotion > 0){
            potionMessage = " ("+healthFromPotion+" was reduced from potion)";
            potionEffect.setHealthIncrease(0);
        }

        health.takeDamage(totalDamage);
        System.out.println("Hero "+this+" has taken "+totalDamage+" damage!" + potionMessage + armorMessage);
        if (!health.isAlive()){
            System.out.println("Hero "+this+" has fainted!");
        }
    }

    /**
     * Method to see whether hero will dodge an attack
     */
    public boolean doesDodgeAttack(){
        final double randomNumber = Math.random();
        return (double) (agility + potionEffect.getAgilityIncrease()) / (double)(2*maxAgility) > randomNumber; //Max odds are 50%;
    }

    public void attackMonsterWithWeapon(Monster monsterToAttack){
        assert equippedWeapon != null;
        final int totalStrength = dexterity + potionEffect.getStrengthIncrease();
        //As instructed to do something like this:
        int damageToInflict = equippedWeapon.getDamage() + (int)((double) equippedWeapon.getDamage() * ((double)totalStrength) / 10000);
        if (equippedWeapon.numberOfHandsToUse == 1){
            damageToInflict = (int)(damageToInflict * oneHandStrengthIncreaseFactor);
            System.out.println("Since this is equipped with one hand, the attack would yield "+damageToInflict+" damage!");
        }
        System.out.println(this+ " has attacked "+monsterToAttack+" for a potential of "+damageToInflict+" damage!");

        monsterToAttack.takeDamage(damageToInflict);

        //Resetting strength increase since it is a Consumable item
        potionEffect.setStrengthIncrease(0);
    }

    /**
     * Method to apply a potion onto a Hero, which is usually
     * done during a battle.
     */
    public void castPotionOnHero(final Potion potion, final Hero hero){
        final PotionEffect potionEffect = potion.getPotionEffect();
        final PotionEffect heroPotionEffect = hero.potionEffect;
        heroPotionEffect.setAgilityIncrease(heroPotionEffect.getAgilityIncrease() + potionEffect.getAgilityIncrease());
        heroPotionEffect.setDefenseIncrease(heroPotionEffect.getDefenseIncrease() + potionEffect.getDefenseIncrease());
        heroPotionEffect.setDexterityIncrease(heroPotionEffect.getDexterityIncrease() + potionEffect.getDexterityIncrease());
        heroPotionEffect.setManaIncrease(heroPotionEffect.getManaIncrease() + potionEffect.getManaIncrease());
        heroPotionEffect.setStrengthIncrease(heroPotionEffect.getStrengthIncrease() + potionEffect.getStrengthIncrease());
        heroPotionEffect.setHealthIncrease(heroPotionEffect.getHealthIncrease() + potionEffect.getHealthIncrease());

        System.out.println(OutputColorer.purple("Potion "+potion+" has successfully been applied to Hero "+hero));

        potion.consumeItem();
        if (potion.getNumberOfUsesLeft() == 0){
            hero.getInventory().removeItem(potion, 1);
        }
    }

    /**
     * Method to cast a spell on a monster, typically done
     * during a battle.
     * @returns whether spell was able to be casted
     */
    public boolean castSpellOnMonster(final Spell spell, Monster monster){
        //Ensure they can afford to use the spell
        final int totalMana = mana + potionEffect.getManaIncrease();
        if (spell.getManaRequired() > totalMana){
            System.out.println(this+OutputColorer.red(" does not have enough mana to use the spell "+spell));
            return false;
        }

        //Doing action since they have enough mana
        final int manaToLose = spell.getManaRequired() - potionEffect.getManaIncrease();
        mana -= manaToLose;
        potionEffect.setManaIncrease(0);

        final int totalDexterity = dexterity + potionEffect.getDexterityIncrease();
        //As instructed to do something like this:
        final int damageWithDexterity = spell.getDamage() + (int)((double) spell.getDamage() * ((double)totalDexterity) / 10000);

        potionEffect.setDexterityIncrease(0);

        System.out.println(OutputColorer.purple("Spell "+spell+" has been shot at to Monster "+monster+"!"));

        //Considering dodging
        final boolean didMonsterDodge = !monster.takeDamage(damageWithDexterity);
        if (!didMonsterDodge) {
            //As instructed, different spell types have different effects
            final SpellType spellType = spell.getSpellType();
            String spellMessage = "As this is a " + spellType.name().toLowerCase() + " spell,";
            final String spellEffectMessage;
            switch (spellType) {
                case ICE: //reduces damage
                    monster.baseDamage = (int) (monster.baseDamage * 0.9);
                    spellEffectMessage = "monster " + monster.name + " has their damage reduced to " + monster.baseDamage + "!";
                    break;
                case FIRE: //reduces defense
                    monster.defenseValue = (int) (monster.defenseValue * 0.9);
                    spellEffectMessage = "monster " + monster.name + " has their defense reduced to " + monster.defenseValue + "!";
                    break;
                case LIGHTNING: //reduces dodge ability
                    monster.dodgeAbility = (int) (monster.dodgeAbility * 0.9);
                    spellEffectMessage = "monster " + monster.name + " has their dodge ability reduced to " + monster.dodgeAbility + "!";
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled spell type " + spellType + " detected when casting a spell");
            }
            System.out.println(OutputColorer.purple(spellMessage + " " + spellEffectMessage));

            spell.consumeItem();
            //Removing potion if it has no more uses left
            if (spell.getNumberOfUsesLeft() == 0) {
                getInventory().removeItem(spell, 1);
            }
            return true;
        }

        return true;
    }

    public void clearPotionEffect(){
        potionEffect.setStrengthIncrease(0);
        potionEffect.setHealthIncrease(0);
        potionEffect.setManaIncrease(0);
        potionEffect.setDexterityIncrease(0);
        potionEffect.setDefenseIncrease(0);
        potionEffect.setAgilityIncrease(0);
    }

    /**
     * Displays the statistics of a certain Hero
     * Increase factors are ignored as they are used in the game's
     * backend.
     * Stats are in bullet points format.
     */
    public String getStats(){
        StringBuilder stats = new StringBuilder();
        stats.append(" - level:        ").append(level).append("\n");
        stats.append(" - health:       ").append(health)
                .append(OutputColorer.purple(" + " + potionEffect.getHealthIncrease()));
                if (equippedArmor != null)
                    stats.append(OutputColorer.green(" + " + equippedArmor.getDamageReduction()));
                else stats.append(OutputColorer.green(" + 0")); //cool to show that no armor is on too!
        stats.append("\n");
        stats.append(" - mana:         ").append(mana)
                .append(OutputColorer.purple(" + " + potionEffect.getManaIncrease())).append("\n");
        stats.append(" - strength:     ").append(strength)
                .append(OutputColorer.purple(" + " + potionEffect.getStrengthIncrease())).append("\n");
        stats.append(" - dexterity:    ").append(dexterity)
                .append(OutputColorer.purple(" + " + potionEffect.getDexterityIncrease())).append("\n");
        stats.append(" - agility:      ").append(agility)
                .append(OutputColorer.purple(" + " + potionEffect.getAgilityIncrease())).append("\n");
        stats.append(" - gold:         ").append(gold);
        return stats.toString();
    }

    @Override
    public Inventory getInventory(){
        return inventory;
    }

    @Override
    public double getWalletBalance() {
        return gold;
    }

    @Override
    public void setWalletBalance(final double newBalance){
        gold = (int) newBalance;
    }

    @Override
    public String getItemName(){
        return toString();
    }

    @Override
    public OutputColor getOutputColor(){
        throw new IllegalArgumentException("getColor() should be implemented in hero type class");
    }

    @Override
    public char getSymbol(){
        //The symbol will simply be the first letter in their name!
        return name.charAt(0);
    }

    /**
     * Method adds experience to the Hero in order to level up
     * @returns how many levels Hero increased by.
     */
    public void addExperience(int experienceToAdd){
        final int currentLevel = level.getCurrentLevel();
        level.addExperience(experienceToAdd);
        final int newLevel = level.getCurrentLevel();
        final int levelDifference = newLevel - currentLevel;

        if (levelDifference > 0){
            setLevel(levelDifference);
        }
    }

    private void setLevel(final int levelDifference){
        mana = (int)(mana * Math.pow(manaIncreaseFactor, levelDifference));
        strength = (int)(strength * Math.pow(strengthIncreaseFactor, levelDifference));
        agility = Math.min(maxAgility, (int) (agility * Math.pow(agilityIncreaseFactor, levelDifference))); //Max odds is 50% or 5000/10000
        dexterity = (int)(dexterity * Math.pow(dexterityIncreaseFactor, levelDifference));
        //And give some gold for the good job!
        gold += 20 * levelDifference;
        //Adding more health too!
        final int newHealth = (int)(health.getMaximumHealth() + 1000*Math.pow(healthIncreaseFactor, levelDifference));
        health.setMaximumHealth(newHealth);
        //We heal when they level up
        health.healByAmount(newHealth);
        levelUp();
    }

    /**
     * Method adds proposed weapon to equipped inventory
     * and places old one back to inventory.
     */
    public void equipWeapon(Weapon weaponToEquip) {
        inventory.removeItem(weaponToEquip, 1);
        inventory.addItem(equippedWeapon, 1);
        equippedWeapon = weaponToEquip;
    }

    /**
     * Method adds proposed armor into equipped inventory
     * and places old one back to inventory.
     */
    public void equipArmor(Armor armorToEquip){
        inventory.removeItem(armorToEquip, 1);
        inventory.addItem(equippedArmor, 1);
        equippedArmor = armorToEquip;
    }

    /**
     * Helper method to calculate the total health of a hero
     */
    public int getTotalHealth(){
        int totalHealth = health.getCurrentHealth();
        if (equippedArmor != null)
            totalHealth += equippedArmor.getDamageReduction();
        totalHealth += potionEffect.getHealthIncrease();
        return totalHealth;
    }

    @Override
    public void levelUp(){

        // Level up all the items that are equipped!
        if (equippedWeapon != null) equippedWeapon.levelUp();
        if (equippedArmor != null) equippedArmor.levelUp();

        final String levelUpMessage = name+" has leveled up to level "+level.getCurrentLevel()+"!" +
                " View your inventory to see full upgrade information.";

        System.out.println(OutputColorer.withColor(getOutputColor(), levelUpMessage));
    }

    /**
     * HeroBuilder:
     * Using builder design to configure certain heroes
     * in a neat and simple manner.
     */
    public final static class HeroBuilder{
        private String name;
        private CharacterLevel level;
        private Health health; //optional
        private int mana;
        private int strength;
        private int dexterity;
        private int agility;
        private double manaIncreaseFactor;
        private double strengthIncreaseFactor;
        private double dexterityIncreaseFactor;
        private double agilityIncreaseFactor;
        private int gold;
        private Inventory inventory; //optional
        //Defaulted values for the game
        protected final int initialMaxExperience = 10;


        public HeroBuilder(){
            health = new Health();
            inventory = new Inventory();
        }

        public HeroBuilder name(final String name){
            this.name = name;
            return this;
        }

        public HeroBuilder level(final int initialLevel, final int initialCurrentExperience, final double experienceGrowthByLevel){
            level = new CharacterLevel(initialLevel, initialCurrentExperience, initialMaxExperience, experienceGrowthByLevel);
            return this;
        }

        public HeroBuilder health(final int maximumHealth){
            health = new Health(maximumHealth);
            return this;
        }

        public HeroBuilder mana(final int initialMana){
            mana = initialMana;
            return this;
        }

        public HeroBuilder strength(final int initialStrength){
            strength = initialStrength;
            return this;
        }

        public HeroBuilder dexterity(final int initialDexterity){
            dexterity = initialDexterity;
            return this;
        }

        public HeroBuilder agility(final int initialAgility){
            agility = initialAgility;
            return this;
        }

        public HeroBuilder manaIncreaseFactor(final double manaIncreaseFactor){
            this.manaIncreaseFactor = manaIncreaseFactor;
            return this;
        }

        public HeroBuilder strengthIncreaseFactor(final double strengthIncreaseFactor){
            this.strengthIncreaseFactor = strengthIncreaseFactor;
            return this;
        }

        public HeroBuilder dexterityIncreaseFactor(final double dexterityIncreaseFactor){
            this.dexterityIncreaseFactor = dexterityIncreaseFactor;
            return this;
        }

        public HeroBuilder agilityIncreaseFactor(final double agilityIncreaseFactor){
            this.agilityIncreaseFactor = agilityIncreaseFactor;
            return this;
        }

        public HeroBuilder gold(final int initialGold){
            gold = initialGold;
            return this;
        }

        public HeroBuilder inventory(final Inventory inventory){
            this.inventory = inventory;
            return this;
        }

        public Hero build(){
            //Ensure required fields were built
            final boolean validName = name != null;
            final boolean validLevel = level != null;
            final boolean validHealth = health != null;
            final boolean validMana = mana != 0;
            final boolean validStrength = strength != 0;
            final boolean validAgility = agility != 0;
            final boolean validManaIncreaseFactor = manaIncreaseFactor != 0;
            final boolean validStrengthIncreaseFactor = strengthIncreaseFactor != 0;
            final boolean validAgilityIncreaseFactor = agilityIncreaseFactor != 0;
            final boolean validGold = gold >= 0;

            if (!validLevel || !validHealth || !validMana || !validStrength || !validAgility
                    || !validManaIncreaseFactor || !validStrengthIncreaseFactor
                    || !validAgilityIncreaseFactor || !validGold || !validName
            ){
                throw new IllegalArgumentException("Hero was attempted to be built with missing required properties");
            }

            return null;
        }
    }

    /**
     * toString will print the colored name,
     * where the color is based on the Hero's type,
     * achieved with dynamic binding.
     */
    public String toString(){
        return OutputColorer.withColor(getOutputColor(), name);
    }
}
