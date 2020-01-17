package models;

public class Users {
    private String name;
    private String position;
    private String role;
    private int departmentId;
    private int id;

    public Users(String name, String position, String role, int departmentId){
        this.name = name;
        this.position = position;
        this.role = role;
        this.departmentId = departmentId;
    }

}
