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
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MyAccount implements Initializable {

    @FXML
    private ImageView enquire;

    @FXML
    private ImageView status;

    @FXML
    private Button back;

    @FXML
    private Label emailAddress;

    @FXML
    private Label firstName;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView maleProfilePhoto;

    @FXML
    private ImageView femaleProfilePhoto;

    @FXML
    private Label secondName;

    @FXML
    private Label sex;

    @FXML
    private ImageView myLeaveDays;

    @FXML
    private Label staffId;

    @FXML
    private ImageView applicationImage;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load employee data
        Employee employee=null;
        try{
            Connection conn= database.getConnection();
            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM tbl_employees" +
                    " WHERE staff_id = "+ EmployeeLogin.loggedUser_id);
            while(rs.next()){
                employee=new Employee(rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"),
                        rs.getString("email"),
                        Integer.parseInt(rs.getString("bonus")),
                        Integer.parseInt(rs.getString("staff_id"))
                        ,rs.getString("DeptNo")
                        //funny business here.....change Number to Name
                );
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        firstName.setText(employee.getFname());
        secondName.setText(employee.getLname());
        emailAddress.setText(employee.getEmail());
        staffId.setText(String.valueOf(employee.getStaff_id()));
        sex.setText(employee.getGender());



        String actualSex = String.valueOf(sex.getText());
        if(actualSex.equals("Male")){
            maleProfilePhoto.setVisible(true);
            femaleProfilePhoto.setVisible(false);
        }
        if(actualSex.equals("Female")){
            maleProfilePhoto.setVisible(false);
            femaleProfilePhoto.setVisible(true);
        }
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
        Stage stage = (Stage) myLeaveDays.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/my_leave_days.fxml"));
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
