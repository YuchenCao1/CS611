Readme.txt

# CS611-Assignment 4
## Monster and Hero
---------------------------------------------------------------------------
Yuchen Cao
caoyc@bu.edu
U51424608

## Files
---------------------------------------------------------------------------

There are 44 files included in the folder.

Some of them are for assignment before: 
Board.java, BoardGame.java, Get_Board_Size.java, Get_user_Answer.java, Order_and_Chaos.java, Piece.java, Player.java, Print_Board.java, Super_TicTacToe.java, Team.java, TicTacToe.java, QuoridorGame.java and Wall.java. Each file contains a class or interface.

Others are for this assignment: 
Game.java, Menu.java, Round.java, Rules.java, Area.java, Armory.java, FireSpell.java, Get_Unique_Random_Numbers.java, Get_World_Size.java, Hero.java, IceSpells.java, Inventory.java, Items.java, LightningSpells.java, Market.java, Monster_and_Hero_Team.java, Monster_and_Hero.java, Paladins.java, Play_Games.java, Potions.java, Print_World.java, RPGGame.java, Sorcerers.java, Warriors.java, Weaponry.java, World.java, Dragons.java, Exoskeletons.java, Spirits.java, Monsters.java, Battle.java.

Here are only files for this assignment:
1. Game.java: Includes an abstract class named Game.
Each game can extend from this class, it has some basic objects and a function used to start a game.

2. Menu.java: Includes a class named Menu.
This class is used for generating a menu for user's selection: play Tic-Tac-Toe, play Order and Chaos or exit. It contains two kinds of menu: main menu and submenu. 

3. Round.java: Includes a class named Round.
This class is used for generating the rounds, resetting the round counter, recording the number of rounds and getting the actions of each user in each round.

4.Rules.java: Includes a class named Rules.
This class is used for generating the rule of each board game and monster and hero game, checking whether the game is over or the battle is over at the end of each round.

5. Area.java: Includes a class named Area.
This class is used to record value of each area and update value of each area to assist in the implementation of the world of the Monster and Hero game.

6. Armory.java: Includes a class named Armory.
This class is used to record armories.  

7. FireSpell.java: Includes a class named FireSpell.
This class is used to record fire spells.  

8. Get_Unique_Random_Numbers.java: Includes a Interface named Get_Unique_Random_Numbers.
This Interface is used to randomly get some unique numbers. Market class uses it to generate some unique numbers to randomly create items to sell.

9. Get_World_Size.java: Includes an interface named Get_World_Size.
The Monster_and_Hero class can implement it to use Get_Worldsize() function to get the size of the world for game.

10. Hero.java: Includes a class named Hero.
This class is used for getting attributes of heroes for .txt, show heroes' attributes, let user select heroes, record and change heroes' attributes.

11. IceSpells.java: Includes a class named IceSpells.
This class is used to record ice spells.  

12. Inventory.java: Includes a class named Inventory.
This class is used to record heroes' inventories, such as weaponry, armory, potion, each kind of spell.  

13. Items.java: Includes an class named Items.
This class is superclass of Weaponry, Armory, Potions and three spells.

14. LightningSpells.java: Includes a class named LightningSpells.
This class is used to record lightning spells.  

15. Market.java: Includes a class named Market.
This class is used for getting attributes of items for .txt, randomly selecting items for this market from all items, showing different types of items in this market, purchasing and selling things.

16. Monster_and_Hero_Team.java: Includes a class named Monster_and_Hero_Team.
This class is used for getting and recording number of heroes in a team.

17. Monster_and_Hero.java: Includes a class named Monster_and_Hero.
This class is used for playing the whole Monster and Hero game. Besides, it also used for getting world size and doing transaction with the markets.

18. Paladins.java: Includes a class named Paladins.
This class is used to record paladins.  

19. Play_Games.java: Includes a class named Play_Games, which is the main class.
This class is the main class, used to begin the whole program. 

20. Potions.java: Includes a class named Potions.
This class is used to record potions. 

