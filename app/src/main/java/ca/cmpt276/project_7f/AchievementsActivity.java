package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;

//Activity that displays all the Achievement options from 1-10
public class AchievementsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    int indexOfConfigInList;
    ImageView btn_back;
    EditText numberOfPlayer_tv;
    Spinner spinner_difficulty_achievement;
    Spinner spinner_theme;
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
        populateDifficultySpinner();
        populateThemeSpinner();
        onClickButtons();
    }

    private void populateDifficultySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.difficulty_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_difficulty_achievement.setAdapter(adapter);
        spinner_difficulty_achievement.setOnItemSelectedListener(this);
    }

    private void populateThemeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.theme_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_theme.setAdapter(adapter);
        spinner_theme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String theme = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"You selected: " + theme,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (numberOfPlayer_tv.length() != 0) {
            showData();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void textWatcher() {
        numberOfPlayer_tv.addTextChangedListener(textWatcher);
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
                showData();
        }
    };

    private void showData() {
        String difficulty = spinner_difficulty_achievement.getSelectedItem().toString();
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

    private ArrayList<String> getTheRangesBasedOnTheDifficultyLevel(String difficulty) {
        ConfigManager instance = ConfigManager.getInstance();
        Config configByIndex = instance.getConfigByIndex(indexOfConfigInList);
        String name = configByIndex.getName();
        String value = numberOfPlayer_tv.getText().toString();
        int numOfPlayers = Integer.parseInt(value);

        ArrayList<String> rangesArray = new ArrayList<>();
        Game game = new Game(name, numOfPlayers, null, difficulty);


        switch(difficulty) {
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
        numberOfPlayer_tv = findViewById(R.id.et_numPlayer_achievement);
        btn_back = findViewById(R.id.achievements_back_button);
        spinner_difficulty_achievement = findViewById(R.id.spinner_difficulty_achievment);
        spinner_theme = findViewById(R.id.spinner_theme_achievement);
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

    private void setTheme(String theme) {
        if (theme == "Disney Characters") {

        }
        else if (theme == "Marvel Heroes") {

        }
        else {

        }
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