package covid.dao;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import covid.db.DatabaseConnection;

public class AdminDao {

    public boolean saveDao(String username, String email, String password, int id) throws SQLException {

        String save_sql = "INSERT INTO `user`(`name`, `email`, `password`, `roleId`) VALUES (?,?,?,?)";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(save_sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,password);
        preparedStatement.setInt(4,id);

        int success = preparedStatement.executeUpdate();

        if (success>0){
            return true;
        }
        return false;
    }

    public void readDao(String username) throws SQLException {
        String read_sql = "SELECT * FROM ";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(read_sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){

        }
    }

    public void updateDao(String username) throws SQLException {
        String update_sql = "";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update_sql);

    }

    public void deleteDao() throws SQLException {

        String delete_sql = "";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(delete_sql);

    }
}