21. Print_World_Size.java: Includes an interface named Print_World_Size.
The World class can implement it to use Print_World_Size() function to print the world for game.

22. RPGGame.java: Includes a class named RPGGame.
This class is superclass of Monster and Hero game.

23. Sorcerers.java: Includes a class named Sorcerers.
This class is used to record sorcerers. 

24. Warriors.java: Includes a class named Warriors.
This class is used to record warriors. 

25. Weaponry.java: Includes a class named Weaponry.
This class is used to record weaponry. 

26. World.java: Includes a class named World.
This class is used to create a new random world for this game and print the world. 

27. Dragons.java: Includes a class named Dragons.
This class is used to record dragons. 

28. Exoskeletons.java: Includes a class named Exoskeletons.
This class is used to record exoskeletons. 

29. Spirits.java: Includes a class named Spirits.
This class is used to record spirits. 

30. Monsters.java: Includes a class named Monsters.
This class is used to get all attributes from the .txt, randomly create monsters and show monsters' information. 

31. Battle.java: Includes a class named Battle.
This class is used to create a battle for heroes and monsters. The heroes and monsters can fight follow the rules, and this class will return the result.


## Notes
---------------------------------------------------------------------------
1. I use some interfaces in my program.
2. I use inheritance class in my program.
3. I've added an extra feature: each hero has a different number of available hands, so some heroes can hold multiple weapons.
4. I have integrated the previous assignment with this one..

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "Monster_and_Hero" after unzipping the files.
2. Run the "Play_Games.java" in "Monster_and_Hero" folder by following steps:
javac *.java -d bin
java -cp ./bin Play_Games


## Input/Output Example
---------------------------------------------------------------------------
Output:

Welcome to Games!!!

===============Games===============
	1. Start a new Monster and Hero game
	2. Start a new Board game
	3. Exit
Please enter your choice(1-3)

Input:
1

Output:

Welcome to Monster and Hero Game

Please enter your name

Input:
cao

Output:
Hello, cao
How many heroes do you want in your team?
Please enter 1-3

Input:
2

Output:
												1. Warriors
1. Name: Gaerdal_Ironhand, MP: 100, strength: 700, agility: 500, dexterity: 600, gold: 1354, experience points: 7, number_of_available_hands: 2
2. Name: Sehanine_Monnbow, MP: 600, strength: 700, agility: 800, dexterity: 500, gold: 2500, experience points: 8, number_of_available_hands: 3
3. Name: Muamman_Duathall, MP: 300, strength: 900, agility: 500, dexterity: 750, gold: 2546, experience points: 6, number_of_available_hands: 2
4. Name: Flandal_Steelskin, MP: 200, strength: 750, agility: 650, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 4
5. Name: Undefeated_Yoj, MP: 400, strength: 800, agility: 400, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 2
6. Name: Eunoia_Cyn, MP: 400, strength: 700, agility: 800, dexterity: 600, gold: 2500, experience points: 6, number_of_available_hands: 3
												2. Sorcerers
1. Name: Rillifane_Rallathil, MP: 1300, strength: 750, agility: 450, dexterity: 500, gold: 2500, experience points: 9, number_of_available_hands: 2
2. Name: Segojan_Earthcaller, MP: 900, strength: 800, agility: 500, dexterity: 650, gold: 2500, experience points: 5, number_of_available_hands: 3
3. Name: Reign_Havoc, MP: 800, strength: 800, agility: 800, dexterity: 800, gold: 2500, experience points: 8, number_of_available_hands: 2
4. Name: Reverie_Ashels, MP: 900, strength: 800, agility: 700, dexterity: 400, gold: 2500, experience points: 7, number_of_available_hands: 2
5. Name: Kalabar, MP: 800, strength: 850, agility: 400, dexterity: 600, gold: 2500, experience points: 6, number_of_available_hands: 4
6. Name: Skye_Soar, MP: 1000, strength: 700, agility: 400, dexterity: 500, gold: 2500, experience points: 5, number_of_available_hands: 10
												3. Paladins
