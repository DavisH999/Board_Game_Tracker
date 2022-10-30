package ca.cmpt276.project_7f.model;

import java.util.ArrayList;

// managing the list of game.
public class GameManager {
    private ArrayList<Game> gameList;
    private static GameManager instance;

    private GameManager()
    {
        gameList = new ArrayList<>();
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

    public int getSizeOfGameList()
    {
        return gameList.size();
    }

    public ArrayList<Game> getGameList()
    {
        return gameList;
    }

    public void addGame(String configName, int numOfPlayer, int score)
    {
        Game game = new Game(configName,numOfPlayer,score);
        game.computeAchievement();
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

    public void updateGames(String oldConfigName, String newConfigName)
    {
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            if(oldConfigName.equals(game.getConfigName()))
            {
                game.setConfigName(newConfigName);
                game.computeAchievement();
            }
        }
    }

    private Game getGameByIndex(String configName,int index)
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
        return tempGameList.get(index);
    }




}
