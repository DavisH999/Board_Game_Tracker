package ca.cmpt276.project_7f.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// game
public class Game {
    private String configName;
    private int numOfPlayers;
    private int score;
    private String achievement;
    private String time;
    private String difficulty;

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDifficult(String difficulty) {
        this.difficulty = difficulty;
    }


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

    public ArrayList<String> getStringOfRanges() {
        ConfigManager configManager = ConfigManager.getInstance();
        Config configByName = configManager.getConfigByName(configName);

        // Create an array of strings which stores the ranges
        ArrayList<String> ranges = new ArrayList<>();

        int greatScore = configByName.getGreatScore();
        int poorScore = configByName.getPoorScore();
        int highestExpectedLevel = greatScore * numOfPlayers;
        int lowestExpectedLevel = poorScore * numOfPlayers;
        int unit = (highestExpectedLevel - lowestExpectedLevel) / 8;

        ranges.add(highestExpectedLevel + " and above");

        for (int i = 8; i > 0; --i) {
            int lowerLimit = lowestExpectedLevel + unit * (i - 1);
            int higherLevel = lowestExpectedLevel + unit * i;
            String range = lowerLimit + " - " + higherLevel;
            ranges.add(range);
        }

        ranges.add(lowestExpectedLevel + " and below");

        return ranges;
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
                achievement = "Victorious Whales";
            else if (score >= lowestExpectedLevel + unit * 7 && score < lowestExpectedLevel + unit * 8)
                achievement = "Glorious Giraffes";
            else if (score >= lowestExpectedLevel + unit * 6 && score < lowestExpectedLevel + unit * 7)
                achievement = "Brave Bears";
            else if (score >= lowestExpectedLevel + unit * 5 && score < lowestExpectedLevel + unit * 6)
                achievement = "Pretty Penguins";
            else if (score >= lowestExpectedLevel + unit * 4 && score < lowestExpectedLevel + unit * 5)
                achievement = "Reckless Racoons";
            else if (score >= lowestExpectedLevel + unit * 3 && score < lowestExpectedLevel + unit * 4)
                achievement = "Crazy Crows";
            else if (score >= lowestExpectedLevel + unit * 2 && score < lowestExpectedLevel + unit * 3)
                achievement = "Rowdy Rats";
            else if (score >= lowestExpectedLevel + unit && score < lowestExpectedLevel + unit * 2)
                achievement = "Fat Flies";
            else if (score >= lowestExpectedLevel && score < lowestExpectedLevel + unit)
                achievement = "Lazy Lizard";
            else if (score < lowestExpectedLevel)
                achievement = "Slow Snakes";
        }
    }

    public String getStringOfDisplayGame()
    {
        return "Time created " + time + "\n"
                + "Combined score: " + score + "\n"
                + "Achievement: " + getAchievement();
    }
}
