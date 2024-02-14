import java.util.Scanner;
import java.util.Random;


public class Battle {//Create Battle
    private String user_enter;

    private char hero_choice;
    private int battle_result;
    private boolean if_choice_valid;
    private char choice;
    private int attack_monster_id;
    private int attack_hero_id;
    private int damage;
    private int spell_id;
    private int potion_choice;
    private int spell_kind;
    private int armory_id;
    private int weapon_id;
    private int number_of_team;
    private Hero[] heroes;
    private Monsters[] monsters;
    Rules rule = new Rules();
    private boolean if_battle_continue;

    public Hero[] Start_Battle(int number_of_team, Hero[] heroes, Monsters[] monsters){
        this.number_of_team = number_of_team;
        this.heroes = heroes;
        this.monsters = monsters;
        Show_Battle_Information();
        do{
            if_battle_continue = Hero_Actions();
            if(!if_battle_continue){
                break;
            }
            if_battle_continue = Moster_Actions();
            if(!if_battle_continue){
                break;
            }
            Hero_Recovery();
        }while(if_battle_continue);
        battle_result = 0;
        for (int i = 0; i < this.number_of_team; i++) {
            if (this.heroes[i].Get_Hp() != 0) {
                battle_result = 1;
            }
        }
        if(battle_result == 1) {
            System.out.println("Heroes win!!!");
        }
        else{
            System.out.println("Monsters win!!!");
        }
        return this.heroes;
    }




    public void Show_Battle_Information(){
        System.out.println("Heroes:");
        for (int i = 0; i < number_of_team; i++) {
            System.out.printf((i+1) +": ");
            heroes[i].Show_Information();
        }
        System.out.println("\nMonsters:");
        for (int i = 0; i < number_of_team; i++) {
            System.out.printf((i+1) +": ");
            monsters[i].Show_Information();
        }
    }

