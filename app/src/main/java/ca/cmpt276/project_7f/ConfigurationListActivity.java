package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;

public class ConfigurationListActivity extends AppCompatActivity {

    ListView listview_config_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initial();
        populateListView();
        registerClickCallBack();
    }


    private void initial() {
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
                        R.layout.list_of_configs,   // layout to use
                        configsToDisplay);          // Items to be displayed

        // Configure the list view
        listview_config_list.setAdapter(adapter);

    }

    private void registerClickCallBack() {
        listview_config_list.setOnItemClickListener((parent, viewClicked, position, id) -> {
            Intent intent = ConfigurationActivity.makeIntent
                    (ConfigurationListActivity.this, position);
            startActivity(intent);
        });
    }
}