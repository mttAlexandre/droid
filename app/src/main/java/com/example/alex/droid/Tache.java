package com.example.alex.droid;

import java.io.Serializable;
import java.util.Date;

public class Tache implements Serializable { //FORME DE LA FREQUENCE A REVOIR
    private long id;
    private String nom;
    private String description;
    private String lieu;
    private Date date;
    private Date deadline;


    enum Statut {
        todo,
        done
    }
    private Statut statut;
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

    public Tache(String nom, String description, Theme theme, String lieu, Date date, Date deadline, Statut statut, Priorite priorite, Date frequence){
        this.nom=nom;
        this.description=description;
        this.theme=theme;
        this.lieu=lieu;
        this.date=date;
        this.deadline=deadline;
        this.statut = statut;
        this.priorite=priorite;
        this.frequence=frequence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Date getFrequence() {
        return frequence;
    }

    public void setFrequence(Date frequence) {
        this.frequence = frequence;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

