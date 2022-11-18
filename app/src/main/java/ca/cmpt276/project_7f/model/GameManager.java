package ca.cmpt276.project_7f.model;

import java.util.ArrayList;
import java.util.Objects;

// managing the list of game.
public class GameManager {
    private ArrayList<Game> gameList;
    private static GameManager instance;

    private GameManager()
    {
        gameList = new ArrayList<>();
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public static GameManager getInstance()
    {
        // use instance because we only have one game list.
        if(instance == null)
        {
            instance = new GameManager();
        }
        return instance;
    }

    public int getSizeOfGameListByName(String configName)
    {
        int size = 0;
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            String configNameFromGame = game.getConfigName();
            if(configName.equals(configNameFromGame))
            {
                size++;
            }
        }
        return size;
    }

    public ArrayList<String> getDisplayedStringListByName(String configName)
    {
        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            String configNameFromGame = game.getConfigName();
            if(configName.equals(configNameFromGame))
            {
                String stringOfDisplayGame = game.getStringOfDisplayGame();
                stringList.add(stringOfDisplayGame);
            }
        }
        if(stringList.size()==0)
            return null;
        else
            return stringList;
    }

    public void addGame(String configName, int numOfPlayer, ArrayList<Integer> scoreList, String difficulty, String theme)
    {
        Game game = new Game(configName,numOfPlayer,scoreList,difficulty,theme);
        gameList.add(game);
    }

    public void removeGames(String configName)
    {
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            if(configName.equals(game.getConfigName()))
            {
                gameList.remove(i);
            }
        }
    }

    public void updateGamesWhenConfigChanges(String oldConfigName, Config newConfig)
    {
        String updatedConfigName = newConfig.getName();

        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            String difficulty = game.getDifficulty();
            if(difficulty == null)
            {
                difficulty = "Normal";
            }
            if(oldConfigName.equals(game.getConfigName()))
            {
                game.setConfigName(updatedConfigName);
                switch (difficulty) {
                    case "Normal":
                        game.computeAchievement(1);
                        break;
                    case "Easy":
                        game.computeAchievement(0.75);
                        break;
                    case "Hard":
                        game.computeAchievement(1.25);
                        break;
                }
            }
        }
    }

    public void updateOneGameWhenGameChanges(
            String configName, int indexInGameList, String difficulty, ArrayList<Integer> scoreList,
            int numberOfPlayers, String strTheme)
    {
        Game targetGame = getGame(configName,indexInGameList);
        targetGame.setDifficulty(difficulty);
        targetGame.setScoreList(scoreList);
        targetGame.setNumOfPlayers(numberOfPlayers);
        targetGame.setTheme(strTheme);

        // Determine the level of difficulty
        if (Objects.equals(difficulty, "Normal")) {
            targetGame.computeAchievement(1);
        }
        else if (Objects.equals(difficulty, "Hard")) {
            targetGame.computeAchievement(1.25);
        }
        else if (Objects.equals(difficulty, "Easy")) {
            targetGame.computeAchievement(0.75);
        }
    }

    public Game getGame(String configName, int indexInGameList)
    {
        ArrayList<Game> tempGameList = new ArrayList<>();
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            if(configName.equals(game.getConfigName()))
            {
                tempGameList.add(game);
            }
        }
        return tempGameList.get(indexInGameList);
    }



}
