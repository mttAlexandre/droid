package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


// AFFICHAGE TACHE, POSSIBILITÉ DE LA MODIFIER, PARTAGER, SUPPRIMER

public class Detail extends Activity{


    private Boolean modified = false;
    private Tache mytask;
    private TextView nom;
    private TextView description;
    private TextView lieu;
    private TextView date;
    private TextView deadline;
    private TextView deadlineLabel;
    private TextView priorite;
    private TextView frequence;
    private TextView statut;
    private TextView theme;
    private TextView timeTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        this.nom = findViewById(R.id.taskname);
        this.description = findViewById(R.id.desctask);
        this.lieu = findViewById(R.id.taskplace);
        this.date = findViewById(R.id.taskdate);
        this.deadline = findViewById(R.id.taskdeadline);
        this.statut = findViewById(R.id.detailstatut);
        this.theme = findViewById(R.id.detailTheme);
        this.priorite = findViewById(R.id.detailPrio);
        this.frequence = findViewById(R.id.detailFrequence);
        this.deadlineLabel = findViewById(R.id.deadlineLabel);
        this.timeTextView = findViewById(R.id.timeTextView);


        mytask = (Tache) getIntent().getSerializableExtra("Tache");



        this.nom.setText(mytask.getNom());
        this.description.setText(mytask.getDescription());
        this.lieu.setText(mytask.getLieu());
        this.date.setText(mytask.getTaskDate());
        if(mytask.getTaskDeadline() == null) {
            this.deadline.setText(null);
            this.deadlineLabel.setText("Pas de deadline");
        }
        else
        {
            this.deadline.setText(mytask.getTaskDeadline());
        }
        this.timeTextView.setText(mytask.getTaskTime());

        if(mytask.getTaskDeadline() == null)
            this.deadlineLabel.setVisibility(View.GONE);


        if(mytask.getPriorite() != null)
            if(mytask.getPriorite() == Tache.Priorite.high)
                priorite.setText("Haute");
            else if(mytask.getPriorite() == Tache.Priorite.medium)
                priorite.setText("Moyenne");
            else
                priorite.setText("Basse");

        if(mytask.getStatut() != null)
            if(mytask.getStatut() == Tache.Statut.done)
                statut.setText("Done");
            else
                statut.setText("To do");

        if(mytask.getTheme() != null)
            if(mytask.getTheme() == Tache.Theme.course)
                theme.setText("Course");
            else if(mytask.getTheme() == Tache.Theme.famille)
                theme.setText("Famille");
            else if(mytask.getTheme() == Tache.Theme.maison)
                theme.setText("Maison");
            else if (mytask.getTheme() == Tache.Theme.travail)
                theme.setText("Travail");
            else
                theme.setText("Rdv");


    }

    public void onClickHome(View v){
        finish();
    }

    public void onClickModify(View v ){
        Intent intent = new Intent(this,TaskModify.class);
        intent.putExtra("modifyTask",mytask);
        startActivity(intent);

    }

}
