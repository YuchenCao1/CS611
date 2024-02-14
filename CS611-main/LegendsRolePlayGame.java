import java.util.ArrayList;

/**
 * This class is used to use common functionality
 * of the Legends-based Role Play Games, such as
 * - Legends: Monsters and Heroes, and
 * - Legends of Valor
 */
public abstract class LegendsRolePlayGame <T extends GameMap> extends RolePlayGame<T> {
    protected Player player;
    protected Hero[] heroes;
    protected int minNumHeroes;
    protected int maxNumHeroes;

    /**
     * This method creates the 1-3 heroes to be used by the player.
     * The player, as instructed, can be selected among the heroes types:
     * - Warriors
     * - Sorcerers
     * - Paladins
     */
    public void setupHeroes(final int minNumHeroes, final int maxNumHeroes){
        //Allowing a single player to have multiple Heroes to use from
        System.out.println("Now we are setting up the heroes");

        //Cool feature!: If player already has heroes from playing before, they will be asked whether they want to use them or not.
        final ArrayList<Hero> playerHeroes = player.getInventory().getItemsOfType(Hero.class);
        String wantsToReuse = "n";
        if (!playerHeroes.isEmpty()){
            System.out.println("You have the following heroes already:");
            for (Hero hero : playerHeroes){
                System.out.println(hero);
                System.out.println(hero.getStats());
            }

            wantsToReuse = UserInteractor.getYesNoInput("Would you like to reuse them?");
            if (wantsToReuse.equals("y")){
                heroes = playerHeroes.toArray(new Hero[0]);
                if (heroes.length >= minNumHeroes)
                    return;
            }
        }

        //Giving hero description from homework:
        System.out.println("There are three types of heroes, each with their own balance of skills:\n" +
                "• "+OutputColorer.green("Warriors")+" are favored on strength and agility.\n" +
                "• "+OutputColorer.purple("Sorcerers")+" are favored on dexterity and agility.\n" +
                "• "+OutputColorer.cyan("Paladins")+" are favored on strength and dexterity."
        );
        final int numberOfHeroes;
        if (minNumHeroes < maxNumHeroes)
            numberOfHeroes = UserInteractor.getIntegerInput("How many heroes do you want?", minNumHeroes, maxNumHeroes);
        else {
            System.out.println("This game requires " + minNumHeroes + " heroes");
            numberOfHeroes = minNumHeroes;
        }
        heroes = new Hero[numberOfHeroes];
        //If the selected heroes weren't enough, then they'll have to create more.
        //First though selected heroes will be added to heroes
        int heroStartingIndex = 0;
        if (wantsToReuse.equals("y")){
            for (Hero selectedHero : playerHeroes){
                heroes[heroStartingIndex++] = selectedHero;
            }
        }
        for (int currentHero = heroStartingIndex+1; currentHero <= numberOfHeroes; currentHero++) {
            System.out.println("You are currently selecting Hero number " + currentHero + " of " + numberOfHeroes);
            HeroType heroType = UserInteractor.getEnumInput(
                    HeroType.class,
                    OutputColorer.blue(
                            "Select what type of Hero you would like to equip (you can select multiple of the same)"
                    )
            );

            System.out.println("Select which " + heroType.toString().toLowerCase() + " hero you'd like:");

            Hero heroToAdd;
            Weapon startingWeapon = null;
            ArrayList<Hero> heroOptions = null;

            ArrayList<Weapon> weaponOptions = WeaponReader.generateFromFile(
                    "legends_monsters_and_heroes_metadata/Weaponry.txt");

            switch (heroType) {
                case WARRIOR:
                    heroOptions = HeroReader.generateFromFile("legends_monsters_and_heroes_metadata/Warriors.txt");
                    startingWeapon = weaponOptions.get(0);
                    break;

                case PALADIN:
                    heroOptions = HeroReader.generateFromFile("legends_monsters_and_heroes_metadata/Paladins.txt");
                    startingWeapon = weaponOptions.get(2);
                    break;

                case SORCERER:
                    heroOptions = HeroReader.generateFromFile("legends_monsters_and_heroes_metadata/Sorcerers.txt");
                    startingWeapon = weaponOptions.get(5);
                    break;
            }
            startingWeapon.setLevel(1); //Initialize heroes with a level 1 item

            for (int heroIndex = 1; heroIndex <= heroOptions.size(); heroIndex++) {
                System.out.println(OutputColorer.blue(heroIndex+ ") "+heroOptions.get(heroIndex - 1)));
                System.out.println(OutputColorer.blue(heroOptions.get(heroIndex - 1).getStats()));
            }

            final int selectedHeroIndex = UserInteractor.getIntegerInput("Choose by number", 1, heroOptions.size());

            heroToAdd = heroOptions.get(selectedHeroIndex - 1);

            //Adding to heroes field and player's inventory
            heroes[currentHero - 1] = heroToAdd;
            player.getInventory().addItem(heroToAdd, 1);

            //Adding weapon to hero
            heroToAdd.getInventory().addItem(startingWeapon, 1);

            //If they choose sorcerer, they get 1 spell since they are a sorcerer :)
            if (heroType == HeroType.SORCERER) {
                Spell starterFireSpell = SpellReader.generateFromFile("legends_monsters_and_heroes_metadata/FireSpells.txt").get(2);
                starterFireSpell.setLevel(1);
                heroToAdd.getInventory().addItem(starterFireSpell, 1);
            }

            System.out.println("Hero "+heroToAdd+" was successfully added.");
            System.out.println("Hero's starting inventory:\n"+heroToAdd.getInventory());
        }
    }

}
