package savala.easyleave.models;

import javafx.scene.control.Button;

public class Application {
    String leaveStart,leaveEnd,leaveDays, leaveType,leaveStatus;
    Button accept,reject;

    public Application( String leaveStart, String leaveEnd, String leaveDays, String leaveType, String leaveStatus) {
        this.leaveStart = leaveStart;
        this.leaveEnd = leaveEnd;
        this.leaveDays = leaveDays;
        this.leaveType = leaveType;
        this.leaveStatus = leaveStatus;
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
