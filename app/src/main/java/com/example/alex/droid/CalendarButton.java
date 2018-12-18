package com.example.alex.droid;

import android.content.Context;
import android.widget.Button;

public class CalendarButton extends android.support.v7.widget.AppCompatButton {
    public CalendarButton(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }
}
