package com.example.alex.droid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DateFragmentForCalendar extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog((CalendarTask)getActivity(), AlertDialog.THEME_HOLO_LIGHT,dateSetListener,year,month,day){
          protected void onCreate(Bundle savedInstanceState){
              super.onCreate(savedInstanceState);
              int day = getContext().getResources().getIdentifier("android:id/day", null, null);
              if(day != 0){
                  View dayPicker = findViewById(day);
                  if(dayPicker != null){
                      //Set Day view visibility Off/Gone
                      dayPicker.setVisibility(View.GONE);
                  }
              }
          }
        };
        return dpd;

    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    Calendar myDate = Calendar.getInstance();
                    myDate.set(view.getYear(),view.getMonth(),view.getDayOfMonth());

                    String date = day+":"+month+":"+year;

                    mListener.onCompleteDate(date);
                }
            };

    public static interface OnCompleteDateListener{
        public abstract void onCompleteDate(String date);
    }

    private DateFragment.OnCompleteDateListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            this.mListener = (DateFragment.OnCompleteDateListener) context;
        }
        catch(final ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "doit implémenter l'interface OnCompleteDateListener");
        }
    }

}
