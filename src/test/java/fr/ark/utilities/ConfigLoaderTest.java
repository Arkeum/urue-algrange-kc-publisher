package fr.ark.utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigLoaderTest {
    ConfigLoader configLoader;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get_success() {
        ConfigLoader configLoader = new ConfigLoader("config.properties");
        assertNotNull(configLoader.get("urue.algrange.api.baseurl"));
    }
}