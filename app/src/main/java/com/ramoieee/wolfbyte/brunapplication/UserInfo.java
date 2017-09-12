package com.ramoieee.wolfbyte.brunapplication;

public class UserInfo {

    public String Year;
    public String Birth;
    public String Gender;
    public String Matricula;
    public String fav_area;

    public UserInfo (String Year, String Birth, String Gender, String Matricula, String fav_area) {
        this.Year = Year;
        this.Birth = Birth;
        this.Gender = Gender;
        this.Matricula = Matricula;
        this.fav_area = fav_area;
    }

    public UserInfo (){
        this.Year = null;
        this.Birth = null;
        this.Gender = null;
        this.Matricula = null;
        this.fav_area = null;
    }
}