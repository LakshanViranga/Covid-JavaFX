package covid.dao;

import com.mysql.jdbc.Connection;
import covid.db.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {

    public boolean register(String name, String email, String password){

        String sql = "INSERT INTO `user`(`name`, `email`, `password`) VALUES (?,?,?)";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,password);

            int success = ps.executeUpdate();

            if(success>0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
