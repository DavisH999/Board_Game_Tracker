package ca.cmpt276.project_7f.model;

import java.util.ArrayList;
import java.util.Arrays;
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

    public ArrayList<Game> getGameListByConfigName(String _configName)
    {
        ArrayList<Game> targetGameList = new ArrayList<>();
        for (int i = 0; i < gameList.size(); i++)
        {
            Game game = gameList.get(i);
            String configName = game.getConfigName();
            if(configName.equals(_configName))
            {
                targetGameList.add(game);
            }
        }
        return targetGameList;
    }

    // return the list with considering all games as normal different.
    public ArrayList<Integer> getCountOfEachAchievementInCorrespondingGameList(String _configName)
    {
        ArrayList<Integer> resultList = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
        ConfigManager configManagerInstance = ConfigManager.getInstance();
        Config configByName = configManagerInstance.getConfigByName(_configName);
        double greatScore = configByName.getGreatScore();
        double poorScore = configByName.getPoorScore();
        ArrayList<Game> gameListByConfigName = getGameListByConfigName(_configName);

        for(int i = 0; i < gameListByConfigName.size(); i++) {
            Game game = gameListByConfigName.get(i);
            int score = game.getScore();
            double unit = (greatScore - poorScore) / 8;
            if (score >= greatScore)
            {
                Integer integer = resultList.get(9);
                resultList.set(9,++integer);
            }
            else if (score >= poorScore + unit * 7 && score < poorScore + unit * 8)
            {
                Integer integer = resultList.get(8);
                resultList.set(8,++integer);
            }
            else if (score >= poorScore + unit * 6 && score < poorScore + unit * 7)
            {
                Integer integer = resultList.get(7);
                resultList.set(7,++integer);
            }
            else if (score >= poorScore + unit * 5 && score < poorScore + unit * 6)
            {
                Integer integer = resultList.get(6);
                resultList.set(6,++integer);
            }
            else if (score >= poorScore + unit * 4 && score < poorScore + unit * 5)
            {
                Integer integer = resultList.get(5);
                resultList.set(5,++integer);
            }
            else if (score >= poorScore + unit * 3 && score < poorScore + unit * 4)
            {
                Integer integer = resultList.get(4);
                resultList.set(4,++integer);
            }
            else if (score >= poorScore + unit * 2 && score < poorScore + unit * 3)
            {
                Integer integer = resultList.get(3);
                resultList.set(3,++integer);
            }
            else if (score >= poorScore + unit && score < poorScore + unit * 2)
            {
                Integer integer = resultList.get(2);
                resultList.set(2,++integer);
            }
            else if (score >= poorScore && score < poorScore + unit)
            {
                Integer integer = resultList.get(1);
                resultList.set(1,++integer);
            }
            else if (score < poorScore)
            {
                Integer integer = resultList.get(0);
                resultList.set(0,++integer);
            }
        }

        return null;
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

    public void addGame(String configName, int numOfPlayer, ArrayList<Integer> scoreList, String difficulty, String theme, String photoJson)
    {
        Game game = new Game(configName,numOfPlayer,scoreList,difficulty,theme,photoJson);
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
            int numberOfPlayers, String strTheme, String photoJson)
    {
        Game targetGame = getGame(configName,indexInGameList);
        targetGame.setDifficulty(difficulty);
        targetGame.setScoreList(scoreList);
        targetGame.setNumOfPlayers(numberOfPlayers);
        targetGame.setTheme(strTheme);
        targetGame.setImageString(photoJson);

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
        if(tempGameList.size()!=0)
            return tempGameList.get(indexInGameList);
        else
            return null;
    }



}
