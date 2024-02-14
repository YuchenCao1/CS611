
public class Weaponry extends Items{//Create Weaponry
    private String name;
    private int cost;
    private int level;
    private int damage;
    private int required_hands;



    public Weaponry(String name, int cost, int level, int damage, int required_hands){
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.required_hands = required_hands;
    }

    public String Get_name() {//Get name
        return name;
    }
    public int Get_cost() {//Get cost
        return cost;
    }
    public int Get_level(){//Get level
        return level;
    }
    public int Get_damage(){//Get damage
        return damage;
    }
    public int Get_required_hands() {//Get required hands
        return required_hands;
    }
}
