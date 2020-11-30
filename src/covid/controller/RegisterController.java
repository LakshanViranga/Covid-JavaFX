package covid.controller;

import covid.dao.RegisterDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    public Pane registerPane;
    @FXML
    private TextField txt_user;

    @FXML
    private PasswordField txt_password;

    @FXML
    private  TextField txt_email;

    @FXML
    private Button btn_register;

    @FXML
    private Button btn_login;

    public void Register(ActionEvent actionEvent) throws IOException {
        String user = txt_user.getText();
        String password = txt_password.getText();
        String email = txt_email.getText();

        RegisterDao registerDao = new RegisterDao();

        boolean flag = registerDao.register(user,password,email);

        if(flag ==true){
            infoBox("Registration Successful",null,"Registration");
            Parent newRoot = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
            Scene loginScene = new Scene(newRoot);
            Stage loginStage = (Stage) registerPane.getScene().getWindow();
            loginStage.setScene(loginScene);
        }else {
            infoBox("Register Unsuccessful",null,"Registration");
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void login(ActionEvent actionEvent) throws Exception{
        Parent newRoot = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene loginScene = new Scene(newRoot);
        Stage loginStage = (Stage) registerPane.getScene().getWindow();
        loginStage.setScene(loginScene);
    }
}
