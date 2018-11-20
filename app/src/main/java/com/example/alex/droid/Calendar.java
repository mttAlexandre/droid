package com.example.alex.droid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.util.Locale;

// GAGA TU TE DEBROUILLES POUR ÇA ;)

public class Calendar extends Activity {

    private java.util.Calendar cal = java.util.Calendar.getInstance(Locale.FRANCE);
    private Context cont = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        if(savedInstanceState != null && savedInstanceState.containsKey("calendarInMilli"))
            cal.setTimeInMillis(savedInstanceState.getLong("calendarInMilli"));

        java.util.Calendar mcal = (java.util.Calendar) cal.clone();

        setTitle("" + getMonthOnString(mcal.get(java.util.Calendar.MONTH)) + " " + mcal.get(java.util.Calendar.YEAR));

        mcal.set(java.util.Calendar.DAY_OF_MONTH, 1);
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
}
