package covid.dao;

import covid.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataViewDao {

    public ArrayList<Object> dataView(int ID) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String view_query = "SELECT * FROM `snapshot` WHERE `UserId`= ?";

        PreparedStatement preparedStatement = connection.prepareStatement(view_query);
        preparedStatement.setObject(1, ID);

        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Object> arrayList = new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet);
        }

        return arrayList;
    }
}
