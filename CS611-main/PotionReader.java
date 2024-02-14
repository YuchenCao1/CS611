import java.util.ArrayList;

public final class PotionReader extends TextFileReader {
    private final static String potionFilePath = "legends_monsters_and_heroes_metadata/Potions.txt";

    public static ArrayList<Potion> readAll(){
        ArrayList<Potion> allPotionsRead = new ArrayList<>();
        allPotionsRead.addAll(generateFromFile(potionFilePath));
        return allPotionsRead;
    }

    public static ArrayList<Potion> generateFromFile(final String potionTextFile){
        /*
         * Ensure we know what type of Potion is being created
         */
        if (!potionTextFile.equals(potionFilePath)){
            throw new IllegalArgumentException("Can't parse file "+potionTextFile+" since it is not recognized as a potion content text file");
        }

        final String[] fileLines = readLines(potionTextFile);

        return generatePotions(fileLines);
    }

    private static ArrayList<Potion> generatePotions(final String[] fileLines){
        ArrayList<Potion> potionsGenerated = new ArrayList<>();

        //Ignoring first line (line index 0) which are column labels
        for (int potionLineIndex = 1; potionLineIndex < fileLines.length; potionLineIndex++){
            final String[] potionMetadata = fileLines[potionLineIndex].split("\\s+");
            final String name = potionMetadata[0];
            final int cost = Integer.parseInt(potionMetadata[1]);
            final int level = Integer.parseInt(potionMetadata[2]);
            final int attributesIncrease = Integer.parseInt(potionMetadata[3]);
            final String[] attributesAffected = potionMetadata[4].split("/");

            int healthIncrease = 0, manaIncrease = 0, strengthIncrease = 0,
                    agilityIncrease = 0, dexterityIncrease = 0, defenseIncrease = 0;

            //Now parsing the attributesAffected line
            for (String attributeAffected  : attributesAffected){
                switch (attributeAffected){
                    case "Health":
                        healthIncrease = attributesIncrease;
                        break;
                    case "Mana":
                        manaIncrease = attributesIncrease;
                        break;
                    case "Strength":
                        strengthIncrease = attributesIncrease;
                        break;
                    case "Dexterity":
                        dexterityIncrease = attributesIncrease;
                        break;
                    case "Defense":
                        defenseIncrease = attributesIncrease;
                        break;
                    case "Agility":
                        agilityIncrease = attributesIncrease;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown potion attribute found "
                                +attributeAffected+" for potion name "+name+" when parsing a potion file."
                        );
                }
            }

            PotionEffect potionEffect = new PotionEffect(
              healthIncrease,
              manaIncrease,
              strengthIncrease,
              dexterityIncrease,
              defenseIncrease,
              agilityIncrease
            );

            Potion potionToAdd = new Potion(name,level,cost,potionEffect);

            potionsGenerated.add(potionToAdd);
        }

        return potionsGenerated;
    }
}