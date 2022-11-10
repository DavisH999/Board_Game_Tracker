package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// adding a new game.
public class GameActivity extends AppCompatActivity {

    private static final String INDEX_OF_GAME_IN_LIST = "indexOfGameInList";
    private static final String CONFIG_NAME = "configName";
    private EditText et_numPlayer;
    private EditText et_score;
    private EditText et_difficultyInGame;
    private TextView tv_game_toolbar_title;
    private ImageView btn_saveGame;
    private ImageView btn_back;
    private int indexOfGameInList;
    private String configName;
    private boolean isAddMode;

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
        setMode();
    }

    private void initial() {
        et_numPlayer = findViewById(R.id.et_difficultyInAchievements);
        et_score = findViewById(R.id.et_scoreInGame);
        btn_saveGame = findViewById(R.id.game_save_button_game);
        btn_back = findViewById(R.id.game_back_button);
        et_difficultyInGame = findViewById(R.id.et_difficultyInGame);
        tv_game_toolbar_title = findViewById(R.id.tv_game_toolbar_title);
    }

    private void setMode() {
        isAddMode = indexOfGameInList == -1;
        if(isAddMode)
        {
            tv_game_toolbar_title.setText("Add Game");
        }
        else
        {
            tv_game_toolbar_title.setText("Edit Game");
            showData();
        }
    }

    private void showData()
    {
        GameManager instanceOfGameM = GameManager.getInstance();
        Game game = instanceOfGameM.getGame(configName, indexOfGameInList);
        int numOfPlayers = game.getNumOfPlayers();
        int score = game.getScore();
        String difficulty = game.getDifficulty();
        // TODO: after using Linearlayout
        et_numPlayer.setText(""+numOfPlayers);
        et_score.setText(""+score);
        et_difficultyInGame.setText(""+difficulty);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfGameInList = intent.getIntExtra(INDEX_OF_GAME_IN_LIST,-1);
        configName = intent.getStringExtra(CONFIG_NAME);
    }

    private void onButtonsClick() {
        btn_saveGame.setOnClickListener(v->onSaveClick());
        btn_back.setOnClickListener(v->onBackClick());
    }

    private void onBackClick() {
        finish();
    }

    private void onSaveClick() {
        // Check if null
        if(et_numPlayer.length() == 0 || et_score.length() == 0 || et_difficultyInGame.length() == 0)
        {
            Toast.makeText(this,"All values must be not empty.",Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        processGame();
    }

    private void processGame() {
        // Get input data
        String strNumOfPlayers = et_numPlayer.getText().toString();
        int numOfPlayers = Integer.parseInt(strNumOfPlayers);
        String strScore = et_score.getText().toString();
        int score = Integer.parseInt(strScore);
        String strDifficultyInGame = et_difficultyInGame.getText().toString();
        // Do the logical part
        GameManager instanceOfGameM = GameManager.getInstance();
        if(isAddMode)
        {
            instanceOfGameM.addGame(configName,numOfPlayers,score,strDifficultyInGame);
        }
        else
        {
            instanceOfGameM.updateOneGameWhenGameChanges(configName,
                    indexOfGameInList,
                    strDifficultyInGame,
                    score,
                    numOfPlayers);
        }
        saveDataToSP();
        finish();
    }

    private void saveDataToSP() {
        SharedPreferencesUtils.saveDataOfGameManager(getApplicationContext());
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList, String configName)
    {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(INDEX_OF_GAME_IN_LIST,indexOfConfigInList);
        intent.putExtra(CONFIG_NAME,configName);
        return intent;
    }
}