package com.example.alex.droid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

// FORM POUR MODIFIER UNE TACHE, OU LA SUPPRIMER

public class CreateTask extends AppCompatActivity implements TimeFragment.OnCompleteListener,DateFragment.OnCompleteDateListener{

    public String time;
    public String myDate;

    public String deadline;

    private TextView nom;
    private TextView desc;
    private TextView lieu;
    private RadioGroup theme;
    private RadioGroup statut;
    private RadioGroup prio;
    private TextView date;
    private TextView dead;
    private RadioGroup frequence;
    private Button dateButton;
    private Button timeButton;
    private Switch deadLineSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);


        Calendar c = Calendar.getInstance();

        this.dateButton = findViewById(R.id.boutonDateTest);
        this.timeButton = findViewById(R.id.timeButton);

        this.dateButton.setText(c.get(Calendar.DAY_OF_MONTH)+":"+(c.get(Calendar.MONTH)+1)+":"+c.get(Calendar.YEAR));
        this.timeButton.setText(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));


        //TODO Faire un "switch" pour savoir si on veut une deadline ou non, afficher le Datepicker ou non en fonction
        this.deadLineSwitch = findViewById(R.id.deadlineSwitch);

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

    public void onClickCancel(View v) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    public void onClickSaveTask(View v){
        Tache t = new Tache();

        if (nom.getText() != "") {
            t.setNom(nom.getText().toString());
        } else {
            Toast.makeText(this,"Donnez un nom a la tache",Toast.LENGTH_SHORT).show();
        }
        t.setDescription(desc.getText().toString());
        t.setLieu(lieu.getText().toString());


        if(this.time != null && this.myDate!=null){
            t.setTaskDate(myDate);
            t.setTaskTime(time);

            if(deadLineSwitch.isEnabled()){
                t.setTaskDeadline(deadline);
            }
            else
            {
                deadline = null;
            }
        }
        else
        {
            Toast.makeText(this,"Il faut choisir date & heure",Toast.LENGTH_SHORT).show();
        }


        // et il faut transformer les radio bouttons en enun avec des switch mais fais date en prio


        try {

            //Mettre en bdd
        } catch (Exception e) {

        }

        // retour Home
        onClickCancel(v);
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
