package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConfigurationListActivity extends AppCompatActivity {

    FloatingActionButton fab_config_list;

    ListView listview_config_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        toolbar();
        initial();
        populateListView();
        registerClickCallBack();
        onClick();
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Configuration List");
    }

    private void initial() {
        fab_config_list = findViewById(R.id.fab_config_list);
        listview_config_list = findViewById(R.id.listview_config_list);
    }

    private void populateListView() {
        ConfigManager configManager = ConfigManager.getInstance();
        ArrayList<Config> configList = configManager.getConfigList();

        // Create a list of items
        ArrayList<String> configsToDisplay = new ArrayList<>();
        for (int i = 0; i < configList.size(); ++i) {
            String nameOfConfig = configList.get(i).getName();
            configsToDisplay.add(nameOfConfig);
        }

        // Build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,
                        R.layout.item,   // layout to use
                        configsToDisplay);          // Items to be displayed

        // Configure the list view
        listview_config_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void registerClickCallBack() {
        listview_config_list.setOnItemClickListener((parent, viewClicked, position, id) -> {
            Intent intent = ConfigurationActivity.makeIntent
                    (ConfigurationListActivity.this, position);
            startActivity(intent);
        });
    }

    private void onClick() {
        fab_config_list.setOnClickListener(v -> onClickFab());
    }

    private void onClickFab() {
        Intent intent = ConfigurationActivity.makeIntent(this, -1);
        startActivity(intent);
    }
}