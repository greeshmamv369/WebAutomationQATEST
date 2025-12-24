package automation.utils;


    import java.io.*;
import java.nio.file.*;
import java.util.List;

    public class FileUtils {

        // ✅ Read entire file content as String
        public static String readFile(String filePath) throws IOException {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }

        // ✅ Read file line by line into List<String>
        public static List<String> readFileLines(String filePath) throws IOException {
            return Files.readAllLines(Paths.get(filePath));
        }

        // ✅ Write text to file (overwrite)
        public static void writeFile(String filePath, String content) throws IOException {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

        // ✅ Append text to file
        public static void appendToFile(String filePath, String content) throws IOException {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }

        // ✅ Copy file
        public static void copyFile(String sourcePath, String destPath) throws IOException {
            Files.copy(Paths.get(sourcePath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
        }

        // ✅ Delete file
        public static boolean deleteFile(String filePath) throws IOException {
            return Files.deleteIfExists(Paths.get(filePath));
        }

        // ✅ Check if file exists
        public static boolean fileExists(String filePath) {
            return Files.exists(Paths.get(filePath));
        }

        // ✅ Create directory if not exists
        public static void createDirectory(String dirPath) throws IOException {
            Files.createDirectories(Paths.get(dirPath));
        }
    }


