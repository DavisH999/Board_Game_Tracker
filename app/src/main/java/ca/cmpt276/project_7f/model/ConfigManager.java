package ca.cmpt276.project_7f.model;

import java.util.ArrayList;

public class ConfigManager {
    // does not allow same name !

    private ArrayList<Config> configList;
    private static ConfigManager instance;

    public ArrayList<Config> getConfigList() {
        return configList;
    }

    private ConfigManager() {
        configList = new ArrayList<>();
    }

    public static ConfigManager getInstance()
    {
        // use instance because we only have one config list.
        if(instance == null)
        {
            instance = new ConfigManager();
        }
        return instance;
    }

    public void addConfig(Config config)
    {
        configList.add(config);
    }

    public void editConfig(int indexInConfigList, Config newConfig)
    {
        Config oldConfig = configList.get(indexInConfigList);
        String oldConfigName = oldConfig.getName();
        String newConfigName = newConfig.getName();

        configList.set(indexInConfigList,newConfig);
        // update games as well.
        GameManager instanceOfGameManager = GameManager.getInstance();
        instanceOfGameManager.updateGames(oldConfigName,newConfigName);
    }

    public void removeConfig(int indexInConfigList)
    {
        configList.remove(indexInConfigList);
        // remove games as well.
        GameManager instanceOfGameManager = GameManager.getInstance();
        Config config = configList.get(indexInConfigList);
        String configName = config.getName();
        instanceOfGameManager.removeGames(configName);
    }

    public Config getConfigByName(String name)
    {
        for(int i = 0; i < configList.size(); i++)
        {
            Config config = configList.get(i);
            if(config.getName().equals(name))
            {
                return config;
            }
        }
        return null;
    }

    public Config getConfigById(int id)
    {
        return configList.get(id);
    }

}
