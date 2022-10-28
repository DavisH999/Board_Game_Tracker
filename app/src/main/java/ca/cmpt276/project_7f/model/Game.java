package ca.cmpt276.project_7f.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Game {
    private String configName;
    private int numOfPlayers;
    private int score;
    private String achievement;
    private String time;

    public Game(String _configName, int _numOfPlayer, int _score) {
        configName = _configName;
        numOfPlayers = _numOfPlayer;
        score = _score;
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd @ HH:mm a"));
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    String getTime()
    {
        return time;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getScore() {
        return score;
    }

    public String getAchievement() {
        computeAchievement();
        return achievement;
    }

    public void computeAchievement()
    {
        ConfigManager configManager = ConfigManager.getInstance();
        Config configByName = configManager.getConfigByName(configName);
        if(configByName == null)
        {
            throw new IllegalArgumentException("No such name found.");
        }
        else {
            int greatScore = configByName.getGreatScore();
            int poorScore = configByName.getPoorScore();
            int highestExpectedLevel = greatScore * numOfPlayers;
            int lowestExpectedLevel = poorScore * numOfPlayers;
            int unit = (highestExpectedLevel - lowestExpectedLevel) / 8;
            if (score >= highestExpectedLevel)
                achievement = "1";
            else if (score >= lowestExpectedLevel + unit * 7 && score < lowestExpectedLevel + unit * 8)
                achievement = "2";
            else if (score >= lowestExpectedLevel + unit * 6 && score < lowestExpectedLevel + unit * 7)
                achievement = "3";
            else if (score >= lowestExpectedLevel + unit * 5 && score < lowestExpectedLevel + unit * 6)
                achievement = "4";
            else if (score >= lowestExpectedLevel + unit * 4 && score < lowestExpectedLevel + unit * 5)
                achievement = "5";
            else if (score >= lowestExpectedLevel + unit * 3 && score < lowestExpectedLevel + unit * 4)
                achievement = "6";
            else if (score >= lowestExpectedLevel + unit * 2 && score < lowestExpectedLevel + unit * 3)
                achievement = "7";
            else if (score >= lowestExpectedLevel + unit && score < lowestExpectedLevel + unit * 2)
                achievement = "8";
            else if (score >= lowestExpectedLevel && score < lowestExpectedLevel + unit)
                achievement = "9";
            else if (score < lowestExpectedLevel)
                achievement = "10";
        }
    }
}
