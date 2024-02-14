# CS611 Assignment 1: "TicTacToe" and "Order and Chaos"
**Name:** _Reshab Chhabra_

**BU Email:** reshabc@bu.edu

**BUID:** U93403317


**GitHub Repository**: https://github.com/rechhabra/CS611

## Java Object-Oriented Design

The code is ran with `Main.java` which will then prompt `Menu.java` to show my CS611 menu! The rest can be traced easily :).

| Main |
|------|
| `public static void main(String[] args)` |

| Menu |
|------|
| `public static void displayHomePage(): void` |
| `private static void displayBoardGamePage(): void` |
| `public static Player displayPlayerCreationPage(): Player` |
| `public static Player displayPlayerSelectionPage(): Player` |
| `public static Team displayTeamCreationPage(): Team` |


|Database|
|-|
|`PLAYERS: List<Players>`|
|`TEAMS: List<Team>`|

| Board |
| - |
| `numRows: int`|
| `numCols: int`|
| `board: Cell<?>[][]`|
|| 
| `Board(int, int)` |
| `getNumRows(): int `|
| `getNumCols(): int`|
| `getCell(int, int): Cell<?>`|
| `setCell(int, int, T): void`|

| Cell |
|-|
|`occupier: T`|
|`symbol: Character`|
||
|`setOccupier(T): void`|
|`getOccupier(): T`|
|`getSymbol(): char`|
|`setSymbol(Character): void`|
|`isOccupied(): boolean`|
|`toString(): string`|

| BoardGame (abstract implements Game)|
|-----------|
| `Queue<Team> teams` |
| `Queue<Player> players` |
| `Board board` |
| `boolean teamsRequired` |
||
| `abstract boolean isLegalMove(int, int): boolean` |
| `abstract Board setupBoard(): Board` |
| `abstract void doTurn(): void` |
| `abstract void assignNextCurrentPlayer(): void` |
| `abstract Player getCurrentPlayer(): Player` |
| `abstract Team getCurrentTeam(): Team` |
| `Queue<Player> setupPlayers(int, int): Queue<Player>` |
| `Queue<Team> setupTeams(int, int, Integer, Queue<Player>): Queue<Team>` |
| `Queue<Player> getPlayers(): Queue<Player>` |
| `Queue<Team> getTeams(): Queue<Team>` |

| Game (interface)|
|------|
| `String description(): String` |
| `void start(): void` |
| `void end(): void` |
| `void reset(): void` |
| `Queue<Team> getTeams(): Queue<Team>` |
| `Queue<Player> getPlayers(): Queue<Player>` |
| `default Queue<Player> setupPlayers(): Queue<Player>` |
| `default Queue<Team> setupTeams(): Queue<Team>` |
| `default void promptToReplay(): void` |
| `default void cleanup(): void` |

| GameRecord |
|------------|
| `int numWins` |
| `int numLosses` |
| `int numDraws` |
||
| `GameRecord()` |
| `void addWin()` |
| `void addLoss()` |
| `void addDraw()` |
| `String toString(): String` |

| OutputColor (enum)|
|-------------|
| `RED` |
| `GREEN` |
| `YELLOW` |
| `BLUE` |
| `PURPLE` |
| `CYAN` |

| OutputColorer |
|---------------|
| `static String red(String message)` |
| `static String green(String message)` |
| `static String yellow(String message)` |
| `static String blue(String message)` |
| `static String purple(String message)` |
| `static String cyan(String message)` |
| `static String withColor(OutputColor color, String message)` |

