package com.example.alex.droid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import static android.view.Gravity.AXIS_PULL_BEFORE;

// GAGA TU TE DEBROUILLES POUR ÇA ;)

public class CalendarTask extends Activity {

    private java.util.Calendar cal = java.util.Calendar.getInstance(Locale.FRANCE);
    private Context cont = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        if(savedInstanceState != null && savedInstanceState.containsKey("calendarInMilli"))
            cal.setTimeInMillis(savedInstanceState.getLong("calendarInMilli"));

        makeCalendar();
    }

    private void makeCalendar(){
        Calendar mcal = (Calendar) cal.clone();
        mcal.set(Calendar.DAY_OF_MONTH, 1);
        int compteurJour = 1;

        TableLayout lay = findViewById(R.id.layoutButton);
        for(int j = 0; j < 6; j++){

            int dayMax = mcal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
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
                    switch(mcal.get(java.util.Calendar.DAY_OF_WEEK)){
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

                        tr.addView(b);
                    }

                    for(int i = firstDay; i < 7; i++){
                        Button b = new Button(cont);

                        mcal.set(java.util.Calendar.DAY_OF_MONTH, compteurJour);
                        compteurJour++;

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        b.setText("" + mcal.get(java.util.Calendar.DAY_OF_MONTH));
                        b.setGravity(AXIS_PULL_BEFORE);
                        b.setSingleLine(true);
                        b.setPadding(5, 0, 0, 0);
                        b.setTextSize(10);

                        //b.setEllipsize(TextUtils.TruncateAt.END);



                        b.setBackgroundResource(R.drawable.button_border);

                        tr.addView(b);
                    }

                }
                else if(j == 4 || j == 5){



                    for(int i = 0; i < parcours; i++){
                        Button b = new Button(cont);

                        mcal.set(java.util.Calendar.DAY_OF_MONTH, compteurJour);
                        compteurJour++;

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        b.setText("" + mcal.get(java.util.Calendar.DAY_OF_MONTH));
                        b.setGravity(AXIS_PULL_BEFORE);
                        b.setSingleLine(true);
                        b.setPadding(5, 0, 0, 0);
                        b.setTextSize(10);

                        //b.setEllipsize(TextUtils.TruncateAt.END);



                        b.setBackgroundResource(R.drawable.button_border);

                        tr.addView(b);
                    }

                    for(int i = parcours; i < 7; i++){
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

                        tr.addView(b);
                    }
                }
                else {
                    for (int i = 0; i < 7; i++) {
                        Button b = new Button(cont);

                        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, (float) 0.14));
                        b.setId(i);
                        mcal.set(java.util.Calendar.DAY_OF_MONTH, compteurJour);
                        compteurJour++;
                        b.setText("" + mcal.get(java.util.Calendar.DAY_OF_MONTH));
                        b.setGravity(AXIS_PULL_BEFORE);
                        b.setSingleLine(true);
                        b.setPadding(5, 0, 0, 0);
                        b.setTextSize(10);
                        //b.setEllipsize(TextUtils.TruncateAt.END);

                        b.setBackgroundResource(R.drawable.button_border);

                        tr.addView(b);

                    }
                }
            }
        }
    }

    private String getMonthOnString(int idMonth){
        String month;
        switch (idMonth){
            case 0:
            default:
                month = "Janvier";
                break;
            case 1:
                month = "Février";
                break;
            case 2:
                month = "Mars";
                break;
            case 3:
                month = "Avril";
                break;
            case 4:
                month = "Mai";
                break;
            case 5:
                month = "Juin";
                break;
            case 6:
                month = "Juillet";
                break;
            case 7:
                month = "Août";
                break;
            case 8:
                month = "Septembre";
                break;
            case 9:
                month = "Octobre";
                break;
            case 10:
                month = "Novembre";
                break;
            case 11:
                month = "Décembre";
                break;
        }
        return month;
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putLong("calendarInMilli", cal.getTimeInMillis());
    }
}
