package ca.cmpt276.project_7f.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ConfigTest {

    @Test
    void testToString() {
        Config config = new Config();
        config.setName("NAME");
        config.setGreatScore(100);
        config.setPoorScore(0);
        String toString1 = config.toString();
        String toString2 = "Config{" +
                "configName='" + config.getName() + '\'' +
                ", poorScore=" + config.getPoorScore() +
                ", greatScore=" + config.getGreatScore() +
                '}';
        assertEquals(toString1,toString2);
    }

    @Test
    void getName() {
        Config config = new Config();
        config.setName("NAME");
        assertEquals("NAME",config.getName());
    }

    @Test
    void setName() {
        Config config = new Config();
        config.setName("NAME");
        assertEquals("NAME",config.getName());
    }

    @Test
    void getPoorScore() {
    }

    @Test
    void setPoorScore() {
    }

    @Test
    void getGreatScore() {
    }

    @Test
    void setGreatScore() {
    }
}