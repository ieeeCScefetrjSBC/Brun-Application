package com.ramoieee.wolfbyte.brunapplication;

public class user_teste {

    public String Ano;
    public String Birth;
    public String Gender;
    public String Matricula;
    public String Name;
    public String fav_area;


    public user_teste() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public user_teste(String Ano, String Birth, String Gender, String Matricula, String Name, String fav_area) {
        this.Ano = Ano;
        this.Birth = Birth;
        this.Gender = Gender;
        this.Matricula = Matricula;
        this.Name = Name;
        this.fav_area = fav_area;
    }

}
