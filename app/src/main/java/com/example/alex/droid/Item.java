package com.example.alex.droid;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String nom;
    private boolean checked=false;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom(){
        return nom;
    }

    public boolean getChecked(){
        return checked;
    }

    public void setChecked(boolean c){
        checked=c;
    }

    public static List<Item> tacheToItem(List<Tache> values) {
        List<Item> res = new ArrayList<Item>();


        return res;
    }
}
