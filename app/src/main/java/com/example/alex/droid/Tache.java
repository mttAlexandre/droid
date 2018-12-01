package com.example.alex.droid;

import java.util.Date;

public class Tache { //FORME DE LA FREQUENCE A REVOIR
    private long id;
    private String nom;
    private String description;
    private String lieu;
    private Date date;
    private Date deadline;
    enum Statu {
        todo,
        done
    }
    private Statu statu;
    enum Priorite {
        high,
        medium,
        low
    }
    private Priorite priorite;
    enum Theme {
        travail,
        maison,
        course,
        famille,
        rdv
    }
    private Theme theme;
    private Date frequence;
    private boolean checked=false;


    public Tache() {

    }

    public Tache(String nom){
        this.nom=nom;
    }

    public Tache(String nom, String description, Theme theme, String lieu, Date date, Date deadline, Statu statu, Priorite priorite, Date frequence){
        this.nom=nom;
        this.description=description;
        this.theme=theme;
        this.lieu=lieu;
        this.date=date;
        this.deadline=deadline;
        this.statu=statu;
        this.priorite=priorite;
        this.frequence=frequence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getDate(){
        return this.date;
    }
}

