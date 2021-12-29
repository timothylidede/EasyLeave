package savala.easyleave.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.models.Employee;
import savala.easyleave.models.LeaveDays;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MyLeaveDays implements Initializable {

    @FXML
    private ImageView enquire;

    @FXML
    private ImageView status;

    @FXML
    private ImageView applicationImage;

    @FXML
    private Button back;

    @FXML
    private Label bonusDays;

    @FXML
    private Label daysRemaining;

    @FXML
    private Label daysTaken;

    @FXML
    private Label totalDays;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView myAccount;

    Database database = new Database();
    LeaveDays leaveDays= null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection conn= database.getConnection();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM tbl_employees WHERE staff_id = "+ EmployeeLogin.loggedUser_id);
            while(rs.next()){
                leaveDays=new LeaveDays(
                        Integer.parseInt(rs.getString("leave_totaldays")),
                        Integer.parseInt(rs.getString("leave_daystaken")),
                        Integer.parseInt(rs.getString("leave_daysremaining")),
                        Integer.parseInt(rs.getString("bonus"))
                );
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        totalDays.setText(String.valueOf(leaveDays.getTotalDays()));
        daysTaken.setText(String.valueOf(leaveDays.getDaysTaken()));
        daysRemaining.setText(String.valueOf(leaveDays.getRemainingDays()));
        bonusDays.setText(String.valueOf(leaveDays.getBonusDays()));

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
    void enquire(MouseEvent event) throws IOException {
        Stage stage = (Stage) enquire.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_enquire.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
