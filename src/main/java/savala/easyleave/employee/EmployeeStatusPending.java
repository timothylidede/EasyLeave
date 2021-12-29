package savala.easyleave.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.models.Application;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EmployeeStatusPending implements Initializable {

    @FXML
    private TableView<Application> leave_table;

    @FXML
    private TableColumn<Application, String> leave_type;

    @FXML
    private TableColumn<Application, String> leave_start;

    @FXML
    private TableColumn<Application, String> leave_end;

    @FXML
    private TableColumn<Application, Integer> leave_days;

    @FXML
    private TableColumn<Application, String> leave_status;

    @FXML
    private HBox accepted;

    @FXML
    private ImageView applicationImage;

    @FXML
    private Button back;

    @FXML
    private ImageView enquiries;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView myAccount;

    @FXML
    private ImageView myLeaveDays;

    @FXML
    private HBox rejected;

    Database database = new Database();
    ObservableList<Application> apList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String verify;
        verify = "SELECT * FROM `tbl_leavedetails` WHERE leave_staff= '" + EmployeeLogin.loggedUser_id + "' AND leave_status = 'Pending' ";
        try{
            Connection connection=database.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(verify);

            while(rs.next()) {
                    //labelThis.setText("Congrats");
                    apList.add(new Application(rs.getString("leave_startdate"), rs.getString("leave_enddate"),rs.getString("leave_daysrequested"),
                            rs.getString("leave_type"),
                            rs.getString("leave_status")
                    ));
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        leave_type.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
        leave_status.setCellValueFactory(new PropertyValueFactory<>("leaveStatus"));
        leave_start.setCellValueFactory(new PropertyValueFactory<>("leaveStart"));
        leave_end.setCellValueFactory(new PropertyValueFactory<>("leaveEnd"));
        leave_days.setCellValueFactory(new PropertyValueFactory<>("leaveDays"));
        leave_table.setItems(apList);

    }

    @FXML
    void applicationImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) applicationImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_normal_application.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void enquiries(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquiries.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_enquire.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_dashboard.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myAccount(MouseEvent event) throws IOException {
        Stage stage = (Stage) myAccount.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_account.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myLeaveDays(MouseEvent event) throws IOException {
        Stage stage = (Stage) myLeaveDays.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_leave_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void accepted(MouseEvent event) throws IOException {
        Stage stage = (Stage) accepted.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_status_accepted.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void rejected(MouseEvent event) throws IOException {
        Stage stage = (Stage) rejected.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_status_rejected.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

}
