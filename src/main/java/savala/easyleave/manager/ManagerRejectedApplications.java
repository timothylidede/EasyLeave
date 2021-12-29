package savala.easyleave.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.models.ViewApplication;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ManagerRejectedApplications implements Initializable {

    @FXML
    private TableColumn<ViewApplication, String> leave_staff;

    @FXML
    private TableColumn<ViewApplication, String> leave_dept;

    @FXML
    private TableView<ViewApplication> leave_table;

    @FXML
    private TableColumn<ViewApplication, String> leave_type;

    @FXML
    private TableColumn<ViewApplication, String> leave_start;

    @FXML
    private TableColumn<ViewApplication, String> leave_end;

    @FXML
    private TableColumn<ViewApplication, Integer> leave_days;

    @FXML
    private TableColumn<ViewApplication, String> leave_status;

    @FXML
    private TableColumn<ViewApplication, String> leave_employee;


    @FXML
    private TableColumn<ViewApplication, String> leave_reasons;

    @FXML
    private TableColumn<ViewApplication, String> leave_accept;

    @FXML
    private TableColumn<ViewApplication, String> leave_reject;


    @FXML
    private HBox accepted;

    @FXML
    private TableColumn<ViewApplication, String> leave_firstname;

    @FXML
    private TableColumn<ViewApplication, String> leave_lastname;

    @FXML
    private Button back;

    @FXML
    private ImageView bonusDaysImage;

    @FXML
    private ImageView enquiries;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView myAccount;

    @FXML
    private ImageView myEmployeesImage;

    @FXML
    private HBox pending;

    Database database=new Database();
    ObservableList<ViewApplication> apList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String verify;
        verify = "SELECT * FROM `tbl_leavedetails` JOIN tbl_employees " +
                "ON tbl_leavedetails.leave_staff =tbl_employees.staff_id " +
                " WHERE DeptNo= '" + ManagerLogin.DeptNo + "' AND leave_status = 'Rejected' ";
        try{
            Connection connection=database.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(verify);

            while(rs.next()) {
                //labelThis.setText("Congrats");
                apList.add(new ViewApplication(Integer.parseInt(rs.getString("leave_staff")),Integer.parseInt(rs.getString("leave_id")),Integer.parseInt(rs.getString("DeptNo")),
                        rs.getString("first_name"),rs.getString("last_name"),rs.getString("leave_startdate"),
                        rs.getString("leave_enddate"),rs.getString("leave_daysrequested"),
                        rs.getString("leave_type"), rs.getString("leave_comments"),
                        rs.getString("leave_status"),
                        new Button(),
                        new Button(),
                        rs.getDate("leave_startdate")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        if(apList!=null) {
            leave_firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            leave_lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            leave_start.setCellValueFactory(new PropertyValueFactory<>("leaveStart"));
            leave_end.setCellValueFactory(new PropertyValueFactory<>("leaveEnd"));
            leave_days.setCellValueFactory(new PropertyValueFactory<>("leaveDays"));
            leave_type.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
            leave_status.setCellValueFactory(new PropertyValueFactory<>("leaveStatus"));
            leave_reasons.setCellValueFactory(new PropertyValueFactory<>("leaveReason"));
//            leave_accept.setCellValueFactory(new PropertyValueFactory<>("accept"));
//            leave_reject.setCellValueFactory(new PropertyValueFactory<>("reject"));


            leave_table.setItems(apList);
        }
        else{
//            leave_table.setDisable(true);
//            leave_table.setVisible(false);

        }


    }


    @FXML
    void accepted(MouseEvent event) throws IOException {
        Stage stage = (Stage) accepted.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_accepted_applications.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void bonusDaysImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) bonusDaysImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/bonus_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void enquiries(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquiries.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/enquiries.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void goBack(MouseEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_dashboard.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myAccount(MouseEvent event) throws IOException{
        Stage stage = (Stage) myAccount.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/my_account.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void myEmployeesImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) myEmployeesImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/my_employees.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void pending(MouseEvent event) throws IOException {
        Stage stage = (Stage) pending.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_pending_applications.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

}
