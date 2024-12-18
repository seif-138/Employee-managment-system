package org.example.employeemanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class EmployeeManagementSystem extends Application {
    private double x=0;
    private double y=0;
    @Override
    public void start(Stage stage) throws IOException {

        try {
            URL fxmlLocation = getClass().getResource("/org/example/employeemanagmentsystem/hello-view.fxml");
            if (fxmlLocation == null) {
                throw new IOException("Could not find FXML file");
            }
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Parent root = fxmlLoader.load();

//        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeManagementSystem.class.getResource("hello-view.fxml"));
//        Parent root= fxmlLoader.load();
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
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show(); } catch (IOException e) {
            System.err.println("Failed to load FXML: " + e.getMessage());
            e.printStackTrace();
            // Handle the error appropriately
        }
    }

    public static void main(String[] args) {
        launch();
    }
}