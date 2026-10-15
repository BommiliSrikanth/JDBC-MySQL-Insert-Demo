// JDBC Demo: Create table (if not exists) and insert data using PreparedStatement
// Uses try-with-resources for automatic resource management

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCInsertDemo {

    // Step-2: JDBC connection constants
    // Change these values according to your MySQL setup
    private static final String JDBC_URL  = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME  = "root";          // your MySQL username
    private static final String PASSWORD  = "password";      // your MySQL password

    public static void main(String[] args) {

        // Step-3: try-with-resources for Connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {

            System.out.println("Connected to MySQL database successfully!");

            // Step-4: Create the users table if it does not exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "name VARCHAR(100) NOT NULL, " +
                                    "email VARCHAR(100) NOT NULL)";

            try (PreparedStatement createStmt = connection.prepareStatement(createTableSQL)) {
                createStmt.executeUpdate();
                System.out.println("Table 'users' is ready (created if it did not exist).");
            }

            // Step-5 & Step-6: Insert first record using PreparedStatement
            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                // First record: John Doe
                insertStmt.setString(1, "John Doe");
                insertStmt.setString(2, "johndoe@example.com");
                int rows1 = insertStmt.executeUpdate();
                System.out.println("Inserted " + rows1 + " row(s): John Doe");

                // Step-7: Second record: Jane Doe
                insertStmt.setString(1, "Jane Doe");
                insertStmt.setString(2, "janedoe@example.com");
                int rows2 = insertStmt.executeUpdate();
                System.out.println("Inserted " + rows2 + " row(s): Jane Doe");
            }

            // Step-8: Success message
            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            // Step-9: Handle SQLException
            System.err.println("SQLException occurred!");
            System.err.println("Error Message : " + e.getMessage());
            System.err.println("SQL State     : " + e.getSQLState());
            System.err.println("Error Code    : " + e.getErrorCode());
            e.printStackTrace();
        }
        // Step-10: Resources are automatically closed by try-with-resources
    }
}
