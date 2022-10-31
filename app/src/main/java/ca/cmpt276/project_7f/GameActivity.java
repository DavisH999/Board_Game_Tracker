package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// adding a new game.
public class GameActivity extends AppCompatActivity {

    EditText et_numPlayer;
    EditText et_score;
    Button btn_saveGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initial();
        toolbar();
        saveData();
    }

    private void saveData() {
        btn_saveGame.setOnClickListener(v->OnSaveClick());
    }

    private void OnSaveClick() {
        addGame();
    }

    private void addGame() {
        // Get input data
        String StrNumOfPlayers = et_numPlayer.getText().toString();
        int numOfPlayers = Integer.parseInt(StrNumOfPlayers);
        String StrScore = et_score.getText().toString();
        int score = Integer.parseInt(StrScore);

        // Get the config name
        int indexOfConfigSelected = getIntent().getIntExtra("indexOfConfigInList", -1);
        ConfigManager configManager = ConfigManager.getInstance();
        Config configSelected = configManager.getConfigByIndex(indexOfConfigSelected);
        String nameOfConfig = configSelected.getName();

        // Get an instance of gameManager
        GameManager gameManager = GameManager.getInstance();
        gameManager.addGame(nameOfConfig, numOfPlayers, score);
        SharedPreferencesUtils.saveDataOfGameManager(getApplicationContext());
        finish();
    }

    private void initial() {
        et_numPlayer = findViewById(R.id.et_numPlayer);
        et_score = findViewById(R.id.et_score);
        btn_saveGame = findViewById(R.id.btn_saveGame);
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Add Game");
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("indexOfConfigInList",indexOfConfigInList);
        return intent;
    }
}