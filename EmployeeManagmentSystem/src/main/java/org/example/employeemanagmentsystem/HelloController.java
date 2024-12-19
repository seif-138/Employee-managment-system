package org.example.employeemanagmentsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Button loginBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    private double x=0;
    private double y=0;
   
    public void loginAdmin() {
        connect = Database.connectDB();
        if (connect == null) {
            // Handle the case when the database connection fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Failed to connect to the database");
            alert.showAndWait();
            return;
        }

        String sql = "select * from admin where username= ? and password= ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            Alert alert;

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else {
                if(result.next()){
                    getData.username =username.getText();

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!!");
                    alert.showAndWait();
                    loginBtn.getScene().getWindow().hide();

                    URL fxmlLocation = getClass().getResource("/org/example/employeemanagmentsystem/dashboard.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
                     Parent root= fxmlLoader.load();
                    Stage stage=new Stage();
                    Scene scene = new Scene(root);
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
                else{
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("wrong username or password");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            // Close the database connection and resources
            if (prepare!= null) {
                try {
                    prepare.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (connect!= null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
        public void close () {
            System.exit(0);
        }
    }