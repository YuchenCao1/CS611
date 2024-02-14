import java.util.ArrayList;

public final class HeroReader extends TextFileReader {

    public static ArrayList<Hero> generateFromFile(final String heroTextFile){
        /*
         * Ensure we know what type of Hero is being created,
         * which is given in the file name.
         */
        final Class<? extends Hero> heroClass;

        switch (heroTextFile){
            case "legends_monsters_and_heroes_metadata/Paladins.txt":
                heroClass = Paladin.class;
                break;
            case "legends_monsters_and_heroes_metadata/Sorcerers.txt":
                heroClass = Sorcerer.class;
                break;
            case "legends_monsters_and_heroes_metadata/Warriors.txt":
                heroClass = Warrior.class;
                break;
            default:
                throw new IllegalArgumentException("Can't parse file "+heroTextFile+" since it is not recognized as a hero content text file.");
        }

        final String[] fileLines = readLines(heroTextFile);
        return generateHeroes(heroClass, fileLines);
    }

    private static ArrayList<Hero> generateHeroes(Class<? extends Hero> heroClass, String[] fileLines){
        ArrayList<Hero> generatedHeroes = new ArrayList<>();

        //Ignoring first line (line index 0) which are column labels
        for (int heroLineIndex = 1; heroLineIndex < fileLines.length; heroLineIndex++){
            final String[] heroMetadata = fileLines[heroLineIndex].split("\\s+");
            final String name = heroMetadata[0];
            final int mana = Integer.parseInt(heroMetadata[1]);
            final int strength = Integer.parseInt(heroMetadata[2]);
            final int agility = Integer.parseInt(heroMetadata[3]);
            final int dexterity = Integer.parseInt(heroMetadata[4]);
            final int startingMoney = Integer.parseInt(heroMetadata[5]);
            final int startingExperience = Integer.parseInt(heroMetadata[6]);
            final double experienceGrowthByLevel = 1.3;

            //Building hero off these details
            Hero.HeroBuilder heroBuilder = new Hero.HeroBuilder()
                    .name(name)
                    .mana(mana)
                    .strength(strength)
                    .agility(agility)
                    .dexterity(dexterity)
                    .gold(startingMoney)
                    .level(1, startingExperience, experienceGrowthByLevel)
                    //Defaulting initial health to 1000
                    .health(1000);

            generatedHeroes.add(
                    HeroFactory.createHero(heroClass, heroBuilder)
            );
        }

        return generatedHeroes;
    }
}
