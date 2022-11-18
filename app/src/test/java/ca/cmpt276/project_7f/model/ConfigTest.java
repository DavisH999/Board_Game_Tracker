package ca.cmpt276.project_7f.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ConfigTest {

    @Test
    void getName() {
        Config config = new Config();
        config.setName("TEST");
        assertEquals("TEST",config.getName());
    }

    @Test
    void setName() {
        Config config = new Config();
        config.setName("TEST");
        assertEquals("TEST",config.getName());
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