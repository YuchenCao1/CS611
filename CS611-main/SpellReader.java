import java.util.ArrayList;

public final class SpellReader extends TextFileReader {
    private static final String fireSpellsTextFilePath = "legends_monsters_and_heroes_metadata/FireSpells.txt";
    private static final String iceSpellsTextFilePath = "legends_monsters_and_heroes_metadata/IceSpells.txt";
    private static final String lightningSpellsFilePath = "legends_monsters_and_heroes_metadata/LightningSpells.txt";

    public static ArrayList<Spell> readAll(){
        ArrayList<Spell> allReadSpells = new ArrayList<>();
        allReadSpells = generateFromFile(fireSpellsTextFilePath);
        allReadSpells.addAll(generateFromFile(iceSpellsTextFilePath));
        allReadSpells.addAll(generateFromFile(lightningSpellsFilePath));

        return allReadSpells;
    }

    public static ArrayList<Spell> generateFromFile(final String spellTextFile){
        /*
         * Ensure we know what type of Weapon is being created
         */
        if (!spellTextFile.equals(fireSpellsTextFilePath) && !spellTextFile.equals(iceSpellsTextFilePath)
        && !spellTextFile.equals(lightningSpellsFilePath)) {
            throw new IllegalArgumentException("Can't parse file " + spellTextFile + " since it is not recognized as a spell content text file");
        }

        SpellType spellType = null;
        switch (spellTextFile){
            case iceSpellsTextFilePath:
                spellType = SpellType.ICE;
                break;
            case fireSpellsTextFilePath:
                spellType = SpellType.FIRE;
                break;
            case lightningSpellsFilePath:
                spellType = SpellType.LIGHTNING;
                break;
        }

        final String[] fileLines = readLines(spellTextFile);

        return generateSpells(fileLines, spellType);
    }

    private static ArrayList<Spell> generateSpells(final String[] fileLines, final SpellType spellType){
        ArrayList<Spell> spellsGenerated = new ArrayList<>();

        //Ignoring first line (line index 0) which are column labels
        for (int spellLineIndex = 1; spellLineIndex < fileLines.length; spellLineIndex++){
            final String[] spellMetadata = fileLines[spellLineIndex].split("\\s+");
            final String name = spellMetadata[0];
            final int cost = Integer.parseInt(spellMetadata[1]);
            final int level = Integer.parseInt(spellMetadata[2]);
            final int damage = Integer.parseInt(spellMetadata[3]);
            final int manaRequired = Integer.parseInt(spellMetadata[4]);

            Spell spellToAdd = new Spell(
                    name, level, cost, damage,
                    manaRequired,
                    spellType
            );

            spellsGenerated.add(spellToAdd);
        }

        return spellsGenerated;
    }
}