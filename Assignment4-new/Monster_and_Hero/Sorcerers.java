
public class Sorcerers extends Hero{//Create Sorcerers
    private String name;
    private int MP;
    private int strength;
    private int agility;
    private int dexterity;
    private int gold;
    private int experience_points;
    private int number_of_available_hands;


    public Sorcerers(String name, int MP, int strength, int agility, int dexterity, int gold, int experience_points, int number_of_available_hands){
        this.name = name;
        this.MP = MP;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = gold;
        this.experience_points = experience_points;
        this.number_of_available_hands = number_of_available_hands;
    }

    public String Get_name() {//Reset pieces
        return name;
    }
    public int Get_MP() {//Reset pieces
        return MP;
    }
    public int Get_strength() {//Reset pieces
        return strength;
    }
    public int Get_agility()  {//Reset pieces
        return agility;
    }
    public int Get_dexterity() {//Reset pieces
        return dexterity;
    }
    public int Get_gold() {//Reset pieces
        return gold;
    }
    public int Get_experience_points() {//Reset pieces
        return experience_points;
    }
    public int Get_number_of_available_hands() {//Reset pieces
        return number_of_available_hands;
    }

}
