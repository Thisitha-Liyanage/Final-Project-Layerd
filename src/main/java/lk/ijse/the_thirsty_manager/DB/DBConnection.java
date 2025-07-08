package lk.ijse.the_thirsty_manager.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
    public class DBConnection {
        private static DBConnection dbConnection;
        private final Connection connection;

        private DBConnection() throws SQLException {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/the_thirsty_manager",
                    "thisitha",
                    "thisitha@2007"
            );
        }

        public static DBConnection getInstance() throws SQLException {
            return dbConnection == null ?
                    dbConnection = new DBConnection() :
                    dbConnection;
        }

        public Connection getConnection() {
            return connection;
        }
    }


