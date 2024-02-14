
public class Potions extends Items{//Create Potions
    private String name;
    private int cost;
    private int level;
    private int attribute_increase;
    private String[] attribute_affected;
    private int attribute_affected_number;



    public Potions(String name, int cost, int level, int attribute_increase, String[] attribute_affected, int attribute_affected_number){
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.attribute_increase = attribute_increase;
        this.attribute_affected = attribute_affected;
        this.attribute_affected_number = attribute_affected_number;
    }

    public String Get_name() {//Get_name
        return name;
    }
    public int Get_cost() {//Get_cost
        return cost;
    }
    public int Get_level() {//Get_level
        return level;
    }
    public int Get_attribute_increase(){//Get_attribute_increase
        return attribute_increase;
    }
    public String[] Get_attribute_affected() {//Get_attribute_affected
        return attribute_affected;
    }
    public int Get_attribute_affected_number(){//Get_attribute_increase
        return attribute_affected_number;
    }

}
