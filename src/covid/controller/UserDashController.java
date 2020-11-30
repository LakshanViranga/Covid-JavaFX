package covid.controller;

import covid.dao.SnapDao;
import covid.json.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDashController {

    public Pane userPane;

    @FXML
    private Button btn_save;

    @FXML
    private DatePicker date_pick;

    @FXML
    private ComboBox select_country;

    @FXML
    private TextField txt_total_case;

    @FXML
    private TextField txt_total_death;

    @FXML
    private TextField txt_new_cases;

    @FXML
    private TextField test;

    @FXML
    private MenuItem graphic;

    @FXML
    private void graphic() throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("../view/data_view.fxml"));
        Scene graphic = new Scene(newRoot);
        Stage graphicStage = (Stage) userPane.getScene().getWindow();
        graphicStage.setScene(graphic);
    }

    @FXML
    private void edit() throws IOException{
        
    }

    public void search() throws IOException {

        LocalDate date = date_pick.getValue();

        String new_date = date.toString().replaceAll("-","");
        String country = (String) select_country.getValue();

        System.out.println("Clicked dashboard");
        HttpRequest httpRequest = new HttpRequest();
        StringBuffer result = httpRequest.HttpRequest();

        JSONObject jsonObject  = new JSONObject(result.toString());
        System.out.println(jsonObject);

        int total = jsonObject.getInt("total");
        int new_cases = jsonObject.getInt("positive");
        int death = jsonObject.getInt("death");

        System.out.println("Result:"+total);
        System.out.println("Positive:"+new_cases);
        System.out.println("Death:"+death);

        txt_new_cases.setText(String.valueOf(new_cases));
        txt_total_case.setText(String.valueOf(total));
        txt_total_death.setText(String.valueOf(death));

    }

    public void save_snap() throws IOException{

        LocalDate select_date = date_pick.getValue();
        String state = (String) select_country.getValue();
        String total_cases = txt_total_case.getText();
        String new_cases = txt_new_cases.getText();
        String total_death = txt_total_death.getText();

        System.out.println(select_date);
        System.out.println(state);
        System.out.println(total_cases);
        System.out.println(new_cases);
        System.out.println(total_death);

        SnapDao snapDao = new SnapDao();

        boolean flag = snapDao.save_snap(state,select_date,total_cases,new_cases,total_death,6);

        if (flag==true){
            infoBox("Saved Successful",null,"Saving data");
        }else{
            infoBox("Not Saved",null,"Saving data");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}

