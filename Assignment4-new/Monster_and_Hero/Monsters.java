import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Monsters{//Create Monsters
    private int type;
    private String name;
    private int level;
    private int HP;
    private int damage;
    private int defense;
    private int dodge_chance;
    private String path;
    private Dragons[] dragons = new Dragons[12];
    private Exoskeletons[] exoskeletons = new Exoskeletons[12];
    private Spirits[] spirits = new Spirits[11];
    private int number_of_dragons = 0;
    private int number_of_exoskeletons = 0;
    private int number_of_spirits = 0;
    private int monster_id;



    public void Create_Monsters(int hero_level) throws Exception {//Get_name
        //System.out.println("\t\t\t\t\t\t\t1. Dragons");
        path = "../Legends_Monsters_and_Heroes/Dragons.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] values = line.split("\\s+");
                    name = values[0];
                    level = Integer.parseInt(values[1]);
                    damage = Integer.parseInt(values[2]);
                    defense = Integer.parseInt(values[3]);
                    dodge_chance = Integer.parseInt(values[4]);
                    //System.out.println((lineNumber-1) + ". Name: " + name + ", level: " + level + ", damage: " + damage + ", defense: " + defense + ", dodge_chance: " + dodge_chance);
                    Dragons dragon = new Dragons(name, level, damage, defense, dodge_chance);
                    dragons[number_of_dragons] = dragon;
                    number_of_dragons++;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("\t\t\t\t\t\t\t2. Exoskeletons");
        path = "../Legends_Monsters_and_Heroes/Exoskeletons.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] values = line.split("\\s+");
                    name = values[0];
                    level = Integer.parseInt(values[1]);
                    damage = Integer.parseInt(values[2]);
                    defense = Integer.parseInt(values[3]);
                    dodge_chance = Integer.parseInt(values[4]);
                    //System.out.println((lineNumber-1) + ". Name: " + name + ", level: " + level + ", damage: " + damage + ", defense: " + defense + ", dodge_chance: " + dodge_chance);
                    Exoskeletons exoskeleton = new Exoskeletons(name, level, damage, defense, dodge_chance);
                    exoskeletons[number_of_exoskeletons] = exoskeleton;
                    number_of_exoskeletons++;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("\t\t\t\t\t\t\t3. Spirits");
        path = "../Legends_Monsters_and_Heroes/Spirits.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] values = line.split("\\s+");
                    name = values[0];
                    level = Integer.parseInt(values[1]);
                    damage = Integer.parseInt(values[2]);
                    defense = Integer.parseInt(values[3]);
                    dodge_chance = Integer.parseInt(values[4]);
                    //System.out.println((lineNumber-1) + ". Name: " + name + ", level: " + level + ", damage: " + damage + ", defense: " + defense + ", dodge_chance: " + dodge_chance);
                    Spirits spirit = new Spirits(name, level, damage, defense, dodge_chance);
                    spirits[number_of_spirits] = spirit;
                    number_of_spirits++;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int chance = random.nextInt(3) + 1;
        if (chance == 1) {
            type = 1;
            Random rand = new Random();
            do {
                monster_id = rand.nextInt(11);
            }while(dragons[monster_id].Get_level() > hero_level);
            name = dragons[monster_id].Get_name();
            level = dragons[monster_id].Get_level();
            HP = level * 100;
            damage = dragons[monster_id].Get_damage();
            defense = dragons[monster_id].Get_defense();
            dodge_chance = dragons[monster_id].Get_dodge_chance();
        }else if (chance == 2) {
            type = 2;
            Random rand = new Random();
            do {
                monster_id = rand.nextInt(10);
            }while(spirits[monster_id].Get_level() > hero_level);
            name = spirits[monster_id].Get_name();
            level = spirits[monster_id].Get_level();
            HP = level * 100;
            damage = spirits[monster_id].Get_damage();
            defense = spirits[monster_id].Get_defense();
            dodge_chance = spirits[monster_id].Get_dodge_chance();
        }else{
            type = 3;
            Random rand = new Random();
            do {
                monster_id = rand.nextInt(10);
            }while(exoskeletons[monster_id].Get_level() > hero_level);
            name = exoskeletons[monster_id].Get_name();
            level = exoskeletons[monster_id].Get_level();
            HP = level * 100;
            damage = exoskeletons[monster_id].Get_damage();
            defense = exoskeletons[monster_id].Get_defense();
            dodge_chance = exoskeletons[monster_id].Get_dodge_chance();
        }
    }

    public int Get_type() {//Get_name
        return type;
    }
    public String Get_name()  {//Get_name
        return name;
    }
    public int Get_level(){//Get_level
        return level;
    }
    public void Show_Information() {//Reset pieces
        switch (type){
            case 1:
                System.out.println("Type: Dragons, Name: " + name + ", Level: " + level + ", HP: " + HP +", Damage: " + damage + ", Defense: " + defense + ", Dodge chance: " + dodge_chance);
                break;
            case 2:
                System.out.println("Type: Spirits, Name: " + name + ", Level: " + level +  ", HP: " + HP +", Damage: " + damage + ", Defense: " + defense + ", Dodge chance: " + dodge_chance);
                break;
            case 3:
                System.out.println("Type: Exoskeletons, Name: " + name + ", Level: " + level + ", HP: " + HP +", Damage: " + damage + ", Defense: " + defense +", Dodge chance: " + dodge_chance);
                break;
        }

    }
    public int Get_HP() {//Get_level
        return HP;
    }
    public int Get_damage() {//Get_damage
        return damage;
    }
    public int Get_defense()  {//Get_defense
        return defense;
    }
    public int Get_dodge_chance()  {//Get_dodge_chance
        return dodge_chance;
    }
    public void Change_HP(int HP) {//Get_level
         this.HP = HP;
    }
    public void Change_damage(int damage)  {//Get_defense
        this.defense = damage;
    }
    public void Change_defense(int defense)  {//Get_defense
        this.defense = defense;
    }
    public void Change_dodge_chance(int dodge_chance)  {//Get_defense
        this.defense = dodge_chance;
    }
}
