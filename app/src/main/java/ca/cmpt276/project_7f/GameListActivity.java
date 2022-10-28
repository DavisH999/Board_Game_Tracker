package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

public class GameListActivity extends AppCompatActivity {

    private String nameOfConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        extractDataFromIntent();
        initial();
        toolbar();
        populateListView();


    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        nameOfConfig = intent.getStringExtra("nameOfConfig");
    }

    private void populateListView() {
        // show num players, combined score, and the achievement we earned.
        // TODO: get info of name of config.
        GameManager instance = GameManager.getInstance();
        ArrayList<Game> gameList = instance.getGameList();


        String[] myItems;
    }

    private void initial() {

    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Configuration List");
    }

    public static Intent makeIntent(Context context, String nameOfConfig)
    {
        Intent intent = new Intent(context, GameListActivity.class);
        intent.putExtra("nameOfConfig",nameOfConfig);
        return intent;
    }
}