package com.example.alex.droid;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DeadlineFragment extends DialogFragment {
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

                    deadlineListener.onCompleteDeadline(date);
                    Toast.makeText(getActivity(),"Deadline selectionnée : "+day+"/"+month+"/"+year,Toast.LENGTH_SHORT).show();
                }
            };

   public static interface OnCompleteDeadlineListener {
       public abstract void onCompleteDeadline(String deadline);
   }

   private DeadlineFragment.OnCompleteDeadlineListener deadlineListener;

   @Override
    public void onAttach(Context context){
       super.onAttach(context);
       try{
           this.deadlineListener = (OnCompleteDeadlineListener) context;
       }
       catch(final ClassCastException e){
           throw new ClassCastException(getActivity().toString() + "doit implémenter l'interface OnCompleteDeadlineListener");
       }
   }

}