1. Name: Parzival, MP: 300, strength: 750, agility: 650, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 2
2. Name: Sehanine_Moonbow, MP: 300, strength: 750, agility: 700, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 2
3. Name: Skoraeus_Stonebones, MP: 250, strength: 650, agility: 600, dexterity: 350, gold: 2500, experience points: 4, number_of_available_hands: 6
4. Name: Garl_Glittergold, MP: 100, strength: 600, agility: 500, dexterity: 400, gold: 2500, experience points: 5, number_of_available_hands: 3
5. Name: Amaryllis_Astra, MP: 500, strength: 500, agility: 500, dexterity: 500, gold: 2500, experience points: 5, number_of_available_hands: 2
6. Name: Caliber_Heist, MP: 400, strength: 400, agility: 400, dexterity: 400, gold: 2500, experience points: 8, number_of_available_hands: 2
Please select the kind of heroes (1 to 3)

Input:
1

Output:
Please select the id of heroes (1 to 6)

Input:
2

Output:
												1. Warriors
1. Name: Gaerdal_Ironhand, MP: 100, strength: 700, agility: 500, dexterity: 600, gold: 1354, experience points: 7, number_of_available_hands: 2
2. Name: Sehanine_Monnbow, MP: 600, strength: 700, agility: 800, dexterity: 500, gold: 2500, experience points: 8, number_of_available_hands: 3
3. Name: Muamman_Duathall, MP: 300, strength: 900, agility: 500, dexterity: 750, gold: 2546, experience points: 6, number_of_available_hands: 2
4. Name: Flandal_Steelskin, MP: 200, strength: 750, agility: 650, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 4
5. Name: Undefeated_Yoj, MP: 400, strength: 800, agility: 400, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 2
6. Name: Eunoia_Cyn, MP: 400, strength: 700, agility: 800, dexterity: 600, gold: 2500, experience points: 6, number_of_available_hands: 3
												2. Sorcerers
1. Name: Rillifane_Rallathil, MP: 1300, strength: 750, agility: 450, dexterity: 500, gold: 2500, experience points: 9, number_of_available_hands: 2
2. Name: Segojan_Earthcaller, MP: 900, strength: 800, agility: 500, dexterity: 650, gold: 2500, experience points: 5, number_of_available_hands: 3
3. Name: Reign_Havoc, MP: 800, strength: 800, agility: 800, dexterity: 800, gold: 2500, experience points: 8, number_of_available_hands: 2
4. Name: Reverie_Ashels, MP: 900, strength: 800, agility: 700, dexterity: 400, gold: 2500, experience points: 7, number_of_available_hands: 2
5. Name: Kalabar, MP: 800, strength: 850, agility: 400, dexterity: 600, gold: 2500, experience points: 6, number_of_available_hands: 4
6. Name: Skye_Soar, MP: 1000, strength: 700, agility: 400, dexterity: 500, gold: 2500, experience points: 5, number_of_available_hands: 10
												3. Paladins
1. Name: Parzival, MP: 300, strength: 750, agility: 650, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 2
2. Name: Sehanine_Moonbow, MP: 300, strength: 750, agility: 700, dexterity: 700, gold: 2500, experience points: 7, number_of_available_hands: 2
3. Name: Skoraeus_Stonebones, MP: 250, strength: 650, agility: 600, dexterity: 350, gold: 2500, experience points: 4, number_of_available_hands: 6
4. Name: Garl_Glittergold, MP: 100, strength: 600, agility: 500, dexterity: 400, gold: 2500, experience points: 5, number_of_available_hands: 3
5. Name: Amaryllis_Astra, MP: 500, strength: 500, agility: 500, dexterity: 500, gold: 2500, experience points: 5, number_of_available_hands: 2
6. Name: Caliber_Heist, MP: 400, strength: 400, agility: 400, dexterity: 400, gold: 2500, experience points: 8, number_of_available_hands: 2
Please select the kind of heroes (1 to 3)

Input:
2

Output:
Please select the id of heroes (1 to 6)

Input:
6

Output:

