import java.util.Random;
import java.util.Scanner;

public class Monster_and_Hero extends RPGGame implements Get_World_Size{// Play TicTacToe
    private char choice;
    private int heroes_id;
    private int monsters_id;
    private int market_id;
    private int number_of_markets;
    private String user_enter;
    private char round_choice;
    private int[] player_position = new int[2];
    private int[] this_move = new int[2];
    private boolean if_this_round_choice_valid;
    private int now_round;
    private boolean if_exit;
    private int transaction_type;
    private boolean if_choice_valid;
    private int[] sell_id = new int[2];

    public int Get_Worldsize() throws Exception{// Get world size for Monster and Hero game
        do {
            System.out.println("Only enter a number between 8 and 10 (Recommend 8)");

            Scanner sc = new Scanner(System.in);
            user_enter = sc.next();
            if (user_enter.length() != 1){
                choice = user_enter.charAt(0);
                if (Character.getNumericValue(choice) != 1){
                    System.out.println("\n\nPlease enter only a number from 8 to 10!");
                }
                else {
                    choice = user_enter.charAt(1);
                    this.worldsize = 10 + Character.getNumericValue(choice);
                    if (this.worldsize == 10) {
                        break;
                    }
                    else {
                        System.out.println("\n\nPlease enter only a number from 8 to 10!");
                    }
                }
            }
            else {
                choice = user_enter.charAt(0);
                this.worldsize = Character.getNumericValue(choice);
                if (this.worldsize >= 8 && this.worldsize <= 9) {
                    break;
                }
                else{
                    System.out.println("\n\nPlease enter only a number from 8 to 10!");
                }
            }
        }while(true);
        return this.worldsize;
    }

    public int Do_Transaction() throws Exception {//Reset pieces
        System.out.println("\n===============Market===============");
        System.out.println("\t1. Purchase items");
        System.out.println("\t2. Sell items");
        System.out.println("\t3. Exit");
        if_choice_valid = false;
        System.out.println("Please enter your choice 1 to 3");
        do{
            Scanner sc = new Scanner(System.in);
            this.user_enter = sc.next();
            if (this.user_enter.length() != 1) {
                System.out.println("\nPlease only enter 1 to 3");
            }
            else{
                this.choice = user_enter.charAt(0);
                transaction_type = Character.getNumericValue(choice);
                if (transaction_type >= 1 && transaction_type <= 3) {
                    if_choice_valid = true;
                }
                else {
                    System.out.println("\nPlease only enter 1 to 3");
                }
            }
        }while(!if_choice_valid);
        return transaction_type;
    }

