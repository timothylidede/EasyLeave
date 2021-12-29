package savala.easyleave.models;

import javafx.scene.control.Button;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

public class ViewApplication {
    String first_name,last_name,leaveStart,leaveEnd,leaveDays, leaveType,leaveReason,leaveStatus;
    Button accept,reject;
    int employee,staff,dept;
    Date start_date;
    //staff=leave_id....change later please

    public ViewApplication( int employee,int staff,int dept,String first_name,String last_name,String leaveStart, String leaveEnd, String leaveDays, String leaveType, String leaveReason, String leaveStatus,Button accept,Button reject,Date start_date) {
        this.employee=employee;
        this.last_name=last_name;
        this.staff=staff;
        this.start_date=start_date;
        this.dept=dept;
        this.first_name=first_name;
        this.leaveStart = leaveStart;
        this.leaveEnd = leaveEnd;
        this.leaveDays = leaveDays;
        this.leaveType = leaveType;
        this.leaveStatus = leaveStatus;
        this.leaveReason=leaveReason;
        this.accept= new Button("Accept");
        this.reject=new Button("Reject");
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public LocalDate addDaysSkippingWeekends(Date date, int days) {
        LocalDate result = start_date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLeaveStart() {
        return leaveStart;
    }

    public void setLeaveStart(String leaveStart) {
        this.leaveStart = leaveStart;
    }

    public String getLeaveEnd() {
        return leaveEnd;
    }

    public void setLeaveEnd(String leaveEnd) {
        this.leaveEnd = leaveEnd;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(String leaveDays) {
        this.leaveDays = leaveDays;
    }

    public Button getAccept() {
        return accept;
    }

    public void setAccept(Button accept) {
        this.accept = accept;
    }

    public Button getReject() {
        return reject;
    }

    public void setReject(Button reject) {
        this.reject = reject;
    }

}
