package ca.cmpt276.project_7f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;
import ca.cmpt276.project_7f.utils.SharedPreferencesUtils;

// showing the configuration.
public class ConfigurationActivity extends AppCompatActivity {

    private ConfigManager instanceOfCM;
    private boolean isAddMode;
    private int indexOfConfigInList;
    private Button btn_goToGameList;
    private Button btn_goToAchievements;
    private ImageView btn_save;
    private ImageView btn_back;
    private Button btn_delete;
    private EditText et_configName;
    private EditText et_configGreatScore;
    private EditText et_configPoorScore;
    private Button btn_goToBarChart;

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
        toolbar();
        setButtonInvisible();
        fillData();
        onClickButtons();
    }

    private void fillData() {
        if(!isAddMode) {
            Config configByIndex = instanceOfCM.getConfigByIndex(indexOfConfigInList);
            String name = configByIndex.getName();
            int greatScore = configByIndex.getGreatScore();
            int poorScore = configByIndex.getPoorScore();
            et_configName.setText(name);
            et_configGreatScore.setText(String.valueOf(greatScore));
            et_configPoorScore.setText(String.valueOf(poorScore));
        }
    }

    private void onClickButtons() {
        // click save
        btn_save.setOnClickListener(v->onSaveCLick());
        // click delete
        btn_delete.setOnClickListener(v->onDeleteClick());
        // click go_to_game_list
        btn_goToGameList.setOnClickListener(v->onGameListClick());
        // click go_to_achievements
        btn_goToAchievements.setOnClickListener(v->onAchievements());
        // click to go back
        btn_back.setOnClickListener(v->onBackClick());
        // click to go chart
        btn_goToBarChart.setOnClickListener(v-> onChartClick());
    }

    private void onChartClick() {
        Intent intent = AchievementStatisticsActivity.makeIntent(getApplicationContext(), indexOfConfigInList);
        startActivity(intent);
    }

    private void onAchievements() {
        Intent intent = AchievementsActivity.makeIntent(this, indexOfConfigInList);
        startActivity(intent);
    }

    private void onGameListClick() {
        Intent intent = GameListActivity.makeIntent(this, indexOfConfigInList);
        startActivity(intent);
    }

    private void onDeleteClick() {
        instanceOfCM.removeConfig(indexOfConfigInList);
        saveData();
        finish();
    }

    private void onBackClick() {
        finish();
    }

    private void saveData() {
        SharedPreferencesUtils.saveDataOfConfigManager(getApplicationContext());
        SharedPreferencesUtils.saveDataOfGameManager(getApplicationContext());
    }

    private void onSaveCLick() {
        if(et_configName.length() == 0 || et_configGreatScore.length() == 0 || et_configPoorScore.length() == 0) {
            Toast.makeText(this,"All fields must not be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        String configName = et_configName.getText().toString();
        String configGreatScore = et_configGreatScore.getText().toString();
        String configPoorScore = et_configPoorScore.getText().toString();
        int greatScore = stringToInt(configGreatScore);
        int poorScore = stringToInt(configPoorScore);

        if(greatScore <= poorScore) {
            Toast.makeText(this,"Great score must be bigger than poor Score.",Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Config config = new Config();
        config.setName(configName);
        config.setGreatScore(greatScore);
        config.setPoorScore(poorScore);

        if(indexOfConfigInList == -1) {
            if(instanceOfCM.isNameExisted(configName)) {
                Toast.makeText(this,
                        "The name of " + configName + " already exists. please use another name.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            // add mode
            instanceOfCM.addConfig(config);
        }
        else {
            // edit mode
            Config configByIndex = instanceOfCM.getConfigByIndex(indexOfConfigInList);
            String oldName = configByIndex.getName();
            // if name remains same then directly edit it
            if(configName.equals(oldName)) {
                instanceOfCM.editConfig(indexOfConfigInList,config);
            }
            // if name has been changed then check if new name existed.
            else {
                // if name existed then reject.
                if(instanceOfCM.isNameExisted(configName)) {
                    Toast.makeText(this,
                            "The name of " + configName + " has been existed. please use another name.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                // if name not existed, then edit it.
                else {
                    Log.e("TAG2",config.toString());
                    Log.e("TAG2",indexOfConfigInList+"");
                    instanceOfCM.editConfig(indexOfConfigInList,config);
                }
            }
        }
        saveData();
        finish();
    }

    private int stringToInt(String str)
    {
        return Integer.parseInt(str);
    }

     private void setButtonInvisible() {
        if(isAddMode) {
            btn_goToGameList.setVisibility(View.INVISIBLE);
            btn_goToAchievements.setVisibility(View.INVISIBLE);
            btn_delete.setVisibility(View.INVISIBLE);
        }
    }

    private void initial() {
        btn_goToGameList = findViewById(R.id.btn_goToGameList);
        btn_goToAchievements = findViewById(R.id.btn_goToAchievements);
        btn_save = findViewById(R.id.config_save_button_config);
        btn_back = findViewById(R.id.config_back_button);
        btn_delete = findViewById(R.id.btn_delete);
        et_configName = findViewById(R.id.et_configName);
        et_configGreatScore = findViewById(R.id.et_configGreatScore);
        et_configPoorScore = findViewById(R.id.et_configPoorScore);
        instanceOfCM = ConfigManager.getInstance();
        btn_goToBarChart = findViewById(R.id.btn_goToBarChart);
    }

    private void extractDataFromIntent() {
        Intent intent = getIntent();
        indexOfConfigInList = intent.getIntExtra("indexOfConfigInList", -1);
        if(indexOfConfigInList == -1) {
            isAddMode = true;
        }
        else {
            isAddMode = false;
        }
    }

    public static Intent makeIntent(Context context, int index) {
        Intent intent = new Intent(context, ConfigurationActivity.class);
        intent.putExtra("indexOfConfigInList", index);
        return intent;
    }

    private void toolbar() {
        TextView tv_title = findViewById(R.id.tv_config_toolbar_title);
        if(isAddMode){
            tv_title.setText("Add a configuration");
        }
        else {
            tv_title.setText("Edit a configuration");
        }
    }
}