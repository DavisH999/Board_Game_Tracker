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

    public void addGame(String configName, int numOfPlayer, int score, String difficulty)
    {
        Game game = new Game(configName,numOfPlayer,score);
        game.setDifficulty(difficulty);

        // Determine the level of difficulty
        if (Objects.equals(difficulty, "Normal")) {
            game.computeAchievement(1);
        }

        else if (Objects.equals(difficulty, "Hard")) {
            game.computeAchievement(1.25);
        }

        else if (Objects.equals(difficulty, "Easy")) {
            game.computeAchievement(0.75);
        }

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
            if(oldConfigName.equals(game.getConfigName()))
            {
                // BUG FOUND AND FIXED!
                // Log.e("TAG","! "+i + " " + game.getConfigName());
                game.setConfigName(updatedConfigName);
                game.computeAchievement(1);
            }
        }
    }

    public void updateOneGameWhenGameChanges(String configName, int indexInGameList, String difficulty, int score, int numberOfPlayers)
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
        Game targetGame = tempGameList.get(indexInGameList);
        targetGame.setDifficulty(difficulty);
        targetGame.setScore(score);
        targetGame.setNumOfPlayers(numberOfPlayers);

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
