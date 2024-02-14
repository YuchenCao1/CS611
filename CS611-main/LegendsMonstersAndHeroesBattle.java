import java.util.ArrayList;

public class LegendsMonstersAndHeroesBattle implements Battle1v1 {
    private ArrayList<Monster> enemies;
    private ArrayList<Hero> allies;
    private ArrayList<Hero> deadAllies;
    private ArrayList<Monster> deadEnemies;
    private Player playerFighting;
    private final int winningExperience;
    private final int winningGold;

    public LegendsMonstersAndHeroesBattle(final ArrayList<Hero> allies, final Player playerFighting){
        this.allies = allies;
        this.playerFighting = playerFighting;
        enemies = new ArrayList<>();
        deadEnemies = new ArrayList<>();
        deadAllies = new ArrayList<>();

        //Generate enemies of the same level, same # as # of allies
        for (final Hero ally : allies){
            final ArrayList<Monster> possibleMonsters = MonsterReader.readAll();
            final Monster randomMonster = possibleMonsters.get((int)(Math.random() * possibleMonsters.size()));
            //Make it the same level as the hero
            randomMonster.setLevel(ally.getLevel());
            enemies.add(randomMonster);
        }

        //Making an experience from winning value
        //Experience added will be the (10*sum of the levels of the monsters / # of heroes)^ 0.9
        //Winning gold for heroes will be 100*sum of all levels of monsters / # of heroes

        int winningExperience = 0, winningGold = 0;
        for (Monster monster : enemies){
            winningExperience += 10*monster.level;
            winningGold += 100 * monster.level;
        }
        this.winningExperience = (int)(Math.pow((double) winningExperience / (double) allies.size(), 0.9));
        this.winningGold = winningGold /= allies.size();
    }

    /**
     * Method to indicate whether the overall game should end
     */
    public boolean didHeroesLose(){
        return allies.isEmpty();
    }

    @Override
    public void start(){
        while (!isBattleDone()){
            displayBattle();
            doAction(); // Hero's turn
            removeDeadMonsters();

            if (isBattleDone()) break; //In case all monsters died
            //Monster's turn:
            //Greedy strategy: select monster with HIGHEST damage to attack the hero with the LEAST health
            Monster monsterWithMostDamage = enemies.get(0);
            assert monsterWithMostDamage != null;
            for (Monster monster : enemies){
                if (monster.baseDamage > monsterWithMostDamage.baseDamage){
                    monsterWithMostDamage = monster;
                }
            }
            Hero heroWithTheLeastHealth = allies.get(0);
            assert heroWithTheLeastHealth != null;
            for (Hero hero : allies){
                if (hero.getTotalHealth() < heroWithTheLeastHealth.getTotalHealth()){
                    heroWithTheLeastHealth = hero;
                }
            }
            monsterWithMostDamage.attackHero(heroWithTheLeastHealth);

            removeDeadHeroes();
            healAndAdjustManaForHeroes();
        }

        end();
    }

    @Override
    public void end(){
        //Winner is announced and if applicable experience and health is granted
        if (enemies.isEmpty()){
            System.out.println("Heroes have won the battle!");
            //Healing all heroes as instructed, ACTUALLY, I'll heal only the dead ones to make it interesting!
            for (Hero deadHero : deadAllies){
                deadHero.health.healByAmount(deadHero.health.getMaximumHealth());
                System.out.println(OutputColorer.green("Fainted hero "+deadHero+" has been fully healed!"));
                System.out.println(OutputColorer.yellow("Fainted hero "+deadHero.name+" has been rewarded "+winningExperience+" experience!"));
                System.out.println(OutputColorer.yellow("Fainted Hero "+deadHero.name+" has been rewarded "+winningGold+" gold!"));
                deadHero.clearPotionEffect();
                deadHero.gold += winningGold;
                deadHero.addExperience(winningExperience);
            }
            for (Hero aliveHero : allies){
                System.out.println(OutputColorer.yellow("Hero "+aliveHero.name+" has been rewarded "+winningExperience+" experience!"));
                System.out.println(OutputColorer.yellow("Hero "+aliveHero.name+" has been rewarded "+winningGold+" gold!"));
                aliveHero.clearPotionEffect();
                aliveHero.gold += winningGold;
                aliveHero.addExperience(winningExperience);
            }
        }
        else {
            System.out.println("Oh no! Monsters have won the battle! Returning to the map...");
        }
    }

