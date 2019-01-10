package com.example.alex.droid;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimeFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog((Modify)getActivity(),timeSetListner,hour,minute,true);
    }

    private TimePickerDialog.OnTimeSetListener timeSetListner =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String myTime = "";
                    if(hourOfDay<10)
                        myTime+="0"+hourOfDay+":";
                    else
                        myTime+=hourOfDay+":";
                    if(minute<10)
                        myTime+="0"+minute;
                    else
                        myTime+=minute;

                    mListener.onComplete(myTime);

                    Toast.makeText(getActivity(),"Heure selectionnée : "+myTime,Toast.LENGTH_SHORT).show();
                }
            };
    public static interface OnCompleteListener{
        public abstract void onComplete(String time);
    }

    private OnCompleteListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            this.mListener = (OnCompleteListener) context;
        }
        catch(final ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "doit implémenter l'interface OnCompleteListener");
        }
    }
}
