package ca.cmpt276.project_7f.model;

import android.util.Log;

import java.util.ArrayList;

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
        // TODO: BUG
        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            String configNameFromGame = game.getConfigName();
            if(configName.equals(configNameFromGame))
            {
                String stringOfDisplayGame = game.getStringOfDisplayGame();
                Log.e("TAG_SHOWLIST",stringOfDisplayGame);
                // CORRECT BELOW!!!!!
                Log.e("TAG_SHOWLIST2",game.getAchievement());
                stringList.add(stringOfDisplayGame);
            }
        }
        if(stringList.size()==0)
            return null;
        else
            return stringList;
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

//    public void updateGames(String oldConfigName, String newConfigName)
//    {
//        for(int i = 0; i < gameList.size(); i++)
//        {
//            Game game = gameList.get(i);
//            if(oldConfigName.equals(game.getConfigName()))
//            {
//                game.setConfigName(newConfigName);
//                game.computeAchievement();
//            }
//        }
//    }

    public void updateGames(String oldConfigName, Config newConfig)
    {
        String updatedConfigName = newConfig.getName();
        for(int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            if(oldConfigName.equals(game.getConfigName()))
            {
                Log.e("TAG","! "+i);
                game.setConfigName(updatedConfigName);
                game.computeAchievement();
                Log.e("TAG_ACH",game.getAchievement());
            }
        }
    }

//    private Game getGameByIndex(String configName,int index)
//    {
//        ArrayList<Game> tempGameList = new ArrayList<>();
//        for(int i = 0; i < gameList.size(); i++)
//        {
//            Game game = gameList.get(i);
//            if(configName.equals(game.getConfigName()))
//            {
//                tempGameList.add(game);
//            }
//        }
//        return tempGameList.get(index);
//    }




}
