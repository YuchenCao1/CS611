
public class Dragons extends Monsters{//Create Dragons
    private String name;
    private int level;
    private int damage;
    private int defense;
    private int dodge_chance;


    public Dragons(String name, int level, int damage, int defense, int dodge_chance){
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance;
    }

    public String Get_name() {//Get_name
        return name;
    }
    public int Get_level() {//Get_level
        return level;
    }
    public int Get_damage() {//Get_damage
        return damage;
    }
    public int Get_defense()  {//Get_defense
        return defense;
    }
    public int Get_dodge_chance() {//Get_dodge_chance
        return dodge_chance;
    }
}
