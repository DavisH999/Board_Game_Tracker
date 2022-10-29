package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;

//Activity that displays all the Achivement options from 1-10
public class AchievementsActivity extends AppCompatActivity {


    int indexOfConfigInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);
    }

    @Override
    protected void onResume() {
        super.onResume();

        extractDataFromIntent();
        initial();
        toolbar();
        showData();

    }

    private void showData() {
        // TODO: create game object from gettters and populate with that
        ConfigManager instance = ConfigManager.getInstance();
        Config configByIndex = instance.getConfigByIndex(indexOfConfigInList);
        String name = configByIndex.getName();
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        intent.getIntExtra("indexOfConfigInList",indexOfConfigInList);
    }

    private void initial() {

    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Achievements");
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, AchievementsActivity.class);
        intent.putExtra("indexOfConfigInList", indexOfConfigInList);
        return intent;
    }
}