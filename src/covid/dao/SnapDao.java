package covid.dao;

import com.mysql.jdbc.Connection;
import covid.db.DatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SnapDao {

    public boolean save_snap(String state, LocalDate date, String total_cases, String total_deaths, String new_cases, int user_id){

        String save_snapshot = "INSERT INTO `snapshot`(CountryName`, `Save_date`, `TotalCases`, `TotalDeaths`, `NewCases`, `UserId`) VALUES (?,?,?,?,?,?)";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        System.out.println(date);
        System.out.println(state);
        System.out.println(total_cases);
        System.out.println(new_cases);
        System.out.println(total_deaths);
        System.out.println(user_id);

        try {
            PreparedStatement ps = connection.prepareStatement(save_snapshot);

            ps.setString(1,state);
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3,total_cases);
            ps.setString(4,total_deaths);
            ps.setString(5,new_cases);
            ps.setInt(6,user_id);

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