How many rows do you want for Monster and Hero Game?
Only enter a number between 8 and 10 (Recommend 8)

Input:
8

Output:

How many columns do you want for Monster and Hero Game?
Only enter a number between 8 and 10 (Recommend 8)

Input:
8

Output:
YM	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
Do you need to refresh the map？(y/n)

Input:
n

Output:

Day 1
YM	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 0colume: 0

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
S

Output:

Day 2
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
YM	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 1colume: 0

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
M

Output:
						1. Weaponry
1.Name: Sword, Cost: 500, Level: 1, damage: 800, required_hands: 1
2.Name: Scythe, Cost: 1000, Level: 6, damage: 1100, required_hands: 2
3.Name: Axe, Cost: 550, Level: 5, damage: 850, required_hands: 1

						2. Armory
1.Name: Breastplate, Cost: 350, Level: 3, damage_reduction: 600
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Guardian_Angel, Cost: 1000, Level: 10, damage_reduction: 1000

						3. Potion
1.Name: Healing_Potion, Cost: 250, Level: 1, attribute_increase: 100, attribute_affected: Health
2.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
3.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility

						4. Fire spell
1.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
2.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Ice_Blade, Cost: 250, Level: 1, damage: 450, mana_cost: 100
2.Name: Snow_Cannon, Cost: 500, Level: 2, damage: 650, mana_cost: 250

						6. Lightning spell
1.Name: Lightning_Dagger, Cost: 400, Level: 1, damage: 500, mana_cost: 150
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200

===============Market===============
	1. Purchase items
	2. Sell items
	3. Exit
Please enter your choice 1 to 3

Input:
1

Output:
						1. Weaponry
1.Name: Sword, Cost: 500, Level: 1, damage: 800, required_hands: 1
2.Name: Scythe, Cost: 1000, Level: 6, damage: 1100, required_hands: 2
3.Name: Axe, Cost: 550, Level: 5, damage: 850, required_hands: 1

						2. Armory
1.Name: Breastplate, Cost: 350, Level: 3, damage_reduction: 600
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Guardian_Angel, Cost: 1000, Level: 10, damage_reduction: 1000

						3. Potion
1.Name: Healing_Potion, Cost: 250, Level: 1, attribute_increase: 100, attribute_affected: Health
2.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
3.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility

						4. Fire spell
1.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
2.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Ice_Blade, Cost: 250, Level: 1, damage: 450, mana_cost: 100
2.Name: Snow_Cannon, Cost: 500, Level: 2, damage: 650, mana_cost: 250

						6. Lightning spell
1.Name: Lightning_Dagger, Cost: 400, Level: 1, damage: 500, mana_cost: 150
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200
Which kind do you want to buy
Please enter your choice 1 to 6

Input:
1

Output:
Which one do you want to buy
Please enter your choice

Input:
1

Output:
The price is: 500
Hero 1's gold: 2500
Hero 2's gold: 2500
Which hero will pay for it
Please enter your choice 1 to 2

Input:
1

Output:
Purchase successful!
Do you want to exit market?(y/n)

Input:
n

Output:
						1. Weaponry
1.Name: Axe, Cost: 550, Level: 5, damage: 850, required_hands: 1
2.Name: Scythe, Cost: 1000, Level: 6, damage: 1100, required_hands: 2

						2. Armory
1.Name: Breastplate, Cost: 350, Level: 3, damage_reduction: 600
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Guardian_Angel, Cost: 1000, Level: 10, damage_reduction: 1000

						3. Potion
1.Name: Healing_Potion, Cost: 250, Level: 1, attribute_increase: 100, attribute_affected: Health
2.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
3.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility

						4. Fire spell
1.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
2.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Ice_Blade, Cost: 250, Level: 1, damage: 450, mana_cost: 100
2.Name: Snow_Cannon, Cost: 500, Level: 2, damage: 650, mana_cost: 250

						6. Lightning spell
1.Name: Lightning_Dagger, Cost: 400, Level: 1, damage: 500, mana_cost: 150
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200

===============Market===============
	1. Purchase items
	2. Sell items
	3. Exit