    private void removeDeadMonsters(){
        for (Monster monster : enemies){
            if (!monster.health.isAlive()){
                deadEnemies.add(monster);
            }
        }
        enemies.removeIf(monster -> !monster.health.isAlive());
    }

    private void removeDeadHeroes(){
        for (Hero hero : allies){
            if (!hero.health.isAlive()){
                deadAllies.add(hero);
            }
        }
        allies.removeIf(hero -> !hero.health.isAlive());
    }

    /**
     * As instructed, every round the alive heroes
     * regain some health and mana
     */
    private void healAndAdjustManaForHeroes(){
        for (Hero hero : allies){
            hero.health.healByAmount(hero.health.getCurrentHealth() / 100);
            hero.mana += hero.mana / 100;
            System.out.println("Hero "+hero+" has healed slightly");
            System.out.println("Hero "+hero+" has gained some mana");
        }
    }

    @Override
    public void doAction(){
        System.out.println(
                "1) Attack, using a hero’s equipped weapon\n" +
                "2) Cast a spell from a hero’s inventory\n" +
                "3) Use a potion from a hero’s inventory\n" +
                "4) Equip a weapon or piece of armor for a hero\n" +
                "5) View your overall inventory.\n" +
                "6) View the opponents' stats.\n");

        final int userAction = UserInteractor.getIntegerInput("What action would you like to take?", 1, 6);
        Hero selectedHero;
        switch (userAction){
            case 1:
                selectedHero = selectAHero();
                while (selectedHero.equippedWeapon == null){
                    System.out.println("Hero "+selectedHero+" does not appear to have a weapon equipped");
                    equipAction(selectedHero);
                }
                System.out.println("Who do you want to attack with "+selectedHero.equippedWeapon+"?");
                Monster selectedMonster = selectAMonster();
                selectedHero.attackMonsterWithWeapon(selectedMonster);
                break;
            case 2:
                selectedHero = selectAHero();
                castSpellAction(selectedHero);
                break;
            case 3:
                selectedHero = selectAHero();
                castPotionAction(selectedHero);
                break;
            case 4:
                selectedHero = selectAHero();
                equipAction(selectedHero);
                doAction(); // It doesn't count as a turn
                break;
            case 5:
                viewInventoryAction();
                doAction(); // It doesn't count as a turn
                break;
            case 6:
                viewMonsterDetailsAction();
                doAction(); // It doesn't count as a turn
                break;
        }
    }
    /**
     * Method allows the selected hero to cast a spell
     * (if they have one) on the selected monster.
     */
    private void castSpellAction(Hero selectedHero){
        //Ensure selected hero has a spell to use
        final ArrayList<Spell> possibleSpells = selectedHero.getInventory().getItemsOfType(Spell.class);
        if (possibleSpells.isEmpty()){
            System.out.println(OutputColorer.red("Hero " + selectedHero + " does not have a spell to use"));
            doAction(); //Doesn't count as an action
            return;
        }

        int spellIndex = 0;
        for (final Spell spellOption : possibleSpells) {
            System.out.println(++spellIndex + ") " + spellOption);
            System.out.println(spellOption.getItemContent());
        }
        final int selectedPotionIndex = UserInteractor.getIntegerInput("Select which spell you'd like to use", 1, spellIndex);
        final Spell selectedSpell = possibleSpells.get(selectedPotionIndex - 1);
        if (!selectedHero.castSpellOnMonster(selectedSpell, selectAMonster())){
            doAction(); //Does not count as an action if they couldn't do it
        }
    }

