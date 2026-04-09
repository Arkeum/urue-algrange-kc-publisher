package fr.ark.utilities;

import fr.ark.exceptions.ConfigNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    public ConfigLoader(String configFile) {
        try(InputStream input = this.getClass().getClassLoader().getResourceAsStream(configFile)){
            if (input == null) throw new ConfigNotFoundException("Failed to load configuration file");
            properties.load(input);
        }catch(IOException e){
            throw new RuntimeException("Unable to load configuration file", e);
        }
    }

    public String get(String key){
        return switch (properties.getProperty(key)) {
            case null -> throw new ConfigNotFoundException("Configuration key not found: " + key);
            case "" -> throw new ConfigNotFoundException("Configuration key is empty: " + key);
            default -> properties.getProperty(key);
        };
    }

}
