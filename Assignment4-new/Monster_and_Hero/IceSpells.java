
public class IceSpells extends Items{//Create IceSpells
    private String name;
    private int cost;
    private int level;
    private int damage;
    private int mana_cost;



    public IceSpells(String name, int cost, int level, int damage, int mana_cost){
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.mana_cost = mana_cost;
    }

    public String Get_name(){//Get_name
        return name;
    }
    public int Get_cost() {//Get_cost
        return cost;
    }
    public int Get_level() {//Get_level
        return level;
    }
    public int Get_damage() {//Get_damage
        return damage;
    }
    public int Get_mana_cost() {//Get_mana_cost
        return mana_cost;
    }

}
