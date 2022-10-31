package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

// showing the list of configuration and providing an add button.
public class ConfigurationListActivity extends AppCompatActivity {

    FloatingActionButton fab_config_list;
    ListView listview_config_list;
    TextView tv_noConfigHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
        toolbar();
        initial();
        showHint();
        populateListView();
        registerClickCallBack();
        onClick();
    }

    private void loadData()
    {
        SharedPreferencesUtils.loadDataOfConfigManager(getApplicationContext());
    }

    private void showHint() {
        ConfigManager instanceOfConfigM = ConfigManager.getInstance();
        int sizeOfConfigList = instanceOfConfigM.getSizeOfConfigList();
        if(sizeOfConfigList > 0)
        {
            tv_noConfigHint.setVisibility(View.INVISIBLE);
        }
        else
        {
            tv_noConfigHint.setVisibility(View.VISIBLE);
        }
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Configuration List");
    }

    private void initial() {
        fab_config_list = findViewById(R.id.fab_config_list);
        listview_config_list = findViewById(R.id.listview_config_list);
        tv_noConfigHint = findViewById(R.id.tv_noConfigHint);
    }

    private void populateListView() {
        ConfigManager instanceOfConfigM = ConfigManager.getInstance();
        ArrayList<Config> configList = instanceOfConfigM.getConfigList();

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