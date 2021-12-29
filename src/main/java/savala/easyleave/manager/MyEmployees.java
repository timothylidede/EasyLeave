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
import savala.easyleave.models.Employee;
import savala.easyleave.models.ViewApplication;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MyEmployees implements Initializable {
    @FXML
    private TableView<Employee> employee_table;

    @FXML
    private TableColumn<Employee, String> staff_id;

    @FXML
    private TableColumn<Employee, String> last_name;

    @FXML
    private TableColumn<Employee, String> first_name;

    @FXML
    private TableColumn<Employee, String> deptName;

    @FXML
    private TableColumn<Employee, String> gender;

    @FXML
    private TableColumn<Employee, String> email;

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
    private ImageView bonusDaysImage;
    Database database=new Database();
    ObservableList<Employee> apList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String verify;
        verify = "SELECT * FROM `tbl_employees` JOIN tbl_departments " +
                "ON tbl_employees.DeptNo =tbl_departments.DepartmentNo " +
                " WHERE DeptNo= '" + ManagerLogin.DeptNo + "' ";
        try{
            Connection connection=database.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(verify);

            while(rs.next()) {
                //labelThis.setText("Congrats");
                apList.add(new Employee(
                        rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("gender"),rs.getString("email"),
                        Integer.parseInt(rs.getString("bonus")),Integer.parseInt(rs.getString("staff_id")),
                        rs.getString("DeptName")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        if(apList!=null) {
            first_name.setCellValueFactory(new PropertyValueFactory<>("fname"));
            last_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
            staff_id.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
            gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            deptName.setCellValueFactory(new PropertyValueFactory<>("deptName"));
            employee_table.setItems(apList);
        }
        else{
//            leave_table.setDisable(true);
//            leave_table.setVisible(false);

        }

    }

    @FXML
    void applicationImage(MouseEvent event) throws IOException {
        Stage stage = (Stage) applicationImage.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/manager_pending_applications.fxml"));
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
    void myAccount(MouseEvent event) throws IOException {
        Stage stage = (Stage) myAccount.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/manager/my_account.fxml"));
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

}
