package ca.cmpt276.project_7f.model;

public class Config {
    private String configName;
    private int poorScore;
    private int greatScore;

    public Config() {
    }

    public String getName() {
        return configName;
    }

    public void setName(String name) {
        this.configName = name;
    }

    public int getPoorScore() {
        return poorScore;
    }

    public void setPoorScore(int poorScore) {
        this.poorScore = poorScore;
    }

    public int getGreatScore() {
        return greatScore;
    }

    public void setGreatScore(int greatScore) {
        this.greatScore = greatScore;
    }
}