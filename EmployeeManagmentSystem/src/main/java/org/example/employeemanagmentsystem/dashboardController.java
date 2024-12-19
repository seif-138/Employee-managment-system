package org.example.employeemanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private Button salary_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private TextField salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private Button salary_clearBtn;

    private double x=0;
    private double y=0;
    public void displayUsername(){
        username.setText(getData.username);

    }
    public void switchForm(ActionEvent event) {
        // Reset the button styles first
        home_btn.setStyle("");
        addEmployee_btn.setStyle("");
        salary_btn.setStyle("");

        // Apply the hover effect or selected style to the clicked button
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            home_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        } else if (event.getSource() == addEmployee_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            salary_form.setVisible(false);
            addEmployee_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);
            salary_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        }
    }

    public void logout(){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmatiopn Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to logout");
        Optional<ButtonType> option=alert.showAndWait();
        try{
            if(option.get().equals(ButtonType.OK)){
                logout.getScene().getWindow().hide();
                Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage=new Stage();
                Scene scene=new Scene(root);
                root.setOnMousePressed((e)->{
                    x= e.getSceneX();
                    y=e.getSceneY();
                });
                root.setOnMouseDragged((e)-> {
                    stage.setX(e.getScreenX() - x);
                    stage.setY(e.getScreenY() - y);
                    stage.setOpacity(.8);
                });
                root.setOnMouseReleased((e)->{
                    stage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage=(Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        home_form.setVisible(true);
        addEmployee_form.setVisible(false);
        salary_form.setVisible(false);
    }
}
