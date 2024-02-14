/*
This class serves to be the Menu that will direct
the user to different entertainment options, whether
be playing a Board Game or even just writing notes.
 */
public final class Menu {

    public static void displayHomePage(){
        System.out.println(OutputColorer.purple("Welcome to Reshab Chhabra's CS611 Menu!"));
        System.out.println(OutputColorer.cyan("1) Play a board game"));
        System.out.println(OutputColorer.cyan("2) Play a role play game"));
        System.out.println(OutputColorer.cyan("3) Quit"));

        final int selectedOption = UserInteractor.getIntegerInput("Select an option", 1, 3);

        switch (selectedOption) {
            case 1:
                displayBoardGamePage();
                break;
            case 2:
                displayRolePlayGamePage();
                break;
            case 3:
                break;
        }
    }
    private static void displayRolePlayGamePage(){
        System.out.println(OutputColorer.cyan("1) Legends: Monsters and Heroes"));
        System.out.println(OutputColorer.cyan("2) Legends of Valor"));
        System.out.println(OutputColorer.cyan("3) Back"));
        final int selectedOption = UserInteractor.getIntegerInput("Select a role play game to play", 1, 3);
        RolePlayGame selectedRolePlayGame = null;

        switch (selectedOption){
            case 1:
                selectedRolePlayGame = new LegendsMonstersAndHeroes();
                break;
            case 2:
                selectedRolePlayGame = new LegendsOfValor();
                break;
            case 3:
                displayHomePage();
                break;
        }

        if (selectedRolePlayGame != null){
            selectedRolePlayGame.start();
        }
    }

    private static void displayBoardGamePage(){
        System.out.println(OutputColorer.cyan("1) TicTacToe"));
        System.out.println(OutputColorer.cyan("2) Order of Chaos"));
        System.out.println(OutputColorer.cyan("3) (Unrestricted) Super TicTacToe"));
        System.out.println(OutputColorer.cyan("4) (Restricted)   Super TicTacToe"));
        System.out.println(OutputColorer.cyan("5) Quoridor (paired with Gautam Kalyankar)"));
        System.out.println(OutputColorer.cyan("6) Back"));
        final int selectedOption = UserInteractor.getIntegerInput("Select a board game to play", 1, 6);
        BoardGame selectedBoardGame = null;

        switch (selectedOption){
            case 1:
                selectedBoardGame = new TicTacToe();
                break;
            case 2:
                selectedBoardGame = new OrderAndChaos();
                break;
            case 3:
                selectedBoardGame = new SuperTicTacToeUnrestricted();
                break;
            case 4:
                selectedBoardGame = new SuperTicTacToeRestricted();
                break;
            case 5:
                selectedBoardGame = new Quoridor();
                break;
            case 6:
                displayHomePage();
                break;
        }
        if (selectedBoardGame != null) {
            selectedBoardGame.start();
        }
    }

    public static Player displayPlayerCreationPage(){
        System.out.println(OutputColorer.purple("You currently are adding a new player"));
        final String playerName = UserInteractor.getStringInput("Enter the new player's name",1, 10);
        System.out.println(OutputColorer.purple("Now you need to input player ")+OutputColorer.blue(playerName)+OutputColorer.purple("'s symbol."));
        final char playerSymbol = UserInteractor.getUniqueSymbolInput("Enter player "+playerName+ "'s symbol.");
        final Player newPlayer = new Player(playerName, playerSymbol);
        Database.PLAYERS.add(newPlayer);
        return newPlayer;
    }

    /*
    NOTE: This method assumes there are players in the Database.
    However, this will be called only when there are players.
     */
    public static Player displayPlayerSelectionPage(){
        System.out.println(OutputColorer.purple("You are currently selecting a player"));
        final int minPlayerIndex = 1;
        final int maxPlayerIndex = Database.PLAYERS.size();
        for (int playerIndexNum = minPlayerIndex; playerIndexNum<=maxPlayerIndex; playerIndexNum++){
            System.out.println(OutputColorer.cyan(playerIndexNum+") "+Database.PLAYERS.get(playerIndexNum-1)));
        }
        final int selectedOption = UserInteractor.getIntegerInput("Select the desired player by its number.", minPlayerIndex, maxPlayerIndex);

        return Database.PLAYERS.get(selectedOption - 1);
    }

    public static Team displayTeamCreationPage(){
        System.out.println(OutputColorer.purple("You currently are adding a new team"));
        final String teamName = UserInteractor.getStringInput("Enter the new team's name",1, 10);
        System.out.println(OutputColorer.purple("Now you need to input team ")+OutputColorer.blue(teamName)+OutputColorer.purple("'s symbol."));
        final char teamSymbol = UserInteractor.getUniqueSymbolInput("Enter team "+teamName+ "'s symbol.");
        final Team newTeam = new Team(teamName, teamSymbol);
        Database.TEAMS.add(newTeam);
        return newTeam;
    }
}
