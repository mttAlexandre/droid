package com.example.alex.droid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.Calendar;
import java.util.Locale;

import static android.view.Gravity.AXIS_PULL_BEFORE;

// GAGA TU TE DEBROUILLES POUR ÇA ;)

public class CalendarTask extends AppCompatActivity {

    protected Context cont = this;

    Fragment[] tabFrag = new Fragment[3];

    ViewPager mViewPager;
    PageFragment mPageFragment;

    Context context = this;

    Calendar cal = Calendar.getInstance(Locale.FRANCE);
    Calendar calBef = Calendar.getInstance(Locale.FRANCE);
    Calendar calUnd = Calendar.getInstance(Locale.FRANCE);

    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        //calBef = (Calendar) cal.clone();
        //calBef.set(Calendar.MONTH, calBef.get(Calendar.MONTH)-1);
        calBef.add(Calendar.MONTH, -1);

        ///calUnd = (Calendar) cal.clone();
        //calUnd.set(Calendar.MONTH, calUnd.get(Calendar.MONTH)+1);
        calUnd.add(Calendar.MONTH, 1);

        FragmentCalendar1 m1 = FragmentCalendar1.newInstance(this, calBef);
        FragmentCalendar2 m2 = FragmentCalendar2.newInstance(this, cal);
        FragmentCalendar3 m3 = FragmentCalendar3.newInstance(this, calUnd);

        tabFrag[0] = m1;
        tabFrag[1] = m2;
        tabFrag[2] = m3;

        TextView title = (TextView) findViewById(R.id.calendarDateBar);

        title.setText(getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR));

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

                        mPageFragment.notifyDataSetChanged();
                        mViewPager.setCurrentItem(1, false);

                        calUnd.add(Calendar.MONTH, 1);
                        ((FragmentCalendar3)tabFrag[2]).setCalendar(calUnd);

                        mPageFragment.notifyDataSetChanged();

                    }

                    if(mViewPager.getCurrentItem() == 0){

                        calUnd.add(Calendar.MONTH, -1);
                        ((FragmentCalendar3)tabFrag[2]).setCalendar(calUnd);
                        cal.add(Calendar.MONTH, -1);
                        ((FragmentCalendar2)tabFrag[1]).setCalendar(cal);

                        mPageFragment.notifyDataSetChanged();
                        mViewPager.setCurrentItem(1, false);

                        calBef.add(Calendar.MONTH, -1);
                        ((FragmentCalendar1)tabFrag[0]).setCalendar(calBef);

                        mPageFragment.notifyDataSetChanged();

                    }

                    TextView title = (TextView) findViewById(R.id.calendarDateBar);
                    title.setText(getMonth(cal.get(Calendar.MONTH)) + " " + cal.get(Calendar.YEAR));
                }
            }
        });
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
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
