package covid.controller;

import covid.dao.LoginDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField txt_user;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Button btn_login;

    @FXML
    private Button btn_register;

    public void Login(ActionEvent actionEvent){
        String user_name = txt_user.getText();
        String password =  txt_password.getText();

        LoginDao loginDao = new LoginDao();

        try {
            boolean flag = loginDao.validate(user_name,password);

            if(flag == true){
                infoBox("Login Successful!", null, "Failed");

                Parent newRoot = FXMLLoader.load(getClass().getResource("../view/user_dash.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle("Covid-19 Update");
                primaryStage.setScene(new Scene(newRoot,650,775));
                primaryStage.show();
            }else{
                infoBox("Please enter correct Email and Password", null, "Failed");
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void register(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked");
        Parent newRoot = FXMLLoader.load(getClass().getResource("../view/register.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Covid-19 Update");
        primaryStage.setScene(new Scene(newRoot,650,475));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> Platform.exit());
    }
}
