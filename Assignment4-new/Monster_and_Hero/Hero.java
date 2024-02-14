import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Hero{//Create pieces
    private String name;
    private int level;
    private int type;
    private int experience_points;
    private int HP;
    private int MP;
    private int strength;
    private int defense;
    private int dexterity;
    private int agility;
    private int gold;
    private int available_hands;
    private Inventory inventory;
    private int number_of_inventories;
    private int number_of_available_hands;
    private String path;
    private Warriors[] warriors = new Warriors[6];
    private Sorcerers[] sorcerers = new Sorcerers[6];
    private Paladins[] paladins = new Paladins[6];
    private int number_of_warriors = 0;
    private int number_of_sorcerers = 0;
    private int number_of_paladins = 0;

    private boolean if_choice_valid;
    private int select_id;
    private int select_kind;



    public void Show_Heroes() throws Exception {//Reset pieces
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t1. Warriors");
        path = "../Legends_Monsters_and_Heroes/Warriors.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] values = line.split("\\s+");
                    name = values[0];
                    MP = Integer.parseInt(values[1]);
                    strength = Integer.parseInt(values[2]);
                    agility = Integer.parseInt(values[3]);
                    dexterity = Integer.parseInt(values[4]);
                    gold = Integer.parseInt(values[5]);
                    experience_points = Integer.parseInt(values[6]);
                    number_of_available_hands = Integer.parseInt(values[7]);
                    System.out.println((lineNumber-1) + ". Name: " + name + ", MP: " + MP + ", strength: " + strength + ", agility: " + agility + ", dexterity: " + dexterity + ", gold: " + gold + ", experience points: " + experience_points + ", number_of_available_hands: " + number_of_available_hands);
                    Warriors warrior = new Warriors(name, MP, strength, agility, dexterity, gold, experience_points, number_of_available_hands);
                    warriors[number_of_warriors] = warrior;
                    number_of_warriors++;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t2. Sorcerers");
        path = "../Legends_Monsters_and_Heroes/Sorcerers.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] values = line.split("\\s+");
                    name = values[0];
                    MP = Integer.parseInt(values[1]);
                    strength = Integer.parseInt(values[2]);
                    agility = Integer.parseInt(values[3]);
                    dexterity = Integer.parseInt(values[4]);
                    gold = Integer.parseInt(values[5]);
                    experience_points = Integer.parseInt(values[6]);
                    number_of_available_hands = Integer.parseInt(values[7]);
                    System.out.println((lineNumber-1) + ". Name: " + name + ", MP: " + MP + ", strength: " + strength + ", agility: " + agility + ", dexterity: " + dexterity + ", gold: " + gold + ", experience points: " + experience_points + ", number_of_available_hands: " + number_of_available_hands);
                    Sorcerers sorcerer = new Sorcerers(name, MP, strength, agility, dexterity, gold, experience_points, number_of_available_hands);
                    sorcerers[number_of_sorcerers] = sorcerer;
                    number_of_sorcerers++;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t3. Paladins");
        path = "../Legends_Monsters_and_Heroes/Paladins.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] values = line.split("\\s+");
                    name = values[0];
                    MP = Integer.parseInt(values[1]);
                    strength = Integer.parseInt(values[2]);
                    agility = Integer.parseInt(values[3]);
                    dexterity = Integer.parseInt(values[4]);
                    gold = Integer.parseInt(values[5]);
                    experience_points = Integer.parseInt(values[6]);
                    number_of_available_hands = Integer.parseInt(values[7]);
                    System.out.println((lineNumber-1) + ". Name: " + name + ", MP: " + MP + ", strength: " + strength + ", agility: " + agility + ", dexterity: " + dexterity + ", gold: " + gold + ", experience points: " + experience_points + ", number_of_available_hands: " + number_of_available_hands);
                    Paladins paladin = new Paladins(name, MP, strength, agility, dexterity, gold, experience_points, number_of_available_hands);
                    paladins[number_of_paladins] = paladin;
                    number_of_paladins++;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Select_Heroes() throws Exception {//Reset pieces
        Show_Heroes();
        if_choice_valid = false;
        System.out.println("Please select the kind of heroes (1 to 3)");
        do{
            String user_enter;
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("\nPlease only enter 1 to 3");
            }
            else{
                char choice = user_enter.charAt(0);
                select_kind = Character.getNumericValue(choice);
                if (select_kind >= 1 && select_kind <= 3) {
                    if_choice_valid = true;
                }
                else {
                    System.out.println("\nPlease only enter 1 to 3");
                }
            }
        }while(!if_choice_valid);

        System.out.println("Please select the id of heroes (1 to 6)");
        do{
            String user_enter;
            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1) {
                System.out.println("\nPlease only enter 1 to 6");
            }
            else{
                char choice = user_enter.charAt(0);
                select_id = Character.getNumericValue(choice);
                select_id--;
                if (select_id >= 0 && select_id <= 5) {
                    if_choice_valid = true;
                }
                else {
                    System.out.println("\nPlease only enter 1 to 6");
                }
            }
        }while(!if_choice_valid);

        switch(select_kind){
            case 1:
                type = 1;
                name = warriors[select_id].Get_name();
                MP = warriors[select_id].Get_MP();
                HP = 100;
                level = 1;
                strength = warriors[select_id].Get_strength();
                agility = warriors[select_id].Get_agility();
                dexterity = warriors[select_id].Get_dexterity();
                gold = warriors[select_id].Get_gold();
                experience_points = warriors[select_id].Get_experience_points();
                inventory = new Inventory();
                number_of_inventories = 0;
                number_of_available_hands = warriors[select_id].Get_number_of_available_hands();
                break;

            case 2:
                type = 2;
                name = sorcerers[select_id].Get_name();
                MP = sorcerers[select_id].Get_MP();
                HP = 100;
                level = 1;
                strength = sorcerers[select_id].Get_strength();
                agility = sorcerers[select_id].Get_agility();
                dexterity = sorcerers[select_id].Get_dexterity();
                gold = sorcerers[select_id].Get_gold();
                experience_points = sorcerers[select_id].Get_experience_points();
                inventory = new Inventory();
                number_of_inventories = 0;
                number_of_available_hands = sorcerers[select_id].Get_number_of_available_hands();
                break;

            case 3:
                type = 3;
                name = paladins[select_id].Get_name();
                MP = paladins[select_id].Get_MP();
                HP = 100;
                level = 1;
                strength = paladins[select_id].Get_strength();
                agility = paladins[select_id].Get_agility();
                dexterity = paladins[select_id].Get_dexterity();
                gold = paladins[select_id].Get_gold();
                experience_points = paladins[select_id].Get_experience_points();
                inventory = new Inventory();
                number_of_inventories = 0;
                number_of_available_hands = paladins[select_id].Get_number_of_available_hands();
                break;
        }
    }

    public void Show_Information() {//Reset pieces
        switch (type){
            case 1:
                System.out.println("Type: Warrior, Name: " + name + ", Level: " + level + ", Experience_Points: " + experience_points + ", HP: " + HP +", MP: " + MP + ", Gold: " + gold + ", Strength: " + strength + ", Number of available hands: " + number_of_available_hands);
                System.out.println("Inventory: ");
                Show_inventory();
                break;
            case 2:
                System.out.println("Type: Sorcerer, Name: " + name + ", Level: " + level + ", Experience_Points: " + experience_points + ", HP: " + HP +", MP: " + MP + ", Gold: " + gold + ", Strength: " + strength + ", Number of available hands: " + number_of_available_hands);
                System.out.println("Inventory: ");
                Show_inventory();
                break;
            case 3:
                System.out.println("Type: Paladin, Name: " + name + ", Level: " + level + ", Experience_Points: " + experience_points + ", HP: " + HP +", MP: " + MP + ", Gold: " + gold + ", Strength: " + strength + ", Number of available hands: " + number_of_available_hands);
                System.out.println("Inventory: ");
                Show_inventory();
                break;
        }
    }

    public String Get_Name(){//Reset pieces
        return name;
    }
    public int Get_level() {//Get_level
        return level;
    }
    public int Get_Hp(){//Get_Hp
        return HP;
    }
    public int Get_MP() {//Get_MP
        return MP;
    }
    public int Get_experience_points()  {//Get_experience_points
        return experience_points;
    }
    public int Get_strength()  {//Reset pieces
        return strength;
    }
    public int Get_defense()  {//Reset pieces
        return defense;
    }
    public int Get_dexterity()  {//Reset pieces
        return dexterity;
    }
    public int Get_agility(){//Reset pieces
        return agility;
    }
    public int Get_gold() {//Reset pieces
        return gold;
    }
    public int Get_Number_of_Available_Hands() {//Reset pieces
        return number_of_available_hands;
    }
    public Inventory Get_inventory() {//Reset pieces
        return inventory;
    }

    public void Show_inventory(){//Reset pieces
        inventory.Show_Weaponry();
        inventory.Show_Armory();
        inventory.Show_Potion();
        inventory.Show_Fire_Spell();
        inventory.Show_Ice_Spell();
        inventory.Show_Lightning_Spell();
    }

    public int Get_number_of_inventories() {//Reset pieces
        number_of_inventories = inventory.Get_Number_of_Weaponry() + inventory.Get_Number_of_Armory() +inventory.Get_Number_of_Potions() +inventory.Get_Number_of_FireSpells() +inventory.Get_Number_of_IceSpells() +inventory.Get_Number_of_LightningSpells();
        return number_of_inventories;
    }

    public void Change_gold(int gold)  {//Reset pieces
        this.gold = gold;
    }
    public void Add_Weaponry(Weaponry weaponry){//Reset pieces
        inventory.Add_Weaponry(weaponry);
    }

    public void Add_Armory(Armory armory) {//Reset pieces
        inventory.Add_Armory(armory);
    }
    public void Add_Potions(Potions potion) {//Reset pieces
        inventory.Add_Potions(potion);
    }
    public void Add_FireSpells(FireSpells fire_spell) {//Reset pieces
        inventory.Add_FireSpells(fire_spell);
    }
    public void Add_IceSpells(IceSpells ice_spell) {//Reset pieces
        inventory.Add_IceSpells(ice_spell);
    }

    public void Add_LightningSpells(LightningSpells lightning_spell) {//Reset pieces
        inventory.Add_LightningSpells(lightning_spell);
    }

    public int Get_Number_of_Weaponry() throws Exception {//Reset pieces
        return inventory.Get_Number_of_Weaponry();
    }
    public int Get_Number_of_Armory() throws Exception {//Reset pieces
        return inventory.Get_Number_of_Armory();
    }
    public int Get_Number_of_Potions() throws Exception {//Reset pieces
        return inventory.Get_Number_of_Potions();
    }
    public int Get_Number_of_FireSpells() throws Exception {//Reset pieces
        return inventory.Get_Number_of_FireSpells();
    }
    public int Get_Number_of_IceSpells() throws Exception {//Reset pieces
        return inventory.Get_Number_of_IceSpells();
    }
    public int Get_Number_of_LightningSpells() throws Exception {//Reset pieces
        return inventory.Get_Number_of_LightningSpells();
    }

    public Weaponry[] Get_Weaponry() throws Exception {//Reset pieces
        return inventory.Get_Weaponry();
    }
    public Armory[] Get_Armory() throws Exception {//Reset pieces
        return inventory.Get_Armory();
    }
    public Potions[] Get_Potions() {//Reset pieces
        return inventory.Get_Potions();
    }
    public FireSpells[] Get_FireSpells() throws Exception {//Reset pieces
        return inventory.Get_FireSpells();
    }
    public IceSpells[] Get_IceSpells() throws Exception {//Reset pieces
        return inventory.Get_IceSpells();
    }

    public LightningSpells[] Get_LightningSpells()  {//Reset pieces
        return inventory.Get_LightningSpells();
    }

    public void Remove_Weaponry(int remove_id)  {//Reset pieces
        inventory.Remove_Weaponry(remove_id);
    }
    public void Remove_Armory(int remove_id) {//Reset pieces
        inventory.Remove_Armory(remove_id);
    }
    public void Remove_Potion(int remove_id) {//Reset pieces
        inventory.Remove_Potion(remove_id);
    }
    public void Remove_Fire_Spell(int remove_id) {//Reset pieces
        inventory.Remove_Fire_Spell(remove_id);
    }
    public void Remove_Ice_Spell(int remove_id) {//Reset pieces
        inventory.Remove_Ice_Spell(remove_id);
    }
    public void Remove_Lightning_Spell(int remove_id) {//Reset pieces
        inventory.Remove_Lightning_Spell(remove_id);
    }

    public void Change_HP(int HP){//Change_Hp
        this.HP = HP;
    }
    public void Change_MP(int MP){//Change_Hp
        this.MP = MP;
    }
    public void Change_Number_of_Available_Hands(int number_of_available_hands) throws Exception {//Reset pieces
        this.number_of_available_hands = number_of_available_hands;
    }
    public void Change_defense(int defense)  {//Reset pieces
        this.defense = defense;
    }
    public void Change_strength(int strength) {//Reset pieces
        this.strength = strength;
    }
    public void Change_dexterity(int dexterity) {//Reset pieces
        this.dexterity = dexterity;
    }
    public void Change_agility(int agility) {//Reset pieces
        this.agility = agility;
    }
    public void Change_number_of_available_hands(int number_of_available_hands) {//Reset pieces
        this.number_of_available_hands = number_of_available_hands;
    }
    public Inventory Remove_inventory( ) {//Reset pieces
        return inventory;
    }
    public boolean Gain_Experience_Point(int number_of_monsters) throws Exception {//Reset pieces
        this.experience_points = this.experience_points + 2 * number_of_monsters;
        if(experience_points >= 10 * this.level){
            return true;
        }
        return false;
    }
    public void Gain_Gold() throws Exception {//Reset pieces
        if(HP != 0){
            this.gold = this.gold + 100 * level;
        }
    }
    public void Level_Up() throws Exception {//Reset pieces
        experience_points = experience_points - 10 * this.level;
        level++;
        HP = level * 100;
        MP = (int) (MP * 1.1);
        switch (type){
            case 1:
                strength = (int) (strength * 1.1);
                agility = (int) (agility * 1.1);
                dexterity = (int) (dexterity * 1.05);
                System.out.println("Now his information is:\nType: Warrior, Name: " + name + ", Level: " + level + ", Experience_Points: " + experience_points + ", HP: " + HP +", MP: " + MP + ", Gold: " + gold + ", Strength: " + strength + ", Number of available hands: " + number_of_available_hands);
                System.out.println("Inventory: ");
                Show_inventory();
                break;
            case 2:
                strength = (int) (strength * 1.05);
                agility = (int) (agility * 1.1);
                dexterity = (int) (dexterity * 1.1);
                System.out.println("Now his information is:\nType: Sorcerer, Name: " + name + ", Level: " + level + ", Experience_Points: " + experience_points + ", HP: " + HP +", MP: " + MP + ", Gold: " + gold + ", Strength: " + strength + ", Number of available hands: " + number_of_available_hands);
                System.out.println("Inventory: ");
                Show_inventory();
                break;
            case 3:
                strength = (int) (strength * 1.1);
                agility = (int) (agility * 1.05);
                dexterity = (int) (dexterity * 1.1);
                System.out.println("Now his information is:\nType: Paladin, Name: " + name + ", Level: " + level + ", Experience_Points: " + experience_points + ", HP: " + HP +", MP: " + MP + ", Gold: " + gold + ", Strength: " + strength + ", Number of available hands: " + number_of_available_hands);
                System.out.println("Inventory: ");
                Show_inventory();
                break;
        }
    }
}
