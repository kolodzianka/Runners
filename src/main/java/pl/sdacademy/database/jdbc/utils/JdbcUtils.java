package pl.sdacademy.database.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

    private final static JdbcUtils instance = new JdbcUtils();
    private static Connection connection;

    private JdbcUtils() {
        String dbPassword = "Kuba2017";
        String dbUser = "root";
        String connectionString = "jdbc:mysql://localhost:3306/public";


        Properties prop = new Properties();
        prop.put("password", dbPassword);
        prop.put("user", dbUser);

        try {
            connection = DriverManager.getConnection(connectionString, prop);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public static JdbcUtils getInstance(){
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }


}
