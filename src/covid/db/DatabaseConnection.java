package covid.db;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/covid?useSSL=false";
    private static final String DATABASE_USER ="root";
    private static final String DATABASE_PASSWORD ="";

    Connection connection ;

    public Connection getConnection(){

        try{
            connection = (Connection) DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return connection;

    }
}
