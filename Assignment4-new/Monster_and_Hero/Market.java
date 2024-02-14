import java.lang.invoke.SwitchPoint;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Market implements Get_Unique_Random_Numbers{//Create pieces
    private String name;
    private String user_enter;
    private char choice;
    private char choice2;
    private boolean if_choice_valid;
    private boolean if_choice_valid2;
    private Scanner sc;
    private int price;
    private int level;
    private int hero_id;
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
    private int weaponry_id = 0;
    private int armory_id = 0;
    private int potion_id = 0;
    private int fire_spell_id = 0;
    private int ice_spell_id = 0;
    private int lightning_spell_id = 0;
    private int transaction_type;
    private int transaction_id;

    public Market(){
        rondom_numbers = Get_Unique_Random_Numbers(3,6);
        for(int number : rondom_numbers) {
            String path = "../Legends_Monsters_and_Heroes/Weaponry.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (lineNumber == number+1) {
                        String[] values = line.split("\\s+");

                        String name = values[0];
                        int cost = Integer.parseInt(values[1]);
                        int level = Integer.parseInt(values[2]);
                        int damage = Integer.parseInt(values[3]);
                        int required_hands = Integer.parseInt(values[4]);
                        Weaponry weaponry = new Weaponry(name, cost, level, damage, required_hands);
                        weaponries[weaponry_id] = weaponry;
                    }
                    lineNumber++;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            weaponry_id++;
        }
        number_of_weaponry = number_of_weaponry + 3;

        rondom_numbers = Get_Unique_Random_Numbers(3,5);
        for(int number : rondom_numbers) {
            String path = "../Legends_Monsters_and_Heroes/Armory.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (lineNumber == number+1) {
                        String[] values = line.split("\\s+");

                        String name = values[0];
                        int cost = Integer.parseInt(values[1]);
                        int level = Integer.parseInt(values[2]);
                        int damage_reduction = Integer.parseInt(values[3]);
                        Armory armory = new Armory(name, cost, level, damage_reduction);
                        armories[armory_id] = armory;
                    }
                    lineNumber++;
                }
                armory_id++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        number_of_armory = number_of_armory + 3;

        rondom_numbers = Get_Unique_Random_Numbers(3,6);
        for(int number : rondom_numbers) {
            String path = "../Legends_Monsters_and_Heroes/Potions.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (lineNumber == number+1) {
                        String[] values = line.split("\\s+");

                        String name = values[0];
                        int cost = Integer.parseInt(values[1]);
                        int level = Integer.parseInt(values[2]);
                        int attribute_increase = Integer.parseInt(values[3]);
                        String attribute_affected;
                        if (lineNumber == 7) {
                            attribute_affected = values[5];
                        }
                        else{
                            attribute_affected = values[4];
                        }
                        String[] values2 = attribute_affected.split("/");
                        Potions potion = new Potions(name, cost, level, attribute_increase, values2, values2.length);
                        potions[potion_id] = potion;
                    }
                    lineNumber++;
                }
                potion_id++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        number_of_potion = number_of_potion + 3;

        rondom_numbers = Get_Unique_Random_Numbers(3,5);
        for(int number : rondom_numbers) {
            String path = "../Legends_Monsters_and_Heroes/FireSpells.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (lineNumber == number+1) {
                        String[] values = line.split("\\s+");

                        String name = values[0];
                        int cost = Integer.parseInt(values[1]);
                        int level = Integer.parseInt(values[2]);
                        int damage = Integer.parseInt(values[3]);
                        int mana_cost = Integer.parseInt(values[4]);
                        FireSpells fire_spell = new FireSpells(name, cost, level, damage, mana_cost);
                        fire_spells[fire_spell_id] = fire_spell;
                    }
                    lineNumber++;
                }
                fire_spell_id++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        number_of_fire_spell = number_of_fire_spell + 3;

        rondom_numbers = Get_Unique_Random_Numbers(2,4);
        for(int number : rondom_numbers) {
            String path = "../Legends_Monsters_and_Heroes/IceSpells.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (lineNumber == number+1) {
                        String[] values = line.split("\\s+");

                        String name = values[0];
                        int cost = Integer.parseInt(values[1]);
                        int level = Integer.parseInt(values[2]);
                        int damage = Integer.parseInt(values[3]);
                        int mana_cost = Integer.parseInt(values[4]);
                        IceSpells ice_spell = new IceSpells(name, cost, level, damage, mana_cost);
                        ice_spells[ice_spell_id] = ice_spell;
                    }
                    lineNumber++;
                }
                ice_spell_id++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        number_of_ice_spell = number_of_ice_spell + 2;

        rondom_numbers = Get_Unique_Random_Numbers(2,4);
        for(int number : rondom_numbers) {
            String path = "../Legends_Monsters_and_Heroes/LightningSpells.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    if (lineNumber == number+1) {
                        String[] values = line.split("\\s+");

                        String name = values[0];
                        int cost = Integer.parseInt(values[1]);
                        int level = Integer.parseInt(values[2]);
                        int damage = Integer.parseInt(values[3]);
                        int mana_cost = Integer.parseInt(values[4]);
                        LightningSpells lightning_spell = new LightningSpells(name, cost, level, damage, mana_cost);
                        lightning_spells[lightning_spell_id] = lightning_spell;
                    }
                    lineNumber++;
                }
                lightning_spell_id++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        number_of_lightning_spell = number_of_lightning_spell + 2;
    }

    public int[] Get_Unique_Random_Numbers(int number, int range)
    {
        Random rand = new Random();
        int[] numbers = new int[number];
        int count = 0;

        while (count < number) {
            int newNumber = rand.nextInt(range) + 1;
            boolean isDuplicate = false;

            for (int i = 0; i < count; i++) {
                if (numbers[i] == newNumber) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                numbers[count] = newNumber;
                count++;
            }
        }
        return numbers;
    }

    public void Reset_Market() throws Exception{//Reset pieces

    }

    public void Show_items() throws Exception{//Reset pieces
        int i;
        String attribute_affected;
        System.out.println("\t\t\t\t\t\t1. Weaponry");
        for(i = 0; i < number_of_weaponry; i++) {
            System.out.println(i+1 + ".Name: " + weaponries[i].Get_name() + ", Cost: " + weaponries[i].Get_cost() + ", Level: " + weaponries[i].Get_level() + ", damage: " + weaponries[i].Get_damage() + ", required_hands: " + weaponries[i].Get_required_hands());
        }
        System.out.println("\n\t\t\t\t\t\t2. Armory");
        for(i = 0; i < number_of_armory; i++) {
            System.out.println(i+1 + ".Name: " + armories[i].Get_name() + ", Cost: " + armories[i].Get_cost() + ", Level: " + armories[i].Get_level() + ", damage_reduction: " + armories[i].Get_damage_reduction());
        }
        System.out.println("\n\t\t\t\t\t\t3. Potion");
        for(int k = 0 ; k < number_of_potion; k++) {
            attribute_affected = potions[k].Get_attribute_affected()[0];
            for (int j = 1; j < potions[k].Get_attribute_affected_number(); j++) {
                attribute_affected = attribute_affected + "/" + potions[k].Get_attribute_affected()[j];
            }
            System.out.println(k+1 + ".Name: " + potions[k].Get_name() + ", Cost: " + potions[k].Get_cost() + ", Level: " + potions[k].Get_level() + ", attribute_increase: " + potions[k].Get_attribute_increase() + ", attribute_affected: " + attribute_affected);
        }
        System.out.println("\n\t\t\t\t\t\t4. Fire spell");
        for(i = 0; i < number_of_fire_spell; i++) {
            System.out.println(i+1 + ".Name: " + fire_spells[i].Get_name() + ", Cost: " + fire_spells[i].Get_cost() + ", Level: " + fire_spells[i].Get_level() + ", damage: " + fire_spells[i].Get_damage() + ", mana_cost: " + fire_spells[i].Get_mana_cost());
        }
        System.out.println("\n\t\t\t\t\t\t5. Ice spell");
        for(i = 0; i < number_of_ice_spell; i++) {
            System.out.println(i+1 + ".Name: " + ice_spells[i].Get_name() + ", Cost: " + ice_spells[i].Get_cost() + ", Level: " + ice_spells[i].Get_level() + ", damage: " + ice_spells[i].Get_damage() + ", mana_cost: " + ice_spells[i].Get_mana_cost());
        }
        System.out.println("\n\t\t\t\t\t\t6. Lightning spell");
        for(i = 0; i < number_of_lightning_spell; i++) {
            System.out.println(i+1 + ".Name: " + lightning_spells[i].Get_name() + ", Cost: " + lightning_spells[i].Get_cost() + ", Level: " + lightning_spells[i].Get_level() + ", damage: " + lightning_spells[i].Get_damage() + ", mana_cost: " + lightning_spells[i].Get_mana_cost());
        }
    }

    public Hero[] Purchase_Items(int number_of_heroes, Hero[] heroes) throws Exception {//Reset pieces
        if(number_of_weaponry+number_of_armory+number_of_potion+number_of_fire_spell+number_of_ice_spell+number_of_lightning_spell == 0){
            System.out.println("This market is sold out!");
            return heroes;
        }
        Show_items();

        if_choice_valid = false;
        do {
            System.out.println("Which kind do you want to buy");
            System.out.println("Please enter your choice 1 to 6");
            sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter 1 to 6");
            } else {
                this.choice = user_enter.charAt(0);
                if (Character.getNumericValue(choice) >= 1 && Character.getNumericValue(choice) <= 6) {
                    transaction_type = Character.getNumericValue(choice);
                    switch (transaction_type) {
                        case 1:
                            if (number_of_weaponry <= 0) {
                                System.out.println("There is no weaponries");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 2:
                            if (number_of_armory <= 0) {
                                System.out.println("There is no armories");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 3:
                            if (number_of_potion <= 0) {
                                System.out.println("There is no potions");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 4:
                            if (number_of_fire_spell <= 0) {
                                System.out.println("There is no fire spells");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 5:
                            if (number_of_ice_spell <= 0) {
                                System.out.println("There is no ice spells");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 6:
                            if (number_of_lightning_spell <= 0) {
                                System.out.println("There is no lightning spells");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                    }
                } else {
                    System.out.println("\nPlease only enter 1 to 6");
                }
            }
        } while (!if_choice_valid);

        if_choice_valid2 = false;
        do {
            System.out.println("Which one do you want to buy");
            System.out.println("Please enter your choice");
            sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter a valid choice");
            } else {
                this.choice2 = user_enter.charAt(0);
                transaction_id = Character.getNumericValue(choice2);
                switch (transaction_type) {
                    case 1:
                        if (transaction_id >= 1 && transaction_id <= number_of_weaponry) {
                            if_choice_valid2 = true;
                            price = weaponries[Character.getNumericValue(choice2) - 1].Get_cost();
                            level = weaponries[Character.getNumericValue(choice2) - 1].Get_level();
                        } else {
                            System.out.println("\nPlease only enter a valid choice");
                        }
                        break;
                    case 2:
                        if (transaction_id >= 1 && transaction_id <= number_of_armory) {
                            if_choice_valid2 = true;
                            price = armories[Character.getNumericValue(choice2) - 1].Get_cost();
                            level = armories[Character.getNumericValue(choice2) - 1].Get_level();
                        } else {
                            System.out.println("\nPlease only enter a valid choice");
                        }
                        break;
                    case 3:
                        if (transaction_id >= 1 && transaction_id <= number_of_potion) {
                            if_choice_valid2 = true;
                            price = potions[Character.getNumericValue(choice2) - 1].Get_cost();
                            level = potions[Character.getNumericValue(choice2) - 1].Get_level();
                        } else {
                            System.out.println("\nPlease only enter a valid choice");
                        }
                        break;
                    case 4:
                        if (transaction_id >= 1 && transaction_id <= number_of_fire_spell) {
                            if_choice_valid2 = true;
                            price = fire_spells[Character.getNumericValue(choice2) - 1].Get_cost();
                            level = fire_spells[Character.getNumericValue(choice2) - 1].Get_level();
                        } else {
                            System.out.println("\nPlease only enter a valid choice");
                        }
                        break;
                    case 5:
                        if (transaction_id >= 1 && transaction_id <= number_of_ice_spell) {
                            if_choice_valid2 = true;
                            price = ice_spells[Character.getNumericValue(choice2) - 1].Get_cost();
                            level = ice_spells[Character.getNumericValue(choice2) - 1].Get_level();
                        } else {
                            System.out.println("\nPlease only enter a valid choice");
                        }
                        break;
                    case 6:
                        if (transaction_id >= 1 && transaction_id <= number_of_lightning_spell) {
                            if_choice_valid2 = true;
                            price = lightning_spells[Character.getNumericValue(choice2) - 1].Get_cost();
                            level = lightning_spells[Character.getNumericValue(choice2) - 1].Get_level();
                        } else {
                            System.out.println("\nPlease only enter a valid choice");
                        }
                        break;
                    default:
                        System.out.println("The type doesn't exist");
                        break;
                }
            }
        } while (!if_choice_valid2);


        System.out.println("The price is: " + price);
        for (hero_id = 0; hero_id < number_of_heroes; hero_id++) {
            System.out.println("Hero " + (hero_id + 1) + "'s gold: " + heroes[hero_id].Get_gold());
        }

        if_choice_valid = false;
        do {
            System.out.println("Which hero will pay for it");
            System.out.println("Please enter your choice 1 to " + number_of_heroes);
            sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter 1 to " + number_of_heroes);
            } else {
                this.choice = user_enter.charAt(0);
                hero_id = Character.getNumericValue(choice)-1;
                if (hero_id >= 0 && hero_id < number_of_heroes) {
                    if_choice_valid = true;
                } else {
                    System.out.println("\nPlease only enter 1 to " + number_of_heroes);
                }
            }
        } while (!if_choice_valid);

        if (heroes[hero_id].Get_gold() >= price && heroes[hero_id].Get_level() >= level) {
            heroes[hero_id].Change_gold(heroes[hero_id].Get_gold() - price);
            switch(transaction_type){
                case 1:
                    heroes[hero_id].Add_Weaponry(weaponries[transaction_id-1]);
                    weaponries[transaction_id-1] = weaponries[number_of_weaponry-1];
                    weaponries[number_of_weaponry-1] = null;
                    number_of_weaponry--;
                    break;
                case 2:
                    heroes[hero_id].Add_Armory(armories[transaction_id-1]);
                    armories[transaction_id-1] = armories[number_of_armory-1];
                    armories[number_of_armory-1] = null;
                    number_of_armory--;
                    break;
                case 3:
                    heroes[hero_id].Add_Potions(potions[transaction_id-1]);
                    potions[transaction_id-1] = potions[number_of_potion-1];
                    potions[number_of_potion-1] = null;
                    number_of_potion--;
                    break;
                case 4:
                    heroes[hero_id].Add_FireSpells(fire_spells[transaction_id-1]);
                    fire_spells[transaction_id-1] = fire_spells[number_of_fire_spell-1];
                    fire_spells[number_of_fire_spell-1] = null;
                    number_of_fire_spell--;
                    break;
                case 5:
                    heroes[hero_id].Add_IceSpells(ice_spells[transaction_id-1]);
                    ice_spells[transaction_id-1] = ice_spells[number_of_ice_spell-1];
                    ice_spells[number_of_ice_spell-1] = null;
                    number_of_ice_spell--;
                    break;
                case 6:
                    heroes[hero_id].Add_LightningSpells(lightning_spells[transaction_id-1]);
                    lightning_spells[transaction_id-1] = lightning_spells[number_of_lightning_spell-1];
                    lightning_spells[number_of_lightning_spell-1] = null;
                    number_of_lightning_spell--;
                    break;
            }
            System.out.println("Purchase successful!");
        } else {
            System.out.println("This hero cannot buy this item!");
        }
        return heroes;
    }

    public Hero[] Sell_Items(int number_of_heroes, Hero[] heroes) throws Exception {//Reset pieces
        int number_of_heroes_items = 0 ;
        for(int heroes_id = 0; heroes_id < number_of_heroes; heroes_id++) {
            number_of_heroes_items = number_of_heroes_items+heroes[heroes_id].Get_Number_of_Weaponry()+heroes[heroes_id].Get_Number_of_Armory()+heroes[heroes_id].Get_Number_of_Potions()+heroes[heroes_id].Get_Number_of_FireSpells()+heroes[heroes_id].Get_Number_of_IceSpells()+heroes[heroes_id].Get_Number_of_LightningSpells();
        }
        if(number_of_heroes_items == 0){
            System.out.println("All heroes are out of stock");
            return heroes;
        }
        for(int heroes_id = 0; heroes_id < number_of_heroes; heroes_id++){
            System.out.println("\nHero " + (heroes_id+1) + ": ");
            System.out.println("Weaponry: ");
            for(int i = 0; i < heroes[heroes_id].Get_Number_of_Weaponry(); i++){
                System.out.println((i+1) + ". Name: "+heroes[heroes_id].Get_Weaponry()[i].Get_name() + ", Cost: " + heroes[heroes_id].Get_Weaponry()[i].Get_cost() + ", Level: " + heroes[heroes_id].Get_Weaponry()[i].Get_level() + ", Damage: " + heroes[heroes_id].Get_Weaponry()[i].Get_damage() + ", Required hands: " + heroes[heroes_id].Get_Weaponry()[i].Get_required_hands());
            }
            System.out.println("Armory: ");
            for(int i = 0; i < heroes[heroes_id].Get_Number_of_Armory(); i++){
                System.out.println((i+1) + ". Name: "+heroes[heroes_id].Get_Armory()[i].Get_name() + ", Cost: " + heroes[heroes_id].Get_Armory()[i].Get_cost() + ", Level: " + heroes[heroes_id].Get_Armory()[i].Get_level() + ", Damage Reduction: " + heroes[heroes_id].Get_Armory()[i].Get_damage_reduction());
            }
            System.out.println("Potion: ");
            for(int i = 0; i < heroes[heroes_id].Get_Number_of_Potions(); i++){
                System.out.println((i+1) + ". Name: "+heroes[heroes_id].Get_Potions()[i].Get_name() + ", Cost: " + heroes[heroes_id].Get_Potions()[i].Get_cost() + ", Level: " + heroes[heroes_id].Get_Potions()[i].Get_level() + ", Attribute Increase: " + heroes[heroes_id].Get_Potions()[i].Get_attribute_increase() + ", Attribute Affected: " + heroes[heroes_id].Get_Potions()[i].Get_attribute_affected());
            }
            System.out.println("Fire Spell: ");
            for(int i = 0; i < heroes[heroes_id].Get_Number_of_FireSpells(); i++){
                System.out.println((i+1) + ". Name: "+heroes[heroes_id].Get_FireSpells()[i].Get_name() + ", Cost: " + heroes[heroes_id].Get_FireSpells()[i].Get_cost() + ", Level: " + heroes[heroes_id].Get_FireSpells()[i].Get_level() + ", Damage: " + heroes[heroes_id].Get_FireSpells()[i].Get_damage() + ", Mana Cost: " + heroes[heroes_id].Get_FireSpells()[i].Get_mana_cost());
            }
            System.out.println("Ice Spell: ");
            for(int i = 0; i < heroes[heroes_id].Get_Number_of_IceSpells(); i++){
                System.out.println((i+1) + ". Name: "+heroes[heroes_id].Get_IceSpells()[i].Get_name() + ", Cost: " + heroes[heroes_id].Get_IceSpells()[i].Get_cost() + ", Level: " + heroes[heroes_id].Get_IceSpells()[i].Get_level() + ", Damage: " + heroes[heroes_id].Get_IceSpells()[i].Get_damage() + ", Mana Cost: " + heroes[heroes_id].Get_IceSpells()[i].Get_mana_cost());
            }
            System.out.println("Lightning Spell: ");
            for(int i = 0; i < heroes[heroes_id].Get_Number_of_LightningSpells(); i++){
                System.out.println((i+1) + ". Name: "+heroes[heroes_id].Get_LightningSpells()[i].Get_name() + ", Cost: " + heroes[heroes_id].Get_LightningSpells()[i].Get_cost() + ", Level: " + heroes[heroes_id].Get_LightningSpells()[i].Get_level() + ", Damage: " + heroes[heroes_id].Get_LightningSpells()[i].Get_damage() + ", Mana Cost: " + heroes[heroes_id].Get_LightningSpells()[i].Get_mana_cost());
            }
        }

        if_choice_valid = false;
        do {
            System.out.println("\nPlease enter the hero id (1 to " + number_of_heroes + ")");
            sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("Please only enter the hero id (1 to " + number_of_heroes + ")");
            } else {
                this.choice = user_enter.charAt(0);
                hero_id = Character.getNumericValue(choice) - 1;
                if (hero_id >= 0 && hero_id < number_of_heroes) {
                    if(heroes[hero_id].Get_number_of_inventories() <= 0) {
                        System.out.println("This hero doesn't have any items to sell!");
                    }
                    else {
                        if_choice_valid = true;
                    }
                } else {
                    System.out.println("Please only enter the hero id (1 to " + number_of_heroes + ")");
                }
            }
        } while (!if_choice_valid);

        System.out.println("\nHero " + (hero_id+1) + ": ");
        System.out.println("Weaponry: ");
        for(int i = 0; i < heroes[hero_id].Get_Number_of_Weaponry(); i++){
            System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_Weaponry()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_Weaponry()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_Weaponry()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_Weaponry()[i].Get_damage() + ", Required hands: " + heroes[hero_id].Get_Weaponry()[i].Get_required_hands());
        }
        System.out.println("Armory: ");
        for(int i = 0; i < heroes[hero_id].Get_Number_of_Armory(); i++){
            System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_Armory()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_Armory()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_Armory()[i].Get_level() + ", Damage Reduction: " + heroes[hero_id].Get_Armory()[i].Get_damage_reduction());
        }
        System.out.println("Potion: ");
        for(int i = 0; i < heroes[hero_id].Get_Number_of_Potions(); i++){
            System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_Potions()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_Potions()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_Potions()[i].Get_level() + ", Attribute Increase: " + heroes[hero_id].Get_Potions()[i].Get_attribute_increase() + ", Attribute Affected: " + heroes[hero_id].Get_Potions()[i].Get_attribute_affected());
        }
        System.out.println("Fire Spell: ");
        for(int i = 0; i < heroes[hero_id].Get_Number_of_FireSpells(); i++){
            System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_FireSpells()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_FireSpells()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_FireSpells()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_FireSpells()[i].Get_damage() + ", Mana Cost: " + heroes[hero_id].Get_FireSpells()[i].Get_mana_cost());
        }
        System.out.println("Ice Spell: ");
        for(int i = 0; i < heroes[hero_id].Get_Number_of_IceSpells(); i++){
            System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_IceSpells()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_IceSpells()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_IceSpells()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_IceSpells()[i].Get_damage() + ", Mana Cost: " + heroes[hero_id].Get_IceSpells()[i].Get_mana_cost());
        }
        System.out.println("Lightning Spell: ");
        for(int i = 0; i < heroes[hero_id].Get_Number_of_LightningSpells(); i++){
            System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_LightningSpells()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_LightningSpells()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_LightningSpells()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_LightningSpells()[i].Get_damage() + ", Mana Cost: " + heroes[hero_id].Get_LightningSpells()[i].Get_mana_cost());
        }
        if_choice_valid = false;
        do {
            System.out.println("\nItem Type: 1.Weapon  2.Armory  3.Potions  4.FireSpells  5.IceSpells  6.LightningSpells");
            System.out.println("Please enter the item type you want to sell(1 to 6)");
            sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("Please only enter the item id you want to sell(1 to 6)");
            } else {
                this.choice = user_enter.charAt(0);
                transaction_type = Character.getNumericValue(choice);
                if (transaction_type >= 1 && transaction_type <= 6) {
                    switch (transaction_type) {
                        case 1:
                            if (heroes[hero_id].Get_Number_of_Weaponry() <= 0) {
                                System.out.println("This hero doesn't have weaponry");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 2:
                            if (heroes[hero_id].Get_Number_of_Armory() <= 0) {
                                System.out.println("This hero doesn't have armory");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 3:
                            if (heroes[hero_id].Get_Number_of_Potions() <= 0) {
                                System.out.println("This hero doesn't have potion");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 4:
                            if (heroes[hero_id].Get_Number_of_FireSpells() <= 0) {
                                System.out.println("This hero doesn't have fire spells");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 5:
                            if (heroes[hero_id].Get_Number_of_IceSpells() <= 0) {
                                System.out.println("This hero doesn't have ice spells");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                        case 6:
                            if (heroes[hero_id].Get_Number_of_LightningSpells() <= 0) {
                                System.out.println("This hero doesn't have lightning spells");
                            } else {
                                if_choice_valid = true;
                            }
                            break;
                    }
                } else {
                    System.out.println("Please only enter the item id you want to sell(1 to 6)");
                }
            }
        } while (!if_choice_valid);

        if_choice_valid = false;
        switch (transaction_type) {
            case 1:
                System.out.println("Weaponry: ");
                for(int i = 0; i < heroes[hero_id].Get_Number_of_Weaponry(); i++){
                    System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_Weaponry()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_Weaponry()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_Weaponry()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_Weaponry()[i].Get_damage() + ", Required hands: " + heroes[hero_id].Get_Weaponry()[i].Get_required_hands());
                }
                do {
                    System.out.println("Please enter the Weaponry id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Weaponry() + ")");
                    sc = new Scanner(System.in);
                    this.user_enter = sc.next();
                    if (this.user_enter.length() != 1) {
                        System.out.println("Please only enter the Weaponry id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Weaponry() + ")");
                    } else {
                        this.choice = user_enter.charAt(0);
                        transaction_id = Character.getNumericValue(choice);
                        if (transaction_id >= 1 && transaction_id <= heroes[hero_id].Get_Number_of_Weaponry()) {
                            if_choice_valid = true;
                        } else {
                            System.out.println("Please only enter the Weaponry id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Weaponry() + ")");
                        }
                    }
                } while (!if_choice_valid);
                break;
            case 2:
                System.out.println("Armory: ");
                for(int i = 0; i < heroes[hero_id].Get_Number_of_Armory(); i++){
                    System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_Armory()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_Armory()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_Armory()[i].Get_level() + ", Damage Reduction: " + heroes[hero_id].Get_Armory()[i].Get_damage_reduction());
                }
                do {
                    System.out.println("Please enter the Armory id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Armory() + ")");
                    sc = new Scanner(System.in);
                    this.user_enter = sc.next();
                    if (this.user_enter.length() != 1) {
                        System.out.println("Please only enter the Armory id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Armory() + ")");
                    } else {
                        this.choice = user_enter.charAt(0);
                        transaction_id = Character.getNumericValue(choice);
                        if (transaction_id >= 1 && transaction_id <= heroes[hero_id].Get_Number_of_Armory()) {
                            if_choice_valid = true;
                        } else {
                            System.out.println("Please only enter the Armory id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Armory() + ")");
                        }
                    }
                } while (!if_choice_valid);
                break;
            case 3:
                System.out.println("Potion: ");
                for(int i = 0; i < heroes[hero_id].Get_Number_of_Potions(); i++){
                    System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_Potions()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_Potions()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_Potions()[i].Get_level() + ", Attribute Increase: " + heroes[hero_id].Get_Potions()[i].Get_attribute_increase() + ", Attribute Affected: " + heroes[hero_id].Get_Potions()[i].Get_attribute_affected());
                }
                do {
                    System.out.println("Please enter the Potion id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Potions() + ")");
                    sc = new Scanner(System.in);
                    this.user_enter = sc.next();
                    if (this.user_enter.length() != 1) {
                        System.out.println("Please only enter the Potion id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Potions() + ")");
                    } else {
                        this.choice = user_enter.charAt(0);
                        transaction_id = Character.getNumericValue(choice);
                        if (transaction_id >= 1 && transaction_id <= heroes[hero_id].Get_Number_of_Potions()) {
                            if_choice_valid = true;
                        } else {
                            System.out.println("Please only enter the Potion id you want to sell(1 to " + heroes[hero_id].Get_Number_of_Potions() + ")");
                        }
                    }
                } while (!if_choice_valid);
                break;
            case 4:
                System.out.println("Fire Spell: ");
                for(int i = 0; i < heroes[hero_id].Get_Number_of_FireSpells(); i++){
                    System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_FireSpells()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_FireSpells()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_FireSpells()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_FireSpells()[i].Get_damage() + ", Mana Cost: " + heroes[hero_id].Get_FireSpells()[i].Get_mana_cost());
                }
                do {
                    System.out.println("Please enter the Fire Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_FireSpells() + ")");
                    sc = new Scanner(System.in);
                    this.user_enter = sc.next();
                    if (this.user_enter.length() != 1) {
                        System.out.println("Please only enter the Fire Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_FireSpells() + ")");
                    } else {
                        this.choice = user_enter.charAt(0);
                        transaction_id = Character.getNumericValue(choice);
                        if (transaction_id >= 1 && transaction_id <= heroes[hero_id].Get_Number_of_FireSpells()) {
                            if_choice_valid = true;
                        } else {
                            System.out.println("Please only enter the Fire Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_FireSpells() + ")");
                        }
                    }
                } while (!if_choice_valid);
                break;
            case 5:
                System.out.println("Ice Spell: ");
                for(int i = 0; i < heroes[hero_id].Get_Number_of_IceSpells(); i++){
                    System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_IceSpells()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_IceSpells()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_IceSpells()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_IceSpells()[i].Get_damage() + ", Mana Cost: " + heroes[hero_id].Get_IceSpells()[i].Get_mana_cost());
                }
                do {
                    System.out.println("Please enter the Ice Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_IceSpells() + ")");
                    sc = new Scanner(System.in);
                    this.user_enter = sc.next();
                    if (this.user_enter.length() != 1) {
                        System.out.println("Please only enter the Ice Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_IceSpells() + ")");
                    } else {
                        this.choice = user_enter.charAt(0);
                        transaction_id = Character.getNumericValue(choice);
                        if (transaction_id >= 1 && transaction_id <= heroes[hero_id].Get_Number_of_IceSpells()) {
                            if_choice_valid = true;
                        } else {
                            System.out.println("Please only enter the Ice Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_IceSpells() + ")");
                        }
                    }
                } while (!if_choice_valid);
                break;
            case 6:
                System.out.println("Lightning Spell: ");
                for(int i = 0; i < heroes[hero_id].Get_Number_of_LightningSpells(); i++){
                    System.out.println((i+1) + ". Name: "+heroes[hero_id].Get_LightningSpells()[i].Get_name() + ", Cost: " + heroes[hero_id].Get_LightningSpells()[i].Get_cost() + ", Level: " + heroes[hero_id].Get_LightningSpells()[i].Get_level() + ", Damage: " + heroes[hero_id].Get_LightningSpells()[i].Get_damage() + ", Mana Cost: " + heroes[hero_id].Get_LightningSpells()[i].Get_mana_cost());
                }
                do {
                    System.out.println("Please enter the Lightning Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_LightningSpells() + ")");
                    sc = new Scanner(System.in);
                    this.user_enter = sc.next();
                    if (this.user_enter.length() != 1) {
                        System.out.println("Please only enter the Lightning Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_LightningSpells() + ")");
                    } else {
                        this.choice = user_enter.charAt(0);
                        transaction_id = Character.getNumericValue(choice);
                        if (transaction_id >= 1 && transaction_id <= heroes[hero_id].Get_Number_of_LightningSpells()) {
                            if_choice_valid = true;
                        } else {
                            System.out.println("Please only enter the Lightning Spell id you want to sell(1 to " + heroes[hero_id].Get_Number_of_LightningSpells() + ")");
                        }
                    }
                } while (!if_choice_valid);
                break;
        }

        switch(transaction_type){
            case 1:
                weaponries[number_of_weaponry] = heroes[hero_id].Get_Weaponry()[transaction_id-1];
                number_of_weaponry++;
                heroes[hero_id].Change_gold(heroes[hero_id].Get_Weaponry()[transaction_id-1].Get_cost()/2 + heroes[hero_id].Get_gold());
                heroes[hero_id].Remove_Weaponry(transaction_id-1);
                break;
            case 2:
                armories[number_of_armory] = heroes[hero_id].Get_Armory()[transaction_id-1];
                number_of_armory++;
                heroes[hero_id].Change_gold(heroes[hero_id].Get_Armory()[transaction_id-1].Get_cost()/2 + heroes[hero_id].Get_gold());
                heroes[hero_id].Remove_Armory(transaction_id-1);
                break;
            case 3:
                potions[number_of_potion] = heroes[hero_id].Get_Potions()[transaction_id-1];
                number_of_potion++;
                heroes[hero_id].Change_gold(heroes[hero_id].Get_Potions()[transaction_id-1].Get_cost()/2 + heroes[hero_id].Get_gold());
                heroes[hero_id].Remove_Potion(transaction_id-1);
                break;
            case 4:
                fire_spells[number_of_fire_spell] = heroes[hero_id].Get_FireSpells()[transaction_id-1];
                number_of_fire_spell++;
                heroes[hero_id].Change_gold(heroes[hero_id].Get_FireSpells()[transaction_id-1].Get_cost()/2 + heroes[hero_id].Get_gold());
                heroes[hero_id].Remove_Fire_Spell(transaction_id-1);
                break;
            case 5:
                ice_spells[number_of_ice_spell] = heroes[hero_id].Get_IceSpells()[transaction_id-1];
                number_of_ice_spell++;
                heroes[hero_id].Change_gold(heroes[hero_id].Get_IceSpells()[transaction_id-1].Get_cost()/2 + heroes[hero_id].Get_gold());
                heroes[hero_id].Remove_Ice_Spell(transaction_id-1);
                break;
            case 6:
                lightning_spells[number_of_lightning_spell] = heroes[hero_id].Get_LightningSpells()[transaction_id-1];
                number_of_lightning_spell++;
                heroes[hero_id].Change_gold(heroes[hero_id].Get_LightningSpells()[transaction_id-1].Get_cost()/2 + heroes[hero_id].Get_gold());
                heroes[hero_id].Remove_Lightning_Spell(transaction_id-1);
                break;
        }
        System.out.println("Sell successfully!");
        return heroes;
    }

}
