package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// adding a new game.
public class GameActivity extends AppCompatActivity {

    private static final String INDEX_OF_GAME_IN_LIST = "indexOfGameInList";
    private static final String CONFIG_NAME = "configName";
    private EditText et_numPlayer;
    private TextView tv_game_toolbar_title;
    private LinearLayout linearlayoutForScores;
    private Button btn_generateEditTexts;
    private ImageView btn_saveGame;
    private ImageView btn_back;
    private int indexOfGameInList;
    private String configName;
    private boolean isAddMode;
    private Spinner spinner_difficultyInGame;

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
        fillSpinner();
        setMode();
    }

    private void fillSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficulty_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_difficultyInGame.setAdapter(adapter);
    }

    private void generateDynamicEditTexts() {
        if(et_numPlayer.length() == 0)
        {
            Toast.makeText(this,"number player must not be empty.",Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        String s = et_numPlayer.getText().toString();
        int numPlayer = Integer.parseInt(s);
        if(numPlayer == 0)
        {
            Toast.makeText(this,"number player must not be 0.",Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        linearlayoutForScores.removeAllViews();
        addEditViews(numPlayer,null);
    }

    private void addEditViews(int numOfPlayers, ArrayList<Integer> scoreList) {
        for(int i = 0; i < numOfPlayers; i++)
        {
            EditText editText = new EditText(this);
            if(scoreList != null)
            {
                int score = scoreList.get(i);
                editText.setText(String.valueOf(score));
            }
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            linearlayoutForScores.addView(editText);
        }
    }

    private void initial() {
        et_numPlayer = findViewById(R.id.et_numPlayersInGame);
        btn_saveGame = findViewById(R.id.game_save_button_game);
        btn_back = findViewById(R.id.game_back_button);
        tv_game_toolbar_title = findViewById(R.id.tv_game_toolbar_title);
        btn_generateEditTexts = findViewById(R.id.btn_generateEditTexts);
        linearlayoutForScores = findViewById(R.id.linearlayoutForScores);
        spinner_difficultyInGame = findViewById(R.id.spinner_difficultyInGame);
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

    private void showData() {
        GameManager instanceOfGameM = GameManager.getInstance();
        Game game = instanceOfGameM.getGame(configName, indexOfGameInList);
        int numOfPlayers = game.getNumOfPlayers();
        String difficulty = game.getDifficulty();
        ArrayList<Integer> scoreList = game.getScoreList();
        et_numPlayer.setText(String.valueOf(numOfPlayers));
        // set spinner selection
        for (int i = 0; i < spinner_difficultyInGame.getCount(); i++)
        {
            String str = spinner_difficultyInGame.getItemAtPosition(i).toString();
            if(difficulty.equals(str))
            {
                spinner_difficultyInGame.setSelection(i);
                break;
            }
        }
        //show score list
        addEditViews(numOfPlayers,scoreList);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfGameInList = intent.getIntExtra(INDEX_OF_GAME_IN_LIST,-1);
        configName = intent.getStringExtra(CONFIG_NAME);
    }

    private void onButtonsClick() {
        btn_saveGame.setOnClickListener(v->onSaveClick());
        btn_back.setOnClickListener(v->onBackClick());
        btn_generateEditTexts.setOnClickListener(v->generateDynamicEditTexts());
    }

    private void onBackClick() {
        finish();
    }

    private void onSaveClick() {
        // Check if null
        if(et_numPlayer.length() == 0)
        {
            Toast.makeText(this,"All values must be not empty.",Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(linearlayoutForScores.getChildCount() == 0)
        {
            Toast.makeText(this,"Input score list must be generated ",Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        processGame();
    }

    private void processGame() {
        // Get input data
        String strNumOfPlayers = et_numPlayer.getText().toString();
        int numOfPlayers = Integer.parseInt(strNumOfPlayers);
        String strDifficultyInGame = spinner_difficultyInGame.getSelectedItem().toString();

        // read score list
        if(linearlayoutForScores.getChildCount() != numOfPlayers)
        {
            Toast.makeText(this,"num scores must equal to num players",Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        ArrayList<Integer> scoreList = new ArrayList<>();
        for(int i = 0; i < linearlayoutForScores.getChildCount(); i++)
        {
            EditText et_score = (EditText) linearlayoutForScores.getChildAt(i);
            if(et_score.length() == 0)
            {
                Toast.makeText(this,"All scores must not be empty.",Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            scoreList.add(Integer.parseInt(et_score.getText().toString()));
        }

        // Do the logical part
        GameManager instanceOfGameM = GameManager.getInstance();
        if(isAddMode)
        {
            instanceOfGameM.addGame(configName,numOfPlayers,scoreList,strDifficultyInGame);
        }
        else
        {
            instanceOfGameM.updateOneGameWhenGameChanges(configName,
                    indexOfGameInList,
                    strDifficultyInGame,
                    scoreList,
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