package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// showing the list of game and providing an add button.
public class GameListActivity extends AppCompatActivity {

    private ListView lv_game_list;
    private FloatingActionButton fab_game_list;
    private TextView tv_noGameHint;
    private ImageView btn_back;
    private int indexOfConfigInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
    }

    // Restart the GameList Activity
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();

        extractDataFromIntent();
        initial();
        showHint();
        populateListView();
        onClickButton();
    }

    private void showHint() {
        ConfigManager instanceOfConfigM = ConfigManager.getInstance();
        Config configByIndex = instanceOfConfigM.getConfigByIndex(indexOfConfigInList);
        String configName = configByIndex.getName();
        GameManager instanceOfGameM = GameManager.getInstance();
        int sizeOfGameList = instanceOfGameM.getSizeOfGameListByName(configName);
        if(sizeOfGameList > 0)
        {
            tv_noGameHint.setVisibility(View.INVISIBLE);
        }
        else
        {
            tv_noGameHint.setVisibility(View.VISIBLE);
        }
    }

    private void onClickButton() {
        btn_back.setOnClickListener(v->onBackClick());
        fab_game_list.setOnClickListener(v -> {
            Intent intent = GameActivity.makeIntent(GameListActivity.this, indexOfConfigInList);
            startActivity(intent);
        });
    }

    private void onBackClick() {
        finish();
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfConfigInList = intent.getIntExtra("indexOfConfigInList",-1);
    }

    private void populateListView() {
        // show num players, combined score, and the achievement we earned.
        ConfigManager instanceOfConfigM = ConfigManager.getInstance();
        Config configByIndex = instanceOfConfigM.getConfigByIndex(indexOfConfigInList);
        String configName = configByIndex.getName();
        GameManager instanceOfGameM = GameManager.getInstance();
        ArrayList<String> displayedStringList = instanceOfGameM.getDisplayedStringListByName(configName);
        if(displayedStringList == null) {
            return;
        }
        // Build Adapter
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item,
                displayedStringList);
        // configure the list
        lv_game_list.setAdapter(stringArrayAdapter);
    }

    private void initial() {
        lv_game_list = findViewById(R.id.lv_game_list);
        fab_game_list = findViewById(R.id.fab_game_list);
        tv_noGameHint = findViewById(R.id.tv_noGameHint);
        btn_back = findViewById(R.id.game_list_back_button);
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, GameListActivity.class);
        intent.putExtra("indexOfConfigInList",indexOfConfigInList);
        return intent;
    }
}