package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

// adding a new game.
public class GameActivity extends AppCompatActivity {

    EditText et_numPlayer;
    EditText et_score;
    EditText et_achievementForOneGame;
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
        // TODO: save a new game.

    }

    private void initial() {
        et_numPlayer = findViewById(R.id.et_numPlayer);
        et_score = findViewById(R.id.et_score);
        et_achievementForOneGame = findViewById(R.id.et_achievementForOneGame);
        btn_saveGame = findViewById(R.id.btn_saveGame);
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Add Game");
    }

    public static Intent makeIntent(Context context)
    {
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }
}