    /**
     * Method casts a selected potion on a given hero if
     * a potion already exists
     */
    private void castPotionAction(Hero selectedHero) {
        //Ensure selected hero has a potion to use
        final ArrayList<Potion> possiblePotions = selectedHero.getInventory().getItemsOfType(Potion.class);
        if (possiblePotions.isEmpty()) {
            System.out.println(OutputColorer.red("Hero ") + selectedHero + OutputColorer.red(" does not have potions to use"));
            doAction(); //Doesn't count as an action
            return;
        }

        int potionIndex = 0;
        for (final Potion potionOption : possiblePotions) {
            System.out.println(++potionIndex + ") " + potionOption);
            System.out.println(potionOption.getItemContent());
        }
        final int selectedPotionIndex = UserInteractor.getIntegerInput("Select which potion you'd like to use", 1, potionIndex);
        final Potion selectedPotion = possiblePotions.get(selectedPotionIndex - 1);
        selectedHero.castPotionOnHero(selectedPotion, selectedHero);
    }

    /**
     * Helper method to select a hero
     */
    public Hero selectAHero(){
        for (int heroIndex = 0; heroIndex < allies.size(); heroIndex++){
            System.out.println(heroIndex+1+") "+allies.get(heroIndex));
        }
        final int heroSelectedIndex = UserInteractor.getIntegerInput(
                "Select the desired hero", 1, allies.size());
        return allies.get(heroSelectedIndex - 1);
    }

    /**
     * Helper method to select a monster
     */
    public Monster selectAMonster(){
        for (int monsterIndex = 0; monsterIndex < enemies.size(); monsterIndex++){
            System.out.println(monsterIndex+1+") "+enemies.get(monsterIndex));
        }
        final int monsterSelectedIndex = UserInteractor.getIntegerInput(
                "Select the desired monster", 1, enemies.size());
        return enemies.get(monsterSelectedIndex - 1);
    }

    /**
     * User can also see the monsters' details as instructed
     * from the assignment
     */
    private void viewMonsterDetailsAction(){
        for (Monster monster : enemies){
            System.out.println(monster);
            System.out.println(monster.getStats());
        }
        UserInteractor.getIntegerInput("Type 1 when you are done looking", 1, 1);
    }

    /**
     * User sees overall inventory for all heroes
     */
    private void viewInventoryAction(){
        System.out.println(playerFighting.getInventory());
        UserInteractor.getIntegerInput("Type 1 when you are done looking", 1, 1);
    }

    /**
     * User equips a weapon or armor to use in battle.
     * As instructed, this does not count as an attack.
     */
    private void equipAction(final Hero heroToEquip){
        final String[] validChoices = new String[]{"weapon", "armor"};

        System.out.println("You have the following equipped:");
        System.out.println(" - Weapon: "+heroToEquip.equippedWeapon);
        System.out.println(" - Armor:  "+heroToEquip.equippedArmor);

        final String equipType = UserInteractor.getStringInput("Would you like to equip a \"weapon\" or \"armor\"?", validChoices);
        if (equipType.equals("armor")){
            ArrayList<Armor> armorChoices = heroToEquip.getInventory().getItemsOfType(Armor.class);
            if (armorChoices.isEmpty()){
                System.out.println("You do not have any armor to equip");
                return;
            }
            int armorIndex = 0;
            for (Armor armorChoice : armorChoices){
                System.out.println(++armorIndex+") "+armorChoice.name);
                System.out.println(armorChoice.getItemContent()+"\n");
            }
            final int armorToSelectIndex = UserInteractor.getIntegerInput("Select an armor to equip by its number", 1, armorIndex);
            final Armor armorToEquip = armorChoices.get(armorToSelectIndex - 1);
            heroToEquip.equipArmor(armorToEquip);
            System.out.println(heroToEquip+" has equipped armor "+armorToEquip);
        }
        else {
            ArrayList<Weapon> weaponChoices = heroToEquip.getInventory().getItemsOfType(Weapon.class);
            if (weaponChoices.isEmpty()){
                System.out.println("You do not have any weapons to equip");
                return;
            }
            int weaponIndex = 0;
            for (Weapon weaponChoice : weaponChoices){
                System.out.println(++weaponIndex+") "+weaponChoice.name);
                System.out.println(weaponChoice.getItemContent()+"\n");
            }
            final int weaponToSelectIndex = UserInteractor.getIntegerInput("Select a weapon to equip by its number", 1, weaponIndex);
            final Weapon weaponToEquip = weaponChoices.get(weaponToSelectIndex - 1);
            heroToEquip.equipWeapon(weaponToEquip);
            System.out.println(heroToEquip+" has equipped weapon "+weaponToEquip);
        }
    }

