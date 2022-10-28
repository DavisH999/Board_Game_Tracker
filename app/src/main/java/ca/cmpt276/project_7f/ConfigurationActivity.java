package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfigurationActivity extends AppCompatActivity {

    private boolean isAddMode;
    int indexOfConfigInList;
    Button btn_goToGameList;
    Button btn_goToAchievements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initial();
        extractDataFromIntent();
        setButtonInvisible();
        toolbar();
    }

    private void setButtonInvisible() {
        if(isAddMode)
        {
            btn_goToGameList.setVisibility(View.INVISIBLE);
            btn_goToAchievements.setVisibility(View.INVISIBLE);
        }
    }

    private void initial() {
        btn_goToGameList = findViewById(R.id.btn_goToGameList);
        btn_goToAchievements = findViewById(R.id.btn_goToAchievements);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfConfigInList = intent.getIntExtra("indexOfConfigInList", -1);
        if(indexOfConfigInList == -1)
        {
            isAddMode = true;
        }
        else
        {
            isAddMode = false;
        }
    }

    public static Intent makeIntent(Context context, int index)
    {
        Intent intent = new Intent(context, ConfigurationActivity.class);
        intent.putExtra("indexOfConfigInList", index);
        return intent;
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        if(isAddMode == true)
            supportActionBar.setTitle("Add a Configuration");
        else
            supportActionBar.setTitle("Edit a configuration");
    }


}