Please enter your choice 1 to 3

Input:
1

Output:
						1. Weaponry
1.Name: Axe, Cost: 550, Level: 5, damage: 850, required_hands: 1
2.Name: Scythe, Cost: 1000, Level: 6, damage: 1100, required_hands: 2

						2. Armory
1.Name: Breastplate, Cost: 350, Level: 3, damage_reduction: 600
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Guardian_Angel, Cost: 1000, Level: 10, damage_reduction: 1000

						3. Potion
1.Name: Healing_Potion, Cost: 250, Level: 1, attribute_increase: 100, attribute_affected: Health
2.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
3.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility

						4. Fire spell
1.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
2.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Ice_Blade, Cost: 250, Level: 1, damage: 450, mana_cost: 100
2.Name: Snow_Cannon, Cost: 500, Level: 2, damage: 650, mana_cost: 250

						6. Lightning spell
1.Name: Lightning_Dagger, Cost: 400, Level: 1, damage: 500, mana_cost: 150
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200
Which kind do you want to buy
Please enter your choice 1 to 6

Input:
3

Output:
Which one do you want to buy
Please enter your choice

Input:
1

Output:
The price is: 250
Hero 1's gold: 2000
Hero 2's gold: 2500
Which hero will pay for it
Please enter your choice 1 to 2

Input:
1

Output:
Purchase successful!
Do you want to exit market?(y/n)

Input:
n

Output:
						1. Weaponry
1.Name: Axe, Cost: 550, Level: 5, damage: 850, required_hands: 1
2.Name: Scythe, Cost: 1000, Level: 6, damage: 1100, required_hands: 2

						2. Armory
1.Name: Breastplate, Cost: 350, Level: 3, damage_reduction: 600
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Guardian_Angel, Cost: 1000, Level: 10, damage_reduction: 1000

						3. Potion
1.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
2.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility

						4. Fire spell
1.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
2.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Ice_Blade, Cost: 250, Level: 1, damage: 450, mana_cost: 100
2.Name: Snow_Cannon, Cost: 500, Level: 2, damage: 650, mana_cost: 250

						6. Lightning spell
1.Name: Lightning_Dagger, Cost: 400, Level: 1, damage: 500, mana_cost: 150
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200

===============Market===============
	1. Purchase items
	2. Sell items
	3. Exit
Please enter your choice 1 to 3

Input:
1

Output:
						1. Weaponry
1.Name: Axe, Cost: 550, Level: 5, damage: 850, required_hands: 1
2.Name: Scythe, Cost: 1000, Level: 6, damage: 1100, required_hands: 2

						2. Armory
1.Name: Breastplate, Cost: 350, Level: 3, damage_reduction: 600
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Guardian_Angel, Cost: 1000, Level: 10, damage_reduction: 1000

						3. Potion
1.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
2.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility

						4. Fire spell
1.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
2.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Ice_Blade, Cost: 250, Level: 1, damage: 450, mana_cost: 100
2.Name: Snow_Cannon, Cost: 500, Level: 2, damage: 650, mana_cost: 250

						6. Lightning spell
1.Name: Lightning_Dagger, Cost: 400, Level: 1, damage: 500, mana_cost: 150
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200
Which kind do you want to buy
Please enter your choice 1 to 6

Input:
5

Output:
Which one do you want to buy
Please enter your choice

Input:
1

Output:
The price is: 250
Hero 1's gold: 1750
Hero 2's gold: 2500
Which hero will pay for it
Please enter your choice 1 to 2

Input:
2

Output:
Purchase successful!
Do you want to exit market?(y/n)

Input:
y

Output:

Day 2
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
YM	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 1colume: 0

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
s

Output:
Day 3
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
YM	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 2colume: 0

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
D

Output:

Day 4
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|Y	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 2colume: 1

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
S

Output:

Day 5
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|Y	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 3colume: 1

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
D

Output:

Day 6
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|Y	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 3colume: 2

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
D

Output:

Day 7
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|Y	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 3colume: 3

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
D

