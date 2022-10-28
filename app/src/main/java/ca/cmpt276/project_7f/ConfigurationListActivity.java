package ca.cmpt276.project_7f;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConfigurationListActivity extends AppCompatActivity {

    FloatingActionButton fab_config_list;

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
        onClick();
    }

    private void onClick() {
        fab_config_list.setOnClickListener(v -> onClickFab());
    }

    private void onClickFab() {
        Intent intent = ConfigurationActivity.makeIntent(this, -1);
        startActivity(intent);
    }



    private void initial() {
        fab_config_list = findViewById(R.id.fab_config_list);
    }

    private void toolbar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("Configuration List");
    }
}