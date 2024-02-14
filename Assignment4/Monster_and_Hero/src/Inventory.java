import java.util.Scanner;

public class Inventory{//Create Inventories
    private Weaponry[] weaponries = new Weaponry[6];
    private Armory[] armories = new Armory[5];
    private Potions[] potions = new Potions[6];
    private FireSpells[] fire_spells = new FireSpells[5];
    private IceSpells[] ice_spells = new IceSpells[6];
    private LightningSpells[] lightning_spells = new LightningSpells[5];

    private int[] rondom_numbers;
    private int number_of_weaponry = 0;
    private int number_of_armory = 0;
    private int number_of_potion = 0;
    private int number_of_fire_spell = 0;
    private int number_of_ice_spell = 0;
    private int number_of_lightning_spell = 0;

    public Inventory() {
    }
    public void Add_Weaponry(Weaponry weaponry){//Add Weaponry
        weaponries[number_of_weaponry] = weaponry;
        number_of_weaponry++;
    }
    public void Add_Armory(Armory armory){//Add Armory
        armories[number_of_armory] = armory;
        number_of_armory++;
    }
    public void Add_Potions(Potions potion){//Add Potions
        potions[number_of_potion] = potion;
        number_of_potion++;
    }
    public void Add_FireSpells(FireSpells fire_spell){//Add FireSpells
        fire_spells[number_of_fire_spell] = fire_spell;
        number_of_fire_spell++;
    }
    public void Add_IceSpells(IceSpells ice_spell){//Add IceSpells
        ice_spells[number_of_ice_spell] = ice_spell;
        number_of_ice_spell++;
    }

    public void Add_LightningSpells(LightningSpells lightning_spell){//Add LightningSpells
        lightning_spells[number_of_lightning_spell] = lightning_spell;
        number_of_lightning_spell++;
    }
    public int Get_Number_of_Weaponry(){//Get Number of Weaponry
        return number_of_weaponry;
    }
    public int Get_Number_of_Armory() {//Get Number of Armory
        return number_of_armory;
    }
    public int Get_Number_of_Potions(){//Get Number of Potions
        return number_of_potion;
    }
    public int Get_Number_of_FireSpells() {//Get Number of FireSpells
        return number_of_fire_spell;
    }
    public int Get_Number_of_IceSpells() {//Get Number of IceSpells
        return number_of_ice_spell;
    }
    public int Get_Number_of_LightningSpells() {//Get Number of LightningSpells
        return number_of_lightning_spell;
    }

    public Weaponry[] Get_Weaponry() {//Get Weaponry
        return weaponries;
    }
    public Armory[] Get_Armory() {//Get Armory
        return armories;
    }
    public Potions[] Get_Potions() {//Get Potions
        return potions;
    }
    public FireSpells[] Get_FireSpells(){//Get FireSpells
        return fire_spells;
    }
    public IceSpells[] Get_IceSpells() {//Get IceSpells
        return ice_spells;
    }

    public LightningSpells[] Get_LightningSpells() {//Get LightningSpells
        return lightning_spells;
    }

    public void Show_Weaponry(){ // Show Weaponry
        for(int i = 0 ; i < number_of_weaponry; i++) {
            System.out.println("Name: " + weaponries[i].Get_name() + ", Cost: " + weaponries[i].Get_cost() + ", Level: " + weaponries[i].Get_level() + ", Damage: " + weaponries[i].Get_damage() + ", Required hands: " + weaponries[i].Get_required_hands());
        }
    }
    public void Show_Armory(){ // Show Armory
        for(int i = 0 ; i < number_of_armory; i++) {
            System.out.println("Name: " + armories[i].Get_name() + ", Cost: " + armories[i].Get_cost() + ", Level: " + armories[i].Get_level() + ", Damage reduction: " + armories[i].Get_damage_reduction());
        }
    }
    public void Show_Potion(){ // Show Potion
        for(int i = 0 ; i < number_of_potion; i++) {
            String attribute_affected = potions[i].Get_attribute_affected()[0];
            for(int j = 1; j < potions[i].Get_attribute_affected_number(); j++){
                attribute_affected = attribute_affected + "/" + potions[i].Get_attribute_affected()[j];
            }
            System.out.println("Name: " + potions[i].Get_name() + ", Cost: " + potions[i].Get_cost() + ", Level: " + potions[i].Get_level() + ", Attribute increase: " + potions[i].Get_attribute_increase() + ", Attribute affected: " + attribute_affected);
        }
    }
    public void Show_Fire_Spell(){ // Show FireSpell
        for(int i = 0 ; i < number_of_fire_spell; i++) {
            System.out.println("Name: " + fire_spells[i].Get_name() + ", Cost: " + fire_spells[i].Get_cost() + ", Level: " + fire_spells[i].Get_level() + ", Damage: " + fire_spells[i].Get_damage() + ", Mana cost: " + fire_spells[i].Get_mana_cost());
        }
    }

    public void Show_Ice_Spell(){ // Show IceSpell
        for(int i = 0 ; i < number_of_ice_spell; i++) {
            System.out.println("Name: " + ice_spells[i].Get_name() + ", Cost: " + ice_spells[i].Get_cost() + ", Level: " + ice_spells[i].Get_level() + ", Damage: " + ice_spells[i].Get_damage() + ", Mana cost: " + ice_spells[i].Get_mana_cost());
        }
    }

    public void Show_Lightning_Spell(){ // Show LightningSpell
        for(int i = 0 ; i < number_of_lightning_spell; i++) {
            System.out.println("Name: " + lightning_spells[i].Get_name() + ", Cost: " + lightning_spells[i].Get_cost() + ", Level: " + lightning_spells[i].Get_level() + ", Damage: " + lightning_spells[i].Get_damage() + ", Mana cost: " + lightning_spells[i].Get_mana_cost());
        }
    }

    public void Remove_Weaponry(int remove_id) {//Remove Weaponry
        weaponries[remove_id] = weaponries[number_of_weaponry - 1];
        weaponries[number_of_weaponry - 1] = null;
        number_of_weaponry--;
    }
    public void Remove_Armory(int remove_id) {//Remove Armory
        armories[remove_id] = armories[number_of_armory - 1];
        armories[number_of_armory - 1] = null;
        number_of_armory--;
    }
    public void Remove_Potion(int remove_id) {//Remove Potion
        potions[remove_id] = potions[number_of_potion - 1];
        potions[number_of_potion - 1] = null;
        number_of_potion--;
    }public void Remove_Fire_Spell(int remove_id) {//Remove Fire Spell
        fire_spells[remove_id] = fire_spells[number_of_fire_spell - 1];
        fire_spells[number_of_fire_spell - 1] = null;
        number_of_fire_spell--;
    }public void Remove_Ice_Spell(int remove_id) {//Remove Ice Spell
        ice_spells[remove_id] = ice_spells[number_of_ice_spell - 1];
        ice_spells[number_of_ice_spell - 1] = null;
        number_of_ice_spell--;
    }
    public void Remove_Lightning_Spell(int remove_id) {//Remove Lightning Spell
        lightning_spells[remove_id] = lightning_spells[number_of_lightning_spell - 1];
        lightning_spells[number_of_lightning_spell - 1] = null;
        number_of_lightning_spell--;
    }

}