Output:

Day 8
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|Y	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 3colume: 4

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
D

Output:

Day 9
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|Y	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 3colume: 5

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
S

Output:
Here comes a group of Monsters
Heroes:
1: Type: Warrior, Name: Sehanine_Monnbow, Level: 1, Experience_Points: 8, HP: 100, MP: 600, Gold: 1750, Strength: 700, Number of available hands: 3
Inventory: 
Name: Sword, Cost: 500, Level: 1, Damage: 800, Required hands: 1
Name: Healing_Potion, Cost: 250, Level: 1, Attribute increase: 100, Attribute affected: Health
2: Type: Sorcerer, Name: Skye_Soar, Level: 1, Experience_Points: 5, HP: 100, MP: 1000, Gold: 2250, Strength: 700, Number of available hands: 10
Inventory: 
Name: Ice_Blade, Cost: 250, Level: 1, Damage: 450, Mana cost: 100

Monsters:
1: Type: Dragons, Name: Natsunomeryu, Level: 1, HP: 100, Damage: 100, Defense: 200, Dodge chance: 10
2: Type: Dragons, Name: Natsunomeryu, Level: 1, HP: 100, Damage: 100, Defense: 200, Dodge chance: 10
What do you want Hero 1 to do?
Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information

Input:
4

Output:
1.Equip a weapon
2.Equip a piece of armory

Input:
1

Output:
Please select an weapon id(from 1 to 1 )

Name: Sword, Cost: 500, Level: 1, Damage: 800, Required hands: 1

Input:
1

Output:
Successfully equip Sword
What do you want Hero 2 to do?
Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information

Input:
I

Output:
Heroes:
1: Type: Warrior, Name: Sehanine_Monnbow, Level: 1, Experience_Points: 8, HP: 100, MP: 600, Gold: 1750, Strength: 1500, Number of available hands: 2
Inventory: 
Name: Healing_Potion, Cost: 250, Level: 1, Attribute increase: 100, Attribute affected: Health
2: Type: Sorcerer, Name: Skye_Soar, Level: 1, Experience_Points: 5, HP: 100, MP: 1000, Gold: 2250, Strength: 700, Number of available hands: 10
Inventory: 
Name: Ice_Blade, Cost: 250, Level: 1, Damage: 450, Mana cost: 100

Monsters:
1: Type: Dragons, Name: Natsunomeryu, Level: 1, HP: 100, Damage: 100, Defense: 200, Dodge chance: 10
2: Type: Dragons, Name: Natsunomeryu, Level: 1, HP: 100, Damage: 100, Defense: 200, Dodge chance: 10
What do you want Hero 2 to do?
Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information

Input:
2

Output:
Please select a spell kind(from 1 to 3)

1.Fire Spell:

2.Ice Spell:
Name: Ice_Blade, Cost: 250, Level: 1, Damage: 450, Mana cost: 100

3.Lightning Spell:

Please enter kind of spell you want to use

Input:
2

Output:
Please select a ice spell id(from 1 to 1 )

Name: Ice_Blade, Cost: 250, Level: 1, Damage: 450, Mana cost: 100

Input:
1

Output:
Please enter id of the monster you want to attack

Input:
1

Output:
Hero 2 use ice spell to Monster 1, causing 450 points of damage, now Monster 1 has 0 HP left 
Monster 1 has been defeated
Moster 2 attacked Hero 2, causing 10 points of damage, now Hero 2 has 90 HP left 
What do you want Hero 1 to do?
Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information

Input:
3

Output:
Please select a potion(from id 1 to 1 )

Name: Healing_Potion, Cost: 250, Level: 1, Attribute increase: 100, Attribute affected: Health

Please enter id of potion you want to use

Input:
1

Output:
What do you want Hero 2 to do?
Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information

Input:
1

Output:
Please enter id of the monster you want to attack

Input:
2

