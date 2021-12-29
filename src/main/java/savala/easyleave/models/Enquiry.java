package savala.easyleave.models;

public class Enquiry {
    private String fname, lname,gender, email,deptName,enquiry_message;
    private int staff_id, bonus,enquiry_id,manager_id;

    public Enquiry(String fname, String lname, String gender, String email, String deptName, String enquiry_message, int staff_id, int bonus, int enquiry_id, int manager_id) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.deptName = deptName;
        this.enquiry_message = enquiry_message;
        this.staff_id = staff_id;
        this.bonus = bonus;
        this.enquiry_id = enquiry_id;
        this.manager_id = manager_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEnquiry_message() {
        return enquiry_message;
    }

    public void setEnquiry_message(String enquiry_message) {
        this.enquiry_message = enquiry_message;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getEnquiry_id() {
        return enquiry_id;
    }

    public void setEnquiry_id(int enquiry_id) {
        this.enquiry_id = enquiry_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
