package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.GameManager;

// adding a new game.
public class GameActivity extends AppCompatActivity {

    EditText et_numPlayer;
    EditText et_score;
    EditText et_difficultyInGame;
    ImageView btn_saveGame;
    ImageView btn_back;
    private int indexOfConfigInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initial();
        onButtonsClick();
        extractDataFromIntent();
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfConfigInList = intent.getIntExtra("indexOfConfigInList",-1);
    }

    private void onButtonsClick() {
        btn_saveGame.setOnClickListener(v->onSaveClick());
        btn_back.setOnClickListener(v->onBackClick());
    }

    private void onBackClick() {
        finish();
    }

    private void onSaveClick() {
        addGame();
    }

    private void addGame() {
        // Get input data
        String strNumOfPlayers = et_numPlayer.getText().toString();
        int numOfPlayers = Integer.parseInt(strNumOfPlayers);
        String strScore = et_score.getText().toString();
        int score = Integer.parseInt(strScore);
        // TODO:
        String strDifficultyInGame = et_difficultyInGame.getText().toString();

        // Get the config name
        int indexOfConfigSelected = getIntent().getIntExtra("indexOfConfigInList", -1);
        ConfigManager configManager = ConfigManager.getInstance();
        Config configSelected = configManager.getConfigByIndex(indexOfConfigSelected);
        String nameOfConfig = configSelected.getName();

        // Get an instance of gameManager
        GameManager gameManager = GameManager.getInstance();
        gameManager.addGame(nameOfConfig, numOfPlayers, score, strDifficultyInGame);
        saveData();
        finish();
    }

    private void saveData() {
        SharedPreferencesUtils.saveDataOfGameManager(getApplicationContext());
    }

    private void initial() {
        et_numPlayer = findViewById(R.id.et_difficultyInAchievements);
        et_score = findViewById(R.id.et_difficultyInGame);
        btn_saveGame = findViewById(R.id.game_save_button_game);
        btn_back = findViewById(R.id.game_back_button);
        et_difficultyInGame = findViewById(R.id.et_difficultyInGame);
    }


    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("indexOfConfigInList",indexOfConfigInList);
        return intent;
    }
}