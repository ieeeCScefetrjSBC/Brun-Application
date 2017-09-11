package com.ramoieee.wolfbyte.brunapplication;

public class UserInfo {

    public String Ano;
    public String Birth;
    public String Gender;
    public String Matricula;
    public String fav_area;

    public UserInfo (String Ano, String Birth, String Gender, String Matricula, String fav_area) {
        this.Ano = Ano;
        this.Birth = Birth;
        this.Gender = Gender;
        this.Matricula = Matricula;
        this.fav_area = fav_area;
    }

    public UserInfo (){
        this.Ano = "";
        this.Birth = "";
        this.Gender = "";
        this.Matricula = "";
        this.fav_area = "";
    }
}