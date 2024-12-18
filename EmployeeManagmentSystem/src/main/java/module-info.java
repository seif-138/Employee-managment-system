module org.example.employeemanagmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.employeemanagmentsystem to javafx.fxml;
    exports org.example.employeemanagmentsystem;
}