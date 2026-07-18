package interview.StreamInterview.questions;

public class Employees {
    String name;
    String sirname;
    String gender;
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Employees [name=" + name + ", sirname=" + sirname + ", gender=" + gender + "]";
    }
    public Employees(String name, String sirname, String gender) {
        this.name = name;
        this.sirname = sirname;
        this.gender = gender;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSirname() {
        return sirname;
    }
    public void setSirname(String sirname) {
        this.sirname = sirname;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