    public boolean Hero_Actions() {//Reset pieces
        for(int hero_id = 0; hero_id < number_of_team; hero_id++){
            System.out.println("\nWhat do you want Hero " + (hero_id+1) + " to do?");
            hero_choice = Get_Hero_Choice();
            switch (hero_choice){
                case '1':
                    attack_monster_id = Get_Attack_Monster_id();
                    Random random = new Random();
                    double chance = random.nextDouble();
                    if(chance <= monsters[attack_monster_id].Get_dodge_chance() * 0.01){
                        System.out.println("Your hero missed the target");
                    }
                    else{
                        damage = (heroes[hero_id].Get_strength() - monsters[attack_monster_id].Get_defense()) / 10;
                        if (monsters[attack_monster_id].Get_HP() - damage > 0) {
                            monsters[attack_monster_id].Change_HP(monsters[attack_monster_id].Get_HP() - damage);
                        }
                        else {
                            monsters[attack_monster_id].Change_HP(0);
                            if(rule.BattleIf_Over(this.number_of_team, this.heroes, this.monsters)){
                                System.out.println("Hero " + (hero_id + 1) + " attacked Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has 0 HP left ");
                                return false;
                            }
                        }
                        System.out.println("Hero " + (hero_id + 1) + " attacked Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has " + monsters[attack_monster_id].Get_HP() + " HP left ");
                        if(monsters[attack_monster_id].Get_HP() == 0) {
                            System.out.println("Monster " + (attack_monster_id + 1) + " has been defeated");
                        }
                    }
                    break;
                case '2':
                    int number_of_spells = heroes[hero_id].Get_inventory().Get_Number_of_FireSpells() + heroes[hero_id].Get_inventory().Get_Number_of_IceSpells() + heroes[hero_id].Get_inventory().Get_Number_of_LightningSpells();
                    if(number_of_spells > 0) {
                        if (!Use_Spell(hero_id)) {
                            return false;
                        }
                    }
                    else {
                        System.out.println("You don't have spells");
                    }
                    break;
                case '3':
                    if(heroes[hero_id].Get_inventory().Get_Number_of_Potions() > 0) {
                        Use_Potion(hero_id);
                    }else {
                        System.out.println("You don't have potions");
                    }
                    break;
                case '4':
                    if(heroes[hero_id].Get_inventory().Get_Number_of_Weaponry()+heroes[hero_id].Get_inventory().Get_Number_of_Armory() > 0) {
                        System.out.println("1.Equip a weapon");
                        System.out.println("2.Equip a piece of armory");
                        int weapon_or_armory;
                        boolean if_weapon_or_armory_valid = false;
                        do {
                            weapon_or_armory = Get_Choice(2);
                            if (weapon_or_armory == 0) {
                                if (heroes[hero_id].Get_inventory().Get_Number_of_Weaponry() > 0) {
                                    if_weapon_or_armory_valid = true;
                                } else {
                                    System.out.println("You don't have weaponries");
                                }
                            } else if (weapon_or_armory == 1) {
                                if (heroes[hero_id].Get_inventory().Get_Number_of_Armory() > 0) {
                                    if_weapon_or_armory_valid = true;
                                } else {
                                    System.out.println("You don't have armories");
                                }
                            }
                        } while (!if_weapon_or_armory_valid);
                        if (weapon_or_armory == 0) {
                            boolean if_equip = false;
                            if_equip = Equip_Weaponry(hero_id);
                            if (!if_equip){
                                hero_id--;
                            }
                        } else {
                            Equip_Armory(hero_id);
                        }
                    }else {
                        System.out.println("You don't have equipments");
                    }
                    break;
                case 'i':
                    Show_Battle_Information();
                    hero_id--;
                    break;
                case 'I':
                    Show_Battle_Information();
                    hero_id--;
                    break;
                default:
                    System.out.println("Action is unreasonable!");
                    break;
            }
        }
        return true;
    }
    public boolean Moster_Actions() {//Reset pieces
        for(int monster_id = 0; monster_id < number_of_team; monster_id++) {
            if(monsters[monster_id].Get_HP() != 0) {
                attack_hero_id = Get_Attack_Hero_id();
                Random random = new Random();
                double chance = random.nextDouble();
                if (chance <= heroes[attack_hero_id].Get_agility() * 0.0002) {
                    System.out.println("The monster " + (monster_id + 1) + " missed the target");
                } else {
                    damage = (monsters[monster_id].Get_damage() - heroes[attack_hero_id].Get_defense()) / 10;
                    if (heroes[attack_hero_id].Get_Hp() - damage > 0) {
                        heroes[attack_hero_id].Change_HP(heroes[attack_hero_id].Get_Hp() - damage);
                    } else {
                        heroes[attack_hero_id].Change_HP(0);
                        if(rule.BattleIf_Over(this.number_of_team, this.heroes, this.monsters)){
                            System.out.println("Moster " + (monster_id + 1) + " attacked Hero " + (attack_hero_id + 1) + ", causing " + damage + " points of damage, now Hero " + (attack_hero_id + 1) + " has 0 HP left ");
                            return false;
                        }
                    }
                    System.out.println("Moster " + (monster_id + 1) + " attacked Hero " + (attack_hero_id + 1) + ", causing " + damage + " points of damage, now Hero " + (attack_hero_id + 1) + " has " + heroes[attack_hero_id].Get_Hp() + " HP left ");
                    if(heroes[attack_hero_id].Get_Hp() == 0) {
                        System.out.println("Hero " + (attack_hero_id + 1) + " has been defeated");
                    }
                }
            }
        }
        return true;
    }

