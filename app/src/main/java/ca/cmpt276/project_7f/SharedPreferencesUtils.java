package ca.cmpt276.project_7f;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import ca.cmpt276.project_7f.model.Config;
import ca.cmpt276.project_7f.model.ConfigManager;

public class SharedPreferencesUtils {
    public static void saveDataOfConfigManager(Context context)
    {
        ConfigManager instanceOfConfigM = ConfigManager.getInstance();
        SharedPreferences shared_preferences = context.getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared_preferences.edit();
        String jsonString = GsonUtils.getJsonStringFromObject(instanceOfConfigM);
        editor.putString("config_manager",jsonString);
        editor.apply();
    }

    public static void loadDataOfConfigManager(Context context)
    {
        SharedPreferences shared_preferences = context.getSharedPreferences("shared_preferences", MODE_PRIVATE);
        String config_manager = shared_preferences.getString("config_manager", null);
        if(config_manager != null)
        {
            ConfigManager savedCM = GsonUtils.getObjectFromJsonString(config_manager, ConfigManager.class);
            ArrayList<Config> configList = savedCM.getConfigList();
            ConfigManager instanceOfConfigM = ConfigManager.getInstance();
            instanceOfConfigM.setConfigList(configList);
        }
    }
}
