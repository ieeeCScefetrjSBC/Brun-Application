package com.ramoieee.wolfbyte.brunapplication;

/**
 * Created by Felipe on 25/09/17.
 */

public class ListData {
    String name;
    boolean isOn;

    public ListData(String name, boolean isOn){
        this.name = name;
        this.isOn = isOn;
    }

    public ListData(){
        this.name = "";
        this.isOn = false;
    }
}