    public char Get_Hero_Choice() {//Let the player give their choice
        if_choice_valid = false;
        System.out.println("Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information");
        do{
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information");
            }
            else{
                this.choice = user_enter.charAt(0);
                if (choice == '1' || choice == '2' || choice == '3' || choice == '4' || choice == 'I' || choice == 'i') {
                    if_choice_valid = true;
                } else {
                    System.out.println("\nPlease only enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information");
                }
            }
        }while(!if_choice_valid);
        return choice;
    }

    public int Get_Attack_Monster_id() {//Let the player give their choice of id of attacking monster
        if_choice_valid = false;
        System.out.println("Please enter id of the monster you want to attack");
        do{
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter a valid id of the monster");
            }
            else{
                this.choice = user_enter.charAt(0);
                attack_monster_id = Character.getNumericValue(choice) - 1;
                if (attack_monster_id >= 0 && attack_monster_id < number_of_team) {
                    if(monsters[attack_monster_id].Get_HP() == 0){
                        System.out.println("This monster is already dead, you cannot attack it again, please enter another id of the monster");
                    }
                    else{
                        if_choice_valid = true;
                    }
                } else {
                    System.out.println("\nPlease only enter a valid id of the monster");
                }
            }
        }while(!if_choice_valid);
        return attack_monster_id;
    }

    public int Get_Attack_Hero_id() {
        Random random = new Random();
        do {
            attack_hero_id = random.nextInt(number_of_team);
        }while(heroes[attack_hero_id].Get_Hp() == 0);
        return attack_hero_id;
    }

    public void Hero_Recovery(){
        for(int hero_id = 0; hero_id < number_of_team; hero_id++) {
            if (heroes[hero_id].Get_Hp()!= 0){
                heroes[hero_id].Change_HP((int) (heroes[hero_id].Get_Hp() * 1.1));
                heroes[hero_id].Change_MP((int) (heroes[hero_id].Get_MP() * 1.1));
            }
        }
    }

    public void Use_Potion(int hero_id){
        System.out.println("Please select a potion(from id 1 to " + heroes[hero_id].Get_inventory().Get_Number_of_Potions() + " )\n");
        heroes[hero_id].Get_inventory().Show_Potion();
        System.out.println();
        System.out.println("Please enter id of potion you want to use");
        potion_choice = Get_Choice(heroes[hero_id].Get_inventory().Get_Number_of_Potions());
        int attribute_affected_number;
        int attribute_increase;
        attribute_affected_number = heroes[hero_id].Get_Potions()[potion_choice].Get_attribute_affected_number();
        attribute_increase = heroes[hero_id].Get_Potions()[potion_choice].Get_attribute_increase();
        System.out.println("\nSuccessfully use potion:");
        for(int i = 0; i < attribute_affected_number; i++){
            String attribute_affected;
            attribute_affected = heroes[hero_id].Get_Potions()[potion_choice].Get_attribute_affected()[i];
            if(attribute_affected.equals("Health")){
                heroes[hero_id].Change_HP(heroes[hero_id].Get_Hp() + attribute_increase);
                System.out.println("HP increases to " + heroes[hero_id].Get_Hp());
            }else if(attribute_affected.equals("Mana")){
                heroes[hero_id].Change_MP(heroes[hero_id].Get_MP() + attribute_increase);
                System.out.println("MP increases to " + heroes[hero_id].Get_MP());
            }else if(attribute_affected.equals("Strength")){
                heroes[hero_id].Change_strength(heroes[hero_id].Get_strength() + attribute_increase);
                System.out.println("Strength increases to " + heroes[hero_id].Get_strength());
            }else if(attribute_affected.equals("Dexterity")){
                heroes[hero_id].Change_dexterity(heroes[hero_id].Get_dexterity() + attribute_increase);
                System.out.println("Dexterity increases to " + heroes[hero_id].Get_dexterity());
            }else if(attribute_affected.equals("Defense")){
                heroes[hero_id].Change_defense(heroes[hero_id].Get_defense() + attribute_increase);
                System.out.println("Defense increases to " + heroes[hero_id].Get_defense());
            }else if(attribute_affected.equals("Agility")){
                heroes[hero_id].Change_agility(heroes[hero_id].Get_agility() + attribute_increase);
                System.out.println("Agility increases to " + heroes[hero_id].Get_agility());
            }
        }
        heroes[hero_id].Remove_Potion(potion_choice);
    }

    public int Get_Choice(int number_of_potion){
        if_choice_valid = false;
        do{
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter a valid id");
            }
            else{
                this.choice = user_enter.charAt(0);
                potion_choice = Character.getNumericValue(choice) - 1;
                if (potion_choice >= 0 && potion_choice < number_of_potion) {
                    if_choice_valid = true;
                } else {
                    System.out.println("\nPlease only enter a valid id");
                }
            }
        }while(!if_choice_valid);
        return potion_choice;
    }

    public boolean Use_Spell(int hero_id){
        System.out.println("Please select a spell kind(from 1 to 3)");
        System.out.println("\n1.Fire Spell:");
        heroes[hero_id].Get_inventory().Show_Fire_Spell();
        System.out.println("\n2.Ice Spell:");
        heroes[hero_id].Get_inventory().Show_Ice_Spell();
        System.out.println("\n3.Lightning Spell:");
        heroes[hero_id].Get_inventory().Show_Lightning_Spell();
        System.out.println();
        System.out.println("Please enter kind of spell you want to use");
        boolean if_spell_kind_valid = false;
        do {
            spell_kind = Get_Choice(3);
            switch (spell_kind){
                case 0:
                    if(heroes[hero_id].Get_inventory().Get_Number_of_FireSpells() > 0) {
                        if_spell_kind_valid = true;
                    }
                    else {
                        System.out.println("You don't have any Fire Spells");
                    }
                    break;
                case 1:
                    if(heroes[hero_id].Get_inventory().Get_Number_of_IceSpells() > 0) {
                        if_spell_kind_valid = true;
                    }
                    else {
                        System.out.println("You don't have any Ice Spells");
                    }
                    break;
                case 2:
                    if(heroes[hero_id].Get_inventory().Get_Number_of_LightningSpells() > 0) {
                        if_spell_kind_valid = true;
                    }
                    else {
                        System.out.println("You don't have any Lightning Spells");
                    }
                    break;
            }
        }while (!if_spell_kind_valid);
        switch (spell_kind){
            case 0:
                System.out.println("Please select a fire spell id(from 1 to " + heroes[hero_id].Get_inventory().Get_Number_of_FireSpells() + " )");
                System.out.println();
                heroes[hero_id].Get_inventory().Show_Fire_Spell();
                spell_id = Get_Choice(heroes[hero_id].Get_inventory().Get_Number_of_FireSpells());
                attack_monster_id = Get_Attack_Monster_id();
                damage = heroes[hero_id].Get_inventory().Get_FireSpells()[spell_id].Get_damage() + (heroes[hero_id].Get_dexterity()/10000) * heroes[hero_id].Get_inventory().Get_FireSpells()[spell_id].Get_damage();
                monsters[attack_monster_id].Change_defense(monsters[attack_monster_id].Get_defense() / 2);
                if (monsters[attack_monster_id].Get_HP() - damage > 0) {
                    monsters[attack_monster_id].Change_HP(monsters[attack_monster_id].Get_HP() - damage);
                }
                else {
                    monsters[attack_monster_id].Change_HP(0);
                    if(rule.BattleIf_Over(this.number_of_team, this.heroes, this.monsters)){
                        System.out.println("Hero " + (hero_id + 1) + " use fire spell to Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has 0 HP left ");
                        return false;
                    }
                }
                System.out.println("Hero " + (hero_id + 1) + " use fire spell to Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has " + monsters[attack_monster_id].Get_HP() + " HP left ");
                if(monsters[attack_monster_id].Get_HP() == 0) {
                    System.out.println("Monster " + (attack_monster_id + 1) + " has been defeated");
                }
                heroes[hero_id].Remove_Fire_Spell(spell_id);
                break;
            case 1:
                System.out.println("Please select a ice spell id(from 1 to " + heroes[hero_id].Get_inventory().Get_Number_of_IceSpells() + " )");
                System.out.println();
                heroes[hero_id].Get_inventory().Show_Ice_Spell();
                spell_id = Get_Choice(heroes[hero_id].Get_inventory().Get_Number_of_IceSpells());
                attack_monster_id = Get_Attack_Monster_id();
                damage = heroes[hero_id].Get_inventory().Get_IceSpells()[spell_id].Get_damage() + (heroes[hero_id].Get_dexterity()/10000) * heroes[hero_id].Get_inventory().Get_IceSpells()[spell_id].Get_damage();
                monsters[attack_monster_id].Change_damage(monsters[attack_monster_id].Get_damage() / 2);
                if (monsters[attack_monster_id].Get_HP() - damage > 0) {
                    monsters[attack_monster_id].Change_HP(monsters[attack_monster_id].Get_HP() - damage);
                }
                else {
                    monsters[attack_monster_id].Change_HP(0);
                    if(rule.BattleIf_Over(this.number_of_team, this.heroes, this.monsters)){
                        System.out.println("Hero " + (hero_id + 1) + " use ice spell to Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has 0 HP left ");
                        return false;
                    }
                }
                System.out.println("Hero " + (hero_id + 1) + " use ice spell to Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has " + monsters[attack_monster_id].Get_HP() + " HP left ");
                if(monsters[attack_monster_id].Get_HP() == 0) {
                    System.out.println("Monster " + (attack_monster_id + 1) + " has been defeated");
                }
                heroes[hero_id].Remove_Ice_Spell(spell_id);
                break;
            case 2:
                System.out.println("Please select a lightning spell id(from 1 to " + heroes[hero_id].Get_inventory().Get_Number_of_LightningSpells() + " )");
                System.out.println();
                heroes[hero_id].Get_inventory().Show_Lightning_Spell();
                spell_id = Get_Choice(heroes[hero_id].Get_inventory().Get_Number_of_LightningSpells());
                attack_monster_id = Get_Attack_Monster_id();
                damage = heroes[hero_id].Get_inventory().Get_LightningSpells()[spell_id].Get_damage() + (heroes[hero_id].Get_dexterity()/10000) * heroes[hero_id].Get_inventory().Get_LightningSpells()[spell_id].Get_damage();
                monsters[attack_monster_id].Change_dodge_chance(monsters[attack_monster_id].Get_dodge_chance() / 2);
                if (monsters[attack_monster_id].Get_HP() - damage > 0) {
                    monsters[attack_monster_id].Change_HP(monsters[attack_monster_id].Get_HP() - damage);
                }
                else {
                    monsters[attack_monster_id].Change_HP(0);
                    if(rule.BattleIf_Over(this.number_of_team, this.heroes, this.monsters)){
                        System.out.println("Hero " + (hero_id + 1) + " use lightning spell to Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has 0 HP left ");
                        return false;
                    }
                }
                System.out.println("Hero " + (hero_id + 1) + " use lightning spell to Monster " + (attack_monster_id + 1) + ", causing " + damage + " points of damage, now Monster " + (attack_monster_id + 1) + " has " + monsters[attack_monster_id].Get_HP() + " HP left ");
                if(monsters[attack_monster_id].Get_HP() == 0) {
                    System.out.println("Monster " + (attack_monster_id + 1) + " has been defeated");
                }
                heroes[hero_id].Remove_Lightning_Spell(spell_id);
                break;
        }
        return true;
    }

    public boolean Equip_Weaponry(int hero_id){
        System.out.println("Please select an weapon id(from 1 to " + heroes[hero_id].Get_inventory().Get_Number_of_Weaponry() + " )");
        System.out.println();
        heroes[hero_id].Get_inventory().Show_Weaponry();
        weapon_id = Get_Choice(heroes[hero_id].Get_inventory().Get_Number_of_Weaponry());
        if(heroes[hero_id].Get_inventory().Get_Weaponry()[weapon_id].Get_required_hands() > heroes[hero_id].Get_Number_of_Available_Hands()){
            System.out.println("You don't have enough available hands to equip this weaponry");
            return false;
        }
        else {
            heroes[hero_id].Change_strength(heroes[hero_id].Get_strength() + heroes[hero_id].Get_inventory().Get_Weaponry()[weapon_id].Get_damage());
            heroes[hero_id].Change_number_of_available_hands(heroes[hero_id].Get_Number_of_Available_Hands() - heroes[hero_id].Get_inventory().Get_Weaponry()[weapon_id].Get_required_hands());
            System.out.println("Successfully equip " + heroes[hero_id].Get_inventory().Get_Weaponry()[weapon_id].Get_name());
            heroes[hero_id].Remove_Weaponry(weapon_id);
        }
        return true;
    }
    public void Equip_Armory(int hero_id){
        System.out.println("Please select an armory id(from 1 to " + heroes[hero_id].Get_inventory().Get_Number_of_Armory() + " )");
        System.out.println();
        heroes[hero_id].Get_inventory().Show_Armory();
        armory_id = Get_Choice(heroes[hero_id].Get_inventory().Get_Number_of_Armory());
        heroes[hero_id].Change_defense(heroes[hero_id].Get_defense() + heroes[hero_id].Get_inventory().Get_Armory()[armory_id].Get_damage_reduction());
        System.out.println("Successfully equip " + heroes[hero_id].Get_inventory().Get_Armory()[armory_id].Get_name());
        heroes[hero_id].Remove_Armory(armory_id);
    }
}
