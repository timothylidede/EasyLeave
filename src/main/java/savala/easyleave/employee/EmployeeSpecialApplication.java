package savala.easyleave.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import savala.easyleave.models.Manager;
import savala.easyleave.utils.Database;
import savala.easyleave.welcome.Launch;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeSpecialApplication implements Initializable {

    @FXML
    private ImageView enquire;

    @FXML
    private ImageView status;

    @FXML
    private ImageView myAccount;

    @FXML
    private Button back;

    @FXML
    private Label managerName;

    @FXML
    private HBox hBox;

    @FXML
    private Label period;

    @FXML
    private ComboBox specialType;

    @FXML
    private HBox normalApplication;

    @FXML
    private TextArea comment;

    @FXML
    private ImageView myLeaveDays;

    private String type;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList(
                "Maternity Leave", "Paternity Leave", "Compassionate Leave",
                "Sick Leave", "Study Leave", "Unpaid Leave", "Other"
        );
        specialType.setItems(list);

        String manager="SELECT * FROM tbl_managers JOIN tbl_employees " +
                "ON tbl_managers.DeptNo =tbl_employees.DeptNo " +
                " WHERE tbl_employees.staff_id="+EmployeeLogin.loggedUser_id+"";
        Manager managerDetails=null;
        try{
            Connection connection=database.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs=statement.executeQuery(manager);

            while(rs.next()) {
                managerDetails=new Manager(rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("email"), rs.getString("gender"),
                        Integer.parseInt(rs.getString("manager_id"))
                );
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        managerName.setText(managerDetails.getFname()+" "+managerDetails.getLname());
    }

    @FXML
    void specialType(ActionEvent event) {
        type = specialType.getSelectionModel().getSelectedItem().toString();

        if(type.equals("Maternity Leave")){
            period.setText("91 days");
        }
        if(type.equals("Paternity Leave")){
            period.setText("14 days");
        }
        if(type.equals("Compassionate Leave")){
            period.setText("15 days");
        }
        if(type.equals("Sick Leave")){
            period.setText("14 days");
        }
        if(type.equals("Study Leave")){
            period.setText("14 days");
        }
        if(type.equals("Unpaid Leave")){
            period.setText("84 days");
        }
        if(type.equals("Other")){
            period.setText("Unbound");
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
    void normalApplication(MouseEvent event) throws IOException {
        Stage stage = (Stage) normalApplication.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_normal_application.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    void sendApplication(MouseEvent event) throws IOException {
        String leaveType = specialType.getSelectionModel().getSelectedItem().toString();
        String leavePeriod = String.valueOf(period.getText());
        String leaveReason = String.valueOf(comment.getText());

        if(leavePeriod.equals("Unbound") && leaveReason.equals("")){
            Alert alert=new Alert((Alert.AlertType.ERROR));
            alert.setTitle("Leave Application");
            alert.setHeaderText("Missing Fields.");
            alert.setContentText("You must provide a reason.");
            alert.showAndWait();
        }else{
            //put data in db: leaveType, leavePeriod, leaveReason
            String insertFields;
            String insertValues;
            String inserttoRegister;
            int leave_days=0;
            LocalDate leave_enddate;
            LocalDate localDate=LocalDate.now();
            if(!type.equals("Other")){
                leave_days= convertString(period.getText());
                leave_enddate=addDaysSkippingWeekends(localDate,leave_days);
                insertValues = "NULL, '" + EmployeeLogin.loggedUser_id+"','"+leave_enddate+"', '"+leave_days+"', '"+comment.getText()+"', '"+type+"');";
                insertFields = "INSERT INTO `tbl_leavedetails` (`leave_id`, `leave_staff`,`leave_enddate`,`leave_daysrequested`, `leave_comments`,`leave_type`) VALUES (";

            }
            else{
                insertFields = "INSERT INTO `tbl_leavedetails` (`leave_id`, `leave_staff`,`leave_daysrequested`, `leave_comments`,`leave_type`) VALUES (";
                insertValues = "NULL, '" + EmployeeLogin.loggedUser_id+"','"+leave_days+"', '"+comment.getText()+"', '"+type+"');";
            }

            inserttoRegister = insertFields + insertValues;
            Connection connection=database.getConnection();
            try{
                Statement statement =connection.createStatement();
                statement.executeUpdate(inserttoRegister);
                Alert alert=new Alert((Alert.AlertType.INFORMATION));
                alert.setTitle("Leave Application");
                alert.setHeaderText("Success.");
                alert.setContentText("Leave Applied for Successfully.");
                alert.showAndWait();
                //label2.setText("Success!!!");
            } catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    private int convertString(String sample){
        char[] chars = sample.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                sb.append(c);
            } else {
                break;
            }
        }
        String myString = sb.toString();

        return Integer.parseInt(myString);
    }
    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }


    @FXML
    void myAccount(MouseEvent event) throws IOException{
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