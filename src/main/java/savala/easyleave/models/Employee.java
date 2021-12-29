package savala.easyleave.models;

public class Employee {
    private String fname, lname,gender, email,deptName;
    private int staff_id, bonus;

    public Employee(String fname, String lname, String gender, String email, int bonus, int staff_id,String deptName) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.staff_id = staff_id;
        this.deptName=deptName;
        this.bonus=bonus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
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

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }
}
