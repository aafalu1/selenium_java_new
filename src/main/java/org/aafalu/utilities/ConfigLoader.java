package org.aafalu.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private static ConfigLoader single_instance = null;
    private static Properties prop = null;

    private ConfigLoader() {
        Path configPath = Paths.get(App.PROJECT_DIR, "src", "main", "resources", "configuration",
                "config.properties");
        try (FileInputStream fis = new FileInputStream(configPath.toFile())) {
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error on File path: " + fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Issue loading file: " + ioe.getMessage());
            ioe.printStackTrace();
        }

    }

    public static ConfigLoader getInstance() {
        if (single_instance == null) {
            single_instance = new ConfigLoader();
        }
        return single_instance;
    }

    public String getStringValue(String key){
        return prop.getProperty(key);
    }

    public boolean getBooleanValue(String key){
        try {
            return Boolean.parseBoolean(prop.getProperty(key));
        } catch (NullPointerException | IllegalArgumentException e) {
            return false;
        }
    }

}