| OrderAndChaos (extends BoardGame)|
|---------------|
| `private final Team order` |
| `private final Team chaos` |
| `private int currentMoveRow` |
| `private int currentMoveCol` |
| `private final int STREAK_TO_WIN = 5` |
||
| `public OrderAndChaos()` |
| `public String description(): String` |
| `public boolean isLegalMove(final int rowNum, final int colNum): boolean` |
| `public boolean isWinningMove(final int moveRow, final int moveCol): boolean` |
| `public Board setupBoard(): Board` |
| `public void doTurn(): void` |
| `public void assignNextCurrentPlayer(): void` |
| `public boolean areNoMovesLeft(): boolean` |
| `public Player getCurrentPlayer(): Player` |
| `public Team getCurrentTeam(): Team` |
| `public void start(): void` |
| `public void end(): void` |
| `public void reset(): void` |
| `public String toString(): String` |

| Symbolable (interface)|
|------------|
| `public char getSymbol(): char` |
| `public OutputColor getOutputColor(): OutputColor` |


| Player (implements Symbolable)|
|--------|
| `private final char symbol` |
| `private final OutputColor color` |
| `private final String name` |
| `private GameRecord gameRecord` |
||
| `public Player(final String name, final char symbol)` |
| `public Player(final String name, final char symbol, final OutputColor color)` |
| `public char getSymbol(): char` |
| `public OutputColor getOutputColor(): OutputColor` |
| `public String getName(): String` |
| `public GameRecord getGameRecord(): GameRecord` |
| `public String toString(): String` |

| Team (implements Symbolable)|
|------|
| `private final String name` |
| `private final char symbol` |
| `private final OutputColor color` |
| `private Queue<Player> players` |
||
| `public Team(final String name, final char symbol)` |
| `public Team(final String name, final char symbol, final OutputColor color)` |
| `public void addPlayer(final Player player): void` |
| `public Queue<Player> getPlayers(): Queue<Player>` |
| `public String getName(): String` |
| `public char getSymbol(): char` |
| `public OutputColor getOutputColor(): OutputColor` |
| `public String toString(): String` |

| TicTacToe (extends BoardGame)|
|-----------|
| `private final int STREAK_TO_WIN` = 3|
| `private final boolean teamsAllowed` |
| `private int currentMoveRow` |
| `private int currentMoveCol` |
||
| `public TicTacToe()` |
| `public String description(): String` |
| `public boolean isLegalMove(final int rowNum, final int colNum): boolean` |
| `public Board setupBoard(): Board` |
| `public void doTurn(): void` |
| `public void start(): void` |
| `public void end(): void` |
| `public void reset(): void` |
| `public boolean isWinningMove(final int moveRow, final int moveCol): boolean` |
| `public boolean areNoMovesLeft(): boolean` |
| `public Queue<Player> setupPlayers(): Queue<Player>` |
| `public Queue<Team> setupTeams(): Queue<Team>` |
| `public Player getCurrentPlayer(): Player` |
| `public Team getCurrentTeam(): Team` |
| `public void assignNextCurrentPlayer(): void` |
| `public String toString(): String` |

| UserInteractor |
|---------------|
||
| `static int getIntegerInput(String prompt): int` |
| `static int getIntegerInput(String prompt, int minNum): int` |
| `static int getIntegerInput(String prompt, int minNum, int maxNum): int` |
| `static String getStringInput(String prompt, int minStringLength, int maxStringLength): String` |
| `static String getYesNoInput(String prompt): String` |
| `static char getSymbolInput(String prompt, char[] validOptions): char` |
| `static char getUniqueSymbolInput(String prompt): char` |

## How To Run

Run the following in your terminal:
```
javac Main.java
java Main.java
```

To test my code, I used IntelliJ with JDK `1.8.032` (Java8)

## Bonus Points

I believe I deserve bonus points because not only did I implement teams, but of how I made my team and player structure: my assignment allows teams to be assigned neatly based on user's preference and is not done randomly (unless it needs to be specifically later on), feel free to try it out!.

## Resourcing

I only used ChatGPT to help me write the Java outlines for the classes in this Markdown file.

## TODOs

I added some TODOS in there for next steps that I want to do in the next assignment; I may even make this code more generalizable in the future :P