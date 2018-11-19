package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Random;

public class Home extends AppCompatActivity { // test push

    int testpush=0;
    private DBTache dbt;
    private ListView hlv;
    private String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };
    private ArrayAdapter<String> adapter;
    private boolean checkbox=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        dbt = new DBTache(this);
        dbt.open();

        hlv = findViewById(R.id.homelist);
        List<String> values = dbt.getAllNomTaches();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
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
        Intent intent = new Intent(this, Calendar.class);
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
                    android.R.layout.simple_list_item_multiple_choice, values);
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
}
