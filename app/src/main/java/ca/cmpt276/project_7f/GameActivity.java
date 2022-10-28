package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    int indexOfGameInList;
    boolean isAddMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initial();
        extractDataFromIntent();
        toolbar();


    }

    private void initial() {

    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfGameInList = intent.getIntExtra("indexOfGameInList", -1);
        if(indexOfGameInList == -1)
        {
            isAddMode = true;
        }
        else
        {
            isAddMode = false;
        }
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if(isAddMode)
        {
            supportActionBar.setTitle("Add Configuration");
        }
        else
        {
            supportActionBar.setTitle("Edit Configuration");
        }

    }

    public static Intent makeIntent(Context context, int index)
    {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("indexOfGameInList", index);
        return intent;
    }
}