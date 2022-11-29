package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

public class AchievementStatisticsActivity extends AppCompatActivity {

    private int indexOfConfigInList;
    private ArrayList<String> stringOfRanges = null;
    private String configName;
    private ArrayList<Integer> countOfEachAchievementInCorrespondingGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_statistics);
    }

    @Override
    protected void onResume() {
        super.onResume();

        extractDataFromIntent();
        getStringOfRanges();
        getAchievementCountList();
        generateBarChart();
    }

    private void generateBarChart() {
        // null means no game is target config found, so all count should be 0.
        if(stringOfRanges == null)
        {
            // TODO: all count should be 0.

        }
        else
        {
            // TODO: all count should be from countOfEachAchievementInCorrespondingGameList.

        }
    }

    private void getAchievementCountList() {
        GameManager gameManagerInstance = GameManager.getInstance();
        countOfEachAchievementInCorrespondingGameList =
                gameManagerInstance.getCountOfEachAchievementInCorrespondingGameList(configName);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfConfigInList = intent.getIntExtra("indexOfConfigInList",-1);
    }

    private void getStringOfRanges() {
        ConfigManager configManagerInstance = ConfigManager.getInstance();
        Config configByIndex = configManagerInstance.getConfigByIndex(indexOfConfigInList);
        configName = configByIndex.getName();
        GameManager gameManagerInstance = GameManager.getInstance();
        // find first game in the config.
        Game game = gameManagerInstance.getGame(configName, 0);
        if(game != null) {
            stringOfRanges = game.getStringOfRanges(1);
        }
    }

    public static Intent makeIntent(int indexOfConfigInList)
    {
        Intent intent = new Intent();
        intent.putExtra("indexOfConfigInList",indexOfConfigInList);
        return intent;
    }

}