Output:
Hero 2 attacked Monster 2, causing 50 points of damage, now Monster 2 has 50 HP left 
Moster 2 attacked Hero 2, causing 10 points of damage, now Hero 2 has 89 HP left 
What do you want Hero 1 to do?
Please enter 1 to Attack, 2 to Cast a spell, 3 to Use a potion, 4 to Equip a weapon or piece of armory, I to show information

Input:
1

Output:
Please enter id of the monster you want to attack

Input:
2

Output:
Hero 1 attacked Monster 2, causing 130 points of damage, now Monster 2 has 0 HP left 
Heroes win!!!

Hero 1 level up!
Now his information is:
Type: Warrior, Name: Sehanine_Monnbow, Level: 2, Experience_Points: 2, HP: 200, MP: 798, Gold: 1750, Strength: 1650, Number of available hands: 2
Inventory: 

Day 10
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|Y	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 4colume: 5

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
M

Output:
This area isn't a market!

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
D

Output:

Day 11
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|Y	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|M	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 4colume: 6

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
S

Output:

Day 12
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|YM	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 5colume: 6

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
M

Output:
						1. Weaponry
1.Name: Sword, Cost: 500, Level: 1, damage: 800, required_hands: 1
2.Name: Bow, Cost: 300, Level: 2, damage: 500, required_hands: 2
3.Name: Dagger, Cost: 200, Level: 1, damage: 250, required_hands: 1

						2. Armory
1.Name: Platinum_Shield, Cost: 150, Level: 1, damage_reduction: 200
2.Name: Full_Body_Armor, Cost: 1000, Level: 8, damage_reduction: 1100
3.Name: Wizard_Shield, Cost: 1200, Level: 10, damage_reduction: 1500

						3. Potion
1.Name: Ambrosia, Cost: 1000, Level: 8, attribute_increase: 150, attribute_affected: Health/Mana/Strength/Dexterity/Defense/Agility
2.Name: Mermaid_Tears, Cost: 850, Level: 5, attribute_increase: 100, attribute_affected: Health/Mana/Strength/Agility
3.Name: Luck_Elixir, Cost: 500, Level: 4, attribute_increase: 65, attribute_affected: Agility

						4. Fire spell
1.Name: Hell_Storm, Cost: 600, Level: 3, damage: 950, mana_cost: 600
2.Name: Heat_Wave, Cost: 450, Level: 2, damage: 600, mana_cost: 150
3.Name: Breath_of_Fire, Cost: 350, Level: 1, damage: 450, mana_cost: 100

						5. Ice spell
1.Name: Frost_Blizzard, Cost: 750, Level: 5, damage: 850, mana_cost: 350
2.Name: Arctic_Storm, Cost: 700, Level: 6, damage: 800, mana_cost: 300

						6. Lightning spell
1.Name: Electric_Arrows, Cost: 550, Level: 5, damage: 650, mana_cost: 200
2.Name: Spark_Needles, Cost: 500, Level: 2, damage: 600, mana_cost: 200

===============Market===============
	1. Purchase items
	2. Sell items
	3. Exit
Please enter your choice 1 to 3

Input:
3

Output:
Do you want to exit market?(y/n)

Input:
y

Output:

Day 12
M	|X	|M	|	|	|M	|M	|
----------------------------------------------------------------
M	|X	|	|	|	|X	|M	|M
----------------------------------------------------------------
M	|	|X	|X	|	|X	|M	|
----------------------------------------------------------------
X	|	|	|	|	|	|M	|X
----------------------------------------------------------------
	|	|	|M	|	|	|	|
----------------------------------------------------------------
	|X	|	|X	|M	|M	|YM	|M
----------------------------------------------------------------
M	|M	|	|	|	|M	|	|
----------------------------------------------------------------
	|M	|	|	|	|	|M	|
row: 5colume: 6

Please enter W/A/S/D to move, Q to quit the game, I to show information, M to enter market

Input:
Q

Output:
Your choice is to quit the game

===============Games===============
	1. Start a new Monster and Hero game
	2. Start a new Board game
	3. Exit
Please enter your choice(1-3)

Input:
3

Output:
Are you sure to exit?(y/n)

Input:
y

Output:

See you next time!!!

