package ly.cel.jvinsights.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
  public static String ACCOUNT_ID_PROPERTY_KEY = "account_id";
  public static String ACCOUNT_KEY_PROPERTY_KEY = "account_key";
  public static String QUERY_KEY_PROPERTY_KEY = "query_key";
  public static String ACCOUNT_ID = "YOUR-ACCOUNT-ID";
  public static String ACCOUNT_KEY = "YOUR-ACCOUNT-KEY";
  public static String QUERY_KEY = "YOUR-QUERY-KEY";
  public static String PROPERTY_FILE = "config.properties";

  private static Properties prop;
  
  public static Properties getPropertiesFile() {
    try {
      if (ConfigProperties.prop == null) {
        ConfigProperties.prop = new Properties();
        InputStream inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
        if (inputStream == null) {
          return null;
        }
        ConfigProperties.prop.load(inputStream);
      }
      return ConfigProperties.prop;
    } catch (IOException e) {
      return null;
    }
  }

  public static String getValue(String property) {
    Properties properties = getPropertiesFile();
    if (properties != null) {
      String result = properties.getProperty(property, getDefaultValue(property));
      return result;
    } else {
      return getDefaultValue(property);
    }
  }
  
  private static String getDefaultValue(String property) {
    if (ACCOUNT_ID_PROPERTY_KEY.equals(property)) {
      return ACCOUNT_ID;
    } else if (ACCOUNT_KEY_PROPERTY_KEY.equals(property)) {
      return ACCOUNT_KEY;
    } else if (QUERY_KEY_PROPERTY_KEY.equals(property)) {
      return QUERY_KEY;
    } else {
      return "";
    }
  }
}
