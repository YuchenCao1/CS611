import java.util.Queue;
import java.util.LinkedList;

public abstract class BoardGame<T extends Board> implements Game {
    protected Queue<Team> teams;
    protected Queue<Player> players;
    protected T board;
    protected boolean teamsRequired;
    protected boolean teamsAllowed;
    protected int minNumPlayers;
    protected int maxNumPlayers;

    /**
    Method checks if the given proposed board coordinates
    yield a valid move.
     */
    protected abstract boolean isLegalMove(final int rowNum, final int colNum);

    /**
     * Method configures board to starting state of the board
     * game.
     */
    protected abstract void setupBoard();

    /**
     * Method assigns the minimum number of players
     * based on the BoardGame type.
     */
    protected abstract void setupMinNumPlayers();

    /**
     * Method assigns the maximum number of players
     * based on the BoardGame type.
     */
    protected abstract void setupMaxNumPlayers();

    /**
     * Method makes the team/player take their turn.
     */
    protected abstract void doTurn();

    /**
     * Method to determine whose turn is next.
     */
    protected abstract void assignNextCurrentPlayer();

    /**
     * Method that gets the current player whose
     * turn it currently is.
     */
    protected abstract Player getCurrentPlayer();

    protected abstract Team getCurrentTeam();

    protected void setupPlayers(final int minNumPlayers, final int maxNumPlayers){
        Queue<Player> players = new LinkedList<>();
        // Prompt for number of players IF there isn't a
        final int numPlayers;
        if (minNumPlayers != maxNumPlayers){
            numPlayers = UserInteractor.getIntegerInput("Enter the number of players", minNumPlayers, maxNumPlayers);
        }
        else {
            numPlayers = maxNumPlayers;
            System.out.println("This game requires "+numPlayers+" players.");
        }

        boolean isDoneWithSelectingPlayers = false;
        final boolean playersHaveBeenMadeBefore = !Database.PLAYERS.isEmpty();

        while (players.size() < numPlayers || players.size() < minNumPlayers) {
            // First prompt user to reuse players in database
            if (playersHaveBeenMadeBefore && !isDoneWithSelectingPlayers) {
                String wantToAddUsedPlayers = UserInteractor.getYesNoInput("You have created some players already. Would you like to add them?");
                if (wantToAddUsedPlayers.equals("y")){
                    // Display player selection
                    // Second condition: if all players are selected from the Database, nothing else can get added
                    while (!isDoneWithSelectingPlayers && players.size() < Database.PLAYERS.size()){
                        Player selectedPlayer = Menu.displayPlayerSelectionPage();
                        while (players.contains(selectedPlayer)){
                            System.out.println("Invalid selection: player "+selectedPlayer+" has already been selected.");
                            selectedPlayer = Menu.displayPlayerSelectionPage();
                        }
                        players.add(selectedPlayer);
                        if (players.size() < numPlayers && players.size() < Database.PLAYERS.size()) {
                            String doneWithSelectionResponse = UserInteractor.getYesNoInput(OutputColorer.purple("Are you done selecting players?"));
                            if (doneWithSelectionResponse.equals("y")){
                                isDoneWithSelectingPlayers = true;
                            }
                        }
                        else {
                            isDoneWithSelectingPlayers = true;
                        }
                    }

                    System.out.println("Selected players have been added.");
                }
                else{
                    isDoneWithSelectingPlayers = true;
                }
            }
            else {
                // Then we will have user input new players IF selecting did not reach threshold
                Player createdPlayer = Menu.displayPlayerCreationPage();
                players.add(createdPlayer);
            }
        }

        System.out.println("All "+numPlayers+" player(s) have been added.");
        this.players = players;
    }

