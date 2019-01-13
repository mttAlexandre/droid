package com.example.alex.droid;

import java.io.Serializable;
import java.util.Date;

public class Tache implements Serializable { //FORME DE LA FREQUENCE A REVOIR
    private long id;
    private String nom;
    private String description;
    private String lieu;
    private String taskDate;
    private String taskTime;
    private String taskDeadline;

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }




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

    /*public Tache(String nom, String description, Theme theme, String lieu, Date date, Date deadline, Statut statut, Priorite priorite, Date frequence){
        this.nom=nom;
        this.description=description;
        this.theme=theme;
        this.lieu=lieu;
        this.date=date;
        this.deadline=deadline;
        this.statut = statut;
        this.priorite=priorite;
        this.frequence=frequence;
    }*/

    public Tache(String nom, String description, String lieu, String taskDate, String taskTime, String taskDeadline, Statut statut, Priorite priorite, Theme theme, Date frequence) {
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.taskDeadline = taskDeadline;
        this.statut = statut;
        this.priorite = priorite;
        this.theme = theme;
        this.frequence = frequence;
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

