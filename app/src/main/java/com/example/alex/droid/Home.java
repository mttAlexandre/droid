package com.example.alex.droid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Home extends AppCompatActivity { // test push

    private DBTache dbt;
    private ListView hlv;
    private ArrayAdapter<String> adapter;
    private boolean checkbox=false;

    MyCustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        dbt = new DBTache(this);
        dbt.open();

        hlv = findViewById(R.id.homelist);
        List<Tache> values = dbt.getAllTaches();
        //adapter = new ArrayAdapter<>(this,
          //      android.R.layout.simple_list_item_1, values);

        dataAdapter = new MyCustomAdapter(this,
                R.layout.checkitem, values);


        hlv.setAdapter(adapter);

        /*
        hlv = findViewById(R.id.homelist);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(Home.this,
                android.R.layout.simple_list_item_1, prenoms);
        hlv.setAdapter(adapter);
        */
    }

    protected void onStart(Bundle savedInstanceState){

    }

    @Override
    protected void onResume() {
        dbt.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dbt.close();
        super.onPause();
    }

    public void onClickSetting(View v){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void onClickDetail(View v){// EXEMPLE A LIRE
        // ÇA C'EST QUAND TU VEUX METTRE QUE LE VUE XML SI ELLE EST STATIC
        //setContentView(R.layout.detail);
        // ÇA C'EST PAREIL MAIS ÇA CHARGE LES ÉLÉMENTS DYNAMIC EN PLUS (COMME LE CONTENU DE LA LISTVIEW POUR CLICKHOME) et il
        // faut remettre un OnCreate dans touts les classes du coup
        Intent intent = new Intent(this, Detail.class);
        startActivity(intent);
    }

    public void onClickModfy(View v){
        Intent intent = new Intent(this, Modify.class);
        startActivity(intent);
    }

    public void onClickCalendar(View v){
        Intent intent = new Intent(this, CalendarTask.class);
        startActivity(intent);
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onClickDelete(View v){
        if(!checkbox) {
            checkbox=true;
            List<String> values = dbt.getAllNomTaches();
            //List<Item> res = null;//Item.tacheToItem(values);
            adapter = new ArrayAdapter<String>(this,
                    R.layout.checkitem, values);
            hlv.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
        }else{
            checkbox=false;
            List<String> values = dbt.getAllNomTaches();
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            hlv.setAdapter(adapter);
        }
    }

    public void onClickAdd(View v){
        Tache t = null;
        t = dbt.createTache("test");
        adapter.add(t.getNom());
        adapter.notifyDataSetChanged();
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        //aller à la page détail et afficher l'item cliqué
    }





    private class MyCustomAdapter extends ArrayAdapter<Tache>{

        private ArrayList<Tache> tacheList;

        public MyCustomAdapter(Context context, int resource, List<Tache> list) {
            super(context, resource, list);
            this.tacheList=new ArrayList<>();
            tacheList.addAll(list);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.checkitem, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Tache tache = (Tache) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        tache.setChecked(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Tache tache = tacheList.get(position);
            holder.code.setText(" (" +  tache.getNom() + ")");
            holder.name.setText(tache.getNom());
            holder.name.setChecked(tache.getChecked());
            holder.name.setTag(tache);

            return convertView;
        }



    }

    /*private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.button3);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Tache> countryList = dataAdapter.tacheList;
                for(int i=0;i<countryList.size();i++){
                    Tache tache = countryList.get(i);
                    if(tache.getChecked()){
                        responseText.append("\n" + tache.getNom());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }*/
}
