package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


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

        Tache t = (Tache) getIntent().getSerializableExtra("Tache");
        mytask = t;
        this.nom.setText(t.getNom());
        this.description.setText(t.getDescription());
        this.lieu.setText(t.getLieu());
        this.date.setText(t.getTaskDate());

        if(t.getTaskDeadline() == null)
            this.deadlineLabel.setVisibility(View.GONE);


        if(t.getPriorite() != null)
            if(t.getPriorite() == Tache.Priorite.high)
                priorite.setText("Haute");
            else if(t.getPriorite() == Tache.Priorite.medium)
                priorite.setText("Moyenne");
            else
                priorite.setText("Basse");

        if(t.getStatut() != null)
            if(t.getStatut() == Tache.Statut.done)
                statut.setText("Done");
            else
                statut.setText("To do");

        if(t.getTheme() != null)
            if(t.getTheme() == Tache.Theme.course)
                theme.setText("Course");
            else if(t.getTheme() == Tache.Theme.famille)
                theme.setText("Famille");
            else if(t.getTheme() == Tache.Theme.maison)
                theme.setText("Maison");
            else if (t.getTheme() == Tache.Theme.travail)
                theme.setText("Travail");
            else
                theme.setText("Rdv");






    }

    public void onClickHome(View v){
        finish();
    }

    public void onClickModify(View v ){
        modified = true;
        Intent intent = new Intent(this,TaskModify.class);
        intent.putExtra("modifyTask",mytask);
        startActivity(intent);

    }

}
