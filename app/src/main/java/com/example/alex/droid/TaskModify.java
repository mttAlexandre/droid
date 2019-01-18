package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

    private String date,time,deadline;


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

        Tache.Priorite prio = tache.getPriorite();
        if(prio != null){
            if(prio == Tache.Priorite.high)
                radioGroupPriorite.check(R.id.modifyradioHaute);
            else if(prio == Tache.Priorite.medium)
                radioGroupPriorite.check(R.id.modifyradioMoyenne);
            else if(prio == Tache.Priorite.low)
                radioGroupPriorite.check(R.id.modifyradioBasse);
        }

        Tache.Statut statut = tache.getStatut();

        if(statut != null){
            if(statut == Tache.Statut.todo)
                radioGroupStatut.check(R.id.modifyradioToDo);
            else
                radioGroupStatut.check(R.id.modifyradioDone);
        }


        //TODO Faire frequence



    }

    public void onClickSaveTask(View v){

        tache.setDescription(editTextDescription.getText().toString());
        tache.setLieu(editTextAdresse.getText().toString());


        String statut;
        RadioButton statutchecked = findViewById(this.radioGroupStatut.getCheckedRadioButtonId());
        if (statutchecked != null) {
            statut = statutchecked.getText().toString();
            if (statut == "done")
                tache.setStatut(Tache.Statut.done);
            else
                tache.setStatut(Tache.Statut.todo);
        }

        String prio;
        RadioButton priochecked = findViewById(this.radioGroupPriorite.getCheckedRadioButtonId());
        if(priochecked != null){
            prio = priochecked.getText().toString();
            switch(prio){
                case "Haute":tache.setPriorite(Tache.Priorite.high);break;
                case "Moyenne":tache.setPriorite(Tache.Priorite.medium);break;
                case "Basse":tache.setPriorite(Tache.Priorite.low);break;
            }
        }

        String freq;
        RadioButton freqchecked = findViewById(this.radioGroupFrequence.getCheckedRadioButtonId());
        if(freqchecked != null) {
            freq = freqchecked.getText().toString();
            switch (freq) {
                case "Tous les jours":
                    break;
                case "Tous les mois":
                    break;
                case "Toutes les semaines":
                    break;
                case "Tous les ans":
                    break;
            }
        }

        Intent data = new Intent(this,Home.class);
        data.putExtra("modifiedTask",tache);
        startActivity(data);

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
