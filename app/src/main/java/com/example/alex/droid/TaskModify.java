package com.example.alex.droid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TaskModify extends Activity {

    private Tache tache;
    private TextView textNom;
    private EditText editTextDescription;
    private Button buttonDate;
    private Button buttonTime;
    private RadioGroup radioGroupStatut;
    private RadioGroup radioGroupPriorite;
    private Button buttonDeadline;
    private RadioGroup radioGroupFrequence;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.tache = null; //Intent blabla

        this.textNom =findViewById(R.id.modifytaskname);
        this.editTextDescription = findViewById(R.id.modifyDescInput);
        this.buttonDate = findViewById(R.id.modifyDateButton);
        this.buttonTime = findViewById(R.id.modifyTimeButton);
        this.radioGroupStatut = findViewById(R.id.modifyGroupStatut);
        this.radioGroupPriorite = findViewById(R.id.modifyPrioriteGroup);
        this.buttonDeadline = findViewById(R.id.modifyDeadlineButton);
        this.radioGroupFrequence = findViewById(R.id.modifyfrequenceGroup);


        this.textNom.setText(tache.getNom());
        this.editTextDescription.setText(tache.getDescription(),null);

    }

    public void onClickSaveTask(View v){
        //DoSmthg

    }

    public void onClickCancel(View v){
        finish();
    }
}
