package com.example.alex.droid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentCalendar2 extends Fragment{

    private static final String KEY_TIME_CALENDAR="calendarInMilli";

    private static FragmentCalendar2 myFragment;
    private static Calendar cal;
    private ArrayList<Tache> value;
    private static Context cont;

    public FragmentCalendar2(){}

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public static FragmentCalendar2 newInstance(Context context, Calendar calendar, ArrayList<Tache> val) {

        // 2.1 Create new fragment

        myFragment = new FragmentCalendar2();
        //if(cal != null)

        // 2.2 Create bundle and add it some data

        Bundle args = new Bundle();

        args.putLong(KEY_TIME_CALENDAR, calendar.getTimeInMillis());

        myFragment.setArguments(args);

        cont = context;
        myFragment.setCalendar(calendar);
        myFragment.setValue(val);
        //Log.e("coucou28", "cal : " + cal.get(Calendar.MONTH));

        return(myFragment);

    }

    public void setValue(ArrayList<Tache> val) {
        this.value = val;
    }

    public Calendar getCalendar(){
        return cal;
    }

    public void setCalendar(Calendar calendar){
        cal = calendar;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar_viewpager, container, false);

        //Log.e("coucou4", "cal : " + cal.get(Calendar.MONTH));

        final Calendar mcal = (Calendar) cal.clone();
        mcal.set(Calendar.DAY_OF_MONTH, 1);
        int compteurJour = 1;

        int compteurListe = 0;

        TableLayout lay = (TableLayout) v.findViewById(R.id.layoutButton);

        /*TextView tv = new TextView(cont);
        tv.layout(30, 30, 30, 30);
        tv.setText("test2");

        lay.addView(tv);*/

        for(int j = 0; j < 6; j++){

            int dayMax = mcal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int parcours = dayMax - compteurJour + 1;

            boolean testExe = true;

            if(parcours >= 7)
                parcours = 7;

            if(j == 5 && parcours == 0)
                testExe = false;

            if(testExe){
                TableRow tr = new TableRow(cont);
                tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.MATCH_PARENT, (float) 0.2 ));
                tr.setId(j);
                lay.addView(tr);
                if(j == 0){
                    int firstDay;
                    switch(mcal.get(Calendar.DAY_OF_WEEK)){
                        case 1:
                            firstDay = 6;
                            break;
                        case 2:
                        default:
                            firstDay = 0;
                            break;
                        case 3:
                            firstDay = 1;
                            break;
                        case 4:
                            firstDay = 2;
                            break;
                        case 5:
                            firstDay = 3;
                            break;
                        case 6:
                            firstDay = 4;
                            break;
                        case 7:
                            firstDay = 5;
                            break;
                    }


                    for(int i = 0; i < firstDay; i++){
                        TextView b = new TextView(cont);
                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        b.setText("");
                        //b.setGravity(AXIS_PULL_BEFORE);
                        //b.setSingleLine(true);
                        b.setPadding(5, 0, 0, 0);
                        //b.setTextSize(10);
                        //b.setEllipsize(TextUtils.TruncateAt.END);
                        b.setBackgroundResource(R.drawable.no_button);

                        //b.setClickable(false);
                        tr.addView(b);
                    }

                    for(int i = firstDay; i < 7; i++){
                        Button b = new CalendarButton(cont);

                        mcal.set(Calendar.DAY_OF_MONTH, compteurJour);
                        compteurJour++;

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        String resText = "";
                        Boolean test = true;
                        while(test && compteurListe < value.size()){
                            String [] breakDate = value.get(compteurListe).getTaskDate().split("/");
                            if(Integer.parseInt(breakDate[0]) == mcal.get(Calendar.DAY_OF_MONTH)) {
                                resText += "<p>" + value.get(compteurListe).getNom() + "</p>";
                                compteurListe++;
                            }
                            else{
                                test = false;
                            }
                        }

                        b.setText( Html.fromHtml("<p>"+mcal.get(Calendar.DAY_OF_MONTH)+"</p>"+resText));
                        //b.setGravity(AXIS_PULL_BEFORE);
                        b.setPadding(5, 0, 0, 0);
                        b.setTextSize(10);

                        final int finalCompteurJour = compteurJour;
                        b.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                Intent intent = new Intent(cont, Home.class);
                                intent.putExtra("daySelected", "" + (finalCompteurJour-1) +"/"+(mcal.get(Calendar.MONTH)+1)+"/"+mcal.get(Calendar.YEAR));
                                startActivity(intent);
                            }
                        });

                        //b.setEllipsize(TextUtils.TruncateAt.END);

                        b.setBackgroundResource(R.drawable.button_border);

                        //b.setClickable(false);
                        tr.addView(b);
                    }

                }
                else if(j == 4 || j == 5){
                    for(int i = 0; i < parcours; i++){
                        Button b = new CalendarButton(cont);

                        mcal.set(Calendar.DAY_OF_MONTH, compteurJour);
                        compteurJour++;

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        String resText = "";
                        Boolean test = true;
                        while(test && compteurListe < value.size()){
                            String [] breakDate = value.get(compteurListe).getTaskDate().split("/");
                            if(Integer.parseInt(breakDate[0]) == mcal.get(Calendar.DAY_OF_MONTH)) {
                                resText += "<p>" + value.get(compteurListe).getNom() + "</p>";
                                compteurListe++;
                            }
                            else{
                                test = false;
                            }
                        }

                        b.setText( Html.fromHtml("<p>"+mcal.get(Calendar.DAY_OF_MONTH)+"</p>"+resText));

                        //b.setGravity(AXIS_PULL_BEFORE);
                        b.setSingleLine(true);
                        b.setPadding(5, 0, 0, 0);
                        b.setTextSize(10);

                        final int finalCompteurJour = compteurJour;
                        b.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                Intent intent = new Intent(cont, Home.class);
                                intent.putExtra("daySelected", "" + (finalCompteurJour-1) +"/"+(mcal.get(Calendar.MONTH)+1)+"/"+mcal.get(Calendar.YEAR));
                                startActivity(intent);
                            }
                        });

                        //b.setEllipsize(TextUtils.TruncateAt.END);



                        b.setBackgroundResource(R.drawable.button_border);

                        //b.setClickable(false);
                        tr.addView(b);
                    }

                    for(int i = parcours; i < 7; i++){
                        TextView b = new TextView(cont);

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        //b.setGravity(AXIS_PULL_BEFORE);
                        //b.setSingleLine(true);
                        b.setPadding(5, 0, 0, 0);
                        //b.setTextSize(10);
                        //b.setEllipsize(TextUtils.TruncateAt.END);
                        b.setBackgroundResource(R.drawable.no_button);
                        b.setText("");
                        //b.setClickable(false);
                        tr.addView(b);
                    }
                }
                else {
                    for (int i = 0; i < 7; i++) {
                        final Button b = (Button) new CalendarButton(cont);

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        mcal.set(java.util.Calendar.DAY_OF_MONTH, compteurJour);
                        compteurJour++;
                        String resText = "";
                        Boolean test = true;
                        while(test && compteurListe < value.size()){
                            String [] breakDate = value.get(compteurListe).getTaskDate().split("/");
                            if(Integer.parseInt(breakDate[0]) == mcal.get(Calendar.DAY_OF_MONTH)) {
                                resText += "<p>" + value.get(compteurListe).getNom() + "</p>";
                                compteurListe++;
                            }
                            else{
                                test = false;
                            }
                        }

                        b.setText( Html.fromHtml("<p>"+mcal.get(Calendar.DAY_OF_MONTH)+"</p>"+resText));

                        final int finalCompteurJour = compteurJour;
                        b.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                Intent intent = new Intent(cont, Home.class);
                                intent.putExtra("daySelected", "" + (finalCompteurJour-1) +"/"+(mcal.get(Calendar.MONTH)+1)+"/"+mcal.get(Calendar.YEAR));
                                startActivity(intent);
                            }
                        });

                        //b.setGravity(AXIS_PULL_BEFORE);
                        b.setPadding(5, 0, 0, 0);
                        b.setTextSize(10);
                        //b.setEllipsize(TextUtils.TruncateAt.END);

                        b.setBackgroundResource(R.drawable.button_border);

                        //b.setClickable(false);

                        tr.addView(b);

                    }
                }
            }
        }



        return v;
    }
}

