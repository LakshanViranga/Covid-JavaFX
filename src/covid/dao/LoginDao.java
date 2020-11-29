package covid.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import covid.db.DatabaseConnection;
import sun.font.TrueTypeFont;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean validate(String username, String password) throws SQLException {

        String SQL_QUERY = "SELECT * FROM user WHERE name = ? and password = ?";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SQL_QUERY);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return true;
        }
            return false;
    }
}
