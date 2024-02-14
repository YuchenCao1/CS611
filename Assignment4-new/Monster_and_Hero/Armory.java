
public class Armory extends Items{//Create Armory
    private String name;
    private int cost;
    private int level;
    private int damage_reduction;



    public Armory(String name, int cost, int level, int damage_reduction){
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage_reduction = damage_reduction;
    }

    public String Get_name() {//Get_name
        return name;
    }
    public int Get_cost(){//Get_cost
        return cost;
    }
    public int Get_level(){//Get_level
        return level;
    }
    public int Get_damage_reduction(){//Get_damage_reduction
        return damage_reduction;
    }

}
