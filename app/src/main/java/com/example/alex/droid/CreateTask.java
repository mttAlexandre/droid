package com.example.alex.droid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

// FORM POUR MODIFIER UNE TACHE, OU LA SUPPRIMER

public class CreateTask extends AppCompatActivity implements TimeFragment.OnCompleteListener,DateFragment.OnCompleteDateListener,DeadlineFragment.OnCompleteDeadlineListener {

    public String time;
    public String myDate;
    public String deadline;

    private EditText nom;
    private EditText desc;
    private EditText lieu;
    private RadioGroup theme;
    private RadioGroup statut;
    private RadioGroup prio;
    private RadioGroup frequence;
    private Button dateButton;
    private Button timeButton;
    private Switch deadLineSwitch;
    private Button deadLineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);


        Calendar c = Calendar.getInstance();

        this.dateButton = findViewById(R.id.boutonDateTest);
        this.timeButton = findViewById(R.id.timeButton);
        this.deadLineButton = findViewById(R.id.deadlineButton);
        this.deadLineSwitch = findViewById(R.id.deadlineSwitch);

        this.dateButton.setText(c.get(Calendar.DAY_OF_MONTH)+":"+(c.get(Calendar.MONTH)+1)+":"+c.get(Calendar.YEAR));
        this.timeButton.setText(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
        this.deadLineButton.setText("Clique moi dessus =)");

        this.nom = findViewById(R.id.inputname);
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
        frequence=findViewById(R.id.radioFrequence);
        frequence.clearCheck();
    }

    public void onClickCancel(View v) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    public void onClickSaveTask(View v){
        Tache t = new Tache();

        if (nom.getText().toString() != "") {
            t.setNom(nom.getText().toString());

            if(this.time != null && this.myDate!=null) {
                t.setTaskDate(myDate);
                t.setTaskTime(time);
                t.setDescription(this.desc.getText().toString());

                if (deadLineSwitch.isEnabled()) {
                    t.setTaskDeadline(deadline);
                } else {
                    deadline = null;
                }

                String statut = null;
                RadioButton statutchecked = findViewById(this.statut.getCheckedRadioButtonId());
                if (statutchecked != null) {
                    statut = statutchecked.getText().toString();
                    if (statut == "done")
                        t.setStatut(Tache.Statut.done);
                    else
                        t.setStatut(Tache.Statut.todo);
                }


                String theme = null;
                RadioButton themechecked = findViewById(this.theme.getCheckedRadioButtonId());
                if (themechecked != null){
                    theme = themechecked.getText().toString();
                    switch (theme) {
                        case "Travail":
                            t.setTheme(Tache.Theme.travail);
                            break;
                        case "Maison":
                            t.setTheme(Tache.Theme.maison);
                            break;
                        case "Course":
                            t.setTheme(Tache.Theme.course);
                            break;
                       case "Famille":
                            t.setTheme(Tache.Theme.famille);
                            break;
                        case "RDV":
                            t.setTheme(Tache.Theme.rdv);
                            break;
                    }
                }

                String freq = null;
                RadioButton freqchecked = findViewById(this.frequence.getCheckedRadioButtonId());
                if(freqchecked != null){
                    freq = freqchecked.getText().toString();
                    switch (freq) {
                        case "Haute":t.setPriorite(Tache.Priorite.high);
                            break;
                        case "Moyenne":t.setPriorite(Tache.Priorite.medium);
                            break;
                        case "Basse":t.setPriorite(Tache.Priorite.low);
                            break;
                    }
                }



                Intent data = new Intent();
                data.putExtra("CreatedTask",t);
                setResult(2,data);
                finish();
            }
            else
            {
                Toast.makeText(this,"Il faut choisir date & heure",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Donnez un nom a la tache",Toast.LENGTH_SHORT).show();
        }





        // et il faut transformer les radio bouttons en enun avec des switch mais fais date en prio
    }

    public void onClickDateButton(View v) {

        DialogFragment newFragment = new DateFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }


    public void onClickTimeButton(View v){
        TimeFragment newFrag = new TimeFragment();
        newFrag.show(getSupportFragmentManager(),"Time picker");
    }

    public void onClickDeadlineSwitch(View v){
        if(this.deadLineSwitch.isChecked())
            this.deadLineButton.setVisibility(View.VISIBLE);
        else
            this.deadLineButton.setVisibility(View.GONE);
    }

    public void onClickDeadlineButton(View v){
        DialogFragment newFragment = new DeadlineFragment();
        newFragment.show(getSupportFragmentManager(),"deadlinepicker");
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

    @Override
    public void onCompleteDeadline(String deadline) {
        this.deadline = deadline;
        this.deadLineButton.setText(deadline);
    }
}
