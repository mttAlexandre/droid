package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// AFFICHAGE TACHE, POSSIBILITÉ DE LA MODIFIER, PARTAGER, SUPPRIMER

public class Detail extends Activity{
    private long id;
    private Tache mytask;
    private TextView nom;
    private TextView description;
    private TextView lieu;
    private TextView date;
    private TextView deadline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        this.nom = findViewById(R.id.taskname);
        this.description = findViewById(R.id.desctask);
        this.lieu = findViewById(R.id.taskplace);
        this.date = findViewById(R.id.taskdate);
        this.deadline = findViewById(R.id.taskdeadline);
        Tache t = (Tache) getIntent().getSerializableExtra("Tache");
        this.nom.setText(t.getNom());
        this.description.setText(t.getDescription());
        this.lieu.setText(t.getLieu());
        this.date.setText(t.getDate().toString());
        this.deadline.setText(t.getDeadline().toString());
        //Après on verra bismila
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

}