    @Override
    public boolean isBattleDone(){
        return enemies.isEmpty() || allies.isEmpty();
    }

    @Override
    public void displayBattle() {
        /*
         * We will display the monsters' avatars,
         * then the heroes', then the player.
         *
         * NOTE that to display them neatly, they will be outputted
         * in a parallel-like manner.
         *
         * We will also have the enemies' stats outputted neatly
         */
        StringBuilder result = new StringBuilder();
        String[][] enemyAvatarsByLines = new String[enemies.size()][3]; //3 is max length of avatar by class definition.

        for (int enemyIndex = 0; enemyIndex < enemies.size(); enemyIndex++){
            String[] enemyAvatarLines = enemies.get(enemyIndex).getAvatar().split("\n");
            enemyAvatarsByLines[enemyIndex] = enemyAvatarLines;
        }

        //Now parallel-output:
        //NOTE: we know avatar boy part has 3 parts due to class definition
        //TODO: maybe have this 3 value for both cases as static method in class
        for (int avatarBodyPartIndex = 0; avatarBodyPartIndex < 3; avatarBodyPartIndex++) {
            for (int enemyIndex = 0; enemyIndex < enemies.size(); enemyIndex++) {
                result.append(enemyAvatarsByLines[enemyIndex][avatarBodyPartIndex]).append(" ");
            }
            result.append("\n");
        }
        //Adding number below enemy for reference
        for (int enemyIndex = 0; enemyIndex < enemies.size(); enemyIndex++) {
            result.append(" ").append(enemyIndex + 1).append(" ").append(" ");
        }
        //Adding some space between ally and enemy
        result.append("\n\n\n\n");

        //Now outputting hero in parallel fashion
        String[][] heroAvatarsByLines = new String[allies.size()][3]; //3 is max length of avatar by class definition.

        for (int allyIndex = 0; allyIndex < allies.size(); allyIndex++){
            String[] heroAvatarLines = allies.get(allyIndex).getAvatar().split("\n");
            heroAvatarsByLines[allyIndex] = heroAvatarLines;
        }

        //Now parallel-output:
        //NOTE: we know avatar boy part has 3 parts due to class definition
        //TODO: maybe have this 3 value for both cases as static method in class
        for (int avatarBodyPartIndex = 0; avatarBodyPartIndex < 3; avatarBodyPartIndex++) {
            for (int allyIndex = 0; allyIndex < allies.size(); allyIndex++) {
                result.append(heroAvatarsByLines[allyIndex][avatarBodyPartIndex]).append(" ");
            }
            result.append("\n");
        }
        //Adding number below enemy for reference
        for (int allyIndex = 0; allyIndex < allies.size(); allyIndex++) {
            result.append(" ").append(allyIndex + 1).append(" ").append(" ");
        }

        //Now printing the relevant stats for the heroes and monsters
        //For each live monster and hero, we will output their name, level, and health.
        result.append("\n").append("Monsters:\n");
        int enemyIndex = 0;
        for (Monster enemy : enemies){
            result.append(++enemyIndex).append(") ")
                    .append(enemy).append(" | ")
                    .append("Level: ").append(enemy.level).append(" | ")
                    .append("Health: ").append(enemy.health).append("\n");
        }
        result.append("\n");
        result.append("Heroes:\n");
        int allyIndex = 0;
        for (Hero ally : allies){
            result.append(++allyIndex).append(") ")
                    .append(ally).append(" | ")
                    .append("Level: ").append(ally.level).append(" | ")
                    .append("Health: ").append(ally.health).append("\n");
        }

        System.out.println(result);
    }
}