    //TODO: overload possibly, add number of players per team option!
    protected void setupTeams(final int minNumTeams, final int maxNumTeams, final Integer teamSizeLimit, final Queue<Player> createdPlayers){
        Queue<Team> teams = new LinkedList<>();
        Queue<Player> assignedPlayers = new LinkedList<>();
        /*
        First, we must ask if they want teams to be made in the first place. If not,
        then there's no need to create teams unless required.
        */
        if (!teamsRequired) {
            final String doesUserWantToMakeTeams = UserInteractor.getYesNoInput("This board game is team optional. Would you like to add teams?");
            if (doesUserWantToMakeTeams.equals("n")){
                return;
            }
        }

        // Only get a custom number of teams if the min and max number aren't equal to each other
        final int numTeams;
        if (minNumTeams != maxNumTeams) {
            numTeams = UserInteractor.getIntegerInput("Enter the number of teams", minNumTeams, maxNumTeams);
        }
        else{
            numTeams = maxNumTeams;
            System.out.println("There are "+numTeams+" to be made.");
        }
        // Automatically making teams if the number of players is the number of teams since logically it's 1 player per team.
        if (numTeams == createdPlayers.size()){
            System.out.println("Since there are "+createdPlayers.size()+" players and "+numTeams+" teams to be made, this can be assigned automatically.");
            for (Player createdPlayer : createdPlayers){
                final Team team = new Team(createdPlayer.getName(), createdPlayer.getSymbol());
                team.addPlayer(createdPlayer);
                teams.add(team);
                System.out.println("Team "+team+" has been added automatically...");
            }
            this.teams = teams;
            return;
        }

        while (teams.size() < numTeams || teams.size() < minNumTeams){
            System.out.println("For Team "+(teams.size()+1)+":");
            final Team team = Menu.displayTeamCreationPage();
            teams.add(team);
            /*
            Now basic team info is inputted, we need to prompt for players on the team.
            It is important to note that a team should be limited in size.
            For example, if there were 9 players but 3 teams made, the max team
            size for assigning the first team is 7, as there should be atleast 1
            player for the other 2 teams. However, is there is a set team
            size, then that can be used.
             */

            final int numTeamsLeftToAssign = numTeams - teams.size();
            final int numAvailablePlayers = createdPlayers.size() - assignedPlayers.size();

            final int maxTeamSizeLimit;

            if (teamSizeLimit == null){
                maxTeamSizeLimit = numAvailablePlayers - numTeamsLeftToAssign;
            }
            else {
                maxTeamSizeLimit = teamSizeLimit;
            }

            /*
            There is also a minimum number players for a team size. For example, if
            2 players have been inputted into one team and there are 4 left to place
            but only 1 team left, then all 4 have to go into that team. Since we
            know this, we can do it automatically.
             */
            if (teams.size() == numTeams){
                System.out.println("There are "+numAvailablePlayers+" player(s) to be put into the last team. Therefore, they will be added automatically.");
                for (final Player createdPlayer : players){
                    if (!assignedPlayers.contains(createdPlayer)){
                        System.out.println("Adding "+createdPlayer+" to team "+team.getName()+"...");
                        team.addPlayer(createdPlayer);
                    }
                }
                break;
            }

            // TODO: adjust this so if there is a set number of players for each team then something else is printed
            System.out.println("This team can have at most "+maxTeamSizeLimit+" player(s).");
            System.out.println("Now select a player to be in this team");

            // Now we can input players onto the team
            // TODO: add second condition in case there is a specified team size
            while (team.getPlayers().size() < maxTeamSizeLimit) {
                boolean isPlayerAlreadyUsed;
                //Another case: selected player may not even in the game!
                boolean isPlayerNotInGame;
                Player selectedPlayer;

                do {
                    selectedPlayer = Menu.displayPlayerSelectionPage();

                    isPlayerAlreadyUsed = assignedPlayers.contains(selectedPlayer);
                    if (isPlayerAlreadyUsed) {
                        System.out.println("Player " + OutputColorer.red(String.valueOf(selectedPlayer)) + " has already been added to a team. Choose another one.");
                    }

                    isPlayerNotInGame = !createdPlayers.contains(selectedPlayer);
                    if (isPlayerNotInGame){
                        System.out.println("Player " + OutputColorer.red(String.valueOf(selectedPlayer)) + " was not selected into this game!");
                    }
                } while (isPlayerAlreadyUsed || isPlayerNotInGame);

                team.addPlayer(selectedPlayer);
                assignedPlayers.add(selectedPlayer);

                if (team.getPlayers().size() < maxTeamSizeLimit) {
                    String doneWithSelectionResponse = UserInteractor.getYesNoInput(OutputColorer.purple("You can add another player. Would you like to?"));
                    if (doneWithSelectionResponse.equals("n")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        this.teams = teams;
    }

    public Queue<Player> getPlayers(){
        return players;
    }

    public Queue<Team> getTeams(){
        return teams;
    }
}
