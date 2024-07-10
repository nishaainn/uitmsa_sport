package com.example.uitmsa_sport.entities;

public class Application {

    private String name;
    private Club club;
    private String email;
    private String phoneNo;
    private String programCode;
    private int semester;

    public Application(String name, Club club, String email, String phoneNo, String programCode, int semester) {
        this.name = name;
        this.club = club;
        this.email = email;
        this.phoneNo = phoneNo;
        this.programCode = programCode;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
