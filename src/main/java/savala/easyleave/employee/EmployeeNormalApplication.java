package savala.easyleave.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class EmployeeNormalApplication implements Initializable {

    @FXML
    private ImageView myAccount;

    @FXML
    private ImageView enquire;

    @FXML
    private ImageView status;

    @FXML
    private Button apply;

    @FXML
    private Button calculateDays;

    @FXML
    private ImageView myLeaveDays;

    @FXML
    private Button back;

    @FXML
    private TextArea comment;

    @FXML
    private DatePicker endDate;

    @FXML
    private HBox hBox;

    @FXML
    private Label managerName;

    @FXML
    private Label numberOfDays;

    @FXML
    private HBox specialApplication;

    @FXML
    private DatePicker startDate;

    private int startDayOfMonth, startMonth, startYear;
    private int endDayOfMonth, endMonth, endYear;
    private long daysBetween = 0;

    Database database = new Database();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(this.getClass().getResource("applica.png").toString()));

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
    void sendApplication(MouseEvent event) throws IOException {
        int daysRequested = Integer.parseInt(numberOfDays.getText());
        String commentForLeave = String.valueOf(comment.getText());
        boolean eligible=true;

        if(daysRequested == 0){
            eligible=false;
            Alert alert=new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Leave Application");
            alert.setHeaderText("Invalid Input.");
            alert.setContentText("Enter valid dates or ensure your leave days are not zero.");
            alert.showAndWait();
        }
        if(daysRequested < 0){
            eligible=false;
            Alert alert=new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Leave Application");
            alert.setHeaderText("Invalid Input.");
            alert.setContentText("Enter valid dates.");
            alert.showAndWait();
        }
        if (daysRequested > 28){
            eligible=false;
            Alert alert=new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Leave Application");
            alert.setHeaderText("Invalid Input.");
            alert.setContentText("Sorry. You cannot have more than 28 days worth of leave.");
            alert.showAndWait();


            daysBetween = 0;
            numberOfDays.setText(String.valueOf(daysBetween));
        }
        int remaining_days=checkDays();
        if(daysRequested>remaining_days){
            eligible=false;
            Alert alert=new Alert((Alert.AlertType.WARNING));
            alert.setTitle("Leave Application.");
            alert.setHeaderText("Invalid Input.");
            alert.setContentText("Sorry. You cannot request for more than available leave days.");
            alert.showAndWait();

        }

        //proceed to update db, (normal_leave_details): daysRequested and comment
        if(eligible) {
            String stringDaysRequested = "" + daysRequested + " days";
            String exists;
            exists = "SELECT COUNT(1) FROM `tbl_leavedetails` WHERE leave_staff= '" + EmployeeLogin.loggedUser_id + "'";
            try {
                Connection conn = database.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(exists);

                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        //Account has already applied for a leave.

                        Dialog<String> dialog = new Dialog<String>();
                        dialog.setTitle("Failed!");
                        dialog.setContentText("You Cannot Apply for more than one Leave.");
                        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                        dialog.showAndWait();

                    } else {
                        String insertFields;
                        String insertValues;
                        String inserttoRegister;
                        int daysreq=convertString(stringDaysRequested);

                        insertFields = "INSERT INTO `tbl_leavedetails` (`leave_id`, `leave_staff`,`leave_startdate`,`leave_enddate`, `leave_daysrequested`, `leave_comments`) VALUES (";
                        insertValues = "NULL, '" + EmployeeLogin.loggedUser_id + "', '" + startDate.getValue() + "', '" + endDate.getValue() + "', '" + daysreq + "', '" + commentForLeave + "');";
                        inserttoRegister = insertFields + insertValues;

                        try {
                            Connection connection = database.getConnection();
                            Statement statement2 = connection.createStatement();
                            statement2.executeUpdate(inserttoRegister);
                            Alert alert=new Alert((Alert.AlertType.INFORMATION));
                            alert.setTitle("Leave Application");
                            alert.setHeaderText("Success.");
                            alert.setContentText("Leave Applied for Successfully.");
                            alert.showAndWait();

                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }
    }

    @FXML
    void specialApplication(MouseEvent event) throws IOException {
        Stage stage = (Stage) specialApplication.getScene().getWindow();
        FXMLLoader root = new FXMLLoader(Launch.class.getResource("/savala/easyleave/employee/employee_special_application.fxml"));
        Scene scene = new Scene(root.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
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

    @FXML
    void calculateDays(MouseEvent event) throws IOException {
        try {
            startDayOfMonth = startDate.getValue().getDayOfMonth();
            startMonth = startDate.getValue().getMonthValue();
            startYear = startDate.getValue().getYear();

            endDayOfMonth = endDate.getValue().getDayOfMonth();
            endMonth = endDate.getValue().getMonthValue();
            endYear = endDate.getValue().getYear();

            LocalDate beginDate = LocalDate.of(startYear, startMonth, startDayOfMonth);
            LocalDate finishDate = LocalDate.of(endYear, endMonth, endDayOfMonth);

            daysBetween = ChronoUnit.DAYS.between(beginDate, finishDate);

            if(daysBetween < 0){
                Alert alert=new Alert((Alert.AlertType.ERROR));
                alert.setTitle("Leave Application.");
                alert.setHeaderText("Invalid Input.");
                alert.setContentText("Enter valid dates.");
                alert.showAndWait();

                daysBetween = 0;
                numberOfDays.setText(String.valueOf(daysBetween));
            }
            if (daysBetween < 7) {
                daysBetween = daysBetween + 0;
            }
            if (daysBetween >= 7 && daysBetween < 14) {
                daysBetween = daysBetween - 2;
            }
            if (daysBetween >= 14 && daysBetween < 21) {
                daysBetween = daysBetween - 4;
            }
            if (daysBetween >= 21 && daysBetween < 28) {
                daysBetween = daysBetween - 6;
            }
            if (daysBetween > 28){
                Alert alert=new Alert((Alert.AlertType.WARNING));
                alert.setTitle("Leave Application.");
                alert.setHeaderText("Invalid Input.");
                alert.setContentText("Sorry. You cannot have more than 28 days worth of leave");
                alert.showAndWait();

//                javafx.scene.control.Dialog<String> dialog=new javafx.scene.control.Dialog<String>();
//                dialog.setTitle("Invalid input");
//                dialog.setContentText("Sorry. You cannot have more than 28 days worth of leave");
//                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
//                dialog.showAndWait();

                daysBetween = 0;
                numberOfDays.setText(String.valueOf(daysBetween));
            }

            numberOfDays.setText(String.valueOf(daysBetween));
            daysBetween = 0;

        }catch (NullPointerException e){
            Alert alert=new Alert((Alert.AlertType.ERROR));
            alert.setTitle("Leave Application.");
            alert.setHeaderText("Invalid Input.");
            alert.setContentText("Kindly make sure you have picked both dates");
            alert.showAndWait();

        }

    }
    private int checkDays(){
        Connection connection=database.getConnection();
        String id;
        String staff_days,bonus;
        int available_days=0,bonus_days=0;
        id="SELECT * FROM `tbl_employees` WHERE staff_id=\"" +EmployeeLogin.loggedUser_id+"\"";
        staff_days="leave_daysremaining";
        bonus="bonus";
        try{
            Statement statement2= connection.createStatement();
            ResultSet rs= statement2.executeQuery(id);
            while(rs.next()) {
                available_days=rs.getInt(staff_days);
                bonus_days=rs.getInt(bonus);
            }
        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return (available_days+bonus_days);
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
}