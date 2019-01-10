package com.example.alex.droid;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

// FORM POUR MODIFIER UNE TACHE, OU LA SUPPRIMER

public class Modify extends AppCompatActivity implements TimeFragment.OnCompleteListener,DateFragment.OnCompleteDateListener{

    public String time;
    public String myDate;

    private TextView nom;
    private TextView desc;
    private TextView lieu;
    private RadioGroup theme;
    private RadioGroup statut;
    private RadioGroup prio;
    private TextView date;
    private TextView dead;
    private RadioGroup frequence;
    private DatePicker datepicker;
    private DatePicker deadlinepicker;
    private Button dateButton;
    private Button timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);


        Calendar c = Calendar.getInstance();

        this.dateButton = findViewById(R.id.boutonDateTest);
        this.timeButton = findViewById(R.id.timeButton);

        this.dateButton.setText(c.get(Calendar.DAY_OF_MONTH)+":"+(c.get(Calendar.MONTH)+1)+":"+c.get(Calendar.YEAR));
        this.timeButton.setText(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));


        //TODO Faire un "switch" pour savoir si on veut une deadline ou non, afficher le Datepicker ou non en fonction

        /*this.nom=(EditText)findViewById(R.id.modifyNomInput);
        /*desc=findViewById(R.id.modifyDescInput);
        desc.clearComposingText();
        lieu=findViewById(R.id.modifyLieuInput);
        lieu.clearComposingText();
        theme=findViewById(R.id.radioTheme);
        theme.clearCheck();
        statut=findViewById(R.id.radioStatut);
        statut.clearCheck();
        prio=findViewById(R.id.radioPriorite);
        prio.clearCheck();
        date=findViewById(R.id.modifyDateInput);
        date.clearComposingText();
        dead=findViewById(R.id.modifyDeadInput);
        dead.clearComposingText();
        frequence=findViewById(R.id.modifyFrequenceInput);
        frequence.clearCheck();*/
    }

    public void onClickHome(View v) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    // TEST SI LES PARAMÈTRES SONT NULL, SINON ON CRÉÉ LA TACHE AVEC ET ON LA MET EN BDD ET ON RETOURNE A HOME
    // avec le nom comme seul paramètre obligatoire pour l'instant
    // du coup oui tu dois transformer les string en dates et faire un try/catch pour voir si sa passe en BDD


    public void onClickValider(View v) {
        Tache t = new Tache();

        if (nom.getText() != "") {
            t.setNom(nom.getText().toString());
        } else {
            // afficher un message d'erreur
        }
        t.setDescription(desc.getText().toString());
        t.setLieu(lieu.getText().toString());

        // et il faut transformer les radio bouttons en enun avec des switch mais fais date en prio

        // a toi boby pour date et deadline :

        //t.setDate(date....);
        //t.setDeadline(dead....);

        // on met en BDD
        try {

        } catch (Exception e) {

        }

        // retour Home
        onClickHome(v);
    }

    public void onClickDateButton(View v) {

        DialogFragment newFragment = new DateFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }


    public void onClickTimeButton(View v){
        TimeFragment newFrag = new TimeFragment();
        newFrag.show(getSupportFragmentManager(),"Time picker");
    }

    @Override
    public void onComplete(String time) {
        this.time = time;
        this.timeButton.setText(time);

    }

    @Override
    public void onCompleteDate(String date){
        this.myDate = date;
        this.dateButton.setText(date);
    }
}
