package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;

//Activity that displays all the Achievement options from 1-10
public class AchievementsActivity extends AppCompatActivity {


    int indexOfConfigInList;
    ImageView btn_back;
    EditText difficulty_level_tv;
    EditText numberOfPlayer_tv;
    TextView tv0;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;

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
        textWatcher();
        onClickButtons();
    }

    private void textWatcher() {
        numberOfPlayer_tv.addTextChangedListener(textWatcher);
        difficulty_level_tv.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            tv0.setText("-------");
            tv1.setText("-------");
            tv2.setText("-------");
            tv3.setText("-------");
            tv4.setText("-------");
            tv5.setText("-------");
            tv6.setText("-------");
            tv7.setText("-------");
            tv8.setText("-------");
            tv9.setText("-------");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (numberOfPlayer_tv.length() != 0 && difficulty_level_tv.length() != 0) {
                showData();
            }
        }
    };

    private void showData() {
        String difficulty = difficulty_level_tv.getText().toString();
        if (Objects.equals(difficulty, "Normal") || Objects.equals(difficulty, "Hard")
        || Objects.equals(difficulty, "Easy")) {
            ArrayList<String> rangesArray = getTheRangesBasedOnTheDifficultyLevel(difficulty);
            tv0.setText(rangesArray.get(0));
            tv1.setText(rangesArray.get(1));
            tv2.setText(rangesArray.get(2));
            tv3.setText(rangesArray.get(3));
            tv4.setText(rangesArray.get(4));
            tv5.setText(rangesArray.get(5));
            tv6.setText(rangesArray.get(6));
            tv7.setText(rangesArray.get(7));
            tv8.setText(rangesArray.get(8));
            tv9.setText(rangesArray.get(9));
        }
        else {
            Toast.makeText(this, "Difficulty level is invalid", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private ArrayList<String> getTheRangesBasedOnTheDifficultyLevel(String Difficulty) {
        ConfigManager instance = ConfigManager.getInstance();
        Config configByIndex = instance.getConfigByIndex(indexOfConfigInList);
        String name = configByIndex.getName();
        String value = numberOfPlayer_tv.getText().toString();
        int numOfPlayers = Integer.parseInt(value);

        ArrayList<String> rangesArray = new ArrayList<>();
        Game game = new Game(name, numOfPlayers, 100);


        switch(Difficulty) {
            case "Normal":
                rangesArray = game.getStringOfRanges(1);
                break;

            case "Hard":
                rangesArray = game.getStringOfRanges(1.25);
                break;

            case "Easy":
                rangesArray = game.getStringOfRanges(0.75);
                break;
        }

        return rangesArray;
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        intent.getIntExtra("indexOfConfigInList",indexOfConfigInList);
    }

    private void initial() {
        difficulty_level_tv = findViewById(R.id.et_difficultyInAchievements);
        numberOfPlayer_tv = findViewById(R.id.et_numPlayer2);
        btn_back = findViewById(R.id.achievements_back_button);
        tv0 = findViewById(R.id.tv_range0);
        tv1 = findViewById(R.id.tv_range1);
        tv2 = findViewById(R.id.tv_range2);
        tv3 = findViewById(R.id.tv_range3);
        tv4 = findViewById(R.id.tv_range4);
        tv5 = findViewById(R.id.tv_range5);
        tv6 = findViewById(R.id.tv_range6);
        tv7 = findViewById(R.id.tv_range7);
        tv8 = findViewById(R.id.tv_range8);
        tv9 = findViewById(R.id.tv_range9);
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, AchievementsActivity.class);
        intent.putExtra("indexOfConfigInList", indexOfConfigInList);
        return intent;
    }

    private void onClickButtons() {
        btn_back.setOnClickListener(v->onBackClick());
    }

    private void onBackClick() {
        finish();
    }
}