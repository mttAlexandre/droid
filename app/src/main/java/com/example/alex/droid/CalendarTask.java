package com.example.alex.droid;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static android.view.Gravity.AXIS_PULL_BEFORE;

// GAGA TU TE DEBROUILLES POUR ÇA ;)

public class CalendarTask extends AppCompatActivity implements DateFragment.OnCompleteDateListener {

    protected Fragment[] tabFrag = new Fragment[3];
    protected DBTache dbt;
    protected ViewPager mViewPager;
    protected PageFragment mPageFragment;
    protected ArrayList<Tache>[] values;
    protected Context context = this;

    protected Calendar cal = Calendar.getInstance(Locale.FRANCE);
    protected Calendar calBef = Calendar.getInstance(Locale.FRANCE);
    protected Calendar calUnd = Calendar.getInstance(Locale.FRANCE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        dbt = new DBTache(this);
        dbt.open();
        values = new ArrayList[3];
        if(savedInstanceState != null && savedInstanceState.containsKey("saveDateCalendar")){
            cal.setTimeInMillis(savedInstanceState.getLong("saveDateCalendar"));
            calBef.setTimeInMillis(savedInstanceState.getLong("saveDateCalendar"));
            calUnd.setTimeInMillis(savedInstanceState.getLong("saveDateCalendar"));
        }

        calBef.add(Calendar.MONTH, -1);
        calUnd.add(Calendar.MONTH, 1);

        values[2] = dbt.getTaskByPeriod(""+(calUnd.get(Calendar.MONTH)+1)+"/"+calUnd.get(Calendar.YEAR));
        values[1] = dbt.getTaskByPeriod(""+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
        values[0] = dbt.getTaskByPeriod(""+(calBef.get(Calendar.MONTH)+1)+"/"+calBef.get(Calendar.YEAR));

        FragmentCalendar1 m1 = FragmentCalendar1.newInstance(this, calBef, values[0]);
        FragmentCalendar2 m2 = FragmentCalendar2.newInstance(this, cal, values[1]);
        FragmentCalendar3 m3 = FragmentCalendar3.newInstance(this, calUnd, values[2]);

        tabFrag[0] = m1;
        tabFrag[1] = m2;
        tabFrag[2] = m3;

        TextView title = (TextView) findViewById(R.id.calendarDateBar);

        title.setText(""+getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR));

        mViewPager = (ViewPager) findViewById(R.id.viewPagerCalendar);
        mPageFragment = new PageFragment(getSupportFragmentManager());

        mViewPager.setAdapter(mPageFragment);
        mViewPager.setCurrentItem(1);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

                if(i == ViewPager.SCROLL_STATE_IDLE){
                    if(mViewPager.getCurrentItem() == 2){

                        calBef.add(Calendar.MONTH, 1);
                        ((FragmentCalendar1)tabFrag[0]).setCalendar(calBef);
                        cal.add(Calendar.MONTH, 1);
                        ((FragmentCalendar2)tabFrag[1]).setCalendar(cal);

                        TextView title = (TextView) findViewById(R.id.calendarDateBar);
                        title.setText(getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR));

                        values[0] = values[1];
                        values[1] = values[2];
                        ((FragmentCalendar1)tabFrag[0]).setValue(values[0]);
                        ((FragmentCalendar2)tabFrag[1]).setValue(values[1]);
                        //mPageFragment.notifyDataSetChanged();
                        mViewPager.setCurrentItem(1, false);

                        calUnd.add(Calendar.MONTH, 1);

                        values[2] = dbt.getTaskByPeriod(""+(calUnd.get(Calendar.MONTH)+1)+"/"+calUnd.get(Calendar.YEAR));
                        ((FragmentCalendar3)tabFrag[2]).setCalendar(calUnd);

                        ((FragmentCalendar3)tabFrag[2]).setValue(values[2]);

                        mPageFragment.notifyDataSetChanged();

                    }

                    if(mViewPager.getCurrentItem() == 0){

                        calUnd.add(Calendar.MONTH, -1);
                        values[2] = values[1];
                        ((FragmentCalendar3)tabFrag[2]).setCalendar(calUnd);
                        ((FragmentCalendar3)tabFrag[2]).setValue(values[2]);

                        cal.add(Calendar.MONTH, -1);
                        values[1] = values[0];
                        ((FragmentCalendar2)tabFrag[1]).setCalendar(cal);
                        ((FragmentCalendar2)tabFrag[1]).setValue(values[1]);

                        TextView title = (TextView) findViewById(R.id.calendarDateBar);
                        title.setText(getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR));

                        //mPageFragment.notifyDataSetChanged();
                        mViewPager.setCurrentItem(1, false);

                        calBef.add(Calendar.MONTH, -1);

                        values[0] = dbt.getTaskByPeriod(""+(calBef.get(Calendar.MONTH)+1)+"/"+calBef.get(Calendar.YEAR));
                        ((FragmentCalendar1)tabFrag[0]).setCalendar(calBef);
                        ((FragmentCalendar1)tabFrag[0]).setValue(values[0]);

                        mPageFragment.notifyDataSetChanged();

                    }


                }
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong("saveDateCalendar", cal.getTimeInMillis());
    }

    public void onClickHome(View v){
        finish();
    }

    public String getMonth(int month){
        switch (month){
            default:
            case 0:
                return "Janvier";
            case 1:
                return "Février";
            case 2:
                return "Mars";
            case 3:
                return "Avril";
            case 4:
                return "Mai";
            case 5:
                return "Juin";
            case 6:
                return "Juillet";
            case 7:
                return "Août";
            case 8:
                return "Septembre";
            case 9:
                return "Octobre";
            case 10:
                return "Novembre";
            case 11:
                return "Décembre";
        }
    }

    public void onClickDateButtonCal(View v) {
        DialogFragment newFragment = new DateFragmentForCalendar();

        newFragment.show(getSupportFragmentManager(), "Date Pick");
    }

    @Override
    public void onCompleteDate(String date) {
        String[] decompDate = date.split(":");
        int month = Integer.parseInt(decompDate[1]);
        int year = Integer.parseInt(decompDate[2]);
        this.cal.set(year, month, 1);
        this.calBef.setTimeInMillis(cal.getTimeInMillis());
        this.calBef.add(Calendar.MONTH, -1);
        this.calUnd.setTimeInMillis(cal.getTimeInMillis());
        this.calUnd.add(Calendar.MONTH, 1);

        values[2] = dbt.getTaskByPeriod(""+(calUnd.get(Calendar.MONTH)+1)+"/"+calUnd.get(Calendar.YEAR));
        values[1] = dbt.getTaskByPeriod(""+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
        values[0] = dbt.getTaskByPeriod(""+(calBef.get(Calendar.MONTH)+1)+"/"+calBef.get(Calendar.YEAR));

        ((FragmentCalendar1)tabFrag[0]).setValue(values[0]);
        ((FragmentCalendar2)tabFrag[1]).setValue(values[1]);
        ((FragmentCalendar3)tabFrag[2]).setValue(values[2]);
        mPageFragment.notifyDataSetChanged();
        TextView title = (TextView) findViewById(R.id.calendarDateBar);
        title.setText(getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR));
    }

    public class PageFragment extends FragmentStatePagerAdapter {


        public PageFragment(FragmentManager fm) {
            super(fm);
        }

        public int getItemPosition(Object object){
            return PageFragment.POSITION_NONE;
        }

        @Override
        public Fragment getItem(int i) {
            return tabFrag[i];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public class MyViewPager extends ViewPager {
        public MyViewPager(@NonNull Context context) {
            super(context);
        }


    }
}
