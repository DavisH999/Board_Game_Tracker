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
    private ArrayList<Integer> scoreList;

    public ArrayList<Integer> getScoreList() {
        return scoreList;
    }

    public void setScoreList(ArrayList<Integer> scoreList) {
        this.scoreList = scoreList;
        computeTotalScore();
    }

    private void computeTotalScore()
    {
        int sum = 0;
        for (int i = 0; i < scoreList.size(); i++)
        {
            sum = sum + scoreList.get(i);
        }
        score = sum;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Game(String _configName, int _numOfPlayer, ArrayList<Integer> _scoreList, String difficulty) {
        configName = _configName;
        numOfPlayers = _numOfPlayer;
        scoreList = _scoreList;
        this.difficulty = difficulty;
        if(_scoreList != null) {
            computeAchievement(convertStrDifficultyToDouble(difficulty));
            computeTotalScore();
        }
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd @ HH:mm a"));
    }

    private double convertStrDifficultyToDouble(String difficultyStr) {
        double difficulty = 1.0;
        switch(difficultyStr) {
            case "Normal":
                difficulty =  1.0;
                break;

            case "Hard":
                difficulty =  1.25;
                break;

            case "Easy":
                difficulty =  0.75;
                break;
        }
        return difficulty;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getTime()
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
        computeAchievement(convertStrDifficultyToDouble(difficulty));
        return achievement;
    }

    @Override
    public String toString() {
        return "Game{" +
                "configName='" + configName + '\'' +
                ", numOfPlayers=" + numOfPlayers +
                ", score=" + score +
                ", achievement='" + achievement + '\'' +
                ", time='" + time + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    public ArrayList<String> getStringOfRanges(double difficulty) {
        ConfigManager configManager = ConfigManager.getInstance();
        Config configByName = configManager.getConfigByName(configName);

        // Create an array of strings which stores the ranges
        ArrayList<String> ranges = new ArrayList<>();

        int greatScore = configByName.getGreatScore();
        int poorScore = configByName.getPoorScore();
        double highestExpectedLevel = greatScore * numOfPlayers * difficulty;
        double lowestExpectedLevel = poorScore * numOfPlayers * difficulty;
        double unit = (highestExpectedLevel - lowestExpectedLevel) / 8;

        ranges.add(highestExpectedLevel + " and above");

        for (int i = 8; i > 0; --i) {
            double lowerLimit = lowestExpectedLevel + unit * (i - 1);
            double higherLevel = lowestExpectedLevel + unit * i;
            String range = lowerLimit + " - " + higherLevel;
            ranges.add(range);
        }

        ranges.add(lowestExpectedLevel + " and below");

        return ranges;
    }

    // TODO: ADD a new params String theme
    public void computeAchievement(double difficulty)
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
            double highestExpectedLevel = greatScore * numOfPlayers * difficulty;
            double lowestExpectedLevel = poorScore * numOfPlayers * difficulty;
            double unit = (highestExpectedLevel - lowestExpectedLevel) / 8;

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
        return "Time created: " + time + "\n"
                + "Combined score: " + score + "\n"
                + "Achievement: " + getAchievement() + "\n"
                + "Difficulty: " + difficulty;
    }
}
