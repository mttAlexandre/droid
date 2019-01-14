package com.example.alex.droid;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DateFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),dateSetListener,year,month,day);

    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month++;
                    Calendar myDate = Calendar.getInstance();
                    myDate.set(view.getYear(),view.getMonth(),view.getDayOfMonth());

                    String date = day+"/"+month+"/"+year;

                    mListener.onCompleteDate(date);
                    Toast.makeText(getActivity(),"Date selectionnée : "+day+"/"+month+"/"+year,Toast.LENGTH_SHORT).show();
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
            this.mListener = (OnCompleteDateListener) context;
        }
        catch(final ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "doit implémenter l'interface OnCompleteDateListener");
        }
    }

}
