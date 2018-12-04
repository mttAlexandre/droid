package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

// FORM POUR MODIFIER UNE TACHE, OU LA SUPPRIMER

public class Modify extends Activity {

    private TextView nom;
    private TextView desc;
    private TextView lieu;
    private RadioGroup theme;
    private RadioGroup statut;
    private RadioGroup prio;
    private TextView date;
    private TextView dead;
    private RadioGroup frequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);

        nom=findViewById(R.id.modifyNomInput);
        nom.clearComposingText();
        desc=findViewById(R.id.modifyDescInput);
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
        frequence.clearCheck();
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    // TEST SI LES PARAMÈTRES SONT NULL, SINON ON CRÉÉ LA TACHE AVEC ET ON LA MET EN BDD ET ON RETOURNE A HOME
    // avec le nom comme seul paramètre obligatoire pour l'instant
    // du coup oui tu dois transformer les string en dates et faire un try/catch pour voir si sa passe en BDD
    public void onClickValider(View v){
        Tache t=new Tache();

        if(nom.getText()!=""){
            t.setNom(nom.getText().toString());
        }else{
            // afficher un message d'erreur
        }
        t.setDescription(desc.getText().toString());
        t.setLieu(lieu.getText().toString());

        // et il faut transformer les radio bouttons en enun avec des switch mais fais date en prio

        // a toi boby pour date et deadline :
        //t.setDate(date....);
        //t.setDeadline(dead....);

        // on met en BDD
        try{

        }catch (Exception e){

        }

        // retour Home
        onClickHome(v);
    }
}
