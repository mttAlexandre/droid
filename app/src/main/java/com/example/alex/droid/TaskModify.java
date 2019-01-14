package com.example.alex.droid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TaskModify extends AppCompatActivity implements DateFragment.OnCompleteDateListener,TimeFragment.OnCompleteListener,DeadlineFragment.OnCompleteDeadlineListener{

    private Tache tache;
    private TextView textNom;
    private EditText editTextDescription;
    private EditText editTextAdresse;
    private Button buttonDate;
    private Button buttonTime;
    private RadioGroup radioGroupStatut;
    private RadioGroup radioGroupPriorite;
    private Button buttonDeadline;
    private RadioGroup radioGroupFrequence;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_modify);

        this.tache = (Tache) getIntent().getSerializableExtra("modifyTask");

        this.textNom =findViewById(R.id.modifytaskname);
        this.editTextDescription = findViewById(R.id.modifyinputtextDesc);
        this.editTextAdresse = findViewById(R.id.modifyAdresse);
        this.buttonDate = findViewById(R.id.modifyDateButton);
        this.buttonTime = findViewById(R.id.modifyTimeButton);
        this.radioGroupStatut = findViewById(R.id.modifyGroupStatut);
        this.radioGroupPriorite = findViewById(R.id.modifyPrioriteGroup);
        this.buttonDeadline = findViewById(R.id.modifyDeadlineButton);
        this.radioGroupFrequence = findViewById(R.id.modifyfrequenceGroup);


        this.textNom.setText(tache.getNom());
        this.editTextDescription.setText(tache.getDescription());
        this.editTextAdresse.setText(tache.getLieu());
        this.buttonDate.setText(tache.getTaskDate());
        this.buttonTime.setText(tache.getTaskTime());

        if(tache.getTaskDeadline() != null)
            this.buttonDeadline.setText(tache.getTaskDeadline());
        else
            this.buttonDeadline.setText("Choisir une deadline");
        //TODO Faire les radioButton



    }

    public void onClickSaveTask(View v){
        //TODO Sauvegarde des modifs

    }

    public void onClickCancel(View v){
        finish();
    }

    public void onClickDate(View v){
        DialogFragment newFrag = new DateFragment();
        newFrag.show(getSupportFragmentManager(),"datepicker");
    }

    public void onClickTime(View v){
        TimeFragment newFrag = new TimeFragment();
        newFrag.show(getSupportFragmentManager(),"timepicker");
    }

    public void onClickDeadline(View v){
        DialogFragment newFrag = new DeadlineFragment();
        newFrag.show(getSupportFragmentManager(),"deadlinepicker");
    }

    @Override
    public void onCompleteDate(String date) {
        this.buttonDate.setText(date);
        tache.setTaskDate(date);
    }

    @Override
    public void onCompleteDeadline(String deadline) {
        this.buttonDeadline.setText(deadline);
        tache.setTaskDeadline(deadline);
    }

    @Override
    public void onComplete(String time) {
        this.buttonTime.setText(time);
        tache.setTaskTime(time);
    }
}
