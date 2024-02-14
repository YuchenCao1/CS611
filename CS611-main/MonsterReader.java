import java.util.ArrayList;

public final class MonsterReader extends TextFileReader {
    private final static String exoskeletonsFile = "legends_monsters_and_heroes_metadata/Exoskeletons.txt";
    private final static String dragonsFile = "legends_monsters_and_heroes_metadata/Dragons.txt";
    private final static String spiritsFile = "legends_monsters_and_heroes_metadata/Spirits.txt";
    public static ArrayList<Monster> readAll(){
        ArrayList<Monster> generatedMonsters = new ArrayList<>();
        generatedMonsters.addAll(generateFromFile(exoskeletonsFile));
        generatedMonsters.addAll(generateFromFile(dragonsFile));
        generatedMonsters.addAll(generateFromFile(spiritsFile));

        return generatedMonsters;
    }

    public static ArrayList<Monster> generateFromFile(final String monsterTextFile){
        /*
         * Ensure we know what type of Hero is being created,
         * which is given in the file name.
         */
        final Class<? extends Monster> monsterClass;

        switch (monsterTextFile){
            case exoskeletonsFile:
                monsterClass = Exoskeleton.class;
                break;
            case dragonsFile:
                monsterClass = Dragon.class;
                break;
            case spiritsFile:
                monsterClass = Spirit.class;
                break;
            default:
                throw new IllegalArgumentException("Can't parse file "+monsterTextFile+" since it is not recognized as a monster content text file.");
        }

        final String[] fileLines = readLines(monsterTextFile);
        return generateMonsters(monsterClass, fileLines);
    }

    private static ArrayList<Monster> generateMonsters(Class<? extends Monster> monsterClass, String[] fileLines){
        ArrayList<Monster> generatedMonsters = new ArrayList<>();

        //Ignoring first line (line index 0) which are column labels
        for (int heroLineIndex = 1; heroLineIndex < fileLines.length; heroLineIndex++){
            final String[] heroMetadata = fileLines[heroLineIndex].split("\\s+");
            final String name = heroMetadata[0];
            final int level = Integer.parseInt(heroMetadata[1]);
            final int damage = Integer.parseInt(heroMetadata[2]);
            final int defense = Integer.parseInt(heroMetadata[3]);
            final int dodgeChance = Integer.parseInt(heroMetadata[4]);

            Monster.MonsterBuilder monsterBuilder = new Monster.MonsterBuilder()
                    .name(name)
                    .level(level)
                    .damage(damage)
                    .defense(defense)
                    .dodgeChance(dodgeChance);

            generatedMonsters.add(
                    MonsterFactory.createMonster(monsterClass, monsterBuilder)
            );
        }

        return generatedMonsters;
    }
}
