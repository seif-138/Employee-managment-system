package org.example.employeemanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_firstName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_lastName;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_position;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_date;
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
    private Label salary_employeeID;
    @FXML
    private TableView<employeeData> salary_tableView;

    @FXML
    private TableColumn<employeeData, String> salary_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> salary_col_firstName;

    @FXML
    private TableColumn<employeeData, String> salary_col_lastName;

    @FXML
    private TableColumn<employeeData, String> salary_col_position;

    @FXML
    private TableColumn<employeeData, String> salary_col_salary;
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
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    private Image image;

    public void home_totalEmployees(){
        String sql = "SELECT COUNT(id) FROM employee ";
        connect = Database.connectDB();
        int countData =0;
        try {
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {

                countData = result.getInt("COUNT(id)");
            }

            home_totalEmployees.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeTotalPresent(){
        String sql = "SELECT COUNT(id) FROM employee_info ";
        connect = Database.connectDB();
        try {
            statement = connect.createStatement();
            ResultSet result = prepare.executeQuery(sql);
            int countData =0;

            while (result.next()) {

                countData = result.getInt("COUNT(id)");
            }

            home_totalPresents.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }




    }





    public void home_totalInactive(){
        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary ='0.0' ";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            int countData =0;

            while (result.next()) {

                countData = result.getInt("COUNT(id)");
            }
            home_totalInactiveEm.setText(String.valueOf(countData));

        } catch (Exception e) {


        }



    }
    public void homechart(){


        home_chart.getData().clear();
        String sql = "SELECT date,COUNT(id) FROM employee.GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7 ";
        connect = Database.connectDB();
        try {
            XYChart.Series chart= new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            int countData =0;

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));

            }

            home_chart.getData().add(chart);
        } catch (Exception e) {


        }

    }
    public void addEmployeeSearch() {
        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);
        addEmployee_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getEmployeeId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPhoneNum().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPosition().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDate().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());
        addEmployee_tableView.setItems(sortList);
    }
    public void addEmployeeAdd() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employee "
                + "(employee_id,firstName,lastName,gender,phoneNum,position,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        addEmployee_search.clear();
        connect =Database.connectDB();

        try {
            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT employee_id FROM employee WHERE employee_id = '"
                        + addEmployee_employeeID.getText() + "'";

                statement = connect.createStatement();
                resultSet = statement.executeQuery(check);

                if ( resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + addEmployee_employeeID.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, addEmployee_phoneNum.getText());
                    prepare.setString(6, (String) addEmployee_position.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);
                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    String insertInfo= "INSERT INTO employee_info "
                            + "(employee_id,firstname,lastname,position,salary,date) "
                            + "VALUES(?,?,?,?,?,?)";

                    prepare= connect.prepareStatement(insertInfo);
                    prepare.setString(1, addEmployee_employeeID.getText());
                    prepare.setString(2, addEmployee_firstName.getText());
                    prepare.setString(3, addEmployee_lastName.getText());
                    prepare.setString(4, (String) addEmployee_position.getSelectionModel().getSelectedItem());
                    prepare.setString(5, "0.0");
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    AddEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addEmployeeUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName = '"
                + addEmployee_firstName.getText() + "', lastName = '"
                + addEmployee_lastName.getText() + "', gender = '"
                + addEmployee_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"
                + addEmployee_phoneNum.getText() + "', position = '"
                + addEmployee_position.getSelectionModel().getSelectedItem() + "', image = '"
                + uri + "', date = '" + sqlDate + "' WHERE employee_id ='"
                + addEmployee_employeeID.getText() + "'";

        connect = Database.connectDB();

        try {
            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    double salary = 0;

                    String checkData = "SELECT * FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(checkData);
                    resultSet = prepare.executeQuery();

                    while (resultSet.next()) {
                        salary = resultSet.getDouble("salary");
                    }

                    String updateInfo = "UPDATE employee_info SET firstName = '"
                            + addEmployee_firstName.getText() + "', lastName = '"
                            + addEmployee_lastName.getText() + "', position = '"
                            + addEmployee_position.getSelectionModel().getSelectedItem()
                            + "' WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    AddEmployeeShowListData();
                    addEmployeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addEmployeeDelete() {

        String sql = "DELETE FROM employee WHERE employee_id = '"
                + addEmployee_employeeID.getText() + "'";

        connect = Database.connectDB();

        try {

            Alert alert;
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_firstName.getText().isEmpty()
                    || addEmployee_lastName.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_position.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + addEmployee_employeeID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    String deleteInfo = "DELETE FROM employee_info WHERE employee_id = '"
                            + addEmployee_employeeID.getText() + "'";

                    prepare = connect.prepareStatement(deleteInfo);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    AddEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeReset(){
        addEmployee_employeeID.setText("");
        addEmployee_firstName.setText("");
        addEmployee_lastName.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_position.getSelectionModel().clearSelection();
        addEmployee_phoneNum.setText("");
        addEmployee_image.setImage(null);
        getData.path = "";
    }

    public void addEmployeeInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 127, false, true);
            addEmployee_image.setImage(image);
        }
    }
    private String[] positionList = {"Marketer Coordinator", "Web Developer (Back End)", "Web Developer (Front End)", "App Developer"};
    public void addEmployeePositionList(){
        List<String> ListP = new ArrayList<>();

        for(String data: positionList){

            ListP.add(data);
        }


        ObservableList ListData= FXCollections.observableArrayList(ListP);
        addEmployee_position.setItems(ListData);
    }


    private String[] listGender = {"Male", "Female"};

    public void addEmployeeGendernList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);

        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_gender.setItems(listData);

    }





    public ObservableList<employeeData> addemployeelistData()
    {
        ObservableList<employeeData> ListData= FXCollections.observableArrayList();
        String sql ="select * from employee";
        connect=Database.connectDB();
        try{
            prepare=connect.prepareStatement(sql);
            resultSet=prepare.executeQuery();
            employeeData employeeD;
            while (resultSet.next())
            {
                employeeD =new employeeData(resultSet.getInt("employee_id"), resultSet.getString("firstName")
                        ,resultSet.getString("lastName")
                        ,resultSet.getString("gender"), resultSet.getString("phoneNum"),
                        resultSet.getString("position")
                        ,resultSet.getString("image")
                        ,resultSet.getDate("date"));
                ListData.add(employeeD);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListData;
    }
    private ObservableList<employeeData> addEmployeeList = FXCollections.observableArrayList();
    public void AddEmployeeShowListData()
    {
        addEmployeeList=addemployeelistData();
        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        addEmployee_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addEmployee_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        addEmployee_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        addEmployee_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addEmployee_tableView.setItems(addEmployeeList);

    }

    public void addEmployeeSelect(){

        employeeData employeeD = addEmployee_tableView.getSelectionModel().getSelectedItem();
        int num = addEmployee_tableView.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){
            return;
        }
        addEmployee_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        addEmployee_firstName.setText(employeeD.getFirstName());
        addEmployee_lastName.setText(employeeD.getLastName());
        addEmployee_phoneNum.setText(employeeD.getPhoneNum());




        getData.path = employeeD.getImage();
        String uri = "file:"+employeeD.getImage();

        image = new Image(uri, 101, 127, false, true);
        addEmployee_image.setImage(image);
    }

    public void salaryUpdate(){

        String sql = "UPDATE employee_info SET salary ='"+salary_salary.getText()
                + "' WHERE employee_id ='"+ salary_employeeID.getText() + "'";

        // String sql = "UPDATE employee_info SET firstName ='"
        // +
        // salary_firstName.getText()
        // +"',lastName='"
        // +salary_lastName.getText()+"' ,Position ='"
        // +
        // salary_position.getText()

        // +"' salary = '"
        // +  Double.parseDouble(salary_salary.getText())
        //         +"'WHERE employee_id = "+salary_employeeID.getText();

        //
        connect = Database.connectDB();

        try{
            Alert alert;
            if(salary_employeeID.getText().isEmpty() ||
                    salary_firstName.getText().isEmpty() ||
                    salary_lastName.getText().isEmpty() ||
                    salary_position.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();

            }
            else{
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();
                salaryShowListData();
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void salaryreset(){
        salary_employeeID.setText("");
        salary_firstName.setText("");
        salary_lastName.setText("");
        salary_position.setText("");
        salary_salary.setText("");

    }
    public ObservableList<employeeData> salaryListData(){
        ObservableList<employeeData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employee_info";

        connect=Database.connectDB();

        try{
            prepare =connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            employeeData employeeD;
            while(resultSet.next()){
                employeeD = new employeeData(
                        resultSet.getInt("employee_id")
                        , resultSet.getString("firstName")
                        , resultSet.getString("lastName")
                        ,resultSet.getString("position")
                        ,resultSet.getDouble("salary"));



                listData.add(employeeD);
            }
        }catch (Exception e){e.printStackTrace();}
        return listData;
    }
    private ObservableList<employeeData> salaryList;
    public void salaryShowListData(){
        salaryList = salaryListData();

        salary_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        salary_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        salary_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        salary_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        salary_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        salary_tableView.setItems(salaryList);

    }

    public void salarySelect(){
        employeeData employeeD = salary_tableView.getSelectionModel().getSelectedItem();
        int num = salary_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }
        salary_employeeID.setText(String.valueOf(employeeD.getEmployeeId()));
        salary_firstName.setText(employeeD.getFirstName());
        salary_lastName.setText(employeeD.getLastName());
        salary_position.setText(employeeD.getPosition());
        salary_salary.setText(String.valueOf(employeeD.getSalary()));



    }
    public void defaultNav(){
        home_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

    }

    public void displayUsername(){
        username.setText(getData.username);

    }

    public void switchForm(ActionEvent event) {
        home_btn.setStyle("");
        addEmployee_btn.setStyle("");
        salary_btn.setStyle("");

        home_totalEmployees();
        addEmployeeTotalPresent();
        home_totalInactive();
        homechart();

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

            addEmployeeGendernList();
            addEmployeePositionList();
            addEmployeeSearch();
        } else if (event.getSource() == salary_btn) {
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);
            salary_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            addEmployee_btn.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");

            salaryShowListData();
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
        displayUsername();
        defaultNav();


        home_totalEmployees();
        addEmployeeTotalPresent();
        home_totalInactive();
        homechart();
        home_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        AddEmployeeShowListData();
        addEmployeePositionList();
        addEmployeeGendernList();
        home_form.setVisible(true);
        addEmployee_form.setVisible(false);
        salary_form.setVisible(false);
        salaryShowListData();
        addEmployeePositionList();
    }
}