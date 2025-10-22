package org.company.utility;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public abstract class ConfigurationReader {
    private static Properties properties;

    public static String readConfigurationFile(String key) {
        try {
            properties = new Properties();
            properties.load(Files.newInputStream(Paths.get(Constants.CONFIG_FILE_PATH)));
        } catch (Exception e) {
            System.out.println("Cannot find key: " + key + " in Config file due to exception: " + e);
        }
        return properties.getProperty(key).trim();
    }
}
