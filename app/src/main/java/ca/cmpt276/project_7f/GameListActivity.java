package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Game;
import ca.cmpt276.project_7f.model.GameManager;

// showing the list of game and providing an add button.
public class GameListActivity extends AppCompatActivity {

    private ListView lv_game_list;
    private FloatingActionButton fab_game_list;

    private int indexOfConfigInList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        extractDataFromIntent();
        initial();
        toolbar();
        populateListView();
        onClickButton();



    }

    private void onClickButton() {
        fab_game_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GameActivity.makeIntent(GameListActivity.this);
                startActivity(intent);
            }
        });
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfConfigInList = intent.getIntExtra("indexOfConfigInList",-1);
    }

    private void populateListView() {
        // show num players, combined score, and the achievement we earned.
        // create list of items.
        ArrayList<String> stringList = new ArrayList<>();
        GameManager instance = GameManager.getInstance();
        ArrayList<Game> gameList = instance.getGameList();
        for(int i = 0; i < gameList.size(); i++) {
            Game game = gameList.get(i);
            String stringOfDisplayGame = game.getStringOfDisplayGame();
            stringList.add(stringOfDisplayGame);
        }

        // Build Adapter
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item,
                stringList);

        // configure the list
        lv_game_list.setAdapter(stringArrayAdapter);
    }

    private void initial() {
        lv_game_list = findViewById(R.id.lv_game_list);
        fab_game_list = findViewById(R.id.fab_game_list);
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Game List");
    }

    public static Intent makeIntent(Context context, int indexOfConfigInList)
    {
        Intent intent = new Intent(context, GameListActivity.class);
        intent.putExtra("indexOfConfigInList",indexOfConfigInList);
        return intent;
    }
}