import java.util.ArrayList;

public final class WeaponReader extends TextFileReader {
    private final static String weaponFilePath = "legends_monsters_and_heroes_metadata/Weaponry.txt";

    public static ArrayList<Weapon> readAll(){
        ArrayList<Weapon> readWeapons = new ArrayList<>();
        readWeapons.addAll(generateFromFile(weaponFilePath));
        return readWeapons;
    }

    public static ArrayList<Weapon> generateFromFile(final String weaponTextFile){
        /*
         * Ensure we know what type of Weapon is being created
         */

        if (!weaponTextFile.equals(weaponFilePath)){
            throw new IllegalArgumentException("Can't parse file "+weaponTextFile+" since it is not recognized as a weapon content text file");
        }

        final String[] fileLines = readLines(weaponTextFile);

        return generateWeapons(fileLines);
    }

    private static ArrayList<Weapon> generateWeapons(final String[] fileLines){
        ArrayList<Weapon> weaponsGenerated = new ArrayList<>();

        //Ignoring first line (line index 0) which are column labels
        for (int weaponLineIndex = 1; weaponLineIndex < fileLines.length; weaponLineIndex++){
            final String[] weaponMetadata = fileLines[weaponLineIndex].split("\\s+");
            final String name = weaponMetadata[0];
            final int cost = Integer.parseInt(weaponMetadata[1]);
            final int level = Integer.parseInt(weaponMetadata[2]);
            final int damage = Integer.parseInt(weaponMetadata[3]);
            final int numRequiredHands = Integer.parseInt(weaponMetadata[4]);

            Weapon weaponToAdd = new Weapon(
              name, level, cost, damage,
              numRequiredHands
            );

            weaponsGenerated.add(weaponToAdd);
        }

        return weaponsGenerated;
    }
}