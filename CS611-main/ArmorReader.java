import java.util.ArrayList;

public final class ArmorReader extends TextFileReader {
    private final static String armorFilePath = "legends_monsters_and_heroes_metadata/Armory.txt";

    public static ArrayList<Armor> readAll(){
        return new ArrayList<>(generateFromFile(armorFilePath));
    }

    public static ArrayList<Armor> generateFromFile(final String armorTextFile){
        /*
         * Ensure we know what typ eof Weapon is being created
         */
        if (!armorTextFile.equals(armorFilePath)){
            throw new IllegalArgumentException("Can't parse file "+armorTextFile+" since it is not recognized as a armor content text file");
        }

        final String[] fileLines = readLines(armorTextFile);

        return generateArmors(fileLines);
    }

    private static ArrayList<Armor> generateArmors(final String[] fileLines){
        ArrayList<Armor> armorsGenerated = new ArrayList<>();

        //Ignoring first line (line index 0) which are column labels
        for (int armorLineIndex = 1; armorLineIndex < fileLines.length; armorLineIndex++){
            final String[] armorMetadata = fileLines[armorLineIndex].split("\\s+");
            final String name = armorMetadata[0];
            final int cost = Integer.parseInt(armorMetadata[1]);
            final int level = Integer.parseInt(armorMetadata[2]);
            final int damageReduction = Integer.parseInt(armorMetadata[3]);

            Armor armorToAdd = new Armor(name, level, cost, damageReduction);

            armorsGenerated.add(armorToAdd);
        }

        return armorsGenerated;
    }
}