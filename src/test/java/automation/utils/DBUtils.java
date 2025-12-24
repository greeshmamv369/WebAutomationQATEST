package automation.utils;


import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    // Reusable method: Get DB Connection
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Reusable method: Close resources safely
    public static void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try { resource.close(); } catch (Exception ignored) {}
        }
    }

    // Reusable method: Execute SELECT and return results as List of String arrays
    public static List<String[]> executeQuery(Connection conn, String query) throws SQLException {
        List<String[]> results = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                results.add(row);
            }
        }
        return results;
    }

    // Reusable method: Execute INSERT/UPDATE/DELETE
    public static int executeUpdate(Connection conn, String query) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(query);
        }
    }
}



