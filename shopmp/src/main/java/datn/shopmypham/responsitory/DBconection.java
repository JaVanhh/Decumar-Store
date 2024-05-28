package datn.shopmypham.responsitory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconection {
    static final String URL = "jdbc:mysql://localhost:3306/shopmypham";
    static final String USERNAME = "root";
    static final String PASS_WORD = "123456";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASS_WORD);
        } catch (SQLException ex) {
            throw ex;
        }

    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeResultSet(ResultSet resultSet)  {
        if (resultSet == null) {
            return;
        }

        try {
            resultSet.close();
        } catch (Exception ex) {
        }
    }

    public static void closeStatement(PreparedStatement statement) {
        if (statement == null) {
            return;
        }
        try {
            statement.close();
        } catch (Exception ex) {
        }
    }
}
