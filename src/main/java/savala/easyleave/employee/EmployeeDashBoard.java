package savala.easyleave.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.stage.StageStyle;
import savala.easyleave.models.Employee;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EmployeeDashBoard implements Initializable{

    @FXML
    private ImageView applicationImage;

    @FXML
    private ImageView close;

    @FXML
    private Label first_name;

    @FXML
    private HBox leave_days;

    @FXML
    private ImageView myAccount;

    @FXML
    private HBox enquire;

    @FXML
    private ImageView enquireImage;

    @FXML
    private HBox status;

    @FXML
    private ImageView statusImage;

    @FXML
    private ImageView my_leave_days;

    @FXML
    private Button employeeLogout;

    @FXML
    private HBox employeeApplication;

    @FXML
    private HBox hBox;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee=null;
        try{
            Connection conn= database.getConnection();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM tbl_employees "+
                    "WHERE staff_id = "+ EmployeeLogin.loggedUser_id);
            while(rs.next()){
                employee=new Employee(rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"),
                        rs.getString("email"),
                        Integer.parseInt(rs.getString("bonus")),
                        Integer.parseInt(rs.getString("staff_id")),
                        rs.getString("DeptNo")
                );
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        first_name.setText(employee.getFname());
    }

    @FXML
    void employee_logout(MouseEvent event) throws IOException {
        Stage stage = (Stage) employeeLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/savala/easyleave/welcome/login.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    void employeeApplication(MouseEvent event) throws IOException {
        Stage stage = (Stage) employeeApplication.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_normal_application.fxml"));
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
    void myLeaveDays(MouseEvent event) throws IOException {
        Stage stage = (Stage) my_leave_days.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_leave_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void leaveDays(MouseEvent event) throws IOException {
        Stage stage = (Stage) leave_days.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_leave_days.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void enquire(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquire.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_enquire.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void enquireImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquireImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_enquire.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void status(MouseEvent event) throws IOException {
        Stage stage = (Stage) status.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_status_accepted.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void statusImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) statusImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_status_accepted.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
