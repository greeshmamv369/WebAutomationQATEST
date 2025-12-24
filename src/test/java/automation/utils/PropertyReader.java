package automation.utils;


    import java.io.*;
import java.util.Properties;

    public class PropertyReader {

        private static Properties properties = new Properties();
        private static final String filePath = "src/test/resources/config.properties";

        static {
            try (FileInputStream fis = new FileInputStream(filePath)) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties file");
            }
        }

        // ============ READ PROPERTY ============
        public static String get(String key) {
            return properties.getProperty(key);
        }

        // ============ WRITE/UPDATE PROPERTY ============
        public static void set(String key, String value) {
            properties.setProperty(key, value);
            save();   // Automatically save to file
        }

        // ============ SAVE PROPERTIES TO FILE ============
        public static void save() {
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                properties.store(fos, "Updated by PropertyReader Utility");
            } catch (IOException e) {
                throw new RuntimeException("Failed to write to config.properties file");
            }
        }

        // ============ RELOAD FROM FILE (if needed) ============
        public static void reload() {
            try (FileInputStream fis = new FileInputStream(filePath)) {
                properties.clear();
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to reload config.properties file");
            }
        }
    }


