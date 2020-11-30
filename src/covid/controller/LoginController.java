package covid.controller;

import covid.Main;
import covid.dao.LoginDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController  {

    public Pane pane;

    public Pane registerPane;

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
            if(user_name.equalsIgnoreCase("admin")){
                boolean flag = loginDao.validate(user_name,password);

                if(flag == true){
                    infoBox("Login Successful!", null, "Covid");

                    Parent newRoot = FXMLLoader.load(getClass().getResource("../view/admin_dash.fxml"));
                    Scene loginScene = new Scene(newRoot);
                    Stage loginStage = (Stage) pane.getScene().getWindow();
                    loginStage.setScene(loginScene);
                }else{
                    infoBox("Please enter correct Email and Password", null, "Failed");
                }
            }else{
                boolean flag = loginDao.validate(user_name,password);

                if(flag == true){
                    infoBox("Login Successful!", null, "Failed");

                    Parent newRoot = FXMLLoader.load(getClass().getResource("../view/user_dash.fxml"));
                    Scene loginScene = new Scene(newRoot);
                    Stage loginStage = (Stage) pane.getScene().getWindow();
                    loginStage.setScene(loginScene);
                }else{
                    infoBox("Please enter correct Email and Password", null, "Failed");
                }
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
        Scene registerScene = new Scene(newRoot);
        Stage registerStage = (Stage) pane.getScene().getWindow();
        registerStage.setScene(registerScene);

    }
}
