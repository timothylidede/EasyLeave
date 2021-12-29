package savala.easyleave.models;

public class Manager {
    private String fname, lname, email,gender;
    private int manager_id;

    public Manager(String fname, String lname, String email, String gender, int manager_id) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.manager_id = manager_id;
        this.gender=gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