    public void Start() throws Exception{// Start a Monster and Hero game
        System.out.println("\nWelcome to Monster and Hero Game");
        Player now_player = new Player();
        now_player.Set_Name();
        System.out.println("Hello, " + now_player.Get_Name());

        Monster_and_Hero_Team now_team = new Monster_and_Hero_Team();
        System.out.println("How many heroes do you want in your team?");
        now_team.Get_Number_of_Heroes_in_Team();
        Hero[] now_heroes = new Hero[now_team.Get_Number_of_Heroes_in_a_Team()];
        for(heroes_id = 0; heroes_id < now_team.Get_Number_of_Heroes_in_a_Team();heroes_id++){
            Hero now_hero = new Hero();
            now_heroes[heroes_id] = now_hero;
        }

        for(heroes_id = 0; heroes_id < now_team.Get_Number_of_Heroes_in_a_Team();heroes_id++){
            now_heroes[heroes_id].Select_Heroes();
        }


        World now_world = new World(0, 0);
        System.out.println("\nHow many rows do you want for Monster and Hero Game?");
        this.world_length = Get_Worldsize();
        System.out.println("\nHow many columns do you want for Monster and Hero Game?");
        this.world_width = Get_Worldsize();
        now_world.Reset_World_Size(this.world_length, this.world_width);

        Area now_areas = new Area(now_world.world_length, now_world.world_width);

        do {
            now_areas.Set_Areas(now_world.Create_World(this.world_length, this.world_width, now_areas.Get_Areas()));
            now_world.PrintWorld(now_areas.Get_Areas());
        } while(!now_world.Confirm_World());

        number_of_markets = now_world.Get_Number_of_Markets();
        Market[] now_markets = new Market[number_of_markets];
        for(market_id = 0; market_id < number_of_markets; market_id++){
            Market now_market = new Market();
            now_market.Reset_Market();
            now_markets[market_id] = now_market;
        }

        Round round = new Round();
        round.ResetRound();

        Rules rule = new Rules();


        if_gameover = false;
        do {
            System.out.println("\nDay " + (round.Get_Round()+1));
            now_world.PrintWorld(now_areas.Get_Areas());
            if_this_round_choice_valid = false;
            this.player_position = now_areas.Get_Player_Position(now_world.world_length, now_world.world_width, now_areas.Get_Areas());
            System.out.println("row: " + player_position[0] + "colume: " + player_position[1]);
            do {
                this.round_choice = round.Get_Next_Round_Choice();
                if (round_choice == 'W' || round_choice == 'A' || round_choice == 'S' || round_choice == 'D' || round_choice == 'w' || round_choice == 'a' || round_choice == 's' || round_choice == 'd') {
                    if_this_round_choice_valid = rule.If_This_Round_Choice_Valid(this.player_position, now_areas.Get_Areas(), round_choice, now_world.world_length, now_world.world_width);
                    if (this.if_this_round_choice_valid) {
                        now_areas.Set_Areas(now_areas.Update_Areas(this.player_position, round_choice, now_areas.Get_Areas()));
                        this.player_position = now_areas.Get_Player_Position(now_world.world_length, now_world.world_width, now_areas.Get_Areas());
                        Random random = new Random();
                        double chance = random.nextDouble();
                        if (chance < 0.3 && now_areas.Get_Areas()[player_position[0]][player_position[1]].length() <= 1) {
                            System.out.println("Here comes a group of Monsters");
                            Monsters[] monsters = new Monsters[now_team.Get_Number_of_Heroes_in_a_Team()];
                            for (monsters_id = 0; monsters_id < now_team.Get_Number_of_Heroes_in_a_Team(); monsters_id++) {
                                Monsters monster = new Monsters();
                                monster.Create_Monsters(now_heroes[monsters_id].Get_level());
                                monsters[monsters_id] = monster;
                            }
                            Battle battle = new Battle();
                            now_heroes = battle.Start_Battle(now_team.Get_Number_of_Heroes_in_a_Team(), now_heroes, monsters);
                            if_gameover = true;
                            for (int i = 0; i < now_team.Get_Number_of_Heroes_in_a_Team(); i++) {
                                if (now_heroes[i].Get_Hp() != 0) {
                                    if_gameover = false;
                                }
                            }
                            if (if_gameover) {
                                System.out.println("All heroes are fainted, Game Over!!!");
                            }
                            else {
                                for(int hero_id = 0; hero_id < now_team.Get_Number_of_Heroes_in_a_Team(); hero_id++){
                                    if(now_heroes[hero_id].Get_Hp() > 0) {
                                        boolean if_level_up;
                                        if_level_up = now_heroes[hero_id].Gain_Experience_Point(now_team.Get_Number_of_Heroes_in_a_Team());
                                        if (if_level_up) {
                                            System.out.println("\nHero " + (hero_id + 1) + " level up!");
                                            now_heroes[hero_id].Level_Up();
                                        }
                                        now_heroes[hero_id].Gain_Gold();
                                    }
                                    else {
                                        now_heroes[hero_id].Change_HP(now_heroes[hero_id].Get_level() * 50);
                                        now_heroes[hero_id].Change_MP(now_heroes[hero_id].Get_MP() / 2);
                                    }
                                }
                            }
                        }
                        round.Next_Round();
                    }
                    else {
                        System.out.println("Cannot take such an action, the area you want to go is inaccessible");
                    }

                } else if (round_choice == 'Q' || round_choice == 'q'){
                    if_this_round_choice_valid = true;
                    if_gameover = true;
                    System.out.println("Your choice is to quit the game");

                } else if (round_choice == 'I' || round_choice == 'i') {
                    if_this_round_choice_valid = true;
                    for (heroes_id = 0; heroes_id < now_team.Get_Number_of_Heroes_in_a_Team(); heroes_id++) {
                        System.out.printf("\n" + (heroes_id+1) + ". ");
                        now_heroes[heroes_id].Show_Information();
                    }
                } else if (round_choice == 'M' || round_choice == 'm') {
                    if (now_areas.Get_Areas()[player_position[0]][player_position[1]].length() > 1) {
                        if_this_round_choice_valid = true;
                        this.market_id = Character.getNumericValue(now_areas.Get_Areas()[player_position[0]][player_position[1]].charAt(2));
                        Menu menu = new Menu();
                        do {
                            now_markets[this.market_id].Show_items();
                            transaction_type = Do_Transaction();
                            if (transaction_type == 1) {
                                now_heroes = now_markets[this.market_id].Purchase_Items(now_team.Get_Number_of_Heroes_in_a_Team(), now_heroes);
                            } else if (transaction_type == 2) {
                                now_heroes = now_markets[this.market_id].Sell_Items(now_team.Get_Number_of_Heroes_in_a_Team(), now_heroes);
                            }

                            if_exit = menu.SubMenu2();
                        } while (!if_exit);
                    } else {
                        System.out.println("This area isn't a market!");
                    }
                }

            } while (!this.if_this_round_choice_valid);

        }while(!if_gameover);
    }
}
